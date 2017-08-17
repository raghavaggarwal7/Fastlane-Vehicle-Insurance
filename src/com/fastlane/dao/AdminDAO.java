package com.fastlane.dao;

import java.util.List;

import com.fastlane.bean.CarDetailsStore;

public interface AdminDAO {
	public List<String> getAllPolicies() throws Exception;
	public String verifyAdmin(String userName, String password) throws Exception;
	public CarDetailsStore updateClaimStatus(CarDetailsStore carDetailsStore) throws Exception;
}