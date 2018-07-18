package com.example.scalableweb.demo.api.v1.controller.model;

public class ServiceResponse {
	
	private String status;
	private String message;
	
	
	
	public ServiceResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ServiceResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static ServiceResponse buildOK() {
		return new ServiceResponse("OK", "");
	}
	
	public static ServiceResponse buildError(Exception e) {
		return new ServiceResponse("ERROR", e.getMessage());
	}

}
