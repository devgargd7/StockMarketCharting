package com.stockmarketcharting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {
	
	@GetMapping("/")
	ResponseEntity<String> home(){
		return ResponseEntity.ok("hello");
	}
}
