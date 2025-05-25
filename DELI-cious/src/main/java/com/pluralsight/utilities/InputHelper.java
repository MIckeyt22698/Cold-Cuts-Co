package com.pluralsight.utilities;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner keyStrokes = new Scanner(System.in);

    public static String getString(String prompt) {
        System.out.print(prompt);
        return keyStrokes.nextLine();
    }

    public static int getInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(keyStrokes.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public static double getDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(keyStrokes.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static void closeScanner() {
        keyStrokes.close();
    }
}