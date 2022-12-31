package org.example;

import java.util.Scanner;
public class UserDialog {
    private static Scanner scanner = new Scanner(System.in);
    int getUserInput(String label, String... alternatives) {
        do {
            promptUser(label, alternatives);
            int input = scanUserInput();
            if (input < 0 || input >= alternatives.length) {
                System.out.println("Fehler!␣Eingabe␣bitte␣wiederholen.");
            } else {
                return input;
            }
        } while (true);
    }
    private int scanUserInput() {
        try {
            String inputString = scanner.nextLine();
            return Integer.parseInt(inputString);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }
    private void promptUser(String label, String... alternatives) {
        System.out.print(label);
        int index = 0;
        for (String alternative : alternatives) {
            System.out.format("(%d)␣%s␣", index++, alternative);
        }
        System.out.println();
    }
}

