package com.masters.commodity.dao.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.masters.commodity.dao.PackDao;
import com.masters.commodity.model.Pack;
import com.masters.exception.EntityNotFoundException;

@Repository
public class PackDaoImpl implements PackDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Pack> getPacks(String packId, String packType, ZonedDateTime startDate, ZonedDateTime endDate) {
		// TODO Auto-generated method stub
		List<Pack> packList = null;

		Session session = sessionFactory.openSession();
		try {
			// create Criteria
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Pack> criteriaQuery = builder.createQuery(Pack.class);
			Root<Pack> critRoot = criteriaQuery.from(Pack.class);
			criteriaQuery.select(critRoot);
			// you have to add more predicates when you want more parameters
			List<Predicate> predicates = new ArrayList<Predicate>();
			if (packId != null) {
				predicates.add(builder.equal(critRoot.get("packId"), packId));
			}
			if (packType != null) {
				predicates.add(builder.like(critRoot.get("packType"), "%" + packType + "%"));
			}
			if (startDate != null) {
				predicates.add(builder.greaterThanOrEqualTo(critRoot.<ZonedDateTime>get("createdDate"), startDate));
			}
			if (endDate != null) {
				predicates.add(builder.lessThanOrEqualTo(critRoot.<ZonedDateTime>get("createdDate"), endDate));
			}

			criteriaQuery.where(predicates.toArray(new Predicate[0]));
			packList = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return packList;

	}

	@Override
	public Pack getPack(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Pack pack = null;
		try {

			pack = session.get(Pack.class, id);
		} catch (HibernateException h) {
			System.out.println(h.getMessage());
		} catch (Exception h) {
			System.out.println(h.getMessage());
		} finally {
			session.close();
		}
		return pack;
	}

	@Override
	public Pack savePack(Pack pack) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.save(pack);
			tx.commit();
		} catch (HibernateException h) {
			tx.rollback();
			h.printStackTrace();
			System.out.println(h.getMessage());
		} catch (Exception h) {
			tx.rollback();
			h.printStackTrace();
			System.out.println(h.getMessage());
		} finally {
			session.close();
		}
		return pack;
	}

	@Override
	public Pack updatePack(Pack pack) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.saveOrUpdate(pack);
			tx.commit();
		} catch (HibernateException h) {
			tx.rollback();
			System.out.println(h.getMessage());
		} catch (Exception h) {
			tx.rollback();
			System.out.println(h.getMessage());
		} finally {
			session.close();
		}
		return pack;
	}

	@Override
	public void deletePack(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Pack pack = (Pack) session.load(Pack.class, id);
			if (pack != null) {
				session.delete(pack);
				tx.commit();
			} else {
				throw new EntityNotFoundException(id);
			}

		} catch (HibernateException h) {
			tx.rollback();
			System.out.println(h.getMessage());
		} catch (Exception h) {
			tx.rollback();
			System.out.println(h.getMessage());
		} finally {
			session.close();
		}
	}

}
