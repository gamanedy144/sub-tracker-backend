package com.dissertation.subtrackerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SubTrackerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubTrackerBackendApplication.class, args);
	}

}
