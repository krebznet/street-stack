package com.dunkware.trade.service.stream.server.converter;

import java.time.ZoneOffset;
import java.time.ZoneLocalDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class ZoneLocalDateTimeReadConverter implements Converter<Date, ZoneLocalDateTime> {
    @Override
    public ZoneLocalDateTime convert(Date date) {
        return date.toInstant().atZone(ZoneOffset.UTC);
    }
}