package com.equipeAcelera.EventifyAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EventifyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventifyApiApplication.class, args);
	}

}
