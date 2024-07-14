package com.dunkware.utils.core.helpers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {

    public enum FormatType {
        TEXT,
        PERCENT,
        PERCENT_NATURAL,
        DECIMAL,
        DATE,
        TIME,
        DATETIME,
        FULL_DATE_TIME,
        ISO_DATE_TIME,
        SHORT_DATE,
        SHORT_DATE_TIME,
        TIME_AM_PM,
        TIME_24H,
        DAY_TIME
    }

    public static String format(Object value, FormatType type) {
        switch (type) {
            case TEXT:
                return formatText(value);
            case PERCENT:
                return formatPercentRegular(value);
            case PERCENT_NATURAL:
                return formatPercentNatural(value);
            case DECIMAL:
                return formatDecimal(value);
            case DATE:
                return formatDate(value);
            case TIME:
                return formatTime(value);
            case DATETIME:
                return formatDateTime(value);
            case FULL_DATE_TIME:
                return formatFullDateTime(value);
            case ISO_DATE_TIME:
                return formatIsoDateTime(value);
            case SHORT_DATE:
                return formatShortDate(value);
            case SHORT_DATE_TIME:
                return formatShortDateTime(value);
            case TIME_AM_PM:
                return formatTimeAmPm(value);
            case TIME_24H:
                return formatTime24h(value);
            case DAY_TIME:
            	return formatDateTime(value);
               
            default:
                throw new IllegalArgumentException("Unsupported format type: " + type);
        }
    }

    public static String formatText(Object value) {
        return value.toString();
    }

    public static String formatPercentRegular(Object value) {
        double percent = ((Number) value).doubleValue();
        return String.format("%.2f%%", percent * 100);
    }

    public static String formatPercentNatural(Object value) {
        double percent = ((Number) value).doubleValue();
        return String.format("%.2f%%", percent);
    }

    public static String formatDecimal(Object value) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(((Number) value).doubleValue());
    }

    public static String formatDate(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format((Date) value);
    }

    public static String formatTime(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format((Date) value);
    }

    public static String formatDateTime(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format((Date) value);
    }

    public static String formatFullDateTime(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d, yyyy h:mm a");
        return sdf.format((Date) value);
    }

    public static String formatIsoDateTime(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return sdf.format((Date) value);
    }

    public static String formatShortDate(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format((Date) value);
    }

    public static String formatShortDateTime(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        return sdf.format((Date) value);
    }

    public static String formatTimeAmPm(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        return sdf.format((Date) value);
    }

    public static String formatTime24h(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format((Date) value);
    }

    public static String formatDayTime(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, h:mm a");
        return sdf.format((Date) value);
    }

    public static String formatNumber(Object value) {
        NumberFormat nf = NumberFormat.getInstance();
        return nf.format(((Number) value).doubleValue());
    }

    public static String formatShortNumber(Object value) {
        double number = ((Number) value).doubleValue();
        if (number >= 1_000_000) {
            return String.format("%.1fM", number / 1_000_000);
        } else if (number >= 1_000) {
            return String.format("%.1fK", number / 1_000);
        } else {
            return String.valueOf((int) number);
        }
    }

    public static void main(String[] args) {
        Date now = new Date();
    //    System.out.println(format(24.2, FormatType.PERCENT_NATURAL)); // 24.20%
      //  System.out.println(format(0.242, FormatType.PERCENT)); // 24.20%
      
        System.out.println(format(now, FormatType.DATE)); // Current date in yyyy-MM-dd
        System.out.println(format(now, FormatType.TIME)); // Current time in HH:mm:ss
        System.out.println(format(now, FormatType.DATETIME)); // Current datetime in yyyy-MM-dd HH:mm:ss
        System.out.println(format(now, FormatType.FULL_DATE_TIME)); // Full date and time
        System.out.println(format(now, FormatType.ISO_DATE_TIME)); // ISO 8601 date and time
        System.out.println(format(now, FormatType.SHORT_DATE)); // Short date
        System.out.println(format(now, FormatType.SHORT_DATE_TIME)); // Short date and time
        System.out.println(format(now, FormatType.TIME_AM_PM)); // Time with AM/PM
        System.out.println(format(now, FormatType.TIME_24H)); // 24-hour time
        System.out.println(format(now, FormatType.DAY_TIME)); // Full day and time
    }
}
