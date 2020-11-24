package com.pos.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS_AUTHERATON")
public class UserAutherationModel {
	
	
	@Id
	@Column(name="sr_no")
	private long srNo;
	
	@Column(name="user_code")
	private String userCode;

	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_token")
	private String userToken;
	
	public UserAutherationModel() {}
	
	public UserAutherationModel(String userCode, String userId, String userToken) {
		super();
		this.userCode = userCode;
		this.userId = userId;
		this.userToken = userToken;
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
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	
	
	

}
