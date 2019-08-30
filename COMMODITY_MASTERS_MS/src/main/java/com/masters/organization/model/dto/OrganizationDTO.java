package com.masters.organization.model.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.masters.config.ZoneDateDeSerializer;
import com.masters.config.ZoneDateSerializer;

public class OrganizationDTO {

	private String organizationId;
	private String organizationName;
	private String organizationType;
	private String organizationAddress;
	private String organizationLocation;
	private String organizationCity;
	private String organizationZipCode;
	private String organizationState;
	private ZonedDateTime organizationDate;
	private ZonedDateTime createdDate;
	private ZonedDateTime modifiedDate;
	private String createdBy;
	private String modifiedBy;
	
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}
	public String getOrganizationAddress() {
		return organizationAddress;
	}
	public void setOrganizationAddress(String organizationAddress) {
		this.organizationAddress = organizationAddress;
	}
	public String getOrganizationLocation() {
		return organizationLocation;
	}
	public void setOrganizationLocation(String organizationLocation) {
		this.organizationLocation = organizationLocation;
	}
	public String getOrganizationCity() {
		return organizationCity;
	}
	public void setOrganizationCity(String organizationCity) {
		this.organizationCity = organizationCity;
	}
	public String getOrganizationZipCode() {
		return organizationZipCode;
	}
	public void setOrganizationZipCode(String organizationZipCode) {
		this.organizationZipCode = organizationZipCode;
	}
	public String getOrganizationState() {
		return organizationState;
	}
	public void setOrganizationState(String organizationState) {
		this.organizationState = organizationState;
	}
	
	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public ZonedDateTime getOrganizationDate() {
		return organizationDate;
	}
	
	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public void setOrganizationDate(ZonedDateTime organizationDate) {
		this.organizationDate = organizationDate;
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
}
