package com.login.app.dao;

import org.hibernate.Session;

import com.login.app.model.IdGenerator;

public interface IdGeneratorDao {

	public IdGenerator getNewId(String key, Session session);
}
