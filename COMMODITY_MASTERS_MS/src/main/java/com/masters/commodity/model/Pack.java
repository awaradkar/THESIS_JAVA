package com.masters.commodity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.masters.config.ZonedateTimeConverter;

@Entity
@Table(name = "PACK_MASTER")
public class Pack implements Serializable{

	@Id
	@Column(length = 10)
	private String packId;
	
	@Column(length = 255)
	private String packType;

	@Column(scale = 2, precision = 5)
	private BigDecimal packDeduction;
	
	@Convert(converter = ZonedateTimeConverter.class)
	private ZonedDateTime createdDate;
	
	@Convert(converter = ZonedateTimeConverter.class)
	private ZonedDateTime modifiedDate;
	
	@Column(length = 10)
	private String createdBy;
	
	@Column(length = 10)
	private String modifiedBy;

	public String getPackId() {
		return packId;
	}

	public void setPackId(String packId) {
		this.packId = packId;
	}

	public String getPackType() {
		return packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	public BigDecimal getPackDeduction() {
		return packDeduction;
	}

	public void setPackDeduction(BigDecimal packDeduction) {
		this.packDeduction = packDeduction;
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
