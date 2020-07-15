package com.foodplanner.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodplanner.model.Ingredient;
import com.foodplanner.model.Recipe;
import com.foodplanner.mongoclient.RecipeDbMongoClient;

@RestController
public class IngredientController {

	@RequestMapping(value = "/aggregateingredients", method = RequestMethod.POST)
	public ResponseEntity<List<Ingredient>> aggregateIngredients(@RequestBody List<String> recipeIds) {
		RecipeDbMongoClient recipeClient = new RecipeDbMongoClient();
		List<Ingredient> allIngredients = new LinkedList<Ingredient>();	
		for(String id: recipeIds)
		{
			Recipe recipe = recipeClient.getRecipeById(id);
			allIngredients.addAll(recipe.getIngredients());
		}
		
		List<Ingredient> aggregatedIngredients = Ingredient.aggregateIngredients(allIngredients);
		return new ResponseEntity<List<Ingredient>>(aggregatedIngredients, HttpStatus.OK);
	}
}
