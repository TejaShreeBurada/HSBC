package com.assessment.hsbc.exchangeratesmicroservice.ExposController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.hsbc.exchangeratesmicroservice.clients.ExchangeRates;
import com.assessment.hsbc.exchangeratesmicroservice.clients.ExchangeRatesClient;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/expos")
public class ExposController {

	@Autowired
	private ExposService service;
	
	@Autowired
	private ExchangeRatesClient client;

	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		ResponseEntity<?> responce = null;
		try {
			responce = service.listAll();
		} catch (Exception e) {
			responce = new ResponseEntity<>("Error while fetching data..,", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responce;
	}

	@GetMapping("/{date}")
	public ResponseEntity<?> findByDate(@PathVariable String date) {
		ResponseEntity<?> responce = null;
		try {
			/*
			 * ExchangeRates exRate= client.getExchangeRates(date); responce = new
			 * ResponseEntity<ExchangeRates>(exRate, HttpStatus.OK);
			 */
			responce = service.getByDate(date);
		} catch (Exception e) {
			responce = new ResponseEntity<>("Error while fetching data..,", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responce;
	}
	
	@GetMapping("/fromtodates")
	public ResponseEntity<?> findBetweenDate(@RequestParam String frmDate, @RequestParam String toDate) {
		return service.findBetweenDate(frmDate, toDate);
	}


}
