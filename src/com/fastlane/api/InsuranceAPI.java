package com.fastlane.api;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fastlane.bean.BikeDetails;
import com.fastlane.bean.BikeDetailsStore;
import com.fastlane.bean.CarDetails;
import com.fastlane.bean.CarDetailsStore;
import com.fastlane.bean.User;
import com.fastlane.resources.AppConfig;
import com.fastlane.resources.Factory;
import com.fastlane.resources.JSONParser;
import com.fastlane.service.UserService;



@Path("Insurance")
public class InsuranceAPI {
	@POST
	@Path("signup")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response signup(String dataRecieved) throws Exception{
		Response res=null;
		try{
			//System.out.println("in api signup");
    		User user1=JSONParser.fromJson(dataRecieved, User.class);
    		System.out.println(user1.getEmailId());
    		System.out.println(user1.getName());
    		System.out.println(user1.getPassword());
    		System.out.println(user1.getMobileNo());
    		System.out.println(user1.getUserName());
    	UserService service1 = Factory.createUserService();
    	User user2 = service1.signup(user1);
    	String successMessage= getBookingSuccessMessage(user2);
    	User user3=new User();
    	user3.setMessage(successMessage);
    	String result=JSONParser.toJson(user3);
    	res=Response.status(Status.OK).entity(result).build();
    	}
    	catch(Exception e)
    	{
    		
    		String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
    		User user3=new User();
        	user3.setMessage(errorMessage);
        	String result=JSONParser.toJson(user3) ;
        	res=Response.status(Status.SERVICE_UNAVAILABLE).entity(result).build();
    	}
    	
		return res;
	}
	
	@Path("getusername")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserNames(){
		Response response = null;
		String res = null;
		try{
			System.out.println("in api: getUserName");
			List<String> userNames = Factory.createUserService().getAllUsers();
			//System.out.println(userNames);
			res = JSONParser.toJson(userNames);
			
			response = Response.status(Status.OK).entity(res).build();
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	
	@POST
	@Path("verifyusercredentials")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyUserCredentials(String dataReceived) throws Exception{
		//System.out.println("inside API: verify user credentials function");
		Response response = null;
		try{
			User user = JSONParser.fromJson(dataReceived, User.class);
			String userName = user.getUserName();
			String password = user.getPassword();
			
			//System.out.println(userName);
			//System.out.println(password);
			String as = Factory.createUserService().verifyUser(userName, password);
			User user1 = new User();
			user1.setMessage(as);
			//System.out.println("as: "+as);
			String res = JSONParser.toJson(user1);
			//System.out.println(res);
			response = Response.status(Status.OK).entity(res).build();
		}catch(Exception e){
			String errorMessage = AppConfig.PROPERTIES.getProperty(e.getMessage());
			User user = new User();
			user.setMessage(errorMessage);
			String result = JSONParser.toJson(user);
			response = Response.status(Status.SERVICE_UNAVAILABLE).entity(result).build();
		}
		return response;
	}
	
	private String getBookingSuccessMessage(User user) {
		//System.out.println("In API: getBookingSuccessMessage function");
		String message = null;
		//System.out.println(AppConfig.PROPERTIES.getProperty("RegistrationBean.REGISTRATION_SUCCESS1"));
		message = AppConfig.PROPERTIES
				.getProperty("RegistrationBean.REGISTRATION_SUCCESS1");
		message += user.getUserId();
		return message;
	}
	
/*---------------------------------------------------------Car Insurance------------------------------------------------------------*/
	@Path("fetchpurchasecitylist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response fetchPurchaseCityList() throws Exception {
		//System.out.println("In API: fetchpurchasecitylist function");
		String returnValue = null;
		Response response = null;
		try {
			UserService userService = Factory.createUserService();
			List<String> cities = userService.getListOfCity();

			returnValue = JSONParser.toJson(cities);
			response = Response.status(Status.OK).entity(returnValue).build();
		} catch (Exception e) {
			String errorMessage = AppConfig.PROPERTIES.getProperty(e.getMessage());
			
			CarDetails carDetails = new CarDetails();
			carDetails.setMessage(errorMessage);
			
			returnValue = JSONParser.toJson(carDetails);
			response = Response.status(Status.SERVICE_UNAVAILABLE)
					.entity(returnValue).build();
		}
		
		return response;
	}
	
	@Path("fetchcarbrandlist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchCarBrandList() throws Exception {
		//System.out.println("In API: fetchCarBrandList function");
		String returnValue = null;
		Response response = null;
		try {
			UserService userService = Factory.createUserService();
			List<String> carBrand = userService.getListOfbrand();

			returnValue = JSONParser.toJson(carBrand);
			response = Response.status(Status.OK).entity(returnValue).build();
		} catch (Exception e) {
			String errorMessage = AppConfig.PROPERTIES.getProperty(e.getMessage());
			
			CarDetails carDetails1 = new CarDetails();
			carDetails1.setMessage(errorMessage);
			
			returnValue = JSONParser.toJson(carDetails1);
			response = Response.status(Status.SERVICE_UNAVAILABLE)
					.entity(returnValue).build();
		}
		
		return response;
	}
	
	@Path("fetchcarmodellist")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchCarModelList(String dataReceived) throws Exception {
		//System.out.println("In API: fetchCarModelList function");
		Response response=null;
		try{
		CarDetails car1=JSONParser.fromJson(dataReceived, CarDetails.class);
		String carPurchaseCity=car1.getPurchaseCity();
		String carBrand=car1.getCarBrand();
		//System.out.println(carPurchaseCity);
		//System.out.println(carBrand);
		
		List<String> as=Factory.createUserService().getListOfModel(carPurchaseCity,carBrand);
		
//		CarDetails car2=new CarDetails();
//		car2.setMessage(as);
		String res=JSONParser.toJson(as);
		response=Response.status(Status.OK).entity(res).build();
		}
		 catch (Exception e) {
			//e.printStackTrace();
		}
		
		return response;
	}
	
	@Path("fetchCarPrice")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchCarPrice(String dataReceived) throws Exception {
		//System.out.println("In API: fetchCarPrice function");
		Response response=null;
		try{
		CarDetails car1=JSONParser.fromJson(dataReceived, CarDetails.class);
		String carPurchaseCity=car1.getPurchaseCity();
		String carBrand=car1.getCarBrand();
		String carModel=car1.getCarModel();
		//System.out.println(carPurchaseCity);
		//System.out.println(carBrand);
		//System.out.println(carModel);
		
		List<Float> as=Factory.createUserService().getCarPrice(carPurchaseCity,carBrand,carModel);
		
//		CarDetails car2=new CarDetails();
//		car2.setMessage(as);
		String res=JSONParser.toJson(as);
		response=Response.status(Status.OK).entity(res).build();
		}
		 catch (Exception e) {
			//e.printStackTrace();
		}
		
		return response;
	}
	
	@Path("postCarDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postCarDetails(String dataRecieved) throws Exception{
		Response res=null;
		try{
			//System.out.println("in api postCarDetails");
    		CarDetailsStore car1=JSONParser.fromJson(dataRecieved, CarDetailsStore.class);
    		/*System.out.println(car1.getUserName());
    		System.out.println(car1.getCarBrand());
    		System.out.println(car1.getCarModel());
    		System.out.println(car1.getPurchaseCity());
    		System.out.println(car1.getCarPrice());
    		System.out.println(car1.getDateOfPurchase());
    		System.out.println(car1.getIdv());*/
    	UserService service1 = Factory.createUserService();
    	CarDetailsStore car2 = service1.postCarDetails(car1);
    	
    	String result=JSONParser.toJson(car2);
    	res=Response.status(Status.OK).entity(result).build();
    	}
    	catch(Exception e)
    	{
    		
    		String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
    		//System.out.println(errorMessage);
    		CarDetailsStore car3=new CarDetailsStore();
        	car3.setMessage(errorMessage);
        	String result=JSONParser.toJson(car3) ;
        	res=Response.status(Status.SERVICE_UNAVAILABLE).entity(result).build();
    	}
    	
		return res;
	}
	
	@Path("getCarDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCarDetails(String dataRecieved) throws Exception{
		Response res=null;
		try{
			//System.out.println("in api getCarDetails");
    		CarDetailsStore car1=JSONParser.fromJson(dataRecieved, CarDetailsStore.class);
    		/*System.out.println(car1.getUserName());
    		System.out.println(car1.getCarBrand());
    		System.out.println(car1.getCarModel());
    		System.out.println(car1.getPurchaseCity());
    		System.out.println(car1.getCarPrice());
    		System.out.println(car1.getDateOfPurchase());
    		System.out.println(car1.getIdv());*/
    	UserService service1 = Factory.createUserService();
    	List<String> car2 = service1.getCarDetails(car1.getRegId());
    	//System.out.println("in API: list to be returned"+car2);
    	String result=JSONParser.toJson(car2);
    	//System.out.println("In API: After JSON "+result);
    	res=Response.status(Status.OK).entity(result).build();
    	}
    	catch(Exception e)
    	{
    		
    		String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
    		//System.out.println(errorMessage);
    		CarDetailsStore car3=new CarDetailsStore();
        	car3.setMessage(errorMessage);
        	String result=JSONParser.toJson(car3) ;
        	res=Response.status(Status.SERVICE_UNAVAILABLE).entity(result).build();
    	}
    	
		return res;
	}
	
	@Path("addPremium")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPremium(String dataRecieved) throws Exception{
		Response res=null;
		try{
			//System.out.println("in api: addPremium");
    		CarDetailsStore car1=JSONParser.fromJson(dataRecieved, CarDetailsStore.class);
    		/*System.out.println(car1.getUserName());
    		System.out.println(car1.getCarBrand());
    		System.out.println(car1.getCarModel());
    		System.out.println(car1.getPurchaseCity());
    		System.out.println(car1.getCarPrice());
    		System.out.println(car1.getDateOfPurchase());
    		System.out.println(car1.getIdv());
    		System.out.println(car1.getFinalPremium());
    		System.out.println(car1.getPolicyNo());
    		System.out.println("Policy Date: "+car1.getPolicy_date());
    		System.out.println("Policy Expiry Date: "+car1.getExpiry_date());*/
    	UserService service1 = Factory.createUserService();
    	Integer policyNo = service1.addPremium(car1);
    	//System.out.println("in API: policyNo returned"+policyNo);
    	car1.setPolicyNo(policyNo);
    	String result=JSONParser.toJson(car1);
    	//System.out.println("In API: After JSON "+result);
    	res=Response.status(Status.OK).entity(result).build();
    	}
    	catch(Exception e)
    	{
    		
    		String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
    		//System.out.println(errorMessage);
    		CarDetailsStore car3=new CarDetailsStore();
        	car3.setMessage(errorMessage);
        	String result=JSONParser.toJson(car3) ;
        	res=Response.status(Status.SERVICE_UNAVAILABLE).entity(result).build();
    	}
    	
		return res;
	}
	
	@POST
	@Path("verifyPolicyNo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyPolicyNo(String dataReceived){
		//System.out.println("inside API: verify car policy number function");
		Response response = null;
		try{
			CarDetailsStore car1 = JSONParser.fromJson(dataReceived, CarDetailsStore.class);
			String userName = car1.getUserName();
			Integer policyNo = car1.getPolicyNo();
			
			//System.out.println("userName"+userName);
			//System.out.println("policyNo"+policyNo);
			String as = Factory.createUserService().verifyPolicyNo(userName, policyNo);
			CarDetailsStore car2 = new CarDetailsStore();
			car2.setMessage(as);
			//System.out.println("message: "+as);
			String res = JSONParser.toJson(car2);
			//System.out.println(res);
			response = Response.status(Status.OK).entity(res).build();
		}catch(Exception e){
			String errorMessage = AppConfig.PROPERTIES.getProperty("DAO.TECHNICAL_ERROR");
			response = Response.status(Status.SERVICE_UNAVAILABLE).entity(errorMessage).build();
		}
		return response;
	}
	
	@Path("RenewCar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response renewCar(String dataRecieved) throws Exception{
		Response res=null;
		try{
			//System.out.println("in api renewCar");
    		CarDetailsStore car1=JSONParser.fromJson(dataRecieved, CarDetailsStore.class);
    		
    	UserService service1 = Factory.createUserService();
    	List<String> car2 = service1.renewCar(car1.getPolicyNo());
    	//System.out.println("in API: list to be returned"+car2);
    	String result=JSONParser.toJson(car2);
    	//System.out.println("In API: After JSON "+result);
    	res=Response.status(Status.OK).entity(result).build();
    	}
    	catch(Exception e)
    	{
    		
    		String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
    		//System.out.println(errorMessage);
    		CarDetailsStore car3=new CarDetailsStore();
        	car3.setMessage(errorMessage);
        	String result=JSONParser.toJson(car3) ;
        	res=Response.status(Status.SERVICE_UNAVAILABLE).entity(result).build();
    	}
    	
		return res;
	}
	
	@Path("getProfileDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProfileDetails(String dataRecieved) throws Exception{
		Response res=null;
		try{
			//System.out.println("in api getProfileDetails");
    		CarDetailsStore user1=JSONParser.fromJson(dataRecieved, CarDetailsStore.class);
    		/*System.out.println(user1.getUserName());
    		System.out.println(user1.getCarBrand());
    		System.out.println(user1.getCarModel());
    		System.out.println(user1.getPurchaseCity());
    		System.out.println(user1.getCarPrice());
    		System.out.println(user1.getDateOfPurchase());
    		System.out.println(user1.getIdv());
    		System.out.println(user1.getExpiry_date());
    		System.out.println(user1.getFinalPremium());
    		System.out.println(user1.getPolicyNo());
            System.out.println(user1.getPolicy_date());*/
    		UserService service1 = Factory.createUserService();
    	List<String> user2 = service1.getProfileDetails(user1.getUserName());
    	
    	//System.out.println("in API: list to be returned"+user2);
    	String result=JSONParser.toJson(user2);
    	//System.out.println("In API: After JSON "+result);
    	res=Response.status(Status.OK).entity(result).build();
    	}
    	catch(Exception e)
    	{
    		
    		String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
    		//System.out.println(errorMessage);
    		CarDetailsStore car3=new CarDetailsStore();
        	car3.setMessage(errorMessage);
        	String result=JSONParser.toJson(car3) ;
        	res=Response.status(Status.SERVICE_UNAVAILABLE).entity(result).build();
    	}
    	
		return res;
	}
	
/*---------------------------------------------------------Bike Insurance------------------------------------------------------------*/	
	@Path("fetchbikepurchasecitylist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response fetchBikePurchaseCityList() throws Exception {
		//System.out.println("In API: fetchbikepurchasecitylist function");
		String returnValue = null;
		Response response = null;
		try {
			UserService userService = Factory.createUserService();
			List<String> cities = userService.getListOfBikeCity();

			returnValue = JSONParser.toJson(cities);
			response = Response.status(Status.OK).entity(returnValue).build();
		} catch (Exception e) {
			String errorMessage = AppConfig.PROPERTIES.getProperty(e.getMessage());
			BikeDetails bikeDetails=new BikeDetails();
			bikeDetails.setMessage(errorMessage);
			
			returnValue = JSONParser.toJson(bikeDetails);
			response = Response.status(Status.SERVICE_UNAVAILABLE)
					.entity(returnValue).build();
		}
		
		return response;
	}
	
	@Path("fetchbikeBrandlist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchBikeBrandList() throws Exception {
		//System.out.println("In API: fetchBikeBrandList function");
		String returnValue = null;
		Response response = null;
		try {
			UserService userService = Factory.createUserService();
			List<String> bikeBrand = userService.getListOfBikebrand();

			returnValue = JSONParser.toJson(bikeBrand);
			response = Response.status(Status.OK).entity(returnValue).build();
		} catch (Exception e) {
			String errorMessage = AppConfig.PROPERTIES.getProperty(e.getMessage());
			BikeDetails bikeDetails1=new BikeDetails();
			
			bikeDetails1.setMessage(errorMessage);
			
			returnValue = JSONParser.toJson(bikeDetails1);
			response = Response.status(Status.SERVICE_UNAVAILABLE)
					.entity(returnValue).build();
		}
		
		return response;
	}
	
	@Path("fetchbikeModellist")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchBikeModelList(String dataReceived) throws Exception {
		//System.out.println("In API: fetchBikeModelList function");
		Response response=null;
		try{
		BikeDetails bike1=JSONParser.fromJson(dataReceived, BikeDetails.class);
		String bikePurchaseCity=bike1.getPurchaseCity();
		String bikeBrand=bike1.getBikeBrand();
		//System.out.println(bikePurchaseCity);
		//System.out.println(bikeBrand);
		
		List<String> as=Factory.createUserService().getListOfBikeModel(bikePurchaseCity, bikeBrand);
		String res=JSONParser.toJson(as);
		response=Response.status(Status.OK).entity(res).build();
		}
		 catch (Exception e) {
			//e.printStackTrace();
		}
		
		return response;
	}
	
	@Path("fetchbikePrice")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchBikePrice(String dataReceived) throws Exception {
		//System.out.println("In API: fetchBikePrice function");
		Response response=null;
		try{
		BikeDetails bike1=JSONParser.fromJson(dataReceived, BikeDetails.class);
		String bikePurchaseCity=bike1.getPurchaseCity();
		String bikeBrand=bike1.getBikeBrand();
		String bikeModel=bike1.getBikeModel();
		/*System.out.println(bikePurchaseCity);
		System.out.println(bikeBrand);
		System.out.println(bikeModel);*/
		
		List<Float> as=Factory.createUserService().getBikePrice(bikePurchaseCity, bikeBrand, bikeModel);
		
//		CarDetails car2=new CarDetails();
//		car2.setMessage(as);
		String res=JSONParser.toJson(as);
		response=Response.status(Status.OK).entity(res).build();
		}
		 catch (Exception e) {
			//e.printStackTrace();
		}
		
		return response;
	}
	
	@Path("postBikeDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postBikeDetails(String dataRecieved) throws Exception{
		Response res=null;
		try{
			//System.out.println("in api postBikeDetails");
    		BikeDetailsStore bike1=JSONParser.fromJson(dataRecieved, BikeDetailsStore.class);
    		/*System.out.println(bike1.getUserName());
    		System.out.println(bike1.getBikeBrand());
    		System.out.println(bike1.getBikeModel());
    		System.out.println(bike1.getPurchaseCity());
    		System.out.println(bike1.getBikePrice());
    		System.out.println(bike1.getDateOfPurchase());
    		System.out.println(bike1.getIdv());*/
    	UserService service1 = Factory.createUserService();
    	BikeDetailsStore bike2 = service1.postBikeDetails(bike1);
    	
    	String result=JSONParser.toJson(bike2);
    	res=Response.status(Status.OK).entity(result).build();
    	}
    	catch(Exception e)
    	{
    		
    		String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
    		//System.out.println(errorMessage);
    		CarDetailsStore car3=new CarDetailsStore();
        	car3.setMessage(errorMessage);
        	String result=JSONParser.toJson(car3) ;
        	res=Response.status(Status.SERVICE_UNAVAILABLE).entity(result).build();
    	}
    	
		return res;
	}
	
	@Path("getBikeDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getBikeDetails(String dataRecieved) throws Exception{
		Response res=null;
		try{
			//System.out.println("in api getBikeDetails");
    		BikeDetailsStore bike1=JSONParser.fromJson(dataRecieved, BikeDetailsStore.class);
    		/*System.out.println(bike1.getUserName());
    		System.out.println(bike1.getBikeBrand());
    		System.out.println(bike1.getBikeModel());
    		System.out.println(bike1.getPurchaseCity());
    		System.out.println(bike1.getBikePrice());
    		System.out.println(bike1.getDateOfPurchase());
    		System.out.println(bike1.getIdv());*/
    	UserService service1 = Factory.createUserService();
    	List<String> bike2 = service1.getBikeDetails(bike1.getRegId());
    	//System.out.println("in API: list to be returned"+bike2);
    	String result=JSONParser.toJson(bike2);
    	//System.out.println("In API: After JSON "+result);
    	res=Response.status(Status.OK).entity(result).build();
    	}
    	catch(Exception e)
    	{
    		
    		String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
    		//System.out.println(errorMessage);
    		CarDetailsStore car3=new CarDetailsStore();
        	car3.setMessage(errorMessage);
        	String result=JSONParser.toJson(car3) ;
        	res=Response.status(Status.SERVICE_UNAVAILABLE).entity(result).build();
    	}
    	
		return res;
	}
	
	@Path("addBikePremium")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBikePremium(String dataRecieved) throws Exception{
		Response res=null;
		try{
			//System.out.println("in api: addBikePremium");
    		BikeDetailsStore bike1=JSONParser.fromJson(dataRecieved, BikeDetailsStore.class);
    		/*System.out.println(bike1.getUserName());
    		System.out.println(bike1.getBikeBrand());
    		System.out.println(bike1.getBikeModel());
    		System.out.println(bike1.getPurchaseCity());
    		System.out.println(bike1.getBikePrice());
    		System.out.println(bike1.getDateOfPurchase());
    		System.out.println(bike1.getIdv());
    		System.out.println(bike1.getFinalPremium());
    		System.out.println(bike1.getPolicyNo());
    		System.out.println("Policy Date: "+bike1.getPolicy_date());
    		System.out.println("Policy Expiry Date: "+bike1.getExpiry_date());*/
    	UserService service1 = Factory.createUserService();
    	Integer policyNo = service1.addBikePremium(bike1);
    	//System.out.println("in API: policyNo returned"+policyNo);
    	bike1.setPolicyNo(policyNo);
    	String result=JSONParser.toJson(bike1);
    	//System.out.println("In API: After JSON "+result);
    	res=Response.status(Status.OK).entity(result).build();
    	}
    	catch(Exception e)
    	{
    		
    		String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
    		//System.out.println(errorMessage);
    		BikeDetailsStore bike3=new BikeDetailsStore();
        	bike3.setMessage(errorMessage);
        	String result=JSONParser.toJson(bike3) ;
        	res=Response.status(Status.SERVICE_UNAVAILABLE).entity(result).build();
    	}
    	
		return res;
	}
	
	@POST
	@Path("verifyBikePolicyNo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyBikePolicyNo(String dataReceived){
		//System.out.println("inside API: verify bike policy number function");
		Response response = null;
		try{
			BikeDetailsStore bike1 = JSONParser.fromJson(dataReceived, BikeDetailsStore.class);
			String userName = bike1.getUserName();
			Integer policyNo = bike1.getPolicyNo();
			
			//System.out.println("userName"+userName);
			//System.out.println("policyNo"+policyNo);
			String as = Factory.createUserService().verifyBikePolicyNo(userName, policyNo);
			BikeDetailsStore bike2 = new BikeDetailsStore();
			bike2.setMessage(as);
			//System.out.println("message: "+as);
			String res = JSONParser.toJson(bike2);
			//System.out.println(res);
			response = Response.status(Status.OK).entity(res).build();
		}catch(Exception e){
			String errorMessage = AppConfig.PROPERTIES.getProperty("DAO.TECHNICAL_ERROR");
			response = Response.status(Status.SERVICE_UNAVAILABLE).entity(errorMessage).build();
		}
		return response;
	}
	
	@Path("RenewBike")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response renewBike(String dataRecieved) throws Exception{
		Response res=null;
		try{
    		BikeDetailsStore bike1=JSONParser.fromJson(dataRecieved, BikeDetailsStore.class);
    		
    	UserService service1 = Factory.createUserService();
    	List<String> bike2 = service1.renewBike(bike1.getPolicyNo());
    	//System.out.println("in API: list to be returned"+bike2);
    	String result=JSONParser.toJson(bike2);
    	//System.out.println("In API: After JSON "+result);
    	res=Response.status(Status.OK).entity(result).build();
    	}
    	catch(Exception e)
    	{
    		
    		String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
    		//System.out.println(errorMessage);
    		BikeDetailsStore bike3=new BikeDetailsStore();
        	bike3.setMessage(errorMessage);
        	String result=JSONParser.toJson(bike3) ;
        	res=Response.status(Status.SERVICE_UNAVAILABLE).entity(result).build();
    	}
    	
		return res;
	}
	
	@Path("updateclaimstatus")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateClaimStatus(String dataReceived) throws Exception{
		Response response = null;
		try{
			CarDetailsStore carDetailsStore = JSONParser.fromJson(dataReceived, CarDetailsStore.class);
			CarDetailsStore detailsStore = Factory.createUserService().updateClaimStatus(carDetailsStore);
			String result = JSONParser.toJson(detailsStore);
			response = Response.status(Status.OK).entity(result).build();
		}catch(Exception exception){
			
		}
		return response;
	}
	
}