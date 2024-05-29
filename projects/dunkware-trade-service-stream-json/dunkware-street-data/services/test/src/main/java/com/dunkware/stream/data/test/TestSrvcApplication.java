package com.dunkware.stream.data.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.dunkware")
public class TestSrvcApplication {

	public static void main(String[] args) {

		SpringApplication.run(TestSrvcApplication.class, args);
	}

}
