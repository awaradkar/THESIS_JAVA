package com.login.app.user.dao.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.login.app.user.dao.UserDao;
import com.login.app.user.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getUserList(String userId, String userName, String userFullName, String userOrg, String userRole,
			ZonedDateTime startDate, ZonedDateTime endDate) {
		// TODO Auto-generated method stub
		List<User> userList = null;

		Session session = sessionFactory.openSession();
		try {
			// create Criteria
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
			Root<User> critRoot = criteriaQuery.from(User.class);
			criteriaQuery.select(critRoot);
			// you have to add more predicates when you want more parameters
			List<Predicate> predicates = new ArrayList<Predicate>();
			if (userId != null) {
				predicates.add(builder.equal(critRoot.get("userId"), userId));
			}
			if (userName != null) {
				predicates.add(builder.like(critRoot.get("userName"), "%" + userName + "%"));
			}
			if (userFullName != null) {
				predicates.add(builder.equal(critRoot.get("userFullName"), "%" + userFullName + "%"));
			}
			if (userOrg != null) {
				predicates.add(builder.like(critRoot.get("userOrg"), "%" + userOrg + "%"));
			}
			if (userRole != null) {
				predicates.add(builder.equal(critRoot.get("userRole"), userRole));
			}
			if (startDate != null) {
				predicates.add(builder.greaterThanOrEqualTo(critRoot.<ZonedDateTime>get("depositDate"), startDate));
			}
			if (endDate != null) {
				predicates.add(builder.lessThanOrEqualTo(critRoot.<ZonedDateTime>get("depositDate"), endDate));
			}

			criteriaQuery.where(predicates.toArray(new Predicate[0]));
			userList = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userList;
	}

	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		User user = null;
		try {

			user = session.get(User.class, id);
		} catch (HibernateException h) {
			System.out.println(h.getMessage());
		} catch (Exception h) {
			System.out.println(h.getMessage());
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User saveUser(User user, Session session) {
		// TODO Auto-generated method stub
		try {
			session.save(user);
		} catch (HibernateException h) {
			h.printStackTrace();
			System.out.println(h.getMessage());
		} catch (Exception h) {
			h.printStackTrace();
			System.out.println(h.getMessage());
		}
		return user;
	}

	@Override
	public User updateUser(User user, Session session) {
		// TODO Auto-generated method stub
		try {
			session.saveOrUpdate(user);
		} catch (HibernateException h) {
			System.out.println(h.getMessage());
		} catch (Exception h) {
			System.out.println(h.getMessage());
		}
		return user;
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			User user = (User) session.load(User.class, id);
			if (user != null) {
				session.delete(user);
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

	@Override
	public User loginUser(String userName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		User user = null;
		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
			Root<User> critRoot = criteriaQuery.from(User.class);
			criteriaQuery.select(critRoot);

			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(builder.equal(critRoot.get("userName"), userName));
			
			criteriaQuery.where(predicates.toArray(new Predicate[0]));
			user = (User)session.createQuery(criteriaQuery).getSingleResult();
			
		} catch (HibernateException h) {
			System.out.println(h.getMessage());
		} catch (Exception h) {
			System.out.println(h.getMessage());
		} finally {
			session.close();
		}
		return user;
	}

}
