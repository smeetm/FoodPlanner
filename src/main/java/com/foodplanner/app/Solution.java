package com.foodplanner.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Solution {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Smeet Spring Boot! --- Nidhi APP";
	}
}
