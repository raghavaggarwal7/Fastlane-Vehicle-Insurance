package com.fastlane.bean;

import java.util.Calendar;



public class CarDetailsStore {
	private Integer regId;
	private String userName;
	private String purchaseCity;
	private String carBrand;
	private String carModel;
	private Float carPrice;
	private Float idv;
	private Calendar dateOfPurchase;
	private Float finalPremium;
	private Integer policyNo;
	private Calendar policy_date;
	private Calendar expiry_date;
	private String claimStatus;
	private String policyType;
	/*private Float Claim;*/
	
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
	public Integer getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(Integer policyNo) {
		this.policyNo = policyNo;
	}
	private String message;
	public Float getFinalPremium() {
		return finalPremium;
	}
	public void setFinalPremium(Float finalPremium) {
		this.finalPremium = finalPremium;
	}
	public Integer getRegId() {
		return regId;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Float getIdv() {
		return idv;
	}
	public void setIdv(Float idv) {
		this.idv = idv;
	}
	public Calendar getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Calendar dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	
	
	
}