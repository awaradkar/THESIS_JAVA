package com.masters.organization.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.masters.config.ZonedateTimeConverter;

@Entity
@Table(name = "ORGANIZATION_MASTER")
public class Organization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 10)
	private String organizationId;

	@Column(length = 100)
	private String organizationName;

	@Column(length = 20)
	private String organizationType;

	@Column(length = 512)
	private String organizationAddress;

	@Column(length = 20)
	private String organizationLocation;

	@Column(length = 20)
	private String organizationCity;

	@Column(length = 10)
	private String organizationZipCode;

	@Column(length = 20)
	private String organizationState;

	@Convert(converter = ZonedateTimeConverter.class)
	private ZonedDateTime organizationDate;

	@Convert(converter = ZonedateTimeConverter.class)
	private ZonedDateTime createdDate;

	@Convert(converter = ZonedateTimeConverter.class)
	private ZonedDateTime modifiedDate;

	@Column(length = 20)
	private String createdBy;

	@Column(length = 20)
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

	public ZonedDateTime getOrganizationDate() {
		return organizationDate;
	}

	public void setOrganizationDate(ZonedDateTime organizationDate) {
		this.organizationDate = organizationDate;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public ZonedDateTime getModifiedDate() {
		return modifiedDate;
	}

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
