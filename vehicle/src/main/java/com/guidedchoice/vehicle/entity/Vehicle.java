package com.guidedchoice.vehicle.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

import lombok.ToString;

@ToString
@Entity
public class Vehicle {

	@Id
	@GeneratedValue
	private int id;
	
    @Min(value = 1950, message = "Year cannot be before 1950")
    @Max(value = 2050, message = "Year cannot be after 2050")
	private int year;
	
	@NotNull(message = "Make cannot be null")
	@NotEmpty(message = "Make cannot be empty")
	private String make;
	
	@NotNull(message = "Model cannot be null")
	@NotEmpty(message = "Model cannot be empty")
	private String model;
	
	public Vehicle() {
	}

	public Vehicle(int id, int year, String make, String model) {
		this.id = id;
		this.year = year;
		this.make = make;
		this.model = model;
	}

	public Vehicle(int year, String make, String model) {
		this.year = year;
		this.make = make;
		this.model = model;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * Override method equals for testing purpose.
	 * Compare the internal values.
	 *  
	 * @param theOther
	 * @return true is both vehicles have the same exact internal values; false otherwise.
	 */
	@Override
	public boolean equals(Object theOther) {
		if(!(theOther instanceof Vehicle)) {
			return false;
		}
		
		Vehicle theOtherVehicle = (Vehicle)theOther;
		
		return (this.id == theOtherVehicle.id)
				&& (this.year == theOtherVehicle.year)
				&& this.make.equals(theOtherVehicle.make)
				&& this.model.equals(theOtherVehicle.model);
	}
}
