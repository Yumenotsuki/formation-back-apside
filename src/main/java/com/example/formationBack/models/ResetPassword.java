package com.example.formationBack.models;

public class ResetPassword {
	
	private String email;
	private String password;
	
	public ResetPassword() {
	
	}
	
	public ResetPassword(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
