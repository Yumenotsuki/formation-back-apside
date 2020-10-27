package com.example.formationBack.models;

public class UpdatePassword {
	
	private String oldPassword;
	private String password;
	
	
	public UpdatePassword(String oldPassword, String password) {
		this.oldPassword = oldPassword;
		this.password = password;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
