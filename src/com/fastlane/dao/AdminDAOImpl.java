package com.fastlane.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fastlane.bean.CarDetailsStore;
import com.fastlane.resources.HibernateUtility;

public class AdminDAOImpl implements AdminDAO{
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String verifyAdmin(String userName, String password) throws Exception {
		//System.out.println("inside DAO: verifyAdmin function");
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		String hql = "select AE.password from AdminEntity AE where AE.userName='"+userName+"'";
		List<String> passwords = new ArrayList<String>();
		String result = "Admin not found";
		try {
			session = sessionFactory.openSession();
			
			// Creating the query object
			Query query = session.createQuery(hql);
			
			// Executing the query
			passwords = query.list();
			//System.out.println("list: " + passwords.size() + passwords);
			if(passwords.size() > 0 && passwords.get(0).equals(password))
				result = "Admin verified";
			else if(passwords.size() > 0)
				result = "Passwords do not match";
			
			
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
	@Override
	public List<String> getAllPolicies() throws Exception {
		SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
		Session session = null;
		List<String> car=new ArrayList<String>();
		List<String> bike=new ArrayList<String>();
		String claimStatus = "In processing";
		//CarDetailsStore car = new CarDetailsStore();
		try{
			sessionFactory= HibernateUtility.createSessionFactory();
			session= sessionFactory.openSession();
			String hql="from CarDetailsStoreEntity u where u.claimStatus=:claimStatus";
			String hql1="from BikeDetailsStoreEntity x where x.claimStatus=:claimStatus";
			Query query=  session.createQuery(hql);
			query.setParameter("claimStatus", claimStatus);
			Query query1 = session.createQuery(hql1);
			query1.setParameter("claimStatus", claimStatus);
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
		String hql="Update CarDetailsStoreEntity CDSE set CDSE.claimStatus=:claimStatus where CDSE.policyNo = :policyNo";
		String hql1="Update BikeDetailsStoreEntity BDSE set BDSE.claimStatus=:claimStatus where BDSE.policyNo = :policyNo";
		//System.out.println("policyNo: " + policyNo);
		//System.out.println("userName: " + carDetailsStore.getUserName());
		//System.out.println("claimStatus: " + claimStatus);
		
		try{
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();

			Query query=  session.createQuery(hql);
			query.setParameter("policyNo", policyNo);             //setting parameters
			query.setParameter("claimStatus", claimStatus);
			detailsStore = new CarDetailsStore();
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
			detailsStore.setMessage("Your request has been received.");
			//System.out.println("committed");
			
			Query query1=  session.createQuery(hql1);
			query1.setParameter("policyNo", policyNo);             //setting parameters
			query1.setParameter("claimStatus", claimStatus);
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
}