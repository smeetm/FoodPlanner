package com.foodplanner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodplanner.model.RecipeIngredient;

@RestController
public class HomePageController {

	@RequestMapping("/")
	public String homePage() {
		return "Welcome to food app controller";
	}
}
