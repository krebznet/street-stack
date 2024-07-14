package com.dunkware.utils.core.format;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Formatter {



    
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
            case LOCAL_DATE:
                return formatLocalDate(value);
            case LOCAL_TIME:
                return formatLocalTime(value);
            case LOCAL_DATETIME:
                return formatLocalDateTime(value);
            case ZONED_DATE:
                return formatZonedDate(value);
            case ZONED_TIME:
                return formatZonedTime(value);
            case ZONED_DATETIME:
                return formatZonedDateTime(value);
            case INSTANT:
                return formatInstant(value);
            case EPOCH_SECOND:
                return formatEpochSecond(value);
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
                return formatDayTime(value);
            case NUMBER:
                return formatNumber(value);
            case SHORT_NUMBER:
                return formatShortNumber(value);
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
        DecimalFormat df = new DecimalFormat("0.00");
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

    public static String formatLocalDate(Object value) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ((LocalDate) value).format(dtf);
    }

    public static String formatLocalTime(Object value) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return ((LocalTime) value).format(dtf);
    }

    public static String formatLocalDateTime(Object value) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ((LocalDateTime) value).format(dtf);
    }

    public static String formatZonedDate(Object value) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ((ZonedDateTime) value).format(dtf);
    }

    public static String formatZonedTime(Object value) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return ((ZonedDateTime) value).format(dtf);
    }

    public static String formatZonedDateTime(Object value) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        return ((ZonedDateTime) value).format(dtf);
    }

    public static String formatInstant(Object value) {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;
        return ((Instant) value).toString();
    }

    public static String formatEpochSecond(Object value) {
        long epochSecond = (long) value;
        return Instant.ofEpochSecond(epochSecond).toString();
    }

    public static String formatFullDateTime(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d, yyyy h:mm a");
        return sdf.format((Date) value);
    }

    public static String formatIsoDateTime(Object value) {
        if (value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            return sdf.format((Date) value);
        } else if (value instanceof LocalDate) {
            DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
            return ((LocalDate) value).format(dtf);
        } else if (value instanceof LocalTime) {
            DateTimeFormatter dtf = DateTimeFormatter.ISO_TIME;
            return ((LocalTime) value).format(dtf);
        } else if (value instanceof LocalDateTime) {
            DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
            return ((LocalDateTime) value).format(dtf);
        } else if (value instanceof ZonedDateTime) {
            DateTimeFormatter dtf = DateTimeFormatter.ISO_ZONED_DATE_TIME;
            return ((ZonedDateTime) value).format(dtf);
        } else if (value instanceof Instant) {
            DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;
            return ((Instant) value).toString();
        } else {
            throw new IllegalArgumentException("Unsupported type for ISO formatting: " + value.getClass().getName());
        }
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
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        Instant instant = Instant.now();
        long epochSecond = Instant.now().getEpochSecond();

        System.out.println(format(24.2, FormatType.PERCENT_NATURAL)); // 24.20%
        System.out.println(format(0.242, FormatType.PERCENT)); // 24.20%
        System.out.println(format(1234567.89, FormatType.NUMBER)); // 1,234,567.89
        System.out.println(format(1500, FormatType.SHORT_NUMBER)); // 1.5K
        System.out.println(format(1400000, FormatType.SHORT_NUMBER)); // 1.4M
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
        System.out.println(format(localDate, FormatType.LOCAL_DATE)); // Local date in yyyy-MM-dd
        System.out.println(format(localTime, FormatType.LOCAL_TIME)); // Local time in HH:mm:ss
        System.out.println(format(localDateTime, FormatType.LOCAL_DATETIME)); // Local date and time in yyyy-MM-dd HH:mm:ss
        System.out.println(format(zonedDateTime, FormatType.ZONED_DATE)); // Zoned date in yyyy-MM-dd
        System.out.println(format(zonedDateTime, FormatType.ZONED_TIME)); // Zoned time in HH:mm:ss
        System.out.println(format(zonedDateTime, FormatType.ZONED_DATETIME)); // Zoned datetime in yyyy-MM-dd HH:mm:ss z
        System.out.println(format(instant, FormatType.INSTANT)); // Instant in ISO 8601 format
        System.out.println(format(epochSecond, FormatType.EPOCH_SECOND)); // Epoch second in ISO 8601 format
    }
}
