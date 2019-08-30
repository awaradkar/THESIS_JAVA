package com.masters.dao;

import com.masters.model.IdGenerator;

public interface IdGeneratorDao {

    public IdGenerator getNewId(String key);
}
