package com.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
		return currencyExchangeValue;
	}
}
