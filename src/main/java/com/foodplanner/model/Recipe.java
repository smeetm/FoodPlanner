package com.foodplanner.model;

import java.util.LinkedList;
import java.util.List;

public class Recipe{

	private String user;
	private List<RecipeIngredient> ingredients;
	private String steps;
	private String id;
	private int serves;
	
	
	public int getServes() {
		return serves;
	}

	public void setServes(int serves) {
		this.serves = serves;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String recipeId) {
		this.id = recipeId;
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
	public List<RecipeIngredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}
}
