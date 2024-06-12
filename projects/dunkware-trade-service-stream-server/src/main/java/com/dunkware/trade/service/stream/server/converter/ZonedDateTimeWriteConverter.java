package com.dunkware.trade.service.stream.server.converter;

import java.time.ZoneLocalDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class ZoneLocalDateTimeWriteConverter implements Converter<ZoneLocalDateTime, Date> {
   
	@Override
    public Date convert(ZoneLocalDateTime zoneLocalDateTime) {
        return Date.from(zoneLocalDateTime.toInstant());
    }
}