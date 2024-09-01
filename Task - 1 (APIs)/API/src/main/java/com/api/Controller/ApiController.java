package com.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.Service.ApiServiceImpl;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private ApiServiceImpl service;
	
	@GetMapping("/greet")
	public String returnString(@RequestParam String name) {
		return service.returnString(name);
	}
	
	
	@GetMapping("/palindrome")
	public String palindrome(@RequestParam String str) {
		return service.palindrome(str);
	}
}
