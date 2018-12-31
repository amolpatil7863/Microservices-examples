package com.spring.security.springbootsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value="/hello")
	public String retiveValue(){
		System.out.println("In controller");
		return "Hello";
	}
}
