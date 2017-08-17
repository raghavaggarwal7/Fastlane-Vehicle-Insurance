package com.fastlane.bean;

import java.util.Calendar;


public class CarDetails {
	private Integer carId;
	private String purchaseCity;
	private String carBrand;
	private String carModel;
	private Float carPrice;
	private Calendar policy_date;
	public Calendar getPolicy_date() {
		return policy_date;
	}
	public void setPolicy_date(Calendar policy_date) {
		this.policy_date = policy_date;
	}
	public Calendar getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Calendar expiry_date) {
		this.expiry_date = expiry_date;
	}
	private Calendar expiry_date;
	private String message;
	public Integer getCarId() {
		return carId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	
	public String getPurchaseCity() {
		return purchaseCity;
	}
	public void setPurchaseCity(String purchaseCity) {
		this.purchaseCity = purchaseCity;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public Float getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(Float carPrice) {
		this.carPrice = carPrice;
	}
	
	
	
	
}