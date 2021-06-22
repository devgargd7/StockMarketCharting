package com.stockmarketcharting.dto;

import com.stockmarketcharting.model.User;

public class AuthenticationResponse{

	private final String username;
	private final String password;
	private final String userType;
	private final String email;
	private final String mobileNumber;
	private final boolean confirmed;
	private final String token;


	public AuthenticationResponse(String jwt, User user) {
		this.token = jwt;
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.userType = user.getUserType();
		this.email = user.getEmail();
		this.mobileNumber = user.getMobileNumber();
		this.confirmed = user.isConfirmed();
		
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUserType() {
		return userType;
	}

	public String getEmail() {
		return email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public String getToken() {
		return token;
	}

	public String toString() {
		return "AuthenticationResponse [username=" + username + ", password=" + password + ", userType=" + userType
				+ ", email=" + email + ", mobileNumber=" + mobileNumber + ", confirmed=" + confirmed + ", token="
				+ token + "]";
	}

}
