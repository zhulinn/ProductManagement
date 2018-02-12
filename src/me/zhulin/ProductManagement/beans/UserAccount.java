package me.zhulin.ProductManagement.beans;

import java.io.Serializable;

public class UserAccount implements Serializable{
	public static final String GENDER_MALE="M";
	public static final String GENDER_FEMALE="F";
	
	private String userName;
	private String gender;
	private String password;
	
	public UserAccount() {
		
	}
	
	public String getUserName() { 
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
