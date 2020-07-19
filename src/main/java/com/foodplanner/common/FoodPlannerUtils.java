package com.foodplanner.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.foodplanner.model.MealsForTheDay;
import com.foodplanner.model.Recipe;
import com.foodplanner.model.RecipeAndNoOfPeople;
import com.foodplanner.model.RecipeIngredient;
import com.foodplanner.mongoclient.MongoDbClientFactory;
import com.foodplanner.mongoclient.RecipeDbMongoClient;

public class FoodPlannerUtils {

	public static String generateRecipeId(Recipe recipe)
	{
		String id = recipe.getUser() + "-" + java.util.UUID.randomUUID().toString();
		return id;
	}
	
	public static String generateMealsForTheDayId(MealsForTheDay md)
	{
		String id = md.getUser()+md.getDate();
		return id;
	}
	
	public static List<RecipeIngredient> aggregateIngredients(List<RecipeAndNoOfPeople> recipesForAggregartion) {
		try {
			// IngredientName,qty - map
			HashMap<String, Float> totalIngredientsMap = new HashMap<String, Float>();
			RecipeDbMongoClient recipeClient = (RecipeDbMongoClient) MongoDbClientFactory.getDbClient("recipe");

			for (RecipeAndNoOfPeople recipeAndNoOfPeople : recipesForAggregartion) {
				String recipeId = recipeAndNoOfPeople.getReceipeId();
				int noOfPeopleIngredientsReqdFor = recipeAndNoOfPeople.getNumOfPeopleToServe();

				System.out.println("will search fr recipe id "+recipeId);
				Recipe r = recipeClient.getRecipeById(recipeId);
				System.out.println("Got recipe : "+r);
				if(r == null) continue;
				// from each recipe calculate how much ingredients we require to serve the
				// number of people represented by variable noOfPeople
				int thisRecipeServes = r.getServes();

				for (RecipeIngredient currIngredient : r.getIngredients()) {
					float qtyInRecipe = currIngredient.getQuantity();
					float qtyRequiredForNoOfPeople = (qtyInRecipe / thisRecipeServes) * noOfPeopleIngredientsReqdFor;

					float updatedQtyReqd = totalIngredientsMap.getOrDefault(currIngredient.getName(), (float) 0)
							+ qtyRequiredForNoOfPeople;

					totalIngredientsMap.put(currIngredient.getName(), updatedQtyReqd);
				}
			}

			List<RecipeIngredient> calculatedIngredients = new LinkedList<RecipeIngredient>();

			totalIngredientsMap.forEach((ingredientName, qty) -> {
				RecipeIngredient i = new RecipeIngredient();
				i.setName(ingredientName);
				i.setQuantity(qty);
				calculatedIngredients.add(i);
			});

			return calculatedIngredients;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}
