package com.pos.pos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pos.pos.data.UserAutherationRepo;
import com.pos.pos.data.UserRepo;

import com.pos.pos.dto.UserDTO;

import com.pos.pos.handler.UserHandler;

import com.pos.pos.model.UserAutherationModel;
import com.pos.pos.model.UserModel;
import com.pos.pos.model.WsResponse;


@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserAutherationRepo userAuthRepo;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public  WsResponse addUser(UserModel userModel) {
		WsResponse wsResponse=null;
		
		
		UserModel model=userRepo.save(userModel);
		
		if(model==null) {
			wsResponse=new WsResponse("01", "Record Not Save", model);
		}else {
			wsResponse=new WsResponse("00", "Record  save Successfully", model);
		}
		
		return wsResponse;
		
	}
	

	
	public WsResponse deleteUser(String userCode) {
		
		
		WsResponse wsResponse=null;
		try {
			userRepo.deleteById(userCode);
			wsResponse=new WsResponse("00", "Record delete Successfully", userCode);
		
		}catch(Exception e) {
			wsResponse=new WsResponse("01", "Record not delete", userCode);
		}
		
		
	return wsResponse;
		
	}
	
	
	public WsResponse getUserByCode(String userCode) {
		Optional<UserModel> userModel=userRepo.findById(userCode);
		
		
		WsResponse wsResponse=null;
		
		
		if(userModel.isPresent()) {
			wsResponse=new WsResponse("00", "Record  fetch Successfully", userModel);
		}else {
			wsResponse=new WsResponse("01", "Record  not found", userModel);
		}
		
		return wsResponse;
		
	
	}

	
	public WsResponse getUsers() {
		
		Iterable<UserModel> userList=userRepo.findAll();
		
		List<UserDTO> userDTOList=UserHandler.getUserDTOList(userList);
		
		WsResponse wsResponse=null;
		if(userDTOList.size()==0) {
			wsResponse=new WsResponse("01", "Record Not Found", userDTOList);
		}else {
			wsResponse=new WsResponse("00", "Record fetch Successfully", userDTOList);
		}
		
		return wsResponse;
	}

	public WsResponse login(String userId,String password) {
		
		WsResponse usersObj=getUsers();
		List<UserDTO> userModelist=(List<UserDTO>) usersObj.getResponseObject();
		
		UserDTO currentUser=null;
		for( UserDTO user:userModelist) {
			if(userId.equals(user.getUserId()) && UserHandler.checkPass(password, user.getUserPassword()) ) {
				currentUser=user;
				break;
			}
		}//end for
		
		
		WsResponse wsResponse=null;
		
		
		if(currentUser!=null) {
			String userToken=UserHandler.getUserToken(currentUser.getUserCode(),currentUser.getUserId(),currentUser.getUserPassword());
			UserAutherationModel userAutherationModel=new UserAutherationModel(currentUser.getUserCode(), currentUser.getUserId(), userToken);
			userAuthRepo.save(userAutherationModel);
			
			wsResponse=new WsResponse("00", "Login Successfully", userAutherationModel);
		}else {
			wsResponse=new WsResponse("01", "Login Id and password incorrent", currentUser);
		}
		
		return wsResponse;
		
	
	}
}
