package com.midvi.web;

public class GenericResponse {
	private String message;
	private int status;
	
	public GenericResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenericResponse(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
