package com.pos.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "category")
public class CategoryModel {

	@Id 
	@Column(name = "cat_id", unique = true, nullable = false)
	private String id;
	
	@Column(name="cat_name")
	private String name;
	
	@Column(name="cat_desc")
	private String desc;
	
	@Column(name="cat_status")
	private String status;
	
	public CategoryModel() {}
	
	public CategoryModel(String id, String name, String desc, String status) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	
	
}
