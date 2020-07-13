package com.foodplanner.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodplanner.dataentities.Ingredient;

@RestController
public class IngredientsController {

	@RequestMapping("/")
	public String homePage() {
		return "Welcome to Food Planner App";
	}

	@RequestMapping("/ingredients")
	public ResponseEntity<List<Ingredient>> getIngredient() {
		List<Ingredient> list = new LinkedList<Ingredient>();
		list.add(new Ingredient("Carrot", "Produce"));
		list.add(new Ingredient("Bean", "Produce"));
		list.add(new Ingredient("Milk", "Dairy"));
		return new ResponseEntity<List<Ingredient>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<String> createProduct(@RequestBody Ingredient ingredient) {
		return new ResponseEntity<String>("Working on it", HttpStatus.ACCEPTED);
	}
}
