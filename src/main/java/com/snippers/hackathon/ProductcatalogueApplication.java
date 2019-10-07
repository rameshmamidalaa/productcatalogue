package com.snippers.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.snippers.hackathon")
@EnableJpaAuditing
public class ProductcatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductcatalogueApplication.class, args);
	}

}
