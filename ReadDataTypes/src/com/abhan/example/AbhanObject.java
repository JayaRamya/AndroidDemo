package com.abhan.example;

import android.graphics.Bitmap;

public class AbhanObject {
	private int id;
	private String name;
	private String nullOrString;
	private String eventMonth;
	private int eventYear;
	private Bitmap eventImage;
	private double version;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNullOrString() {
		return nullOrString;
	}
	
	public void setNullOrString(String nullOrString) {
		this.nullOrString = nullOrString;
	}

	public String getEventMonth() {
		return eventMonth;
	}
	
	public void setEventMonth(String eventMonth) {
		this.eventMonth = eventMonth;
	}
	
	public int getEventYear() {
		return eventYear;
	}
	
	public void setEventYear(int eventYear) {
		this.eventYear = eventYear;
	}
	
	public Bitmap getEventImage() {
		return eventImage;
	}
	
	public void setEventImage(Bitmap eventImage) {
		this.eventImage = eventImage;
	}
	
	public double getVersion() {
		return version;
	}
	
	public void setVersion(double version) {
		this.version = version;
	}
}