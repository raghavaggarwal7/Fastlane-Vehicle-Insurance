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

import com.fastlane.bean.Admin;
import com.fastlane.bean.CarDetailsStore;
import com.fastlane.resources.AppConfig;
import com.fastlane.resources.Factory;
import com.fastlane.resources.JSONParser;

@Path("AdminInsurance")
public class AdminInsuranceAPI {

	@POST
	@Path("verifyadmincredentials")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyAdminCredentials(String dataReceived){
		//System.out.println("inside API: verify admin credentials function");
		Response response = null;
		try{
			Admin admin = JSONParser.fromJson(dataReceived,Admin.class);
			String userName = admin.getUserName();
			String password = admin.getPassword();

			//System.out.println(userName);
			//System.out.println(password);
			String as = Factory.createAdminService().verifyAdmin(userName, password);
			Admin admin1=new Admin();
			admin1.setMessage(as);
			//System.out.println("as: "+as);
			String res = JSONParser.toJson(admin1);
			//System.out.println(res);
			response = Response.status(Status.OK).entity(res).build();
		}catch(Exception e){
			String errorMessage = AppConfig.PROPERTIES.getProperty("DAO.TECHNICAL_ERROR");
			response = Response.status(Status.SERVICE_UNAVAILABLE).entity(errorMessage).build();
		}
		return response;
	}


	@GET
	@Path("getpolicylist")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPolicyList(){
		//System.out.println("inside API: verify admin credentials function");
		Response response = null;
		try{
			List<String> user2 = Factory.createAdminDAO().getAllPolicies();
			String res = JSONParser.toJson(user2);
			//System.out.println(res);
			response = Response.status(Status.OK).entity(res).build();
		}catch(Exception e){
			String errorMessage = AppConfig.PROPERTIES.getProperty("DAO.TECHNICAL_ERROR");
			response = Response.status(Status.SERVICE_UNAVAILABLE).entity(errorMessage).build();
		}
		return response;
	}
	
	@Path("updateclaimstatus")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateClaimStatus(String dataReceived) throws Exception{
		Response response = null;
		try{
			CarDetailsStore carDetailsStore = JSONParser.fromJson(dataReceived, CarDetailsStore.class);
			CarDetailsStore detailsStore = Factory.createAdminDAO().updateClaimStatus(carDetailsStore);
			String result = JSONParser.toJson(detailsStore);
			response = Response.status(Status.OK).entity(result).build();
		}catch(Exception exception){
			
		}
		return response;
	}
	
	@Path("AdminGetAllUsers")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response AdminGetAllUsers(){
		Response response = null;
		String res = null;
		try{
			/*System.out.println("in api: getUserName");*/
			List<String> userList = Factory.createUserDAO().AdminGetAllUsers();
			//System.out.println(userNames);
			res = JSONParser.toJson(userList);
			
			response = Response.status(Status.OK).entity(res).build();
		}catch(Exception e){
			//e.printStackTrace();
		}
		return response;
	}

}