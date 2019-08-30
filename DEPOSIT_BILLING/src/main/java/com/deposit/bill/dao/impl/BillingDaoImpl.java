package com.deposit.bill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deposit.bill.config.DBConnection;
import com.deposit.bill.dao.BillingDao;
import com.deposit.bill.model.dto.BillingMasterDTO;
import com.deposit.bill.model.dto.DepositDTO;
import com.deposit.bill.model.dto.WhBillLedgerDTO;
import com.deposit.bill.model.dto.WithdrawalDTO;

@Repository
public class BillingDaoImpl implements BillingDao {

	@Autowired
	private ZoneId zoneId;

	@Override
	public List<DepositDTO> getDepositList() {
		// TODO Auto-generated method stub
		List<DepositDTO> depositList = new ArrayList<>();
		Connection conn = DBConnection.getDBConnection();
		StringBuilder selectSql = new StringBuilder("");
		try {
			selectSql.append("SELECT depositId,clientId,clientName,commodityCode,commodityName,");
			selectSql.append("currentPacks,currenyQty,depositDate,netQuantity,noOfBags,billingType,");
			selectSql.append("packType,quantity,status,uomCode,warehouseCode,warehouseName,lastBilledDate ");
			selectSql.append(" FROM deposit_details where ");
			selectSql.append(" active = ? and status = ? and isBillingCompleted = ? ");
			selectSql.append(" and (lastBilledDate < current_timestamp() or lastBilledDate is null)");
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(selectSql.toString());

				ps.setString(1, "Y");
				ps.setString(2, "COMPLETED");
				ps.setString(3, "N");

				ResultSet resultSet = ps.executeQuery();

				while (resultSet.next()) {
					DepositDTO depositDTO = new DepositDTO();
					depositDTO.setDepositId(resultSet.getString("depositId"));
					depositDTO.setClientId(resultSet.getString("clientId"));
					depositDTO.setClientName(resultSet.getString("clientName"));
					depositDTO.setCommodityCode(resultSet.getString("commodityCode"));
					depositDTO.setCommodityName(resultSet.getString("commodityName"));
					depositDTO.setPackType(resultSet.getString("packType"));
					depositDTO.setUomCode(resultSet.getString("uomCode"));
					depositDTO.setWarehouseCode(resultSet.getString("warehouseCode"));
					depositDTO.setWarehouseName(resultSet.getString("warehouseName"));
					depositDTO.setBillingType(resultSet.getString("billingType"));
					depositDTO.setCurrentPacks(resultSet.getBigDecimal("currentPacks"));
					depositDTO.setCurrenyQty(resultSet.getBigDecimal("currenyQty"));
					depositDTO.setNetQuantity(resultSet.getBigDecimal("netQuantity"));
					depositDTO.setQuantity(resultSet.getBigDecimal("quantity"));
					depositDTO.setNoOfBags(resultSet.getBigDecimal("noOfBags"));
					depositDTO.setDepositDate(resultSet.getTimestamp("depositDate").toInstant().atZone(zoneId));
					depositDTO.setLastBilledDate(resultSet.getTimestamp("lastBilledDate").toInstant().atZone(zoneId));

					depositList.add(depositDTO);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return depositList;
	}

	@Override
	public List<WithdrawalDTO> getWithdrawalList() {
		// TODO Auto-generated method stub
		List<WithdrawalDTO> withdrawalList = new ArrayList<>();
		Connection conn = DBConnection.getDBConnection();
		StringBuilder selectSql = new StringBuilder("");
		try {
			selectSql.append("SELECT withdrawalId,a.depositId,withDrawalNoOfBags,withdrawalDate,withdrawalQuantity");
			selectSql.append("  FROM withdrawal_details a, deposit_details b where a.active = ? and a.status = ?");
			selectSql.append("  and a.depositId = b.depositId and b.isBillingCompleted = ? order by createdDate asc");

			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(selectSql.toString());

				ps.setString(1, "Y");
				ps.setString(2, "COMPLETED");
				ps.setString(3, "N");

				ResultSet resultSet = ps.executeQuery();

				while (resultSet.next()) {
					WithdrawalDTO withdrawalDTO = new WithdrawalDTO();
					withdrawalDTO.setDepositId(resultSet.getString("depositId"));
					withdrawalDTO.setWithdrawalId(resultSet.getString("withdrawalId"));
					withdrawalDTO.setWithDrawalNoOfBags(resultSet.getBigDecimal("withDrawalNoOfBags"));
					withdrawalDTO.setWithdrawalQuantity(resultSet.getBigDecimal("withdrawalQuantity"));
					withdrawalDTO
							.setWithdrawalDate(resultSet.getTimestamp("withdrawalDate").toInstant().atZone(zoneId));

					withdrawalList.add(withdrawalDTO);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return withdrawalList;
	}

	@Override
	public Map<String, BillingMasterDTO> getBillingMaster() {
		// TODO Auto-generated method stub
		Map<String, BillingMasterDTO> billingMasterMap = new HashMap<String, BillingMasterDTO>();

		Connection conn = DBConnection.getDBConnection();
		StringBuilder selectSql = new StringBuilder("");
		try {
			selectSql.append("SELECT warehouse,packTypeChargeMonthly,quantityTypeChargeMonthly,");
			selectSql.append(
					"packTypeChargeWeekly,quantityTypeChargeWeekly,packTypeChargeDaily,quantityTypeChargeDaily");
			selectSql.append("  FROM BillingMaster");

			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(selectSql.toString());
				ResultSet resultSet = ps.executeQuery();

				while (resultSet.next()) {
					BillingMasterDTO billingMasterDTO = new BillingMasterDTO();

					String warehouseCode = resultSet.getString("warehouse");
					billingMasterDTO.setWarehouseCode(resultSet.getString("warehouse"));
					billingMasterDTO.setPackTypeChargeMonthly(resultSet.getFloat("packTypeChargeMonthly"));
					billingMasterDTO.setPackTypeChargeDaily(resultSet.getFloat("packTypeChargeDaily"));
					billingMasterDTO.setPackTypeChargeWeekly(resultSet.getFloat("packTypeChargeWeekly"));
					billingMasterDTO.setQuantityTypeChargeMonthly(resultSet.getFloat("quantityTypeChargeMonthly"));
					billingMasterDTO.setQuantityTypeChargeWeekly(resultSet.getFloat("quantityTypeChargeWeekly"));
					billingMasterDTO.setQuantityTypeChargeDaily(resultSet.getFloat("quantityTypeChargeDaily"));

					billingMasterMap.put(warehouseCode, billingMasterDTO);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return billingMasterMap;
	}

	@Override
	public int saveLedgerData(List<WhBillLedgerDTO> whBillLedgerlist) {
		// TODO Auto-generated method stub
		Connection conn = null;
		int length = 0;
		PreparedStatement ps = null;
		try {
			conn = DBConnection.getDBConnection();
			StringBuffer selectSql = new StringBuffer("INSERT INTO WH_BILL_LEDGER ");
			selectSql.append("(billingId,depositId,warehouseCode,warehouseName,clientId,clientName,");
			selectSql.append("commodityCode,commodityName,uomCode,quantity,noOfPacks,");
			selectSql.append("startBilledDate,endBilledDate,dailyRate,weeklyRate,monthlyRate,");
			selectSql.append("billRate,taxComponent,totalBill,createdBy,createdTimeStamp) VALUES");
			selectSql.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			if (conn != null) {
				ps = conn.prepareStatement(selectSql.toString());

				for (int i = 1; i < whBillLedgerlist.size(); i++) {
					WhBillLedgerDTO whBillLedgerDTO = whBillLedgerlist.get(i);
					ps.setString(1, whBillLedgerDTO.getBillingId());
					ps.setString(2, whBillLedgerDTO.getDepositId());
					ps.setString(3, whBillLedgerDTO.getWarehouseCode());
					ps.setString(4, whBillLedgerDTO.getWarehouseName());
					ps.setString(5, whBillLedgerDTO.getClientId());
					ps.setString(6, whBillLedgerDTO.getClientName());
					ps.setString(7, whBillLedgerDTO.getCommodityCode());
					ps.setString(8, whBillLedgerDTO.getCommodityName());
					ps.setString(9, whBillLedgerDTO.getUomCode());
					ps.setBigDecimal(10, whBillLedgerDTO.getQuantity());
					ps.setBigDecimal(11, whBillLedgerDTO.getNoOfPacks());
					ps.setTimestamp(12, Timestamp.from(whBillLedgerDTO.getStartBilledDate().toInstant()));
					ps.setTimestamp(13, Timestamp.from(whBillLedgerDTO.getEndBilledDate().toInstant()));
					ps.setBigDecimal(14, whBillLedgerDTO.getDailyRate());
					ps.setBigDecimal(15, whBillLedgerDTO.getWeeklyRate());
					ps.setBigDecimal(16, whBillLedgerDTO.getMonthlyRate());
					ps.setBigDecimal(17, whBillLedgerDTO.getBillRate());
					ps.setBigDecimal(18, whBillLedgerDTO.getTaxComponent());
					ps.setBigDecimal(19, whBillLedgerDTO.getTotalBill());
					ps.setString(20, whBillLedgerDTO.getCreatedBy());
					ps.setTimestamp(21, Timestamp.from(ZonedDateTime.now().toInstant()));
					ps.addBatch();

					// int[] results = ps.executeBatch();
					// System.out.println(results.length);
				}
				length = ps.executeBatch().length;
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				ps.clearParameters();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return length;
	}

}
