package com.dunkware.time.data.codec.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class LocalDateCodec {

    public static long encode(LocalDate date) {
       return  date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    public static LocalDate decode(long value) {
        return Instant.ofEpochMilli(value).atZone(ZoneOffset.UTC).toLocalDate();
    }
}
