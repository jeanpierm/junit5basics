package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var result = add(1, 2);
        log("Result: " + result);
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static void log(String message) {
        System.out.println(message);
    }
}