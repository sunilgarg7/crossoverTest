package com.crossover.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crossover.data.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long>{
	
	public static int milliSecondsIn12Hours = 12 * 60 * 60 * 1000; // hours * minute * seconds * milliseconds
	
	@Query("from Activity where activityName = ?1 and reportedTime > ?2")
	Collection<Activity> findAllGreaterThan(String activityName, long time);
	
	@Query("from Activity a where a.reportedTime < ?1")
	Collection<Activity> findAllOldActivity(long time);
	
}
