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

import com.masters.commodity.dao.CommodityDao;
import com.masters.commodity.model.Commodity;
import com.masters.exception.EntityNotFoundException;

@Repository
public class CommodityDaoImpl implements CommodityDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Commodity> getCommodities(String commodityId, String commodityName, ZonedDateTime startDate,
			ZonedDateTime endDate) {
		// TODO Auto-generated method stub
		List<Commodity> commList = null;

		Session session = sessionFactory.openSession();
		try {
			// create Criteria
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Commodity> criteriaQuery = builder.createQuery(Commodity.class);
			Root<Commodity> critRoot = criteriaQuery.from(Commodity.class);
			criteriaQuery.select(critRoot);
			// you have to add more predicates when you want more parameters
			List<Predicate> predicates = new ArrayList<Predicate>();
			if (commodityId != null) {
				predicates.add(builder.equal(critRoot.get("commodityId"), commodityId));
			}
			if (commodityName != null) {
				predicates.add(builder.like(critRoot.get("commodityName"), "%" + commodityName + "%"));
			}
			if (startDate != null) {
				predicates.add(builder.greaterThanOrEqualTo(critRoot.<ZonedDateTime>get("createdDate"), startDate));
			}
			if (endDate != null) {
				predicates.add(builder.lessThanOrEqualTo(critRoot.<ZonedDateTime>get("createdDate"), endDate));
			}

			criteriaQuery.where(predicates.toArray(new Predicate[0]));
			commList = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commList;
	}

	@Override
	public Commodity getCommodity(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Commodity commodity = null;
		try {

			commodity = session.get(Commodity.class, id);
		} catch (HibernateException h) {
			System.out.println(h.getMessage());
		} catch (Exception h) {
			System.out.println(h.getMessage());
		} finally {
			session.close();
		}
		return commodity;
	}

	@Override
	public Commodity saveCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.save(commodity);
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
		return commodity;
	}

	@Override
	public Commodity updateCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.saveOrUpdate(commodity);
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
		return commodity;
	}

	@Override
	public void deleteCommodity(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Commodity comm = (Commodity) session.load(Commodity.class, id);
			if (comm != null) {
				session.delete(comm);
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
