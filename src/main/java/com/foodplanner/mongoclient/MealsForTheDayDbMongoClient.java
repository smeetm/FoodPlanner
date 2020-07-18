package com.foodplanner.mongoclient;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.eq;

import java.util.Date;
import java.util.List;

import org.bson.conversions.Bson;

import com.foodplanner.model.MealsForTheDay;

public class MealsForTheDayDbMongoClient extends MongoClientBase<MealsForTheDay> {

	public MealsForTheDayDbMongoClient()
	{
		super("mongodb+srv://mongodbadmin:smeetarunav@cluster0.xcul4.azure.mongodb.net/foodplanner?retryWrites=true&w=majority",
				"foodplanner",
				"mealsfortheday",
				MealsForTheDay.class);
	}
	
	public boolean mealsForDayExistInDb(MealsForTheDay mealsForDay)
	{
		MealsForTheDay md = this.getUniqueEntityByField("_id", mealsForDay.getId());
		if(md == null) return false;
		return true;
	}
	
	public void addOrUpdateMealForTheDay(MealsForTheDay md){
		
		if(!mealsForDayExistInDb(md))
		{
			this.insertEntity(md);
		}
		else
		{
			this.replaceOneEntity("_id", md.getId(), md);
		}
	}
	
	public List<MealsForTheDay> getMealsForDays(String user,Date start,Date end)
	{
		Bson filter = and(gte("date",start),lte("date",end),eq("user",user));
		return this.getEntitiesSatisfyingFilter(filter);
	}
}
