package org.example.com;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {
    public static void execute(ResourceBundle messages) {
        System.out.println(messages.getString("locales"));
        Locale[] available = Locale.getAvailableLocales();
        for (Locale locale : available) {
            if (!locale.toString().isEmpty()) {
                System.out.println(locale.toString() + " - " + locale.getDisplayName());
            }
        }
    }
}