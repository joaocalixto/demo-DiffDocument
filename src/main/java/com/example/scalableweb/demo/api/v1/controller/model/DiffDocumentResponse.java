package com.example.scalableweb.demo.api.v1.controller.model;

public class DiffDocumentResponse extends ServiceResponse{
	
	private boolean isEqual;

	public boolean isEqual() {
		return isEqual;
	}

	public void setEqual(boolean isEqual) {
		this.isEqual = isEqual;
	}

}
