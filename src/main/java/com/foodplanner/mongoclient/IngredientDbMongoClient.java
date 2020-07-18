package com.foodplanner.mongoclient;

import java.util.List;

import com.foodplanner.model.Ingredient;;

public class IngredientDbMongoClient extends MongoClientBase<Ingredient>{
	
	public IngredientDbMongoClient()
	{
		super("mongodb+srv://mongodbadmin:smeetarunav@cluster0.xcul4.azure.mongodb.net/foodplanner?retryWrites=true&w=majority",
				"foodplanner",
				"ingredient",
				Ingredient.class);
	}
	
	public Ingredient getIngredientByName(String name)
	{
		return this.getUniqueEntityByField("name", name);
	}
	
	public List<Ingredient> getIngredientsByPrefix(String prefix)
	{
		return this.getMultipleEntitiesByFieldPrefix("name",prefix,20);
	}
	
	public void addIngredient(Ingredient i)
	{
		this.insertEntity(i);
	}

}
