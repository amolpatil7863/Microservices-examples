package com.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import com.microservices.currencyconversionservice.feignproxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	@Autowired(required=true)
	private CurrencyExchangeServiceProxy proxy;
	
	/*	Invoking currency exchange
	service Using Rest Template*/
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity) {
		try {
		Map<String,String> requestVariable=new HashMap<String,String>();
		requestVariable.put("from", from);
		requestVariable.put("to", to);
		
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().
				getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,requestVariable);
		
		CurrencyConversionBean response = responseEntity.getBody();
		System.out.println("------------response:-"+response);
		CurrencyConversionBean currencyConversionBean=new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());

		return currencyConversionBean;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	
/*	Invoking currency exchange
	service Using Feign Client*/
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		try {
		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
		System.out.println("Response from Currency Exchange Service:- "+response);
		
		CurrencyConversionBean currencyConversionBean=new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
		System.out.println("Result from Currency Conversion service:- "+currencyConversionBean);
		return currencyConversionBean;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
