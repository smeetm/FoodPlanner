package com.foodplanner.controller;

import org.springframework.web.bind.annotation.RestController;

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
}
