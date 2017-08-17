package com.fastlane.bean;

import java.util.Calendar;


public class User {
	
	       private Integer userId;
	       private String name;
	       private String userName;
	       
		private String emailId;
	       private String password;
	       private Long mobileNo;
	       private Calendar dateOfBirth;
	       private String message;
	       
	       public Calendar getDateOfBirth() {
			return dateOfBirth;
		   }
		   public void setDateOfBirth(Calendar dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		   }
	       public Integer getUserId() {
	              return userId;
	       }
	       public void setUserId(Integer userId) {
	              this.userId = userId;
	       }
	       public String getName() {
	              return name;
	       }
	       public void setName(String name) {
	              this.name = name;
	       }
	       public String getEmailId() {
	              return emailId;
	       }
	       public void setEmailId(String emailId) {
	              this.emailId = emailId;
	       }
	       public String getPassword() {
	              return password;
	       }
	       public void setPassword(String password) {
	              this.password = password;
	       }
	       public Long getMobileNo() {
	              return mobileNo;
	       }
	       public void setMobileNo(Long mobileNo) {
	              this.mobileNo = mobileNo;
	       }
	       
	       public String getMessage() {
	              return message;
	       }
	       public void setMessage(String message) {
	              this.message = message;
	       }
	       public String getUserName() {
				return userName;
			}
			public void setUserName(String userName) {
				this.userName = userName;
			}
}