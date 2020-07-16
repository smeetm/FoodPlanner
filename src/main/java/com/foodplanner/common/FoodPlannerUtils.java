package com.foodplanner.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.foodplanner.model.Ingredient;
import com.foodplanner.model.Recipe;
import com.foodplanner.model.RecipeAndNoOfPeople;
import com.foodplanner.mongoclient.MongoDbClientFactory;
import com.foodplanner.mongoclient.RecipeDbMongoClient;

public class FoodPlannerUtils {

	public static List<Ingredient> aggregateIngredients(List<RecipeAndNoOfPeople> recipesForAggregartion) {
		try {
			// IngredientName,qty - map
			HashMap<String, Float> totalIngredientsMap = new HashMap<String, Float>();
			RecipeDbMongoClient recipeClient = (RecipeDbMongoClient) MongoDbClientFactory.getDbClient("recipe");

			for (RecipeAndNoOfPeople recipeAndNoOfPeople : recipesForAggregartion) {
				String recipeId = recipeAndNoOfPeople.getReceipeId();
				int noOfPeopleIngredientsReqdFor = recipeAndNoOfPeople.getnumOfPeopleToServe();

				Recipe r = recipeClient.getRecipeById(recipeId);

				// from each recipe calculate how much ingredients we require to serve the
				// number
				// number of people represented by variable noOfPeople
				int thisRecipeServes = r.getServes();

				for (Ingredient currIngredient : r.getIngredients()) {
					float qtyInRecipe = currIngredient.getQuantity();
					float qtyRequiredForNoOfPeople = (qtyInRecipe / thisRecipeServes) * noOfPeopleIngredientsReqdFor;

					float updatedQtyReqd = totalIngredientsMap.getOrDefault(currIngredient.getName(), (float) 0)
							+ qtyRequiredForNoOfPeople;

					totalIngredientsMap.put(currIngredient.getName(), updatedQtyReqd);
				}
			}

			List<Ingredient> calculatedIngredients = new LinkedList<Ingredient>();

			totalIngredientsMap.forEach((ingredientName, qty) -> {
				Ingredient i = new Ingredient();
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
