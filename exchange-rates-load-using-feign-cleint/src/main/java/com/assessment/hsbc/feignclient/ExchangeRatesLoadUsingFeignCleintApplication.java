package com.assessment.hsbc.feignclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.hsbc.feignclient.api.ExchangeRates;
import com.assessment.hsbc.feignclient.bind.Root;

import feign.Feign;

@SpringBootApplication
@RestController
@EnableFeignClients
public class ExchangeRatesLoadUsingFeignCleintApplication {

	@Autowired
	private ExchangeRates exchangeRates;
	
	@GetMapping("/findLatest")
	public Root getRoot() {
		return exchangeRates.getRoot();
	}
	
	@GetMapping("/findByData/{data}")
	public Root getRootByDate(@PathVariable String date) {
		return exchangeRates.getRoot(date);
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExchangeRatesLoadUsingFeignCleintApplication.class, args);
	}

	
	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}
}
