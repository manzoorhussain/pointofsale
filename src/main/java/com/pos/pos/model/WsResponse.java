package com.pos.pos.model;

import java.io.Serializable;

public class WsResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private Object responseObject;
	
	public WsResponse(){}
	
	public WsResponse(String code, String message, Object responseObject) {
		super();
		this.code = code;
		this.message = message;
		this.responseObject = responseObject;
	}
	
	public WsResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
		
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
	
	

}
