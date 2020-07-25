package com.foodplanner.model;

public class RecipeAndNoOfPeople{

	private String receipeId;
	private int numOfPeopleToServe;
	public String getReceipeId() {
		return receipeId;
	}
	public void setReceipeId(String receipeId) {
		this.receipeId = receipeId;
	}
	public int getNumOfPeopleToServe() {
		return numOfPeopleToServe;
	}
	public void setNumOfPeopleToServe(int numOfPeopleToServe) {
		this.numOfPeopleToServe = numOfPeopleToServe;
	}	
}
