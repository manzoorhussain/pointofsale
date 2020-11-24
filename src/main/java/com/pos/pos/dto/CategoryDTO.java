package com.pos.pos.dto;



public class CategoryDTO {
	
	private String id;
	private String name;
	private String desc;
	private String status;
	
	
	public CategoryDTO(String id, String name, String desc, String status) {
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
