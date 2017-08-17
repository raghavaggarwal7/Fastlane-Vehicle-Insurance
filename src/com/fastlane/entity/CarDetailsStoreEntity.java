package com.fastlane.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="USER_CAR_DETAILS")
@GenericGenerator(name="generator",strategy="sequence")
public class CarDetailsStoreEntity {
	@Id
	@Column(name="REG_ID")
	@GeneratedValue(generator="generator")
	private Integer regId;
	@Column(name="USERNAME")
	private String userName;
	@Column(name="USER_PURCHASE_CITY")
	private String purchaseCity;
	@Column(name="USER_CAR_BRAND")
	private String carBrand;
	@Column(name="USER_CAR_MODEL")
	private String carModel;
	@Column(name="USER_CAR_PRICE")
	private Float carPrice;
	@Column(name="USER_CAR_IDV")
	private Float idv;
	@Column(name="USER_CAR_PURCHASE_DATE")
	private Calendar dateOfPurchase;
	@Column(name="USER_CAR_FINAL_PREMIUM")
	private Float finalPremium;
	@Column(name="USER_CAR_POLICY_NUMBER")
	private Integer policyNo;
	//@GeneratedValue(generator="generator")
	@Column(name="USER_CAR_POLICY_DATE")
	private Calendar policy_date;
	@Column(name="USER_CAR_EXPIRY_DATE")
	private Calendar expiry_date;
	@Column(name="CLAIM_STATUS")
	private String claimStatus;
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
	
	
	
}