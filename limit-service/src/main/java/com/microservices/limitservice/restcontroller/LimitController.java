package com.microservices.limitservice.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.limitservice.bean.LimitServiceBean;
import com.microservices.limitservice.configuration.LimitServiceConfiguration;

@RestController
public class LimitController {
	
	@Autowired(required=true)
	private LimitServiceConfiguration limitServiceConfiguration;
	
	@GetMapping("/limit-service")
	public LimitServiceBean getMinValue() {
		LimitServiceBean limitServiceBean=new LimitServiceBean(limitServiceConfiguration.getMinimum(),limitServiceConfiguration.getMaximum());
		return limitServiceBean;
	}

}
