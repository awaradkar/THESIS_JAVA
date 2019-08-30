package com.deposit.bill.dao;

import com.deposit.bill.model.dto.IdGeneratorDTO;

public interface IdGeneratorDao {

    public IdGeneratorDTO getNewId(String key);
}
