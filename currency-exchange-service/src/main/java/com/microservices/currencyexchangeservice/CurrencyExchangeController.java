package com.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CurrencyExchangeController {
	@Autowired(required=true)
	private ExchangeValueRepository currencyExchangeRepository;
	
	@Autowired(required=true)
	private Environment enviornment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from,@PathVariable String to) {
//		ExchangeValue currencyExchangeValue=new ExchangeValue(100L,from,to,8001,BigDecimal.valueOf(72));
		ExchangeValue currencyExchangeValue=currencyExchangeRepository.findByFromAndTo(from, to);
		currencyExchangeValue.setPort(Integer.parseInt(enviornment.getProperty("server.port")));
		System.out.println("Currency Exchange Service:- "+currencyExchangeValue);
		return currencyExchangeValue;
	}
	
	@GetMapping("/sayhello")
	@HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
	public ExchangeValue sayHello() {
		System.out.println("Exception");
		throw new RuntimeException();
	}
	
	public ExchangeValue fallbackRetrieveConfiguration() {
		System.out.println("Calling Fault tolerance");
		return new ExchangeValue(1l, "USD", "INR", 8001, BigDecimal.valueOf(72));
	}
}
