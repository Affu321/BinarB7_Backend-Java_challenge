package com.Binar.Challenge4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Challenge4Application {

	private static final Logger logger = LoggerFactory.getLogger(Challenge4Application.class);
	public static void main(String[] args) {
		SpringApplication.run
				(Challenge4Application.class, args);
		logger.info("info logging level");
		logger.error("eror logging level");
		logger.warn("warning logging level");
		logger.debug("debug logging level");
		logger.trace("trace logging level");
		logger.error("Error processing request");
	}

}
