package com.foodplanner.model;

import java.util.LinkedList;
import java.util.List;

public class Recipe{

	private String user;
	private List<Ingredient> ingredients;
	private List<Ingredient> ingredientsForSinglePerson;
	private String steps;
	private String recipeId;
	private int serves;
	
	
	public int getServes() {
		return serves;
	}

	public void setServes(int serves) {
		this.serves = serves;
	}
	
	public String getRecipeId() {
		return recipeId;
	}
	
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
		ingredientsForSinglePerson = new LinkedList<Ingredient>();
		
		for(Ingredient i: this.ingredients)
		{
			ingredientsForSinglePerson.add(new Ingredient(i.getName(),i.getQuantity()/this.getServes()));
		}
	}
}
