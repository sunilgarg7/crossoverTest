package com.crossover.controllers;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.dao.ActivityRepository;
import com.crossover.data.Activity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@RestController
public class ActivityController {
	
	@Autowired
	ActivityRepository activityRepo;

	@GetMapping("/")
	public String welcome() {
		return "Welcome to Crossover Activity Logging and Reporting App";
	}

	@PostMapping("/activity/{key}")
	public String logActivity(@PathVariable String key, @RequestBody Map<String, Integer> map) {
		Activity activity = new Activity(key, map.get("value"));
		activityRepo.save(activity);
		System.out.println(activity);
		return "{}";
	}
	
	
	@GetMapping("/activity/{key}/total")
	public String calculateActivity(@PathVariable String key) {
		Collection<Activity> activities = activityRepo.findAllGreaterThan(key, System.currentTimeMillis() - ActivityRepository.milliSecondsIn12Hours);
		System.out.println(activities);
		int sum =  activities.stream().mapToInt(x -> x.getValue()).sum();
		return "{\n \"value\": " + sum + "\n}";
	}
	
	// Extra functionality for reporting activity more easier
	@GetMapping("/activity/{key}/{value}") 
	public String logActivity(@PathVariable String key, @PathVariable int value) {
		Activity activity = new Activity(key, value);
		activityRepo.save(activity);
		return "{}";
		
	}
	
}

@JsonSerialize 
class EmptyJsonResponse { }





