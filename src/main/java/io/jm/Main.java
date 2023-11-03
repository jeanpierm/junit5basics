package io.jm;

public class Main {
    public static void main(String[] args) {
        var mathUtils = new MathUtils();
        var result = mathUtils.add(1, 2);
        log("Result: " + result);
    }

    private static void log(String message) {
        System.out.println(message);
    }
}