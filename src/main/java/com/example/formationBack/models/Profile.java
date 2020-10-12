package com.example.formationBack.models;

//Objet contenant les informations apparaissant dans le profil utilisateur
public class Profile {
	
	private String username;
	private String email;
	
	public Profile(String username, String email) {
		this.username = username;
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
