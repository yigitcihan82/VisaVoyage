package ui;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    // Kullanıcıdan metin almak için
    public static String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    // Kullanıcıdan sayı (int) almak için (Hata korumalı)
    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(">> Hatalı giriş! Lütfen bir tam sayı giriniz.");
            }
        }
    }

    // Kullanıcıdan ondalıklı sayı (double) almak için
    public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine().replace(",", "."); // Virgül/nokta karmaşasını çözer
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(">> Hatalı giriş! Lütfen geçerli bir tutar giriniz.");
            }
        }
    }

    public static void printSeparator() {
        System.out.println("==========================================");
    }
}