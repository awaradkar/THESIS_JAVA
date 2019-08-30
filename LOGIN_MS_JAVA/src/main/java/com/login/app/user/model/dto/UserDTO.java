package com.login.app.user.model.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.login.app.config.ZoneDateDeSerializer;
import com.login.app.config.ZoneDateSerializer;

public class UserDTO {

	private String userId;

	private String userName;

	private String userPassword;

	private String userFullName;

	private String userAddress;

	private String userAge;

	private String userRole;

	private String userOrg;

	private ZonedDateTime createdDate;

	private ZonedDateTime modifiedDate;

	private String createdBy;

	private String modifiedBy;
	
	private String token;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public ZonedDateTime getModifiedDate() {
		return modifiedDate;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public void setModifiedDate(ZonedDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
