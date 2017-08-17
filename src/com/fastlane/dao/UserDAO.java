package com.fastlane.dao;

import java.util.List;

import com.fastlane.bean.*;

public interface UserDAO
{
	public User signup(User user) throws Exception;
	
	public List<String> getAllUsers() throws Exception;
	/*public List<String> getPasswordList() throws Exception;*/
	
	public String verifyUser(String userName, String password) throws Exception;
	
	/*-------------------------------------------------Car Insurance----------------------------------------------------------*/
	public List<String> getListOfCity() throws Exception;
	public List<String> getListOfbrand() throws Exception;
	public List<String> getListOfModel(String purchaseCity, String carBrand) throws Exception;
	public List<Float> getCarPrice(String purchaseCity, String carBrand,String carModel) throws Exception;
	public CarDetailsStore postCarDetails(CarDetailsStore car) throws Exception;
	public List<String> getCarDetails(Integer regId) throws Exception;
	public Integer addPremium(CarDetailsStore car) throws Exception;
	
	public List<String> getProfileDetails(String userName)  throws Exception;
	public String verifyPolicyNo(String userName, Integer policyNo) throws Exception;
	public List<String> renewCar(Integer policyNo) throws Exception;
	public CarDetailsStore updateClaimStatus(CarDetailsStore carDetailsStore) throws Exception;
	public List<String> AdminGetAllUsers() throws Exception;
	
	/*-------------------------------------------------Bike Insurance----------------------------------------------------------*/
	public List<String> getListOfBikeCity() throws Exception;
	public List<String> getListOfBikebrand() throws Exception;
	public List<String> getListOfBikeModel(String purchaseCity, String bikeBrand) throws Exception;
	public List<Float> getBikePrice(String purchaseCity, String bikeBrand,String bikeModel) throws Exception;
	public BikeDetailsStore postBikeDetails(BikeDetailsStore bike) throws Exception;
	public List<String> getBikeDetails(Integer regId) throws Exception;
	public Integer addBikePremium(BikeDetailsStore car) throws Exception;
	public String verifyBikePolicyNo(String userName, Integer policyNo) throws Exception;
	public List<String> renewBike(Integer policyNo) throws Exception;
}