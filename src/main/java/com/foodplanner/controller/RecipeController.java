package com.foodplanner.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodplanner.common.FoodPlannerUtils;
import com.foodplanner.model.RecipeIngredient;
import com.foodplanner.model.Recipe;
import com.foodplanner.model.RecipeAndNoOfPeople;
import com.foodplanner.mongoclient.MongoDbClientFactory;
import com.foodplanner.mongoclient.RecipeDbMongoClient;

@RestController
public class RecipeController {

	RecipeDbMongoClient recipeClient = null;

	public RecipeController() {
		try {
			recipeClient = (RecipeDbMongoClient) MongoDbClientFactory.getDbClient("recipe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/recipe", method = RequestMethod.POST)
	public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe) {

		String id = FoodPlannerUtils.generateRecipeId(recipe);
		recipe.setId(id);
		// Upload MetaData To DB
		recipeClient.addRecipe(recipe);
		return new ResponseEntity<String>(id, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
	public ResponseEntity<Recipe> getRecipe(@PathVariable("id") String id) {
		Recipe recipe = recipeClient.getRecipeById(id);
		if(recipe == null) return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Recipe>(recipe, HttpStatus.ACCEPTED);
	}

	
	@RequestMapping(value = "/aggregateingredients", method = RequestMethod.POST)
	public ResponseEntity<List<RecipeIngredient>> aggregateIngredients(
			@RequestBody List<RecipeAndNoOfPeople> aggIngredientsRequest) {

		List<RecipeIngredient> aggregatedIngredients = FoodPlannerUtils.aggregateIngredients(aggIngredientsRequest);
		return new ResponseEntity<List<RecipeIngredient>>(aggregatedIngredients, HttpStatus.OK);
	}
}
