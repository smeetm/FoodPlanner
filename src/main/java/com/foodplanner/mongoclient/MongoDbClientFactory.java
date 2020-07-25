package com.foodplanner.mongoclient;

import com.foodplanner.mongoclient.IngredientDbMongoClient;
import com.foodplanner.mongoclient.MealsForTheDayDbMongoClient;
import com.foodplanner.mongoclient.RecipeDbMongoClient;

public class MongoDbClientFactory {

		public static MongoClientBase getDbClient(String clientType) throws Exception
		{
			switch(clientType)
			{
			case "recipe":
				return new RecipeDbMongoClient();
			case "ingredient":
				return new IngredientDbMongoClient();
			case "mealsfortheday":
				return new MealsForTheDayDbMongoClient();
			default:
				throw new Exception("Client Type Not Supported");
				
			}
		}
}
