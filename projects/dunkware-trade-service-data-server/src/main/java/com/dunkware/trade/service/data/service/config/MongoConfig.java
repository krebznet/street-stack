package com.dunkware.trade.service.data.service.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.dunkware.trade.service.data.service.converter.ZonedDateTimeReadConverter;
import com.dunkware.trade.service.data.service.converter.ZonedDateTimeWriteConverter;

@Configuration
public class MongoConfig {

    @Bean
    @Primary
    public MongoCustomConversions customConversions() {
        ZonedDateTimeReadConverter zonedDateTimeReadConverter = new ZonedDateTimeReadConverter();
        ZonedDateTimeWriteConverter zonedDateTimeWriteConverter = new ZonedDateTimeWriteConverter();
        return new MongoCustomConversions(Arrays.asList(zonedDateTimeReadConverter, zonedDateTimeWriteConverter));
    }

}



