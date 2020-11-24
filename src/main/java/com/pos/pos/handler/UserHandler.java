package com.pos.pos.handler;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.pos.pos.dto.CategoryDTO;
import com.pos.pos.dto.UserDTO;
import com.pos.pos.model.CategoryModel;
import com.pos.pos.model.UserModel;

public class UserHandler {
	
	public  static String hashPassword(String plainTextPassword){
	return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
	
	public static boolean checkPass(String plainPassword, String hashedPassword) {
		boolean flag=false;
		if (BCrypt.checkpw(plainPassword, hashedPassword)) {
			flag=true;
		}
	return flag;
	}
	
	
	

	
public static List<UserDTO> getUserDTOList(Iterable<UserModel> userList){
		
		List<UserDTO> userDTOList=new ArrayList<UserDTO>();
		
		
		for(UserModel userModel:userList) {
			UserDTO userDTO=new UserDTO(userModel.getUserCode(),userModel.getUserId(),userModel.getUserName(),userModel.getUserPassword(),userModel.getUserStatus(),userModel.getUserImage());
			userDTOList.add(userDTO);
		}
		
		return userDTOList;
	}

	public static String getUserToken(String userCode,String userId,String password) {
	
		String userToken="Token"+hashPassword(userId)+password+userCode;
		return userToken;
}
}
