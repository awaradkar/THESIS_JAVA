package com.dit.app.demo.dao;

import org.hibernate.Session;

import com.dit.app.demo.model.IdGenerator;

public interface IdGeneratorDao {

    public IdGenerator getNewId(String key, Session session);
}
