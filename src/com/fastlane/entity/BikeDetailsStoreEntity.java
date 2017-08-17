package com.fastlane.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="USER_BIKE_DETAILS")
@GenericGenerator(name="generator",strategy="sequence")
public class BikeDetailsStoreEntity {
	@Id
	@Column(name="REG_ID")
	@GeneratedValue(generator="generator")
	private Integer regId;
	@Column(name="USERNAME")
	private String userName;
	@Column(name="USER_PURCHASE_CITY")
	private String purchaseCity;
	@Column(name="USER_BIKE_BRAND")
	private String bikeBrand;
	@Column(name="USER_BIKE_MODEL")
	private String bikeModel;
	@Column(name="USER_BIKE_PRICE")
	private Float bikePrice;
	@Column(name="USER_BIKE_IDV")
	private Float idv;
	@Column(name="USER_BIKE_PURCHASE_DATE")
	private Calendar dateOfPurchase;
	@Column(name="USER_BIKE_FINAL_PREMIUM")
	private Float finalPremium;
	@Column(name="USER_BIKE_POLICY_NUMBER")
	private Integer policyNo;
	@Column(name="USER_BIKE_POLICY_DATE")
	private Calendar policy_date;
	@Column(name="USER_BIKE_EXPIRY_DATE")
	private Calendar expiry_date;
	@Column(name="POLICY_TYPE")
	private String policyType;
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	@Column(name="CLAIM_STATUS")
	private String claimStatus;
	
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
	
}