package com.login.app.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.login.app.dao.IdGeneratorDao;
import com.login.app.model.IdGenerator;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Repository
public class IdGeneratorDaoImpl implements IdGeneratorDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public IdGenerator getNewId(String key, Session session) {
		IdGenerator idGenerator = null;
		try {

			idGenerator = session.get(IdGenerator.class, key);
			if (idGenerator == null) {
				idGenerator = new IdGenerator();
				idGenerator.setIdKey(key);
				idGenerator.setIdValue(new BigDecimal(1));
				idGenerator.setCreatedDate(ZonedDateTime.now());
				idGenerator.setCreatedBy("");

				session.save(idGenerator);
			} else {
				idGenerator.setIdValue(idGenerator.getIdValue().add(new BigDecimal(1)));
				idGenerator.setModifiedDate(ZonedDateTime.now());
				idGenerator.setModifiedBy("");

				session.saveOrUpdate(idGenerator);
			}

		} catch (HibernateException h) {
			System.out.println(h.getMessage());
		} catch (Exception h) {
			System.out.println(h.getMessage());
		}
		return idGenerator;
	}
}
