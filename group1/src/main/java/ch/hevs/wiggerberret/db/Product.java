package ch.hevs.wiggerberret.db;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
	
	@Id
	private String id;
	private String name;
	private String ingredients;
	private int quantity;
	private String unit;
	private int portionQuantity;
	private String portionUnit;
	private ArrayList<Nutrient> nutrients;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public int getPortionQuantity() {
		return portionQuantity;
	}
	
	public void setPortionQuantity(int portionQuantity) {
		this.portionQuantity = portionQuantity;
	}
	
	public String getPortionUnit() {
		return portionUnit;
	}
	
	public void setPortionUnit(String portionUnit) {
		this.portionUnit = portionUnit;
	}
	
	public ArrayList<Nutrient> getNutrients() {
		return nutrients;
	}
	
	public void setNutrients(ArrayList<Nutrient> nutrients) {
		this.nutrients = nutrients;
	}	
}