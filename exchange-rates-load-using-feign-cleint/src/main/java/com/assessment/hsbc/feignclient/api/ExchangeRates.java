package com.assessment.hsbc.feignclient.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.assessment.hsbc.feignclient.bind.Root;

import feign.Headers;

@FeignClient(url = "https://api.ratesapi.io/api", name = "ExchangeRates")
public interface ExchangeRates {

	@Headers("Content-Type: application/json")
	@RequestMapping(value = "/latest", method = RequestMethod.GET, consumes = "application/json")
	public Root getRoot();

	@Headers("Content-Type: application/json")
	@RequestMapping(value = "/{date}", method = RequestMethod.GET, consumes = "application/json")
	public Root getRoot(@PathVariable("date") String date);

}
