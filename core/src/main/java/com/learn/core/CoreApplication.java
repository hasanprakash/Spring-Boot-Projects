package com.learn.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(CoreApplication.class);
		application.setApplicationStartup(new BufferingApplicationStartup(2048));
		application.run(args);
		// SpringApplication.run(CoreApplication.class, args);
	}

}
