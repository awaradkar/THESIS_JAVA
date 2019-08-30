package com.deposit.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deposit.bill.config.ZonedateTimeConverter;

@Entity
@Table(name = "WH_BILL_LEDGER")
public class WhBillLedger implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length = 10)
	private String billingId;

	@Column(length = 10)
	private String depositId;
	
	@Column(length = 10)
	private String warehouseCode;
	
	@Column(length = 100)
	private String warehouseName;
	
	@Column(length = 10)
	private String clientId;
	
	@Column(length = 100)
	private String clientName;
	
	@Column(length = 10)
	private String commodityCode;
	
	@Column(length = 100)
	private String commodityName;
	
	@Column(length = 10)
	private String uomCode;
	
	@Column(scale = 5, precision = 25)
	private BigDecimal quantity;
	
	@Column(scale = 5, precision = 25)
	private BigDecimal noOfPacks;
	
	@Convert(converter = ZonedateTimeConverter.class)
	private ZonedDateTime startBilledDate;
	
	@Convert(converter = ZonedateTimeConverter.class)
	private ZonedDateTime endBilledDate;
	
	@Column(scale = 5, precision = 10)
	private BigDecimal dailyRate;
	
	@Column(scale = 5, precision = 10)
	private BigDecimal weeklyRate;
	
	@Column(scale = 5, precision = 10)
	private BigDecimal monthlyRate;
	
	@Column(scale = 5, precision = 25)
	private BigDecimal billRate;
	
	@Column(scale = 5, precision = 25)
	private BigDecimal taxComponent;
	
	@Column(scale = 5, precision = 25)
	private BigDecimal totalBill;
	
	@Column(length = 10)
	private String invoiceId;
	
	@Column(length = 10)
	private String createdBy;
	
	@Convert(converter = ZonedateTimeConverter.class)
	private ZonedDateTime createdTimeStamp;
	
	@Column(length = 10)
	private String modifiedBy;
	
	@Convert(converter = ZonedateTimeConverter.class)
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

	public ZonedDateTime getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(ZonedDateTime createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ZonedDateTime getModifiedTimeStamp() {
		return modifiedTimeStamp;
	}

	public void setModifiedTimeStamp(ZonedDateTime modifiedTimeStamp) {
		this.modifiedTimeStamp = modifiedTimeStamp;
	}

	public ZonedDateTime getStartBilledDate() {
		return startBilledDate;
	}

	public void setStartBilledDate(ZonedDateTime startBilledDate) {
		this.startBilledDate = startBilledDate;
	}

	public ZonedDateTime getEndBilledDate() {
		return endBilledDate;
	}

	public void setEndBilledDate(ZonedDateTime endBilledDate) {
		this.endBilledDate = endBilledDate;
	}

	public BigDecimal getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(BigDecimal dailyRate) {
		this.dailyRate = dailyRate;
	}
	
	
}
