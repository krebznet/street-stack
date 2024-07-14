package com.dunkware.time.cassy.srvc;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.dunkware")
public class CassyApplication {

	public static void main(String[] args) {

		SpringApplication.run(CassyApplication.class, args);
	}

	public static void exitApplication(ConfigurableApplicationContext ctx, int code) {
		int exitCode = SpringApplication.exit(ctx, new ExitCodeGenerator() {
			@Override
			public int getExitCode() {
				// no errors
				return code;
			}
		});
		System.exit(exitCode);
	}

}
