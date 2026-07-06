package org.example;

public class ModernSwitch {
    public static void main(String[] args) {}

    public String process(int input){
        return switch (input) {
            case 1 -> "one";
            case 2,3 -> "two or three";
            case 4 -> {
                System.out.println("called ...");
                yield "four";
            }
            default -> "0";
        };
    }
}
