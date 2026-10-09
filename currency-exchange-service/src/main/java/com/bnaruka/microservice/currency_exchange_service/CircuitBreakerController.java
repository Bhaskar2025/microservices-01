package com.bnaruka.microservice.currency_exchange_service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    @CircuitBreaker(name = "default", fallbackMethod = "hardCodedMethod")
    public String sampleApi(){
        logger.info("Sample API call received");
        ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/sample-api",
                String.class);
        return entity.getBody();
    }

    public String hardCodedMethod(Exception exception){
        return "Fallback Response";
    }
}
