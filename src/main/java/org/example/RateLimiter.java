package org.example;

public class RateLimiter {
    private long startReqTime = System.currentTimeMillis();
    private int reqCount = 0;
    private static final int MAX_REQUESTS = 4;
    private static final long TIME_WINDOW_MS = 1000; // 1 second

    public boolean rateLimiter(Request req) {
        long currentTime = System.currentTimeMillis();

        // Reset window if more than 1 second has passed
        if (currentTime - startReqTime > TIME_WINDOW_MS) {
            startReqTime = currentTime;
            reqCount = 0;
        }

        if (req != null) {
            reqCount++;
            return reqCount <= MAX_REQUESTS; // too many requests
        }

        return true; // allowed
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter();

        // Simulate 7 quick requests within the same second
        for (int i = 1; i <= 7; i++) {
            Request req = new Request("req-" + i, "GET", "Request body " + i);
            boolean allowed = limiter.rateLimiter(req);
            System.out.println("Request " + i + " allowed: " + allowed);
            Thread.sleep(100); // 100 ms between requests
        }

        // Wait to let the time window reset (1 sec)
        System.out.println("\nWaiting 1.2 seconds to reset window...\n");
        Thread.sleep(1200);

        // Send 3 more requests after reset
        for (int i = 8; i <= 10; i++) {
            Request req = new Request("req-" + i, "GET", "Request body " + i);
            boolean allowed = limiter.rateLimiter(req);
            System.out.println("Request " + i + " allowed: " + allowed);
            Thread.sleep(100);
        }
    }
}

class Request {
    String requestId;
    String requestType;
    String requestBody;

    Request(){}
    Request(String requestId, String requestType, String requestBody) {
        this.requestId = requestId;
        this.requestType = requestType;
        this.requestBody = requestBody;
    }
}