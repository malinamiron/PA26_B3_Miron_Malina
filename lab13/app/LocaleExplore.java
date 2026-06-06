package org.example.app;

import org.example.com.DisplayLocales;
import org.example.com.Info;
import org.example.com.SetLocale;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Locale currentLocale = Locale.ENGLISH;
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", currentLocale);

        Scanner scanner = new Scanner(System.in);


        try {
            while (true) {
                System.out.print(messages.getString("prompt") + " ");

                if (!scanner.hasNextLine()) {
                    break;
                }

                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("iesire")) {
                    break;
                }

                if (input.isEmpty()) {
                    continue;
                }

                String[] tokens = input.split("\\s+");
                String command = tokens[0].toLowerCase();

                switch (command) {
                    case "displaylocales":
                        DisplayLocales.execute(messages);
                        break;

                    case "setlocale":
                        if (tokens.length < 2) {
                            System.out.println("Usage: setlocale <language-tag>");
                        } else {
                            currentLocale = SetLocale.execute(tokens[1], messages);
                            messages = ResourceBundle.getBundle("res.Messages", currentLocale);
                        }
                        break;

                    case "info":
                        if (tokens.length < 2) {
                            Info.execute(currentLocale, currentLocale, messages);
                        } else {
                            Locale targetLocale = Locale.forLanguageTag(tokens[1]);
                            Info.execute(targetLocale, currentLocale, messages);
                        }
                        break;

                    default:
                        System.out.println(messages.getString("invalid"));
                        break;
                }
                System.out.println();
            }
        } finally {
            scanner.close();
        }
    }
}