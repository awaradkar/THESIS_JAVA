package com.dit.app.demo.deposit.dao;

import com.dit.app.demo.deposit.model.Deposit;

import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.Session;

public interface DepositDao {
    List<Deposit> getDepositList(String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate, String status);

    Deposit saveDeposit(Deposit deposit, Session session);

    Deposit updateDeposit(Deposit deposit, Session session);

    void deleteDeposit(String id);

    Deposit getDeposit(String id);
}
