package com.crossover.data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Activity {

	@JsonIgnore
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Activity_Seq") 
	Long id;
	@JsonIgnore
	String activityName;
	int value;
	@JsonIgnore
	long reportedTime;
	
	public Activity() {
			
	}
	
	public Activity(String activityName, int value) {
		this.activityName = activityName;
		this.value = value;
		this.reportedTime = System.currentTimeMillis();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public long getReportedTime() {
		return reportedTime;
	}
	public void setReportedTime(long reportedTime) {
		this.reportedTime = reportedTime;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", activityName=" + activityName + ", value=" + value + ", reportedTime="
				+ reportedTime + "]";
	}
}
