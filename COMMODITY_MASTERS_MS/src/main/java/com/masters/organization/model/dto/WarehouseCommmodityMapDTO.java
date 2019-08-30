package com.masters.organization.model.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.masters.config.ZoneDateDeSerializer;
import com.masters.config.ZoneDateSerializer;

public class WarehouseCommmodityMapDTO {

	private String wareCommId;

	private String warehouseCode;

	private String commodityCode;

	private ZonedDateTime createdDate;

	private ZonedDateTime modifiedDate;

	private String createdBy;

	private String modifiedBy;

	public String getWareCommId() {
		return wareCommId;
	}

	public void setWareCommId(String wareCommId) {
		this.wareCommId = wareCommId;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
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
