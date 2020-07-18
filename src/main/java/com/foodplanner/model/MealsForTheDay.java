package com.foodplanner.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class MealsForTheDay {
	
	String id;
	List<RecipeAndNoOfPeople> recipeAndNumberOfPeople;
	String user;
	Date date;
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
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}
