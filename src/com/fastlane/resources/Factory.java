package com.fastlane.resources;

import org.apache.log4j.Logger;

import com.fastlane.dao.AdminDAOImpl;
import com.fastlane.dao.UserDAOImpl;
import com.fastlane.service.AdminServiceImpl;
import com.fastlane.service.UserServiceImpl;

public class Factory {
	public static UserServiceImpl createUserService() {
		/*Logger logger=Logger.getLogger(Factory.class);
		logger.info("FactoryService Method: createUserService()");*/
		return new UserServiceImpl();
	}
	public static UserDAOImpl createUserDAO() {
		
		return new UserDAOImpl();
	}
	public static AdminServiceImpl createAdminService() {
		/*Logger logger=Logger.getLogger(Factory.class);
		logger.info("FactoryService Method: createAdminService()");*/
		return new AdminServiceImpl();
	}
	public static AdminDAOImpl createAdminDAO() {
		/*Logger logger=Logger.getLogger(Factory.class);
		logger.info("FactoryDAO Method: createAdminDAO()");*/
		return new AdminDAOImpl();
	}
}