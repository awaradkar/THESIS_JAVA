package com.deposit.bill.model.dto;

import com.deposit.bill.config.ZoneDateDeSerializer;
import com.deposit.bill.config.ZoneDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class WithdrawalDTO {

	private String withdrawalId;

	private String depositId;

	private ZonedDateTime withdrawalDate;

	private BigDecimal withDrawalNoOfBags;

	private BigDecimal withdrawalQuantity;

	public String getWithdrawalId() {
		return withdrawalId;
	}

	public void setWithdrawalId(String withdrawalId) {
		this.withdrawalId = withdrawalId;
	}

	public String getDepositId() {
		return depositId;
	}

	public void setDepositId(String depositId) {
		this.depositId = depositId;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public ZonedDateTime getWithdrawalDate() {
		return withdrawalDate;
	}

	@JsonSerialize(using = ZoneDateSerializer.class)
	@JsonDeserialize(using = ZoneDateDeSerializer.class)
	public void setWithdrawalDate(ZonedDateTime withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}

	public BigDecimal getWithDrawalNoOfBags() {
		return withDrawalNoOfBags;
	}

	public void setWithDrawalNoOfBags(BigDecimal withDrawalNoOfBags) {
		this.withDrawalNoOfBags = withDrawalNoOfBags;
	}

	public BigDecimal getWithdrawalQuantity() {
		return withdrawalQuantity;
	}

	public void setWithdrawalQuantity(BigDecimal withdrawalQuantity) {
		this.withdrawalQuantity = withdrawalQuantity;
	}

}
