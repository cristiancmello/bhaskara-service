package com.bhaskara.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		if(args[0].equals("report")) {
			Controlador.start();
			return;
		}

		SpringApplication.run(ServiceApplication.class, args);
	}

}
