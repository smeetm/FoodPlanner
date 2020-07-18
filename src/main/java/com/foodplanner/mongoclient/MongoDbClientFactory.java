package com.foodplanner.mongoclient;

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
