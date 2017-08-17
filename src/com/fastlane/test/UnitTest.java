package com.fastlane.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;










import org.junit.rules.ExpectedException;

import com.fastlane.bean.BikeDetailsStore;
import com.fastlane.bean.CarDetailsStore;
import com.fastlane.dao.AdminDAO;
import com.fastlane.dao.UserDAO;
import com.fastlane.resources.Factory;






public class UnitTest {

	@Rule
	public ExpectedException ee = ExpectedException.none();
	//Negative test case
	@Test
	public void InvalidUserName() throws Exception {
		ee.expectMessage("DAO.NO_USER_EXCEPTION");
		UserDAO dao = Factory.createUserDAO();
		String userName="raghav1324@hgjh";
		String password="Coldplay";
		String message = dao.verifyUser(userName,password);
		//Assert.assertEquals(message, "DAO.NO_USER_EXCEPTION");
		
	}

	//Positive test case
	@Test
	public void ValidUserName() throws Exception {
		
		UserDAO dao = Factory.createUserDAO();
		String userName="yashu";
		String password="yash%123";
		String message = dao.verifyUser(userName,password);
		Assert.assertEquals(message, "User verified");
		
	}
	//Negative test case
	@Test
	public void InvalidUserPassword() throws Exception {
		ee.expectMessage("DAO.PWD_MISMATCH");
		UserDAO dao = Factory.createUserDAO();
		String userName="yashu";
		String password="yashu";
		String message = dao.verifyUser(userName,password);
		//Assert.assertEquals(message, "Passwords do not match");
		
	}
	//Negative test case
		@Test
		public void InvalidAdminName() throws Exception {
			
			AdminDAO dao = Factory.createAdminDAO();
			String userName="Steve";
			String password="rahul";
			String message = dao.verifyAdmin(userName, password);
			Assert.assertEquals(message, "Admin not found");
			
	}
	//Negative test case
		@Test
		public void ValidAdminName() throws Exception {
					
			AdminDAO dao = Factory.createAdminDAO();
			String userName="admin";
			String password="admin";
			String message = dao.verifyAdmin(userName, password);
			Assert.assertEquals(message, "Admin verified");
					
	}
		//Negative test case
				@Test
				public void InvalidAdminPassword() throws Exception {
							
					AdminDAO dao = Factory.createAdminDAO();
					String userName="admin";
					String password="admin1";
					String message = dao.verifyAdmin(userName, password);
					Assert.assertEquals(message, "Passwords do not match");
							
			}
				
				@Test
				public void validListOfModel() throws Exception {
							
					UserDAO dao = Factory.createUserDAO();
					String purchaseCity="BANGALORE-KARNATAKA";
					
					String carBrand="Honda";
					List<String> message = dao.getListOfModel(purchaseCity,carBrand);
					List<String> actual = new ArrayList<String>();
					actual.add("Honda Brio");
					actual.add("Honda City 1.5");
					actual.add("Honda BRV S I-DTEC PETROL");
					actual.add("Amaze 1.2 EX");
					actual.add("Honda BRV VX I-DTEC DIESEL");
					
					Assert.assertEquals(message, actual);
			
			}
				
				@Test
				public void validListOfPrice() throws Exception {
							
					UserDAO dao = Factory.createUserDAO();
					String purchaseCity="BANGALORE-KARNATAKA";
					
					String carBrand="Honda";
					String carModel="Amaze 1.2 EX";
					List<Float> message = dao.getCarPrice(purchaseCity, carBrand, carModel);
					List<Float> actual = new ArrayList<Float>();
					actual.add(563000F);
					Assert.assertEquals(message, actual);

			}
			
				@Test
				public void validListOfBikeModel() throws Exception {
							
					UserDAO dao = Factory.createUserDAO();
					String purchaseCity="BANGALORE-KARNATAKA";
					
					String bikeBrand="Hero Honda";
					List<String> message = dao.getListOfBikeModel(purchaseCity,bikeBrand);
					List<String> actual = new ArrayList<String>();
				
					actual.add("Joy");
					actual.add("Splendor Disc");
					actual.add("CD 100");
					actual.add("Passion");
					actual.add("Karizma Red Cast");	
					Assert.assertEquals(message, actual);

			}
				@Test
				public void validListOfBikePrice() throws Exception {
							
					UserDAO dao = Factory.createUserDAO();
					String purchaseCity="BANGALORE-KARNATAKA";
					
					String bikeBrand="Hero Honda";
					String bikeModel="Splendor Disc";
					List<Float> message = dao.getBikePrice(purchaseCity, bikeBrand, bikeModel);
					List<Float> actual = new ArrayList<Float>();
					actual.add(33624F);
					Assert.assertEquals(message, actual);
		
			}
				
				@Test
				public void validRenewBike() throws Exception {
							
					UserDAO dao = Factory.createUserDAO();
					Integer policyNo=1021;
					List<String> message = dao.renewBike(policyNo);
					List<String> actual = new ArrayList<String>();
					Assert.assertEquals(message, actual);
		
			}
				

				@Test
				public void validAddBikePremium() throws Exception {
							
					UserDAO dao = Factory.createUserDAO();
					BikeDetailsStore bike=new BikeDetailsStore();
					bike.setBikeBrand("Suzuki");
					bike.setBikeModel("GT 125");
					bike.setBikePrice(37624F);
					Calendar purchase=Calendar.getInstance();
					purchase.set(1987, Calendar.DECEMBER, 22);
					bike.setDateOfPurchase(purchase);
					Calendar expiry_date=Calendar.getInstance();
					expiry_date.set(2017, Calendar.OCTOBER, 05);
					bike.setExpiry_date(expiry_date);
					bike.setFinalPremium(13013F);
					bike.setIdv(242549F);
					Calendar policy_date=Calendar.getInstance();
					policy_date.set(2017, Calendar.MAY, 05);
					bike.setPolicy_date(policy_date);
					bike.setPolicyNo(1021);
					bike.setPurchaseCity("BANGALORE-KARNATAKA");
					bike.setRegId(9047);
					bike.setUserName("yashu");
					Integer message = dao.addBikePremium(bike);
					Integer actual=10048;
					Assert.assertEquals(message, actual);
		
			}
				
				@Test
				public void validListOfCity() throws Exception {	
					UserDAO dao = Factory.createUserDAO();
					List<String> message = dao.getListOfCity();
					List<String> actual = new ArrayList<String>();
					actual.add("MUMBAI-MAHARASHTRA");
					actual.add("HYDERABAD-TELANGANA");
					
					actual.add("CHENNAI-TAMILNADU");
					actual.add("NEW DELHI-DELHI");
					actual.add("PUNE-MAHARASHTRA");	
					actual.add("BANGALORE-KARNATAKA");
					Assert.assertEquals(message, actual);

			}
				
				@Test
				public void validListOfBrand() throws Exception {	
					UserDAO dao = Factory.createUserDAO();
					List<String> message = dao.getListOfbrand();
					List<String> actual = new ArrayList<String>();
					actual.add("Mahindra");
					actual.add("ISUZU");
					actual.add("Hyundai");
					actual.add("fiat");
					actual.add("Maruti");	
					actual.add("Honda");
					actual.add("Tata");
					actual.add("Skoda");
					Assert.assertEquals(message, actual);

			}
				
				@Test
				public void validListOfBikeBrand() throws Exception {	
					UserDAO dao = Factory.createUserDAO();
					List<String> message = dao.getListOfBikebrand();
					List<String> actual = new ArrayList<String>();
					actual.add("Bajaj Auto");
					actual.add("Royal EnField");
					actual.add("Harley Davidson");
					actual.add("Hero Honda");
					actual.add("Suzuki");
					actual.add("BMW");	
					
					actual.add("Go Green");
					actual.add("YAMAHA");
					Assert.assertEquals(message, actual);

			}
				
				
				
				
				
}