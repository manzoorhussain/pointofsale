package com.pos.pos.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="cat_id")
	private String categoryId;
	
	
	@Column(name="prod_id")
	private String productId;
	
	public ProductPK() {}
	
	
	public ProductPK(String categoryId, String productId) {
		
		this.categoryId = categoryId;
		this.productId = productId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
