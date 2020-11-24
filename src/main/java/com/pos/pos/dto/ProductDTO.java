package com.pos.pos.dto;

import javax.persistence.Column;

public class ProductDTO {
	
	private String serialNo;
	private String categoryId;
	private String prodId;
	private String prodName;
	private String prodDesc;
	private String prodStatus;
	private String prodPrice;
	
	//Extra Field---
	private String categoryDesc;
	
	
	private byte[] image;
	private String displayImage;
	
	public ProductDTO() {}
	
	public ProductDTO(String categoryId, String prodId, String prodName, String prodDesc, String prodStatus) {
		super();
		this.categoryId = categoryId;
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodDesc = prodDesc;
		this.prodStatus = prodStatus;
	}
	
	
	public ProductDTO(String categoryId, String prodId, String prodName,String displayImage) {
		super();
		this.categoryId = categoryId;
		this.prodId = prodId;
		this.prodName = prodName;
		this.displayImage = displayImage;
		
		
	}
	
	public ProductDTO(String categoryDesc,String categoryId, String prodId, String prodName, String prodDesc, String prodStatus,String prodPrice,String displayImage) {
		super();
		this.categoryDesc=categoryDesc;
		this.categoryId = categoryId;
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodDesc = prodDesc;
		this.prodStatus = prodStatus;
		this.prodPrice = prodPrice;
		this.displayImage = displayImage;
	}
	
	
	public ProductDTO(String categoryDesc,String categoryId, String prodId, String prodName, String prodDesc, String prodStatus,String prodPrice,byte[] image) {
		super();
		this.categoryDesc=categoryDesc;
		this.categoryId = categoryId;
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodDesc = prodDesc;
		this.prodStatus = prodStatus;
		this.prodPrice = prodPrice;
		this.image = image;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDisplayImage() {
		return displayImage;
	}

	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	

}
