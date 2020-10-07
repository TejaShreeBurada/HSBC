package com.assessment.hsbc.exchangeratesmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableEurekaClient
@SpringBootApplication
@EnableAsync
public class ExchangeRatesMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRatesMicroserviceApplication.class, args);
	}

}
