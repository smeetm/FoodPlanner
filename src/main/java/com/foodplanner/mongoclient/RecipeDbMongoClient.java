package com.foodplanner.mongoclient;

import com.foodplanner.model.Recipe;

//Can be made singleton
public class RecipeDbMongoClient extends MongoClientBase<Recipe>{
	
	public RecipeDbMongoClient()
	{
		super("mongodb+srv://mongodbadmin:smeetarunav@cluster0.xcul4.azure.mongodb.net/foodplanner?retryWrites=true&w=majority",
				"foodplanner",
				"recipe",
				Recipe.class);
	}
	
	public void addRecipe(Recipe recipe)
	{
		this.insertEntity(recipe);
	}
	
	public Recipe getRecipeById(String id)
	{
		return (Recipe)this.getById("recipeId", id);
	}

}
