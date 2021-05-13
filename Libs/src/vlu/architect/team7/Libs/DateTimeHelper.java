package vlu.architect.team7.Libs;

import java.util.GregorianCalendar;

public final class DateTimeHelper {
    private static final GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();

    public static int getDayCount(int month, int year) {
        if (!isValidMonth(month) || !isValidYear(year))
            throw new IllegalArgumentException("month and/or year is not valid");
        if (month == 2)
            return isLeapYear(year) ? 29 : 28;
        return month <= 7 ? (month % 2 == 1 ? 31 : 30) : (month % 2 == 1 ? 31 : 31);
    }

    public static boolean isLeapYear(int year) {
        if (!isValidYear(year))
            throw new IllegalArgumentException("year is not valid");
        return calendar.isLeapYear(year);
    }

    public static boolean isValidDate(int day, int month, int year) {
        return isValidYear(year) && isValidMonth(month) && day >= 1 && day <= getDayCount(month, year);
    }

    public static boolean isValidYear(int year) {
        return year >= 1;
    }

    public static boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }
}
