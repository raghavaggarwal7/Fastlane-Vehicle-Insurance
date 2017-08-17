package com.fastlane.service;

import java.util.ArrayList;
import java.util.List;

import com.fastlane.dao.AdminDAO;
import com.fastlane.resources.Factory;

public class AdminServiceImpl implements AdminService{
	
	
	
	@Override
	public String verifyAdmin(String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("inside SERVICE: verifyAdmin function");
		String result = null;
		try{
			result = Factory.createAdminDAO().verifyAdmin(userName, password);
		}catch(Exception e){
			//e.printStackTrace();
			
		}
		
		return result;
	}

}