package com.dunkware.trade.service.stream.server.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.dunkware.trade.service.stream.server.converter.ZonedDateTimeReadConverter;
import com.dunkware.trade.service.stream.server.converter.ZonedDateTimeWriteConverter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class MongoConfiguration {

//private final MongoTemplate mongoTemplate;

	@Autowired
	MongoDatabaseFactory mongoDbFactory;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, getDefaultMongoConverter());
        return mongoTemplate;

    }

    @Bean
    public MappingMongoConverter getDefaultMongoConverter() throws Exception {

        MappingMongoConverter converter = new MappingMongoConverter(
                new DefaultDbRefResolver(mongoDbFactory), new MongoMappingContext());
        converter.setCustomConversions(customConversions());
        return converter;
    }



@Bean
public MongoCustomConversions customConversions(){
    List<Converter<?,?>> converters = new ArrayList<>();
    converters.add(new ZonedDateTimeReadConverter());
    converters.add(new ZonedDateTimeWriteConverter());
    
    return new MongoCustomConversions(converters);
}



}