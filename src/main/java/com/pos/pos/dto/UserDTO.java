package com.pos.pos.dto;

public class UserDTO {
	
	
	private String userCode;
	private String userId;
	private String userName;
	private String userPassword;
	private String userStatus;
	private byte[] userImage;
	
	public UserDTO() {}

	
	


	public UserDTO(String userCode, String userId, String userName, String userPassword, String userStatus,
			byte[] userImage) {
		super();
		this.userCode = userCode;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userStatus = userStatus;
		this.userImage = userImage;
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
