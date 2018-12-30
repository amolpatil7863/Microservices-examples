package com.microservices.zualapigatewayservice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
@Component
public class FilterLogging extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest httpRequest=RequestContext.getCurrentContext().getRequest();
		System.out.println("Request Execuuating throgh API Gateway:- "+httpRequest.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return "PRE";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
