package com.fastlane.bean;

import java.util.Calendar;

public class BikeDetailsStore {
	private Integer regId;
	private String userName;
	private String purchaseCity;
	private String bikeBrand;
	private String bikeModel;
	private Float bikePrice;
	private String message;
	private String claimStatus;
	private Float idv;
	private Calendar dateOfPurchase;
	private Float finalPremium;
	private Integer policyNo;
	private Calendar policy_date;
	private Calendar expiry_date;
	private String policyType;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
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
	public Float getFinalPremium() {
		return finalPremium;
	}
	public void setFinalPremium(Float finalPremium) {
		this.finalPremium = finalPremium;
	}
	public Integer getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(Integer policyNo) {
		this.policyNo = policyNo;
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