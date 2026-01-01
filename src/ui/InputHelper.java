package ui;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(">> Invalid input! Please enter a whole number.");
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine().replace(",", ".");
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(">> Invalid input! Please enter a valid amount.");
            }
        }
    }

    public static void printSeparator() {
        System.out.println("==========================================");
    }
}