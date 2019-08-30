package com.masters.organization.dao.impl;

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

import com.masters.exception.EntityNotFoundException;
import com.masters.organization.dao.OrganizationDao;
import com.masters.organization.model.Organization;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Organization> getOrganizations(String organizationId, String organizationName, String organizationCity,
			ZonedDateTime startDate, ZonedDateTime endDate) {
		// TODO Auto-generated method stub
		List<Organization> orgList = null;

		Session session = sessionFactory.openSession();
		try {
			// create Criteria
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);
			Root<Organization> critRoot = criteriaQuery.from(Organization.class);
			criteriaQuery.select(critRoot);
			// you have to add more predicates when you want more parameters
			List<Predicate> predicates = new ArrayList<Predicate>();
			if (organizationId != null) {
				predicates.add(builder.equal(critRoot.get("commodityId"), organizationId));
			}
			if (organizationName != null) {
				predicates.add(builder.like(critRoot.get("commodityName"), "%" + organizationName + "%"));
			}
			if (organizationCity != null) {
				predicates.add(builder.like(critRoot.get("organizationCity"), "%" + organizationCity + "%"));
			}
			if (startDate != null) {
				predicates.add(builder.greaterThanOrEqualTo(critRoot.<ZonedDateTime>get("createdDate"), startDate));
			}
			if (endDate != null) {
				predicates.add(builder.lessThanOrEqualTo(critRoot.<ZonedDateTime>get("createdDate"), endDate));
			}

			criteriaQuery.where(predicates.toArray(new Predicate[0]));
			orgList = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return orgList;

	}

	@Override
	public Organization getOrganization(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Organization organization = null;
		try {

			organization = session.get(Organization.class, id);
		} catch (HibernateException h) {
			System.out.println(h.getMessage());
		} catch (Exception h) {
			System.out.println(h.getMessage());
		} finally {
			session.close();
		}
		return organization;
	}

	@Override
	public Organization saveOrganization(Organization organization) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.save(organization);
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
		return organization;
	}

	@Override
	public Organization updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.saveOrUpdate(organization);
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
		return organization;
	}

	@Override
	public void deleteOrganization(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Organization organization = (Organization) session.load(Organization.class, id);
			if (organization != null) {
				session.delete(organization);
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
