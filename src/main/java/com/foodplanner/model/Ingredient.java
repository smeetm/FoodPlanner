package com.foodplanner.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Ingredient {

	private String name;
	private float quantity;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
	
	public static List<Ingredient> aggregateIngredients(List<Ingredient> ingredients)
	{
		HashMap<String,Float> totalIngredientsMap = new HashMap<String,Float>();
		
		for(Ingredient i : ingredients)
		{
			String currIngredient = i.name;
			float qtyOfIngredientSoFar = totalIngredientsMap.getOrDefault(currIngredient, (float) (0))+i.getQuantity();
			
			totalIngredientsMap.put(currIngredient, qtyOfIngredientSoFar);
		}
		
		List<Ingredient> calculatedIngredients = new LinkedList<Ingredient>();
				
		totalIngredientsMap.forEach( (ingredientName,qty) -> {
			 Ingredient i = new Ingredient();
			 i.setName(ingredientName);
			 i.setQuantity(qty);
			 calculatedIngredients.add(i);
		});
		
		return calculatedIngredients;
	}
	
}
