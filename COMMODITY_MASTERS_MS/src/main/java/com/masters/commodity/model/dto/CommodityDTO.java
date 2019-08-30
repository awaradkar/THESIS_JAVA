package com.masters.commodity.model.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.masters.config.ZoneDateDeSerializer;
import com.masters.config.ZoneDateSerializer;

public class CommodityDTO {

	private String commodityId;
	private String commodityName;
	private String description;
	private String uom;
	private ZonedDateTime createdDate;
	private ZonedDateTime modifiedDate;
	private String createdBy;
	private String modifiedBy;

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
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
