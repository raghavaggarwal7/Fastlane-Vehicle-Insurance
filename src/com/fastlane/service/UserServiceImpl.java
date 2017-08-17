package com.fastlane.service;

import java.util.ArrayList;
import java.util.List;











import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.fastlane.bean.BikeDetailsStore;
import com.fastlane.bean.CarDetailsStore;
import com.fastlane.bean.User;
import com.fastlane.dao.UserDAO;
import com.fastlane.dao.UserDAOImpl;
import com.fastlane.resources.Factory;


public class UserServiceImpl implements UserService {
	public User signup(User user) throws Exception{

		// Add the user
		//System.out.println("In SERVICE: signup function");
		User user1=null;
		try{
			UserDAO dao = Factory.createUserDAO();
			//return dao.addUser(user);
			user1=dao.signup(user);
		}
		catch(Exception e){
			//e.printStackTrace();
		}

		return user1;
	}
	public List<String> getAllUsers() throws Exception{

		List<String> userNames = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			userNames=dao.getAllUsers();
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return userNames;
	}

	@Override
	public String verifyUser(String userName, String password) throws Exception {
		//System.out.println("inside SERVICE: verifyUser function");
		String result = null;
		try{
			result = Factory.createUserDAO().verifyUser(userName, password);
		}catch(Exception exception){
			//e.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
		Logger logger = Logger.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);*/
		throw exception;
		}

		return result;
	}

	/*-------------------------------------------------Car Insurance----------------------------------------------------------*/
	public List<String> getListOfCity() throws Exception{
		//System.out.println("inside list of city function");
		List<String> cityNames = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			cityNames=dao.getListOfCity();
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return cityNames;
	}

	public List<String> getListOfbrand() throws Exception{
		//System.out.println("inside list of brand function");
		List<String> brandNames = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			brandNames=dao.getListOfbrand();
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return brandNames;
	}

	public List<String> getListOfModel(String purchaseCity, String carBrand) throws Exception{
		//System.out.println("inside list of model function");
		List<String> modelNames = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			modelNames=dao.getListOfModel(purchaseCity,carBrand);
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return modelNames;
	}

	public List<Float> getCarPrice(String purchaseCity, String carBrand,String carModel) throws Exception{
		//System.out.println("inside service: get price function");
		List<Float> carPrice=null;
		try{
			UserDAO dao = Factory.createUserDAO();
			carPrice=dao.getCarPrice(purchaseCity,carBrand,carModel);
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return carPrice;

	}

	public CarDetailsStore postCarDetails(CarDetailsStore car) throws Exception{
		//System.out.println("In SERVICE: postCarDetails function");
		CarDetailsStore car1=new CarDetailsStore();
		CarDetailsStore car2=null;
		try{
			Validator v=new Validator();
			Float idv=v.validate(car);
			car1.setIdv(idv);
			car1.setCarBrand(car.getCarBrand());
			car1.setCarModel(car.getCarModel());
			car1.setCarPrice(car.getCarPrice());
			car1.setDateOfPurchase(car.getDateOfPurchase());
			car1.setPurchaseCity(car.getPurchaseCity());
			car1.setRegId(car.getRegId());
			car1.setUserName(car.getUserName());
			UserDAO dao = Factory.createUserDAO();
			//return dao.addUser(user);
			car2=dao.postCarDetails(car1);
		}
		catch(Exception e){
			//e.printStackTrace();
		}

		return car2;

	}

	public List<String> getCarDetails(Integer regId) throws Exception{
		//System.out.println("inside get car details function");
		List<String> car = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			car=dao.getCarDetails(regId);
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return car;
	}

	public Integer addPremium(CarDetailsStore car) throws Exception {
		//System.out.println("inside SERVICE: add premium function");
		Integer policyNo = null;
		try{
			policyNo = Factory.createUserDAO().addPremium(car);
		}catch(Exception e){
			//e.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
		Logger logger = Logger.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
		throw exception;*/
		}

		return policyNo;
	}

	@Override
	public String verifyPolicyNo(String userName, Integer policyNo) throws Exception {
		//System.out.println("inside SERVICE: verifyPolicyNo function");
		String result = null;
		try{
			result = Factory.createUserDAO().verifyPolicyNo(userName, policyNo);
		}catch(Exception e){
			//e.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
		Logger logger = Logger.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
		throw exception;*/
		}

		return result;
	}

	public List<String> renewCar(Integer policyNo) throws Exception{
		//System.out.println("inside renew car function");
		List<String> car = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			car=dao.renewCar(policyNo);
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return car;
	}

	public List<String> getProfileDetails(String userName) throws Exception{
		//System.out.println("inside get profile details function");
		List<String> user = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			user=dao.getProfileDetails(userName);
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return user;
	}

	/*-------------------------------------------------Bike Insurance----------------------------------------------------------*/

	public List<String> getListOfBikeCity() throws Exception{
		//System.out.println("inside list of bike city function");
		List<String> cityNames = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			cityNames=dao.getListOfBikeCity();
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return cityNames;
	}

	public List<String> getListOfBikebrand() throws Exception{
		//System.out.println("inside list of bike brand function");
		List<String> brandNames = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			brandNames=dao.getListOfBikebrand();
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return brandNames;
	}

	public List<String> getListOfBikeModel(String purchaseCity, String bikeBrand) throws Exception{
		//System.out.println("inside list of bike model function");
		List<String> modelNames = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			modelNames=dao.getListOfBikeModel(purchaseCity, bikeBrand);
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return modelNames;
	}

	public List<Float> getBikePrice(String purchaseCity, String bikeBrand,String bikeModel) throws Exception{
		//System.out.println("inside service: get bike price function");
		List<Float> bikePrice=null;
		try{
			UserDAO dao = Factory.createUserDAO();
			bikePrice=dao.getBikePrice(purchaseCity, bikeBrand, bikeModel);
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return bikePrice;

	}

	public BikeDetailsStore postBikeDetails(BikeDetailsStore bike) throws Exception{
		//System.out.println("In SERVICE: postBikeDetails function");
		BikeDetailsStore bike1=new BikeDetailsStore();
		BikeDetailsStore bike2=null;
		try{
			Validator v=new Validator();
			Float idv=v.validateBike(bike);
			bike1.setIdv(idv);
			bike1.setBikeBrand(bike.getBikeBrand());
			bike1.setBikeModel(bike.getBikeModel());
			bike1.setBikePrice(bike.getBikePrice());
			bike1.setDateOfPurchase(bike.getDateOfPurchase());
			bike1.setPurchaseCity(bike.getPurchaseCity());
			bike1.setRegId(bike.getRegId());
			bike1.setUserName(bike.getUserName());
			UserDAO dao = Factory.createUserDAO();
			//return dao.addUser(user);
			bike2=dao.postBikeDetails(bike1);
		}
		catch(Exception e){
			//e.printStackTrace();
		}

		return bike2;

	}

	public List<String> getBikeDetails(Integer regId) throws Exception{
		//System.out.println("inside get bike details function");
		List<String> bike = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			bike=dao.getBikeDetails(regId);
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return bike;
	}

	public Integer addBikePremium(BikeDetailsStore bike) throws Exception {
		//System.out.println("inside SERVICE: add bike premium function");
		Integer policyNo = null;
		try{
			policyNo = Factory.createUserDAO().addBikePremium(bike);
		}catch(Exception e){
			//e.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
		Logger logger = Logger.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
		throw exception;*/
		}

		return policyNo;
	}

	@Override
	public String verifyBikePolicyNo(String userName, Integer policyNo) throws Exception {
		//System.out.println("inside SERVICE: verifyBikePolicyNo function");
		String result = null;
		try{
			result = Factory.createUserDAO().verifyBikePolicyNo(userName, policyNo);
		}catch(Exception e){
			//e.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
		Logger logger = Logger.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
		throw exception;*/
		}

		return result;
	}

	public List<String> renewBike(Integer policyNo) throws Exception{
		//System.out.println("inside renew bike function");
		List<String> bike = new ArrayList<String>();
		try{
			UserDAO dao = Factory.createUserDAO();
			bike=dao.renewBike(policyNo);
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return bike;
	}
	@Override
	public CarDetailsStore updateClaimStatus(CarDetailsStore carDetailsStore)
			throws Exception {
		//System.out.println("in service: updateClaim");
		CarDetailsStore detailsStore = null;
		try{
			detailsStore = Factory.createUserDAO().updateClaimStatus(carDetailsStore);
		}catch(Exception exception){

		}
		return detailsStore;
	}
	

}