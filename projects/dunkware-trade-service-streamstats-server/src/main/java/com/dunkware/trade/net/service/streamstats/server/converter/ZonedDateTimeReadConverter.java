package com.dunkware.trade.net.service.streamstats.server.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime> {
    @Override
    public ZonedDateTime convert(Date date) {
        return date.toInstant().atZone(ZoneOffset.UTC);
    }
    
    public static void main(String[] args) {
		ZonedDateTime dt = ZonedDateTime.now();
		System.out.println(dt.toInstant().atZone(ZoneOffset.UTC));
	}
}