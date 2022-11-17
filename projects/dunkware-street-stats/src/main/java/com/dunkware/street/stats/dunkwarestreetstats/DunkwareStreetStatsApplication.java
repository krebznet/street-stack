package com.dunkware.street.stats.dunkwarestreetstats;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class DunkwareStreetStatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DunkwareStreetStatsApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StatRepository statRepository, MongoTemplate mongoTemplate) {
        return args -> {
            Type type = new Type(
                    "RLA"
            );
            int x = 0;
            while (x < 10) {
                UUID randomUUID = UUID.randomUUID();
                randomUUID.toString().replaceAll("_", "");
                String name = "Name" + randomUUID.toString().replaceAll("_", "");
                Stats stats = new Stats(
                        name,
                        type,
                        LocalDateTime.now().toString(),
                        "Admin"
                );
                statRepository.findStatsByName(name).ifPresentOrElse(err -> {
                    System.out.println("Already Taken");
                }, () -> {
                    statRepository.insert(stats);
                    System.out.println("Saved");
                });
                x++;
            }

        };
    }
}
