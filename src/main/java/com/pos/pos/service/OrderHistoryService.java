package com.pos.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.pos.data.OrderHistoryRepo;

@Service
public class OrderHistoryService {

	@Autowired
	OrderHistoryRepo orderHistoryRepo;
	
	 public String getMaxOrderCode() {
		 
		 return orderHistoryRepo.getMaxOrderCode();
	 }
}

