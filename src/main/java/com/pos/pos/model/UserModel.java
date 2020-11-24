package com.pos.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "users")
public class UserModel {
	
	
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "user_code", updatable = false, nullable = false)
	@Id
	private String userCode;
	
	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_password")
	private String userPassword;
	
	@Column(name = "user_status")
	private String userStatus;
	
	
	@Column(name = "user_image")
	private byte[] userImage;
	
	
	public UserModel() {
		
	}
	
	
	public UserModel( String userId, String userName, String userPassword, String userStatus,
			byte[] userImage) {
		super();
		
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userStatus = userStatus;
		this.userImage = userImage;
	}



	public UserModel( String userId, String userName, String userPassword, String userStatus) {
		super();
		
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userStatus = userStatus;
		
	}

	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}


	public byte[] getUserImage() {
		return userImage;
	}


	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}
	
	
	

}
