package com.foodplanner.mongoclient;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.foodplanner.model.Recipe;

//Can be made singleton
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
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
		return (Recipe)this.getUniqueEntityByField("_id", id);
	}
	
	public List<Recipe> getRecipesByUser(String user)
	{
		return (List<Recipe>)this.getMultipleEntitiesByField("user", user,20);
	}

}
