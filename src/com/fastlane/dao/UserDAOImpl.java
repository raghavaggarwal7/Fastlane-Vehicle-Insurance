package com.fastlane.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import com.fastlane.bean.*;
import com.fastlane.entity.BikeDetailsStoreEntity;
import com.fastlane.entity.CarDetailsStoreEntity;
import com.fastlane.entity.UserEntity;
import com.fastlane.resources.*;

public class UserDAOImpl implements UserDAO{

	public User signup(User user) throws Exception{
		//System.out.println("In DAO: signup function");
		SessionFactory factory=HibernateUtility.createSessionFactory();
		Session session=factory.openSession();

		User user1=null;
		try{
			//System.out.println("in dao try");
			UserEntity entity=new UserEntity();
			entity.setName(user.getName());
			entity.setUserName(user.getUserName());
			entity.setDateOfBirth(user.getDateOfBirth());
			entity.setEmailId(user.getEmailId());
			entity.setPassword(user.getPassword());
			entity.setMobileNo(user.getMobileNo());
			//entity.setDateOfBirth(user.getDateOfBirth());
			/*System.out.println(entity.getName());
		System.out.println(entity.getUserName());
		System.out.println(entity.getEmailId());
		System.out.println(entity.getPassword());
		System.out.println(entity.getMobileNo());*/
			//System.out.println(entity.getDateOfBirth());
			session.getTransaction().begin();
			int id = (Integer) session.save(entity);
			session.getTransaction().commit();
			user1 = new User();
			user1.setUserId(id);
			/*System.out.println("userid"+entity.getUserId());
		System.out.println("done in try");*/
		}
		catch(HibernateException exception)
		{
			//exception.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); */
			//throw new Exception("DAO.TECHNICAL_ERROR");
		}
		catch(Exception exception)
		{
			//exception.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);*/ 
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally
		{
			if(session.isOpen() || session!=null)
			{
				session.close();
			}
		}
		//System.out.println("done");
		return user1;		
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllUsers() throws Exception {
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		List<String> userNames = new ArrayList<String>();
		try{
			sessionFactory= HibernateUtility.createSessionFactory();
			session= sessionFactory.openSession();
			String hql="select u.userName from UserEntity u";
			Query query=  session.createQuery(hql);
			userNames = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			if(session.isOpen() || session!=null)
			{
				session.close();
			}
		}
		System.out.println(userNames);
		return userNames;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String verifyUser(String userName, String password) throws Exception {
		//System.out.println("inside DAO: verifyUser function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		String hql = "select UE.password from UserEntity UE where UE.userName='"+userName+"'";
		List<String> passwords = new ArrayList<String>();
		String result = "User not found";
		try {
			session = sessionFactory.openSession();

			// Creating the query object
			Query query = session.createQuery(hql);

			// Executing the query
			passwords = query.list();
			System.out.println("list: " + passwords.size() + passwords);
			if(passwords.size() == 0)
				throw new Exception("DAO.NO_USER_EXCEPTION");
			if(passwords.size() > 0 && passwords.get(0).equals(password))
				result = "User verified";
			else if(passwords.size() > 0)
				throw new Exception("DAO.PWD_MISMATCH");


		}catch (HibernateException exception) {
			exception.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); */
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		catch (Exception exception) {
			exception.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);*/
			throw exception;
		}
		finally {
			if(session.isOpen()|| session!=null){
				session.close();
			}


		}
		return result;
	}

	/*--------------------------------------------------Car Insurance--------------------------------------------------------*/
	public List<String> getListOfCity() throws Exception {
		//System.out.println("In DAO: getListOfCity function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = sessionFactory.openSession();
		List<String> listOfCities = null;
		try {
			listOfCities = new ArrayList<String>();
			//System.out.println("backkkk");
			Query query = session
					.createQuery("Select distinct(purchaseCity) from CarDetailsEntity");
			//System.out.println("hiiisdbsk");
			List<Object> results = query.list();
			for (Object object : results) {
				listOfCities.add((String) object);
			}
			//System.out.println(listOfCities);
		} catch (Exception exception) {
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return listOfCities;
	}

	public List<String> getListOfbrand() throws Exception {
		//System.out.println("In DAO: getListOfbrand() function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = sessionFactory.openSession();
		List<String> listOfBrand = null;
		try {
			listOfBrand = new ArrayList<String>();
			Query query = session
					.createQuery("Select distinct(carBrand) from CarDetailsEntity");
			List<Object> results = query.list();
			for (Object object : results) {
				listOfBrand.add((String) object);
			}
			//System.out.println(listOfBrand);
		} catch (Exception exception) {
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return listOfBrand;
	}


	public List<String> getListOfModel(String purchaseCity, String carBrand) throws Exception {
		//System.out.println("In DAO: getListOfModel() function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = sessionFactory.openSession();
		List<String> listOfModel = null;
		try {
			listOfModel = new ArrayList<String>();
			Query query = session
					.createQuery("Select distinct(cde.carModel) from CarDetailsEntity cde where cde.purchaseCity='"+purchaseCity+"' and cde.carBrand='"+carBrand+"'");
			List<Object> results = query.list();
			for (Object object : results) {
				listOfModel.add((String) object);
			}
			//System.out.println(listOfModel);
		} catch (Exception exception) {
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return listOfModel;
	}

	public List<Float> getCarPrice(String purchaseCity, String carBrand,String carModel) throws Exception{
		//System.out.println("In DAO: getCarPrice() function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = sessionFactory.openSession();
		List<Float> listPrice = null;
		try {
			listPrice = new ArrayList<Float>();
			Query query = session
					.createQuery("Select cde.carPrice from CarDetailsEntity cde where cde.purchaseCity='"+purchaseCity+"' and cde.carBrand='"+carBrand+"' and cde.carModel='"+carModel+"'");
			List<Object> results = query.list();
			for (Object object : results) {
				listPrice.add((Float) object);
			}
			//System.out.println(listPrice);
		} catch (Exception exception) {
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return listPrice;
	}

	public CarDetailsStore postCarDetails(CarDetailsStore car) throws Exception{
		//System.out.println("In DAO: postCarDetails function");
		SessionFactory factory=HibernateUtility.createSessionFactory();
		Session session=factory.openSession();

		CarDetailsStore car1=null;
		try{
			//System.out.println("in dao try");
			CarDetailsStoreEntity entity=new CarDetailsStoreEntity();
			entity.setCarBrand(car.getCarBrand());
			entity.setUserName(car.getUserName());
			entity.setCarModel(car.getCarModel());
			entity.setCarPrice(car.getCarPrice());
			entity.setDateOfPurchase(car.getDateOfPurchase());
			entity.setIdv(car.getIdv());
			entity.setClaimStatus("CLAIM NOW");
			entity.setPurchaseCity(car.getPurchaseCity());
			/*System.out.println(entity.getCarBrand());
		System.out.println(entity.getUserName());
		System.out.println(entity.getCarModel());
		System.out.println(entity.getPurchaseCity());
		System.out.println(entity.getCarPrice());
		System.out.println(entity.getDateOfPurchase());
		System.out.println(entity.getIdv());*/
			session.getTransaction().begin();
			int id = (Integer) session.save(entity);
			session.getTransaction().commit();
			car1 = new CarDetailsStore();
			car1.setRegId(id);
			/*System.out.println("regid"+entity.getRegId());
		System.out.println("done in try");*/
		}
		catch(HibernateException exception)
		{
			//exception.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); */
			//throw new Exception("DAO.TECHNICAL_ERROR");
		}
		catch(Exception exception)
		{
			//exception.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);*/ 
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally
		{
			if(session.isOpen() || session!=null)
			{
				session.close();
			}
		}
		//System.out.println("done");
		return car1;		

	}

	@SuppressWarnings("unchecked")
	public List<String> getCarDetails(Integer regId) throws Exception {
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		List<String> car=new ArrayList<String>();
		//CarDetailsStore car = new CarDetailsStore();
		try{
			sessionFactory= HibernateUtility.createSessionFactory();
			session= sessionFactory.openSession();
			String hql="from CarDetailsStoreEntity u where u.regId='"+regId+"'";
			Query query=  session.createQuery(hql);
			car=query.list();

		}catch(Exception e){
			//e.printStackTrace();
		}finally
		{
			if(session.isOpen() || session!=null)
			{
				session.close();
			}
		}
		return car;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer addPremium(CarDetailsStore car) throws Exception {
		//System.out.println(car.getPolicyType());
		//System.out.println(car.getRegId());
		//System.out.println(car.getExpiry_date().getTime());
		//System.out.println(car.getFinalPremium());
		//System.out.println("inside DAO: add Premium function");
		SessionFactory sessionFactory= null;
		Session session=null;
		//System.out.println("inside DAO: add Premium function,reg id is: "+car.getRegId());
		Integer policyNo1=car.getRegId()+1001;

		String hql="Update CarDetailsStoreEntity CDSE set CDSE.policyType = :policyType, CDSE.finalPremium=:finalPremium,CDSE.policyNo=:policyNo,CDSE.policy_date=:policy_date,CDSE.expiry_date=:expiry_date where CDSE.regId=:regId";

		try{
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			/*
		Integer policyNo=car.getRegId()+1001;
		Float finalPremium=car.getFinalPremium();
		String userName=car.getUserName();
		String hql = "from CarDetailsStoreEntity UE where UE.userName='"+car.getUserName()+"'";*/
			/*String hql1="from CarDetailsStoreEntity AE where AE.userName='"+car.getUserName()+"'";*/

			Query query=  session.createQuery(hql);
			query.setParameter("policyNo", car.getRegId()+1001);             //setting parameters
			query.setParameter("finalPremium", car.getFinalPremium());
			query.setParameter("regId", car.getRegId());
			query.setParameter("policyType", car.getPolicyType());
			query.setParameter("policy_date", car.getPolicy_date());
			query.setParameter("expiry_date", car.getExpiry_date());


			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();

		}catch (HibernateException exception) {
			//exception.printStackTrace();
			DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); 
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		catch (Exception exception) {
			//exception.printStackTrace();
			DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		finally {
			if(session.isOpen()|| session!=null){
				session.close();
			}


		}
		return policyNo1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String verifyPolicyNo(String userName, Integer policyNo) throws Exception {
		//System.out.println("inside DAO: verifyPolicyNo function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		String hql = "select CE.policyNo from CarDetailsStoreEntity CE where CE.userName='"+userName+"' and CE.policyNo != NULL";
		List<Integer> policyNoList = new ArrayList<Integer>();
		String result = "Invalid Policy Number! Please enter a valid Policy Number.";
		try {
			session = sessionFactory.openSession();

			// Creating the query object
			Query query = session.createQuery(hql);

			// Executing the query
			policyNoList = query.list();
			//System.out.println("list: " + policyNoList.size() + policyNoList);
			if(policyNoList.size() > 0){
				for(int i : policyNoList){
					if(i == policyNo){
						result = "Policy Number Verified";
						break;
					}
				}
			}




		}catch (HibernateException exception) {
			//exception.printStackTrace();
			DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); 
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		catch (Exception exception) {
			//exception.printStackTrace();
			DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		finally {
			if(session.isOpen()|| session!=null){
				session.close();
			}


		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<String> renewCar(Integer policyNo) throws Exception {
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		List<String> car=new ArrayList<String>();
		//CarDetailsStore car = new CarDetailsStore();
		try{
			sessionFactory= HibernateUtility.createSessionFactory();
			session= sessionFactory.openSession();
			String hql="from CarDetailsStoreEntity u where u.policyNo='"+policyNo+"'";
			Query query=  session.createQuery(hql);
			car=query.list();

		}catch(Exception e){
			//e.printStackTrace();
		}finally
		{
			if(session.isOpen() || session!=null)
			{
				session.close();
			}
		}
		return car;
	}

	@SuppressWarnings("unchecked")
	public List<String> getProfileDetails(String userName)  throws Exception {
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		List<String> car=new ArrayList<String>();
		List<String> bike=new ArrayList<String>();
		//CarDetailsStore car = new CarDetailsStore();
		try{
			sessionFactory= HibernateUtility.createSessionFactory();
			session= sessionFactory.openSession();
			String hql="from CarDetailsStoreEntity u where u.userName='"+userName+"' and u.policyNo != NULL";
			String hql1="from BikeDetailsStoreEntity x where x.userName='"+userName+"' and x.policyNo != NULL";
			Query query=  session.createQuery(hql);
			Query query1 = session.createQuery(hql1);
			car=query.list();
			bike=query1.list();
			car.addAll(bike);

		}catch(Exception e){
			//e.printStackTrace();
		}finally
		{
			if(session.isOpen() || session!=null)
			{
				session.close();
			}
		}
		return car;
	}

	/*--------------------------------------------------Bike Insurance--------------------------------------------------------*/
	public List<String> getListOfBikeCity() throws Exception {
		//System.out.println("In DAO: getListOfBikeCity function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = sessionFactory.openSession();
		List<String> listOfCities = null;
		try {
			listOfCities = new ArrayList<String>();
			//System.out.println("bike backkkk");
			Query query = session
					.createQuery("Select distinct(purchaseCity) from BikeDetailsEntity");
			//System.out.println("hi bike");
			List<Object> results = query.list();
			for (Object object : results) {
				listOfCities.add((String) object);
			}
			//System.out.println(listOfCities);
		} catch (Exception exception) {
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return listOfCities;
	}

	public List<String> getListOfBikebrand() throws Exception {
		//System.out.println("In DAO: getListOfCitybrand() function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = sessionFactory.openSession();
		List<String> listOfBrand = null;
		try {
			listOfBrand = new ArrayList<String>();
			Query query = session
					.createQuery("Select distinct(bikeBrand) from BikeDetailsEntity");
			List<Object> results = query.list();
			for (Object object : results) {
				listOfBrand.add((String) object);
			}
			//System.out.println(listOfBrand);
		} catch (Exception exception) {
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return listOfBrand;
	}


	public List<String> getListOfBikeModel(String purchaseCity, String bikeBrand) throws Exception {
		//System.out.println("In DAO: getListOfBikeModel() function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = sessionFactory.openSession();
		List<String> listOfModel = null;
		try {
			listOfModel = new ArrayList<String>();
			Query query = session
					.createQuery("Select distinct(bde.bikeModel) from BikeDetailsEntity bde where bde.purchaseCity='"+purchaseCity+"' and bde.bikeBrand='"+bikeBrand+"'");
			List<Object> results = query.list();
			for (Object object : results) {
				listOfModel.add((String) object);
			}
			//System.out.println(listOfModel);
		} catch (Exception exception) {
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return listOfModel;
	}

	public List<Float> getBikePrice(String purchaseCity, String bikeBrand,String bikeModel) throws Exception{
		//System.out.println("In DAO: getBikePrice() function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = sessionFactory.openSession();
		List<Float> listPrice = null;
		try {
			listPrice = new ArrayList<Float>();
			Query query = session
					.createQuery("Select bde.bikePrice from BikeDetailsEntity bde where bde.purchaseCity='"+purchaseCity+"' and bde.bikeBrand='"+bikeBrand+"' and bde.bikeModel='"+bikeModel+"'");
			List<Object> results = query.list();
			for (Object object : results) {
				listPrice.add((Float) object);
			}
			//System.out.println(listPrice);
		} catch (Exception exception) {
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return listPrice;
	}

	public BikeDetailsStore postBikeDetails(BikeDetailsStore bike) throws Exception{
		//System.out.println("In DAO: postBikeDetails function");
		SessionFactory factory=HibernateUtility.createSessionFactory();
		Session session=factory.openSession();

		BikeDetailsStore bike1=null;
		try{
			//System.out.println("in dao try");
			BikeDetailsStoreEntity entity=new BikeDetailsStoreEntity();
			entity.setBikeBrand(bike.getBikeBrand());
			entity.setUserName(bike.getUserName());
			entity.setBikeModel(bike.getBikeModel());
			entity.setBikePrice(bike.getBikePrice());
			entity.setDateOfPurchase(bike.getDateOfPurchase());
			entity.setIdv(bike.getIdv());
			entity.setPurchaseCity(bike.getPurchaseCity());
			entity.setClaimStatus("CLAIM NOW");
			/*System.out.println(entity.getBikeBrand());
		System.out.println(entity.getUserName());
		System.out.println(entity.getBikeModel());
		System.out.println(entity.getPurchaseCity());
		System.out.println(entity.getBikePrice());
		System.out.println(entity.getDateOfPurchase());
		System.out.println(entity.getIdv());*/
			session.getTransaction().begin();
			int id = (Integer) session.save(entity);
			session.getTransaction().commit();
			bike1 = new BikeDetailsStore();
			bike1.setRegId(id);
			/*System.out.println("regid"+entity.getRegId());
		System.out.println("done in try");*/
		}
		catch(HibernateException exception)
		{
			//exception.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); */
			//throw new Exception("DAO.TECHNICAL_ERROR");
		}
		catch(Exception exception)
		{
			//exception.printStackTrace();
			/*DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);*/ 
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally
		{
			if(session.isOpen() || session!=null)
			{
				session.close();
			}
		}
		//System.out.println("done");
		return bike1;		

	}

	@SuppressWarnings("unchecked")
	public List<String> getBikeDetails(Integer regId) throws Exception {
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		List<String> bike=new ArrayList<String>();
		try{
			sessionFactory= HibernateUtility.createSessionFactory();
			session= sessionFactory.openSession();
			String hql="from BikeDetailsStoreEntity u where u.regId='"+regId+"'";
			Query query=  session.createQuery(hql);
			bike=query.list();

		}catch(Exception e){
			//e.printStackTrace();
		}finally
		{
			if(session.isOpen() || session!=null)
			{
				session.close();
			}
		}
		return bike;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer addBikePremium(BikeDetailsStore bike) throws Exception {
		//System.out.println(bike.getPolicyType());
		//System.out.println("inside DAO: add bike Premium function");
		SessionFactory sessionFactory= null;
		Session session=null;
		//System.out.println("inside DAO: add bike Premium function,reg id is: "+bike.getRegId());
		Integer policyNo1=bike.getRegId()+1001;

		String hql="Update BikeDetailsStoreEntity BDSE set BDSE.policyType = :policyType, BDSE.finalPremium=:finalPremium,BDSE.policyNo=:policyNo,BDSE.policy_date=:policy_date,BDSE.expiry_date=:expiry_date where BDSE.regId=:regId";

		try{
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();

			Query query=  session.createQuery(hql);
			query.setParameter("policyNo", bike.getRegId()+1001);             //setting parameters
			query.setParameter("finalPremium", bike.getFinalPremium());
			query.setParameter("policyType", bike.getPolicyType());
			query.setParameter("regId", bike.getRegId());
			query.setParameter("policy_date", bike.getPolicy_date());
			query.setParameter("expiry_date", bike.getExpiry_date());


			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();

		}catch (HibernateException exception) {
			//exception.printStackTrace();
			DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); 
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		catch (Exception exception) {
			//exception.printStackTrace();
			DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		finally {
			if(session.isOpen()|| session!=null){
				session.close();
			}


		}
		return policyNo1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String verifyBikePolicyNo(String userName, Integer policyNo) throws Exception {
		//System.out.println("inside DAO: verifyBikePolicyNo function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		String hql = "select BE.policyNo from BikeDetailsStoreEntity BE where BE.userName='"+userName+"' and BE.policyNo != NULL";
		List<Integer> policyNoList = new ArrayList<Integer>();
		String result = "Invalid Policy Number! Please enter a valid Policy Number.";
		try {
			session = sessionFactory.openSession();

			// Creating the query object
			Query query = session.createQuery(hql);

			// Executing the query
			policyNoList = query.list();
			//System.out.println("list: " + policyNoList.size() + policyNoList);
			if(policyNoList.size() > 0){
				for(int i : policyNoList){
					if(i == policyNo){
						result = "Policy Number Verified";
						break;
					}
				}
			}

		}catch (HibernateException exception) {
			//exception.printStackTrace();
			DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); 
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		catch (Exception exception) {
			//exception.printStackTrace();
			DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		finally {
			if(session.isOpen()|| session!=null){
				session.close();
			}


		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<String> renewBike(Integer policyNo) throws Exception {
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		List<String> bike=new ArrayList<String>();
		//CarDetailsStore car = new CarDetailsStore();
		try{
			sessionFactory= HibernateUtility.createSessionFactory();
			session= sessionFactory.openSession();
			String hql="from BikeDetailsStoreEntity u where u.policyNo='"+policyNo+"'";
			Query query=  session.createQuery(hql);
			bike=query.list();

		}catch(Exception e){
			//e.printStackTrace();
		}finally
		{
			if(session.isOpen() || session!=null)
			{
				session.close();
			}
		}
		return bike;
	}

	@Override
	public CarDetailsStore updateClaimStatus(CarDetailsStore carDetailsStore)
			throws Exception {
		//System.out.println("policyNo: " + carDetailsStore.getPolicyNo());
		//System.out.println("inside DAO: updateClaimStatus");
		SessionFactory sessionFactory= null;
		Session session=null;
		//System.out.println("inside DAO: add bike Premium function,reg id is: "+bike.getRegId());
		Integer policyNo=carDetailsStore.getPolicyNo();
		String claimStatus = null;
		if(carDetailsStore.getClaimStatus().equals("CLAIM NOW")){
			claimStatus = "In processing";
			//System.out.println("in if");
		}else{
			//System.out.println("in else");
			claimStatus = carDetailsStore.getClaimStatus();
		}
		CarDetailsStore detailsStore = null;
		String hql="Update CarDetailsStoreEntity CDSE set CDSE.claimStatus=:claimStatus where CDSE.userName=:userName and CDSE.policyNo = :policyNo";
		String hql1="Update BikeDetailsStoreEntity BDSE set BDSE.claimStatus=:claimStatus where BDSE.userName=:userName and BDSE.policyNo = :policyNo";
		//System.out.println("policyNo: " + policyNo);
		//System.out.println("userName: " + carDetailsStore.getUserName());
		//System.out.println("claimStatus: " + claimStatus);
		
		try{
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();

			Query query=  session.createQuery(hql);
			query.setParameter("policyNo", policyNo);             //setting parameters
			query.setParameter("claimStatus", claimStatus);
			query.setParameter("userName", carDetailsStore.getUserName());
			detailsStore = new CarDetailsStore();
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
			detailsStore.setMessage("Your request has been received.");
			//System.out.println("committed");
			
			Query query1=  session.createQuery(hql1);
			query1.setParameter("policyNo", policyNo);             //setting parameters
			query1.setParameter("claimStatus", claimStatus);
			query1.setParameter("userName", carDetailsStore.getUserName());
			detailsStore = new CarDetailsStore();
			session.beginTransaction();
			query1.executeUpdate();
			session.getTransaction().commit();
			detailsStore.setMessage("Your request has been received.");
			//System.out.println("committed");

		}catch (HibernateException exception) {
			//exception.printStackTrace();
			DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); 
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		catch (Exception exception) {
			//exception.printStackTrace();
			DOMConfigurator.configure("src/com/fastlane/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		finally {
			if(session.isOpen()|| session!=null){
				session.close();
			}


		}
		return detailsStore;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<String> AdminGetAllUsers()  throws Exception {
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		List<String> car=new ArrayList<String>();
		List<String> bike=new ArrayList<String>();
		//CarDetailsStore car = new CarDetailsStore();
		try{
			sessionFactory= HibernateUtility.createSessionFactory();
			session= sessionFactory.openSession();
			String hql="from CarDetailsStoreEntity u where u.policyNo != NULL";
			String hql1="from BikeDetailsStoreEntity x where x.policyNo != NULL";
			Query query=  session.createQuery(hql);
			Query query1 = session.createQuery(hql1);
			car=query.list();
			bike=query1.list();
			car.addAll(bike);

		}catch(Exception e){
			//e.printStackTrace();
		}finally
		{
			if(session.isOpen() || session!=null)
			{
				session.close();
			}
		}
		return car;
	}
}