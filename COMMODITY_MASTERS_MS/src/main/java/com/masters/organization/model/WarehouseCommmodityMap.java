package com.masters.organization.model;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.masters.config.ZonedateTimeConverter;

@Entity
@Table(name = "WARE_COMM_MASTER")
public class WarehouseCommmodityMap {

	@Id
	@Column(length = 10)
	private String wareCommId;

	@Column(length = 10)
	private String warehouseCode;

	@Column(length = 10)
	private String commodityCode;

	@Convert(converter = ZonedateTimeConverter.class)
	private ZonedDateTime createdDate;

	@Convert(converter = ZonedateTimeConverter.class)
	private ZonedDateTime modifiedDate;

	@Column(length = 10)
	private String createdBy;

	@Column(length = 10)
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
