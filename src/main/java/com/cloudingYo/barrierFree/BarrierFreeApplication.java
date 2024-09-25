package com.cloudingYo.barrierFree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BarrierFreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarrierFreeApplication.class, args);
	}

}
