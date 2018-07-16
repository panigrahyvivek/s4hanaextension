package com.s4hanaextension.dataadjustments.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
	
	private static Logger log = LoggerFactory.getLogger(HelloWorldController.class);

	@GetMapping
	public String sayHelloWorld(){
		return "Hello World";
	}
	}
