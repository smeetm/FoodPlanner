package com.foodplanner.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Ingredient {

	private String name;
	private float quantity;
	
	public Ingredient()
	{
		
	}
	
	public Ingredient(String name,float qty)
	{
		this.name = name;
		this.quantity = qty;
	}
	
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
}
