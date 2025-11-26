package org.example;

import java.util.*;

public class RaftSimulation {

    enum NodeState { FOLLOWER, CANDIDATE, LEADER }

    static class LogEntry {
        final int term;
        final String command;

        LogEntry(int term, String command) {
            this.term = term;
            this.command = command;
        }

        @Override
        public String toString() {
            return "[t=" + term + ", cmd=" + command + "]";
        }
    }

    static class Cluster {
        private final List<RaftNode> nodes = new ArrayList<>();

        void addNode(RaftNode node) {
            nodes.add(node);
        }

        List<RaftNode> getNodes() {
            return nodes;
        }

        int size() {
            return nodes.size();
        }

        int majority() {
            return (size() / 2) + 1;
        }

        RaftNode getLeader() {
            for (RaftNode n : nodes) {
                if (n.state == NodeState.LEADER) return n;
            }
            return null;
        }

        boolean requestVote(int targetId, int term, int candidateId,
                            int lastLogIndex, int lastLogTerm) {
            RaftNode target = nodes.get(targetId);
            return target.onRequestVote(term, candidateId, lastLogIndex, lastLogTerm);
        }

        boolean appendEntries(int targetId, int term, int leaderId,
                              int prevLogIndex, int prevLogTerm,
                              List<LogEntry> entries, int leaderCommit) {
            RaftNode target = nodes.get(targetId);
            return target.onAppendEntries(term, leaderId, prevLogIndex,
                    prevLogTerm, entries, leaderCommit);
        }

        void tickAll() {
            for (RaftNode n : nodes) {
                n.tick();
            }
        }

        void printState(int tick) {
            System.out.println("\n=== TICK " + tick + " ===");
            for (RaftNode n : nodes) {
                System.out.printf("Node %d: term=%d, state=%s, logSize=%d, commitIndex=%d%n",
                        n.id, n.currentTerm, n.state, n.log.size(), n.commitIndex);
            }
        }
    }

    static class RaftNode {
        final int id;
        final Cluster cluster;
        final Random random = new Random();

        NodeState state = NodeState.FOLLOWER;
        int currentTerm = 0;
        Integer votedFor = null;

        final List<LogEntry> log = new ArrayList<>();
        int commitIndex = -1;
        int lastApplied = -1;

        // Leader fields
        int[] nextIndex;
        int[] matchIndex;

        // Timers (logical ticks)
        int electionTimeout;
        final int minElectionTimeout = 5;
        final int maxElectionTimeout = 10;

        int heartbeatInterval = 3;
        int heartbeatCountdown = heartbeatInterval;

        RaftNode(int id, Cluster cluster) {
            this.id = id;
            this.cluster = cluster;
            resetElectionTimeout();
        }

        void resetElectionTimeout() {
            electionTimeout = minElectionTimeout
                    + random.nextInt(maxElectionTimeout - minElectionTimeout + 1);
        }

        int lastLogIndex() {
            return log.size() - 1;
        }

        int lastLogTerm() {
            if (log.isEmpty()) return 0;
            return log.get(log.size() - 1).term;
        }

        void tick() {
            switch (state) {
                case LEADER:
                    heartbeatCountdown--;
                    if (heartbeatCountdown <= 0) {
                        sendHeartbeats();
                        heartbeatCountdown = heartbeatInterval;
                    }
                    break;
                case FOLLOWER:
                case CANDIDATE:
                    electionTimeout--;
                    if (electionTimeout <= 0) {
                        startElection();
                    }
                    break;
            }

            applyCommitted();
        }

        void applyCommitted() {
            while (lastApplied < commitIndex) {
                lastApplied++;
                LogEntry e = log.get(lastApplied);
                System.out.printf("Node %d applies log[%d]: %s%n",
                        id, lastApplied, e);
            }
        }

        void startElection() {
            state = NodeState.CANDIDATE;
            currentTerm++;
            votedFor = id;
            resetElectionTimeout();

            int votesGranted = 1; // self vote
            int lastIndex = lastLogIndex();
            int lastTerm = lastLogTerm();

            for (RaftNode peer : cluster.getNodes()) {
                if (peer.id == this.id) continue;
                boolean granted = cluster.requestVote(
                        peer.id, currentTerm, id, lastIndex, lastTerm);
                if (granted) votesGranted++;
            }

            if (votesGranted >= cluster.majority()) {
                becomeLeader();
            } else {
                System.out.printf(
                        "Node %d failed to win election in term %d (votes=%d)%n",
                        id, currentTerm, votesGranted);
            }
        }

        void becomeLeader() {
            state = NodeState.LEADER;
            System.out.printf("Node %d becomes LEADER in term %d%n", id, currentTerm);
            int n = cluster.size();
            nextIndex = new int[n];
            matchIndex = new int[n];
            int next = log.size();
            for (int i = 0; i < n; i++) {
                nextIndex[i] = next;
                matchIndex[i] = -1;
            }
            sendHeartbeats();
        }

        boolean onRequestVote(int term, int candidateId,
                              int lastLogIndex, int lastLogTerm) {
            if (term < currentTerm) {
                return false;
            }
            if (term > currentTerm) {
                currentTerm = term;
                state = NodeState.FOLLOWER;
                votedFor = null;
            }

            boolean upToDate = (lastLogTerm > lastLogTerm())
                    || (lastLogTerm == lastLogTerm()
                    && lastLogIndex >= lastLogIndex());

            if ((votedFor == null || votedFor == candidateId) && upToDate) {
                votedFor = candidateId;
                resetElectionTimeout();
                System.out.printf("Node %d grants vote to %d for term %d%n",
                        id, candidateId, term);
                return true;
            }
            return false;
        }

        boolean onAppendEntries(int term, int leaderId,
                                int prevLogIndex, int prevLogTerm,
                                List<LogEntry> entries, int leaderCommit) {
            if (term < currentTerm) {
                return false;
            }

            if (term > currentTerm) {
                currentTerm = term;
            }
            state = NodeState.FOLLOWER;
            resetElectionTimeout();

            // Simplified log consistency
            if (prevLogIndex != -1) {
                if (prevLogIndex >= log.size()
                        || log.get(prevLogIndex).term != prevLogTerm) {
                    return false;
                }
            }

            // Append new entries (no conflict delete for simplicity)
            if (entries != null && !entries.isEmpty()) {
                log.addAll(entries);
            }

            if (leaderCommit > commitIndex) {
                commitIndex = Math.min(leaderCommit, lastLogIndex());
            }

            return true;
        }

        void sendHeartbeats() {
            if (state != NodeState.LEADER) return;
            for (RaftNode peer : cluster.getNodes()) {
                if (peer.id == this.id) continue;
                int prevIndex = nextIndex[peer.id] - 1;
                int prevTerm = (prevIndex >= 0 && prevIndex < log.size())
                        ? log.get(prevIndex).term : 0;

                List<LogEntry> entries = Collections.emptyList();
                boolean ok = cluster.appendEntries(
                        peer.id, currentTerm, id,
                        prevIndex, prevTerm, entries, commitIndex);

                if (!ok) {
                    nextIndex[peer.id] = Math.max(0, nextIndex[peer.id] - 1);
                }
            }
        }

        void replicateCommand(String command) {
            if (state != NodeState.LEADER) {
                System.out.printf("Node %d is not leader, cannot accept command '%s'%n",
                        id, command);
                return;
            }
            LogEntry entry = new LogEntry(currentTerm, command);
            log.add(entry);
            int index = log.size() - 1;

            for (RaftNode peer : cluster.getNodes()) {
                if (peer.id == this.id) continue;
                sendAppendEntriesToPeer(peer.id);
            }

            updateCommitIndex();
        }

        void sendAppendEntriesToPeer(int peerId) {
            int next = nextIndex[peerId];
            int prevIndex = next - 1;
            int prevTerm = (prevIndex >= 0 && prevIndex < log.size())
                    ? log.get(prevIndex).term : 0;

            List<LogEntry> entries = new ArrayList<>();
            for (int i = next; i < log.size(); i++) {
                entries.add(log.get(i));
            }

            boolean ok = cluster.appendEntries(
                    peerId, currentTerm, id,
                    prevIndex, prevTerm, entries, commitIndex);

            if (ok) {
                nextIndex[peerId] = log.size();
                matchIndex[peerId] = log.size() - 1;
            } else {
                nextIndex[peerId] = Math.max(0, nextIndex[peerId] - 1);
            }
        }

        void updateCommitIndex() {
            int n = cluster.size();
            int[] matchCopy = new int[n];
            for (int i = 0; i < n; i++) {
                matchCopy[i] = (i == id) ? lastLogIndex() : matchIndex[i];
            }
            Arrays.sort(matchCopy);
            int newCommit = matchCopy[n / 2]; // majority index
            if (newCommit > commitIndex && log.get(newCommit).term == currentTerm) {
                commitIndex = newCommit;
                System.out.printf("Leader %d advances commitIndex to %d%n",
                        id, commitIndex);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Cluster cluster = new Cluster();

        // Create 5 nodes
        for (int i = 0; i < 5; i++) {
            RaftNode node = new RaftNode(i, cluster);
            cluster.addNode(node);
        }

        // Run simulation
        for (int t = 1; t <= 40; t++) {
            cluster.tickAll();
            cluster.printState(t);

            // Let leader accept commands at specific ticks
            if (t == 15 || t == 25 || t == 30) {
                RaftNode leader = cluster.getLeader();
                if (leader != null) {
                    leader.replicateCommand("cmd-" + t);
                }
            }

            Thread.sleep(200); // slow down output
        }

        System.out.println("\nFinal logs:");
        for (RaftNode n : cluster.getNodes()) {
            System.out.println("Node " + n.id + " log: " + n.log);
        }
    }
}
