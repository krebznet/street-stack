package com.dunkware.time.stream.srvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import com.zaxxer.hikari.HikariDataSource;

@EnableWebFlux
@EnableJpaRepositories(basePackages  = { "com.dunkware.time.script.mod.repo.repository"})
@EntityScan(basePackages = {"com.dunkware.time.script.mod.repo.entity"})
@SpringBootApplication(scanBasePackages = "com.dunkware")
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, CassandraAutoConfiguration.class})
public class IStreamService {

	public static void main(String[] args) {

		SpringApplication.run(IStreamService.class, args);
	}

}
 	