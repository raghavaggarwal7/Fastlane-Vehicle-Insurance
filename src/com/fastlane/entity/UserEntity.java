package com.fastlane.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="FASTLANEVEHICLE_USER")
@GenericGenerator(name="generator",strategy="sequence")
public class UserEntity {
	@Id
	@Column(name="USER_ID")
	@GeneratedValue(generator="generator")
	private Integer userId;
	@Column(name="NAME")
    private String name;
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="EMAIL_ID")
    private String emailId;
	@Column(name="PASSWORD")
    private String password;
	@Column(name="MOBILE_NUMBER")
    private Long mobileNo;
	@Column(name="DATE_OF_BIRTH")
    private Calendar dateOfBirth;
	
	
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
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}