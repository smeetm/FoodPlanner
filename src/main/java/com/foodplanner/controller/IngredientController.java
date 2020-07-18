package com.foodplanner.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodplanner.model.RecipeIngredient;
import com.foodplanner.model.Ingredient;
import com.foodplanner.model.Recipe;
import com.foodplanner.mongoclient.IngredientDbMongoClient;
import com.foodplanner.mongoclient.MongoDbClientFactory;

@RestController
public class IngredientController {

	IngredientDbMongoClient ingredientClient = null;
	
	public IngredientController()
	{
		try {
			ingredientClient = (IngredientDbMongoClient) MongoDbClientFactory.getDbClient("ingredient");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/ingredientwithprefix/{prefix}", method = RequestMethod.GET)
	public List<Ingredient> getIngredientWithPrefix(@PathVariable("prefix") String prefix)
	{
		return (List<Ingredient>)ingredientClient.getIngredientsByPrefix(prefix);
	}
	
	@RequestMapping(value = "/ingredient", method = RequestMethod.POST)
	public void addIngredient(@RequestBody Ingredient i)
	{
		ingredientClient.addIngredient(i);
	}
	
	@RequestMapping(value = "/ingredient/{name}", method = RequestMethod.GET)
	public ResponseEntity<Ingredient> getIngredient(@PathVariable("name") String name)
	{
		Ingredient i = ingredientClient.getIngredientByName(name);
		if( i == null) new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Ingredient>(i, HttpStatus.OK);
	}
}
