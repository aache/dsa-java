package org.example;

import java.util.Map;
import java.util.Stack;

public class ValidParantheses {
    public static void main(String[] args) {
        System.out.println(new ValidParantheses().isValid("({[]})"));
    }

    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> pairs = Map.of(')', '(', '}', '{', ']', '[');
        for(char c : s.toCharArray()){
            if(pairs.containsValue(c)){
                stack.push(c);
            } else if(pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c))
                    return false;
            }
        }
        return stack.isEmpty();
    }
}

//Create a stack
// Create a map of parantheses
// if opening paranthesis found a add to stack
// if closing parantheses found ,
// check if stack is empty or if opening paranthesis of given closing exist in stack
// if not - return false else do nothing
// return false if stack not empty
