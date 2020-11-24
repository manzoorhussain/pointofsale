package com.pos.pos.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import com.pos.pos.model.Bill;

public class BillDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String bill;
	private String orderNumber;
	private String totalAmount;

	
	
	public BillDTO() {}


	public String getOrderNumber() {
		return orderNumber;
	}



	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}



	public String getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getBill() {
		return bill;
	}


	public void setBill(String bill) {
		this.bill = bill;
	}


	






	
	
}
