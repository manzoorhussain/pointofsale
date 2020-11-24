package com.pos.pos.model;



import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Table;




import com.pos.pos.pk.ProductPK;

@Entity
@Table(name = "product")

public class ProductModel{
	

	@EmbeddedId
    private ProductPK productPK;
	
	
	
    @Column(name="prod_name")
	private String name;
	
	@Column(name="prod_desc")
	private String desc;
	
	@Column(name="prod_status")
	private String status;
	
	@Column(name="prod_price")
	private String price;
	
	
	@Column(name = "prod_image")
	private byte[] image;
	
	public ProductModel(ProductPK productPK, String name, String desc, String status) {
		super();
		this.productPK = productPK;
		this.name = name;
		this.desc = desc;
		this.status = status;
	}

	
	public ProductModel(ProductPK productPK, String name, String desc, String status,String price) {
		super();
		this.productPK = productPK;
		this.name = name;
		this.desc = desc;
		this.status = status;
		this.price=price;
	}


	public ProductModel(ProductPK productPK, String name, String desc, String status,String price,byte[] image) {
		super();
		this.productPK = productPK;
		this.name = name;
		this.desc = desc;
		this.status = status;
		this.price=price;
		this.image=image;
		
	}

	public ProductModel() {}
	
	
	
	public ProductPK getProductPK() {
		return productPK;
	}
	public void setProductPK(ProductPK productPK) {
		this.productPK = productPK;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	


	
	

}
