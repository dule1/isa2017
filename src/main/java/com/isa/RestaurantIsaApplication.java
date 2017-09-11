package com.isa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RestaurantIsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantIsaApplication.class, args);
	}
}
