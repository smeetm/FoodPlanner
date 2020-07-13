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

	private List<Ingredient> list = new LinkedList<Ingredient>();

	public IngredientsController()
	{
		list.add(new Ingredient("Carrot", "Produce"));
		list.add(new Ingredient("Bean", "Produce"));
		list.add(new Ingredient("Milk", "Dairy"));
		list.add(new Ingredient("Eggs", "Dairy"));
	}
	
	@RequestMapping("/ingredients")
	public ResponseEntity<List<Ingredient>> getIngredient() {
		return new ResponseEntity<List<Ingredient>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredients", method = RequestMethod.POST)
	public ResponseEntity<List<Ingredient>> createProduct(@RequestBody Ingredient ingredient) {
		list.add(ingredient);
		return new ResponseEntity<List<Ingredient>>(list, HttpStatus.ACCEPTED);
	}
}
