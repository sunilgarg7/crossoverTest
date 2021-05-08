package com.crossover;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.crossover.dao.ActivityRepository;
import com.crossover.data.Activity;

@SpringBootApplication
public class CrossoverActivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrossoverActivityApplication.class, args);
	}

	
	// one cleaner service to remove the 12 hours older activity
	@Service
	class OldActivityRemover {
		
		@Autowired
		ActivityRepository activityRepo;
		
		@PostConstruct // Refresh after 24 Hours
		@Scheduled(fixedRate = 12 * 60 * 60 * 1000)
		public void removeOldActivities(){
			//System.out.println("scheduled activity run");
			Collection<Activity> activities = activityRepo.findAllOldActivity(System.currentTimeMillis() - ActivityRepository.milliSecondsIn12Hours);
			activityRepo.deleteAll(activities);
		}
	}
	
}
