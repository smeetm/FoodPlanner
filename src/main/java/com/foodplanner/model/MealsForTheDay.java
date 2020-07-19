package com.foodplanner.model;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class MealsForTheDay {
	
	String id;
	List<RecipeAndNoOfPeople> recipeAndNumberOfPeople;
	String user;
	Long date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<RecipeAndNoOfPeople> getRecipeAndNumberOfPeople() {
		return recipeAndNumberOfPeople;
	}
	public void setRecipeAndNumberOfPeople(List<RecipeAndNoOfPeople> recipeAndNumberOfPeople) {
		this.recipeAndNumberOfPeople = recipeAndNumberOfPeople;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Long getDate() {
		return date;
	}
	
	public void setDate(Long date) {
		this.date = date;
	}
}
