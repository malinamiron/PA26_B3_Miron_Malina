package org.example.com;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    public static Locale execute(String languageTag, ResourceBundle messages) {
        Locale newLocale = Locale.forLanguageTag(languageTag);

        if (newLocale.getDisplayLanguage().isEmpty() && newLocale.getDisplayCountry().isEmpty()) {
            System.out.println("Invalid language tag. Defaulting to system locale.");
            newLocale = Locale.getDefault();
        }

        String pattern = messages.getString("locale.set");
        System.out.println(MessageFormat.format(pattern, newLocale.getDisplayName(newLocale)));
        return newLocale;
    }
}