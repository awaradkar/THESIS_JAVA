package com.deposit.bill.model.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


import com.deposit.bill.config.ZoneDateDeSerializer;
import com.deposit.bill.config.ZoneDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class WhBillLedgerDTO {

	private String billingId;
	private String depositId;
	private String warehouseCode;
	private String warehouseName;
	private String clientId;
	private String clientName;
	private String commodityCode;
	private String commodityName;
	private String uomCode;
	private BigDecimal quantity;
	private BigDecimal noOfPacks;
	private ZonedDateTime startBilledDate;
	private ZonedDateTime endBilledDate;
	private BigDecimal dailyRate;
	private BigDecimal weeklyRate;
	private BigDecimal monthlyRate;
	private BigDecimal billRate;
	private BigDecimal taxComponent;
	private BigDecimal totalBill;
	private String invoiceId;
	private String createdBy;
	private ZonedDateTime createdTimeStamp;
	private String modifiedBy;
	private ZonedDateTime modifiedTimeStamp;

	public String getBillingId() {
		return billingId;
	}

	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	public String getDepositId() {
		return depositId;
	}

	public void setDepositId(String depositId) {
		this.depositId = depositId;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getNoOfPacks() {
		return noOfPacks;
	}

	public void setNoOfPacks(BigDecimal noOfPacks) {
		this.noOfPacks = noOfPacks;
	}
	
	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public ZonedDateTime getStartBilledDate() {
		return startBilledDate;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public void setStartBilledDate(ZonedDateTime startBilledDate) {
		this.startBilledDate = startBilledDate;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public ZonedDateTime getEndBilledDate() {
		return endBilledDate;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public void setEndBilledDate(ZonedDateTime endBilledDate) {
		this.endBilledDate = endBilledDate;
	}

	public BigDecimal getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(BigDecimal dailyRate) {
		this.dailyRate = dailyRate;
	}

	public BigDecimal getWeeklyRate() {
		return weeklyRate;
	}

	public void setWeeklyRate(BigDecimal weeklyRate) {
		this.weeklyRate = weeklyRate;
	}

	public BigDecimal getMonthlyRate() {
		return monthlyRate;
	}

	public void setMonthlyRate(BigDecimal monthlyRate) {
		this.monthlyRate = monthlyRate;
	}

	public BigDecimal getBillRate() {
		return billRate;
	}

	public void setBillRate(BigDecimal billRate) {
		this.billRate = billRate;
	}

	public BigDecimal getTaxComponent() {
		return taxComponent;
	}

	public void setTaxComponent(BigDecimal taxComponent) {
		this.taxComponent = taxComponent;
	}

	public BigDecimal getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(BigDecimal totalBill) {
		this.totalBill = totalBill;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public ZonedDateTime getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public void setCreatedTimeStamp(ZonedDateTime createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public ZonedDateTime getModifiedTimeStamp() {
		return modifiedTimeStamp;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public void setModifiedTimeStamp(ZonedDateTime modifiedTimeStamp) {
		this.modifiedTimeStamp = modifiedTimeStamp;
	}

}
