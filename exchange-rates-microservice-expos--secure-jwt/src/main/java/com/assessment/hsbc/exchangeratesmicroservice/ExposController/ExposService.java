package com.assessment.hsbc.exchangeratesmicroservice.ExposController;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.assessment.hsbc.exchangeratesmicroservice.utils.DateValidator;
import com.assessment.hsbc.exchangeratesmicroservice.utils.DateValidatorUsingDateFormat;

@Service
public class ExposService {

	final String baseUrl = "http://Exchange-Rates-Microservice-Load/expos/";
	
	private static final Logger log = LoggerFactory.getLogger(ExposService.class);
	
	private DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");

	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<?> listAll() {
		log.info("==== RESTful API Response using Spring RESTTemplate START =======");
		log.info("URL:" + baseUrl);
		
		ResponseEntity<LoadEntity[]> forObject = restTemplate.getForEntity(baseUrl, LoadEntity[].class);
		
		log.info("==== RESTful API Response using Spring RESTTemplate START ======="+forObject.getBody());
		
		return forObject;
	}

	public ResponseEntity<?> getByDate(String inputDate) {
		String url = baseUrl;
		try {
			Date date = validator.isValid(inputDate);
			if (date == null) {
				return new ResponseEntity<String>("Invalied Date..,", HttpStatus.INTERNAL_SERVER_ERROR);
			} else if (date != null) {
				LocalDate currentDate = LocalDate.parse(inputDate);
				if (currentDate.getDayOfMonth() != 1) {
					log.info("Date day:" + date.getDay());
					return new ResponseEntity<String>("Allowed rate as of the 1'st Day of Month only.",
							HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					url += "{date}";
				}
			}
			
			Map<String, String> params = new HashMap<String, String>();
		    params.put("date", validator.stringDate(date));
		    log.info("==== RESTful API Response using Spring RESTTemplate END ======= URL:"+url+"  Params:"+params.toString());
		    
			return restTemplate.getForEntity(url, Object.class,params);
		} catch (Exception e) {
			log.error("Error While fetching data from restend points.", e);
			return new ResponseEntity<String>("Invalied Payload.., Error: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> findBetweenDate(String frmStrDate, String toStrDate) {
		String url=baseUrl;
		Date fromDate = validator.isValid(frmStrDate);
		if (fromDate == null) {
			return new ResponseEntity<String>("Invalied From Date Formate..,", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Date toDate = validator.isValid(toStrDate);
		if (toDate == null) {
			return new ResponseEntity<String>("Invalied To Date Formate..,", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(fromDate!=null && toDate!=null) {
			url+="frmToDates";
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		// URI (URL) parameters
		Map<String, String> urlParams = new HashMap<>();
		
		     // Query parameters
		        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
		                // Add query parameter
		                .queryParam("frmDate", frmStrDate)
		                .queryParam("toDate", toStrDate);
		        
		log.info("==== RESTful API Response using Spring RESTTemplate START =======");
		log.info("URL:" + builder.buildAndExpand(urlParams).toUri());
		ResponseEntity<Object> exchange = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(),HttpMethod.GET, null,Object.class);
		log.info("==== RESTful API Response using Spring RESTTemplate END =======");
		return exchange;
	}
}
