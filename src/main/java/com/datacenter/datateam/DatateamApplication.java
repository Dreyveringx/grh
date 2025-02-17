package com.datacenter.datateam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.datacenter.datateam")
public class DatateamApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatateamApplication.class, args);
	}

}
