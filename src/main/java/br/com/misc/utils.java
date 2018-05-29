package br.com.misc;

import java.util.Calendar;

public class utils {
    public static boolean containsNumbers(String value) {
        return !value.matches("[0-9]+");
    }

    public static boolean isDayOfTheWeek(Calendar date) {
        return date.get(Calendar.DAY_OF_WEEK) <= Calendar.FRIDAY && date.get(Calendar.DAY_OF_WEEK) >= Calendar.MONDAY;
    }
}
