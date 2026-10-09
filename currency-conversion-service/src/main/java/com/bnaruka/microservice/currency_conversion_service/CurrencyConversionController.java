package com.bnaruka.microservice.currency_conversion_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @Autowired
    CurrencyExchangeProxy proxy;

    @GetMapping(path = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity){
        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
        BigDecimal totalCalculatedValue = currencyConversion.getConversionMultiple().multiply(quantity);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculationAmount(totalCalculatedValue);
        return  currencyConversion;
    }
}
