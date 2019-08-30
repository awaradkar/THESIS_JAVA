package com.deposit.bill.dao;

import java.util.List;
import java.util.Map;

import com.deposit.bill.model.WhBillLedger;
import com.deposit.bill.model.dto.BillingMasterDTO;
import com.deposit.bill.model.dto.DepositDTO;
import com.deposit.bill.model.dto.WhBillLedgerDTO;
import com.deposit.bill.model.dto.WithdrawalDTO;

public interface BillingDao {

	List<DepositDTO> getDepositList();

	List<WithdrawalDTO> getWithdrawalList();

	Map<String, BillingMasterDTO> getBillingMaster();

	int saveLedgerData(List<WhBillLedgerDTO> whBillLedgerlist);

}
