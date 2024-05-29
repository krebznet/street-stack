package com.dunkware.stream.data.injest;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.dunkware")
public class InjestApplication {

	public static void main(String[] args) {

		SpringApplication.run(InjestApplication.class, args);
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
