package com.assessment.hsbc.exchangeratesmicroservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url = "http://Exchange-Rates-Microservice-Load/expos/", name = "EXCHANGE-RATES-Load")
public interface ExchangeRatesClient {

	@RequestMapping(value = "/{date}")
	ExchangeRates getExchangeRates(@PathVariable("date") String date);
}
