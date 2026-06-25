package org.example;

import java.util.Map;
import java.util.Stack;

public class ValidParenthesis1 {

    public static void main(String[] args) {
        boolean res = new ValidParenthesis1().isValid("()[]{}");
        System.out.println(res);
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> pairs = Map.of('}', '{', ']', '[' , ')', '(');

        for(Character c : s.toCharArray()){
            System.out.println(stack);
            if(pairs.containsValue(c)){
                stack.push(c);
            } else {
                if(stack.isEmpty() || stack.pop() != pairs.get(c)){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
