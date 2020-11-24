package com.pos.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.pos.data.UserAutherationRepo;
import com.pos.pos.model.UserAutherationModel;

@Service
public class UserAutherationService {

	@Autowired
	UserAutherationRepo userAuthRepo;
	
	public List<UserAutherationModel> getAllTokens(){
		return userAuthRepo.findAll();
		
	}
	
	public int deleteByUserCode(String userCode){
		return userAuthRepo.deleteByUserCode(userCode);
		
	}
}
