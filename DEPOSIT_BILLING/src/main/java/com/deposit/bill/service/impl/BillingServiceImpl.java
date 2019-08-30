package com.deposit.bill.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deposit.bill.dao.BillingDao;
import com.deposit.bill.dao.IdGeneratorDao;
import com.deposit.bill.model.WhBillLedger;
import com.deposit.bill.model.dto.BillingMasterDTO;
import com.deposit.bill.model.dto.DepositDTO;
import com.deposit.bill.model.dto.WhBillLedgerDTO;
import com.deposit.bill.model.dto.WithdrawalDTO;
import com.deposit.bill.service.BillingService;

@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	BillingDao billingDao;

	@Autowired
	IdGeneratorDao idGeneratorDao;

	@Autowired
	ZoneId zoneId;

	@Override
	public String runBilling() {
		// TODO Auto-generated method stub
		String responseStr = "";

		List<DepositDTO> depositList = billingDao.getDepositList();
		List<WithdrawalDTO> withdrawalList = billingDao.getWithdrawalList();
		Map<String, BillingMasterDTO> billingMasterMap = billingDao.getBillingMaster();

		List<WhBillLedgerDTO> whBillLedgerlist = new ArrayList<>();

		Map<String, List<WithdrawalDTO>> depMap = new HashMap<>();

		int filtersedSize = 0;
		for (WithdrawalDTO withdrawal : withdrawalList) {
			if (!depMap.containsKey(withdrawal.getDepositId())) {
				List<WithdrawalDTO> withdrawalListDep = withdrawalList.stream()
						.filter(e -> withdrawal.getDepositId().equals(e.getDepositId())).collect(Collectors.toList());
				depMap.put(withdrawal.getDepositId(), withdrawalListDep);
				filtersedSize = filtersedSize + withdrawalListDep.size();
			}
			if (filtersedSize == withdrawalList.size())
				break;
		}

		for (DepositDTO depositDTO : depositList) {
			List<WithdrawalDTO> lst = depMap.get(depositDTO.getDepositId());
			BillingMasterDTO dto = billingMasterMap.get(depositDTO.getWarehouseCode());

			if (lst.size() > 0) {

				List<WhBillLedgerDTO> billLedgers = calculateWithdrawal(depositDTO, dto, lst);
				whBillLedgerlist.addAll(billLedgers);

			} else {

				WhBillLedgerDTO ledger = calculateWithoutWithdrawal(depositDTO, dto);
				whBillLedgerlist.add(ledger);

			}

		}

		int count = billingDao.saveLedgerData(whBillLedgerlist);
		responseStr = "No of records updated:" + count;
		return responseStr;
	}

	private WhBillLedgerDTO calculateWithoutWithdrawal(DepositDTO depositDTO, BillingMasterDTO master) {
		// TODO Auto-generated method stub
		LocalDate newBillingDate = LocalDate.now();
		LocalDate previousBillingDate = null;
		LocalTime newBillTime = LocalTime.now();
		LocalTime previousBillTime = null;

		WhBillLedgerDTO whBillLedger = new WhBillLedgerDTO();

		if (depositDTO.getLastBilledDate() != null) {
			previousBillingDate = depositDTO.getLastBilledDate().toLocalDate().plusDays(1);
			previousBillTime = depositDTO.getLastBilledDate().toLocalTime().plusHours(24);
		} else {
			previousBillingDate = depositDTO.getDepositDate().toLocalDate();
			previousBillTime = depositDTO.getDepositDate().toLocalTime();
		}

		Period period = Period.between(previousBillingDate, newBillingDate);

		int years = period.getYears();
		int months = period.getMonths();
		int days = period.getDays();

		BigDecimal billAmount = BigDecimal.ZERO;

		int totalMonths = years * 12 + months;
		int weeks = days / 7;
		int remainingDays = days % 7;
		float dailyRate = 0;
		float weeklyRate = 0;
		float monthlyRate = 0;

		if ("P".equals(depositDTO.getBillingType())) {
			monthlyRate = master.getPackTypeChargeMonthly();
			weeklyRate = master.getPackTypeChargeWeekly();
			dailyRate = master.getPackTypeChargeDaily();

			billAmount = depositDTO.getNoOfBags().multiply(new BigDecimal(totalMonths))
					.multiply(new BigDecimal(monthlyRate));
			billAmount = billAmount
					.add(depositDTO.getNoOfBags().multiply(new BigDecimal(weeks)).multiply(new BigDecimal(weeklyRate)));
			billAmount = billAmount.add(depositDTO.getNoOfBags().multiply(new BigDecimal(remainingDays))
					.multiply(new BigDecimal(dailyRate)));
		} else {
			monthlyRate = master.getQuantityTypeChargeMonthly();
			weeklyRate = master.getQuantityTypeChargeWeekly();
			dailyRate = master.getQuantityTypeChargeDaily();

			billAmount = depositDTO.getNoOfBags().multiply(new BigDecimal(totalMonths))
					.multiply(new BigDecimal(monthlyRate));
			billAmount = billAmount
					.add(depositDTO.getNoOfBags().multiply(new BigDecimal(weeks)).multiply(new BigDecimal(weeklyRate)));
			billAmount = billAmount.add(depositDTO.getNoOfBags().multiply(new BigDecimal(remainingDays))
					.multiply(new BigDecimal(dailyRate)));
		}

		BigDecimal taxAmount = billAmount.multiply(BigDecimal.TEN).divide(new BigDecimal(100));

		whBillLedger.setBillingId(idGeneratorDao.getNewId("BILL").toString());
		whBillLedger.setBillRate(billAmount);
		whBillLedger.setClientId(depositDTO.getClientId());
		whBillLedger.setClientName(depositDTO.getClientName());
		whBillLedger.setCommodityCode(depositDTO.getCommodityCode());
		whBillLedger.setCommodityName(depositDTO.getCommodityName());
		whBillLedger.setDailyRate(new BigDecimal(dailyRate));
		whBillLedger.setDepositId(depositDTO.getDepositId());
		whBillLedger.setMonthlyRate(new BigDecimal(monthlyRate));
		whBillLedger.setNoOfPacks(depositDTO.getNoOfBags());
		whBillLedger.setQuantity(depositDTO.getNetQuantity());
		whBillLedger.setStartBilledDate(ZonedDateTime.of(previousBillingDate, previousBillTime, zoneId));
		whBillLedger.setCreatedTimeStamp(ZonedDateTime.now());
		whBillLedger.setTaxComponent(taxAmount);
		whBillLedger.setEndBilledDate(ZonedDateTime.of(newBillingDate, newBillTime, zoneId));
		whBillLedger.setTotalBill(billAmount.add(taxAmount));
		whBillLedger.setUomCode(depositDTO.getUomCode());
		whBillLedger.setWarehouseCode(depositDTO.getWarehouseCode());
		whBillLedger.setWarehouseName(depositDTO.getWarehouseName());
		whBillLedger.setWeeklyRate(new BigDecimal(weeklyRate));

		return whBillLedger;
	}

	private List<WhBillLedgerDTO> calculateWithdrawal(DepositDTO depositDTO, BillingMasterDTO dto,
			List<WithdrawalDTO> lst) {
		// TODO Auto-generated method stub
		List<WhBillLedgerDTO> whBillLedgers = new ArrayList<>();

		ZonedDateTime previousBillingDate = null;
		ZonedDateTime newBillingDate = null;
		BigDecimal withdrawalQty = BigDecimal.ZERO;
		BigDecimal withdrawalPacks = BigDecimal.ZERO;
		int n = lst.size();

		for (int i = 0; i < n; i++) {
			WithdrawalDTO withdrawalDTO = lst.get(i);

			if (i != 0) {
				withdrawalQty = withdrawalQty.add(withdrawalDTO.getWithdrawalQuantity());
				withdrawalPacks = withdrawalPacks.add(withdrawalDTO.getWithDrawalNoOfBags());
			}

			if (i == 0) {
				if (depositDTO.getLastBilledDate() != null) {
					previousBillingDate = depositDTO.getLastBilledDate().plusDays(1);
				} else {
					previousBillingDate = depositDTO.getDepositDate();
				}

				newBillingDate = withdrawalDTO.getWithdrawalDate();

			} else if (n > 0 && i == n - 1) {
				previousBillingDate = lst.get(i - 1).getWithdrawalDate().plusDays(1);
				newBillingDate = ZonedDateTime.now();
			} else {
				previousBillingDate = lst.get(i - 1).getWithdrawalDate().plusDays(1);
				newBillingDate = lst.get(i).getWithdrawalDate();
			}

			WhBillLedgerDTO whBillLedger = getWhBillLedger(depositDTO, dto, withdrawalQty, withdrawalPacks,
					previousBillingDate, newBillingDate);
			whBillLedgers.add(whBillLedger);
		}

		return whBillLedgers;
	}

	private WhBillLedgerDTO getWhBillLedger(DepositDTO depositDTO, BillingMasterDTO dto, BigDecimal withdrawalQty,
			BigDecimal withdrawalPacks, ZonedDateTime previousBilling, ZonedDateTime newBilling) {
		// TODO Auto-generated method stub
		WhBillLedgerDTO whBillLedger = new WhBillLedgerDTO();

		LocalDate newBillingDate = newBilling.toLocalDate();
		LocalDate previousBillingDate = previousBilling.toLocalDate();
		LocalTime newBillTime = newBilling.toLocalTime();
		LocalTime previousBillTime = previousBilling.toLocalTime();

		Period period = Period.between(previousBillingDate, newBillingDate);

		int years = period.getYears();
		int months = period.getMonths();
		int days = period.getDays();

		BigDecimal billAmount = BigDecimal.ZERO;

		int totalMonths = years * 12 + months;
		int weeks = days / 7;
		int remainingDays = days % 7;
		float dailyRate = 0;
		float weeklyRate = 0;
		float monthlyRate = 0;

		BigDecimal billNoOfBags = depositDTO.getNoOfBags().subtract(withdrawalPacks);
		BigDecimal billNoOfQty = depositDTO.getNetQuantity().subtract(withdrawalQty);

		if ("P".equals(depositDTO.getBillingType())) {
			monthlyRate = dto.getPackTypeChargeMonthly();
			weeklyRate = dto.getPackTypeChargeWeekly();
			dailyRate = dto.getPackTypeChargeDaily();

			billAmount = billNoOfBags.multiply(new BigDecimal(totalMonths)).multiply(new BigDecimal(monthlyRate));
			billAmount = billAmount
					.add(billNoOfBags.multiply(new BigDecimal(weeks)).multiply(new BigDecimal(weeklyRate)));
			billAmount = billAmount
					.add(billNoOfBags.multiply(new BigDecimal(remainingDays)).multiply(new BigDecimal(dailyRate)));
		} else {
			monthlyRate = dto.getQuantityTypeChargeMonthly();
			weeklyRate = dto.getQuantityTypeChargeWeekly();
			dailyRate = dto.getQuantityTypeChargeDaily();

			billAmount = billNoOfQty.multiply(new BigDecimal(totalMonths)).multiply(new BigDecimal(monthlyRate));
			billAmount = billAmount
					.add(billNoOfQty.multiply(new BigDecimal(weeks)).multiply(new BigDecimal(weeklyRate)));
			billAmount = billAmount
					.add(billNoOfQty.multiply(new BigDecimal(remainingDays)).multiply(new BigDecimal(dailyRate)));
		}

		BigDecimal taxAmount = billAmount.multiply(BigDecimal.TEN).divide(new BigDecimal(100));

		whBillLedger.setBillingId(idGeneratorDao.getNewId("BILL").toString());
		whBillLedger.setBillRate(billAmount);
		whBillLedger.setClientId(depositDTO.getClientId());
		whBillLedger.setClientName(depositDTO.getClientName());
		whBillLedger.setCommodityCode(depositDTO.getCommodityCode());
		whBillLedger.setCommodityName(depositDTO.getCommodityName());
		whBillLedger.setDailyRate(new BigDecimal(dailyRate));
		whBillLedger.setDepositId(depositDTO.getDepositId());
		whBillLedger.setMonthlyRate(new BigDecimal(monthlyRate));
		whBillLedger.setNoOfPacks(billNoOfBags);
		whBillLedger.setQuantity(billNoOfQty);
		whBillLedger.setStartBilledDate(ZonedDateTime.of(previousBillingDate, previousBillTime, zoneId));
		whBillLedger.setCreatedTimeStamp(ZonedDateTime.now());
		whBillLedger.setTaxComponent(taxAmount);
		whBillLedger.setEndBilledDate(ZonedDateTime.of(newBillingDate, newBillTime, zoneId));
		whBillLedger.setTotalBill(billAmount.add(taxAmount));
		whBillLedger.setUomCode(depositDTO.getUomCode());
		whBillLedger.setWarehouseCode(depositDTO.getWarehouseCode());
		whBillLedger.setWarehouseName(depositDTO.getWarehouseName());
		whBillLedger.setWeeklyRate(new BigDecimal(weeklyRate));

		return whBillLedger;
	}
}
