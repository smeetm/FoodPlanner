package com.foodplanner.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodplanner.common.FoodPlannerUtils;
import com.foodplanner.model.MealsForTheDay;
import com.foodplanner.mongoclient.MealsForTheDayDbMongoClient;
import com.foodplanner.mongoclient.MongoDbClientFactory;

@RestController
public class MealsForTheDayController {

	MealsForTheDayDbMongoClient mdClient = null;

	public MealsForTheDayController()
	{
		try {
			mdClient = (MealsForTheDayDbMongoClient) MongoDbClientFactory.getDbClient("mealsfortheday");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/mealsForTheDay", method = RequestMethod.POST)
	public ResponseEntity addOrUpdateMealsForTheDay(@RequestBody MealsForTheDay md) {
		String id = FoodPlannerUtils.generateMealsForTheDayId(md);
		md.setId(id);
		mdClient.addOrUpdateMealForTheDay(md);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mealsForTheDay/{user}", method = RequestMethod.GET)
	public List<MealsForTheDay> getMealsForTheDay(@PathVariable("user") String user,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
	{
		return mdClient.getMealsForDays(user,startDate, endDate);
	}

}
