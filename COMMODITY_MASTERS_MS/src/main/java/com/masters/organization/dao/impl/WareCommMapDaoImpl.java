package com.masters.organization.dao.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.masters.commodity.model.Commodity;
import com.masters.exception.EntityNotFoundException;
import com.masters.organization.dao.WareCommMapDao;
import com.masters.organization.model.Organization;
import com.masters.organization.model.WarehouseCommmodityMap;

@Repository
public class WareCommMapDaoImpl implements WareCommMapDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<WarehouseCommmodityMap> getWareCommMapList(String warehouseCode, String warehouseName,
			String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate) {
		// TODO Auto-generated method stub

		List<WarehouseCommmodityMap> wareCommMapList = null;

		Session session = sessionFactory.openSession();
		try {
			// create Criteria
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<WarehouseCommmodityMap> criteriaQuery = builder.createQuery(WarehouseCommmodityMap.class);
			Root<WarehouseCommmodityMap> critRoot = criteriaQuery.from(WarehouseCommmodityMap.class);
			criteriaQuery.select(critRoot);
			// you have to add more predicates when you want more parameters
			/*
			 * Join<WarehouseCommmodityMap, Commodity> join = critRoot.join("commList",
			 * JoinType.INNER); Join<WarehouseCommmodityMap, Organization> joinWare =
			 * critRoot.join("warehouseCode", JoinType.INNER);
			 */

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (warehouseCode != null) {
				predicates.add(builder.equal(critRoot.get("warehouseCode"), warehouseCode));
			}

			/*
			 * if (warehouseName != null) {
			 * predicates.add(builder.like(joinWare.get("organizationName"), "%" +
			 * warehouseName + "%"));
			 * predicates.add(builder.equal(joinWare.get("organizationType"), "WH")); }
			 * 
			 * if (commodityName != null) {
			 * predicates.add(builder.like(join.get("commodityName"), "%" + commodityName +
			 * "%")); }
			 */

			if (startDate != null) {
				predicates.add(builder.greaterThanOrEqualTo(critRoot.<ZonedDateTime>get("createdDate"), startDate));
			}

			if (endDate != null) {
				predicates.add(builder.lessThanOrEqualTo(critRoot.<ZonedDateTime>get("createdDate"), endDate));
			}

			criteriaQuery.where(predicates.toArray(new Predicate[0]));
			wareCommMapList = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return wareCommMapList;
	}

	@Override
	public WarehouseCommmodityMap getWarehouseCommmodityMap(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		WarehouseCommmodityMap warehouseCommmodityMap = null;
		try {

			warehouseCommmodityMap = session.get(WarehouseCommmodityMap.class, id);
		} catch (HibernateException h) {
			System.out.println(h.getMessage());
		} catch (Exception h) {
			System.out.println(h.getMessage());
		} finally {
			session.close();
		}
		return warehouseCommmodityMap;
	}

	@Override
	public WarehouseCommmodityMap saveWareCommMap(WarehouseCommmodityMap warehouseCommmodityMap) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.save(warehouseCommmodityMap);
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
		return warehouseCommmodityMap;
	}

	@Override
	public WarehouseCommmodityMap updateWareCommMap(WarehouseCommmodityMap warehouseCommmodityMap) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.saveOrUpdate(warehouseCommmodityMap);
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
		return warehouseCommmodityMap;
	}

	@Override
	public void deleteWareCommMap(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			WarehouseCommmodityMap warehouseCommmodityMap = (WarehouseCommmodityMap) session
					.load(WarehouseCommmodityMap.class, id);
			if (warehouseCommmodityMap != null) {
				session.delete(warehouseCommmodityMap);
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
