package com.cloudingYo.barrierFree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableMongoRepositories
@ComponentScan(basePackages = {"com.cloudingYo.barrierFree", "com.cloudingYo.barrierFree.common.security"})
public class BarrierFreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarrierFreeApplication.class, args);
	}

}
