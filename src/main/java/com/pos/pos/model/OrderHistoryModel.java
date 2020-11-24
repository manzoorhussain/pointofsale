package com.pos.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_HISTORY")
public class OrderHistoryModel {

	@Id
	@Column(name="sr_no")
	private long srNo;
	
	@Column(name="ord_code")
	private String orderCode;
	
	@Column(name="ord_desc")
	private String ordeDesc;
	
	@Column(name="ord_year")
	private String orderYear;
	
	@Column(name="ord_month")
	private String orderMonth;
	
	@Column(name="ord_day")
	private String orderDay;
	
	@Column(name="ord_created")
	private String orderCreated;
	
	public String getOrderCode() {
	
		
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrdeDesc() {
		return ordeDesc;
	}
	public void setOrdeDesc(String ordeDesc) {
		this.ordeDesc = ordeDesc;
	}
	public String getOrderYear() {
		return orderYear;
	}
	public void setOrderYear(String orderYear) {
		this.orderYear = orderYear;
	}
	public String getOrderMonth() {
		return orderMonth;
	}
	public void setOrderMonth(String orderMonth) {
		this.orderMonth = orderMonth;
	}
	public String getOrderDay() {
		return orderDay;
	}
	public void setOrderDay(String orderDay) {
		this.orderDay = orderDay;
	}
	public String getOrderCreated() {
		return orderCreated;
	}
	public void setOrderCreated(String orderCreated) {
		this.orderCreated = orderCreated;
	}
	public long getSrNo() {
		return srNo;
	}
	public void setSrNo(long srNo) {
		this.srNo = srNo;
	}
	
}
