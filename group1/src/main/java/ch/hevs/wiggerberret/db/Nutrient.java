package ch.hevs.wiggerberret.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Nutrient {
	
	@Id
	private int id;
	private String name;
	private String unit;
	private double perHundred;
	private double perPortion;
	private int perDay;
	
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
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public double getPerHundred() {
		return perHundred;
	}
	
	public void setPerHundred(double perHundred) {
		this.perHundred = perHundred;
	}
	
	public double getPerPortion() {
		return perPortion;
	}
	
	public void setPerPortion(double perPortion) {
		this.perPortion = perPortion;
	}
	
	public int getPerDay() {
		return perDay;
	}
	
	public void setPerDay(int perDay) {
		this.perDay = perDay;
	}	
}