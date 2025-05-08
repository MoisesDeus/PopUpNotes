package br.com.mddeveloper.Util;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeConverter {

    public static String toDateString(LocalDate date) {
        return date.toString();
    }

    public static String toTimeString(LocalTime time) {
        return time.toString();
    }

    public static LocalDate toLocalDate(String dateStr) {
        return LocalDate.parse(dateStr);
    }

    public static LocalTime toLocalTime(String timeStr) {
        return LocalTime.parse(timeStr);
    }
}
