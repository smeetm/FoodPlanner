package com.foodplanner.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodplanner.model.Recipe;
import com.foodplanner.mongoclient.RecipeDbMongoClient;

@RestController
public class RecipeController {
	
	@RequestMapping(value = "/recipe", method = RequestMethod.POST)
	public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe) {
		RecipeDbMongoClient recipeClient = new RecipeDbMongoClient();

		String id = recipe.getUser()+"-"+java.util.UUID.randomUUID().toString();
		recipe.setRecipeId(id);
		//Upload MetaData To DB
		recipeClient.addRecipe(recipe);
		return new ResponseEntity<String>(id, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
	public ResponseEntity<Recipe> getRecipe(@PathVariable("id") String id) {
		RecipeDbMongoClient recipeClient = new RecipeDbMongoClient();
		Recipe recipe = recipeClient.getRecipeById(id);
		return new ResponseEntity<Recipe>(recipe, HttpStatus.ACCEPTED);
	}
}
