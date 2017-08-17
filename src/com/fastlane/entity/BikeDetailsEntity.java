package com.fastlane.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BIKE_DETAILS")
public class BikeDetailsEntity {
	@Id
	private Integer BIKE_ID;
	@Column(name="BIKE_PURCHASE_CITY")
	private String purchaseCity;
	@Column(name="BIKE_BRAND")
	private String bikeBrand;
	@Column(name="BIKE_MODEL")
	private String bikeModel;
	@Column(name="BIKE_PRICE")
	private Float bikePrice;
	
	public Integer getBIKE_ID() {
		return BIKE_ID;
	}
	public void setBIKE_ID(Integer bIKE_ID) {
		BIKE_ID = bIKE_ID;
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