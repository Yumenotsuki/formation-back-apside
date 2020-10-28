package com.example.formationBack.models;

public class UpdatePassword {
	
	private String password;
	
	
	public UpdatePassword(String oldPassword, String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
