package com.bootcamp2024.StockMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StockMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMicroserviceApplication.class, args);
	}

}
