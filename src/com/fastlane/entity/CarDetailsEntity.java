package com.fastlane.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="CAR_DETAILS")
public class CarDetailsEntity {

	@Id
	private Integer CAR_ID;
	@Column(name="CAR_PURCHASE_CITY")
	private String purchaseCity;
	@Column(name="CAR_BRAND")
	private String carBrand;
	@Column(name="CAR_MODEL")
	private String carModel;
	@Column(name="CAR_PRICE")
	private Float carPrice;
	
	public Integer getCAR_ID() {
		return CAR_ID;
	}
	public void setCAR_ID(Integer cAR_ID) {
		CAR_ID = cAR_ID;
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