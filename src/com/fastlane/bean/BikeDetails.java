package com.fastlane.bean;

public class BikeDetails {
	private Integer bikeId;
	private String purchaseCity;
	private String bikeBrand;
	private String bikeModel;
	private Float bikePrice;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getBikeId() {
		return bikeId;
	}
	public void setBikeId(Integer bikeId) {
		this.bikeId = bikeId;
	}
	public String getPurchaseCity() {
		return purchaseCity;
	}
	public void setPurchaseCity(String purchaseCity) {
		this.purchaseCity = purchaseCity;
	}
	public String getBikeBrand() {
		return bikeBrand;
	}
	public void setBikeBrand(String bikeBrand) {
		this.bikeBrand = bikeBrand;
	}
	public String getBikeModel() {
		return bikeModel;
	}
	public void setBikeModel(String bikeModel) {
		this.bikeModel = bikeModel;
	}
	public Float getBikePrice() {
		return bikePrice;
	}
	public void setBikePrice(Float bikePrice) {
		this.bikePrice = bikePrice;
	}

}