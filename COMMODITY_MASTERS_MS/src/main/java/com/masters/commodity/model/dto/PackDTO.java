package com.masters.commodity.model.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.masters.config.ZoneDateDeSerializer;
import com.masters.config.ZoneDateSerializer;

public class PackDTO {

	private String packId;
	private String packType;
	private BigDecimal packDeduction;
	private ZonedDateTime createdDate;
	private ZonedDateTime modifiedDate;
	private String createdBy;
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
