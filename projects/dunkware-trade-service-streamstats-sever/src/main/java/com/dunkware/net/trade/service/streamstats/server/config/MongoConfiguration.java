package com.dunkware.net.trade.service.streamstats.server.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.dunkware.net.trade.service.streamstats.server.converter.ZonedDateTimeReadConverter;
import com.dunkware.net.trade.service.streamstats.server.converter.ZonedDateTimeWriteConverter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class MongoConfiguration {

	
	@Bean
	public MongoCustomConversions customConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(new ZonedDateTimeReadConverter());
		converters.add(new ZonedDateTimeWriteConverter());

		return new MongoCustomConversions(converters);
	}

}