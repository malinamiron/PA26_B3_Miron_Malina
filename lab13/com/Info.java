package org.example.com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Info {
    public static void execute(Locale targetLocale, Locale currentLocale, ResourceBundle messages) {
        String pattern = messages.getString("info");
        System.out.println(MessageFormat.format(pattern, targetLocale.getDisplayName(currentLocale)));

        System.out.println("Country: " + targetLocale.getDisplayCountry(currentLocale) + " (" + targetLocale.getDisplayCountry(targetLocale) + ")");
        System.out.println("Language: " + targetLocale.getDisplayLanguage(currentLocale) + " (" + targetLocale.getDisplayLanguage(targetLocale) + ")");

        try {
            Currency currency = Currency.getInstance(targetLocale);
            System.out.println("Currency: " + currency.getCurrencyCode() + " (" + currency.getDisplayName(currentLocale) + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("Currency: N/A");
        }

        DateFormatSymbols dfs = DateFormatSymbols.getInstance(targetLocale);

        String weekdays = Arrays.stream(dfs.getWeekdays())
                .filter(day -> !day.isEmpty())
                .collect(Collectors.joining(", "));
        System.out.println("Week Days: " + weekdays);

        String months = Arrays.stream(dfs.getMonths())
                .filter(month -> !month.isEmpty())
                .collect(Collectors.joining(", "));
        System.out.println("Months: " + months);

        Date today = new Date();
        DateFormat dfCurrent = DateFormat.getDateInstance(DateFormat.LONG, currentLocale);
        DateFormat dfTarget = DateFormat.getDateInstance(DateFormat.LONG, targetLocale);

        System.out.println("Today: " + dfCurrent.format(today) + " (" + dfTarget.format(today) + ")");
    }
}