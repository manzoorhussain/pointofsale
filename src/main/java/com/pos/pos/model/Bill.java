package com.pos.pos.model;

import java.io.Serializable;

public class Bill implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5716890545643468194L;
	private String productCode;
	private String prodName;
	private String quantity;
	private String price;

	public Bill() {}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
