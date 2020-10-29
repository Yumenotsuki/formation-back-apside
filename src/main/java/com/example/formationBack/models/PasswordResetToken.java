package com.example.formationBack.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PasswordResetToken {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reset_id")
	private long resetId;
	
	@Column(name = "reset_token")
	private String resetToken;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "id")
	private User user;

	public PasswordResetToken() {

	}

	public PasswordResetToken(User user) {
		this.user = user;
		createdDate = new Date();
		resetToken = UUID.randomUUID().toString();
	}

	public long getResetId() {
		return resetId;
	}

	public void setResetId(long resetId) {
		this.resetId = resetId;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
