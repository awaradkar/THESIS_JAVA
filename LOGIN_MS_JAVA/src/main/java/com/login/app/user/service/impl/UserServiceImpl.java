package com.login.app.user.service.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.app.dao.IdGeneratorDao;
import com.login.app.exception.EntityNotFoundException;
import com.login.app.helper.EntityConversion;
import com.login.app.model.IdGenerator;
import com.login.app.user.dao.UserDao;
import com.login.app.user.model.User;
import com.login.app.user.model.dto.UserDTO;
import com.login.app.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	IdGeneratorDao idGeneratorDao;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public List<UserDTO> getUserList(String userId, String userName, String userFullName, String userOrg,
			String userRole, ZonedDateTime startDate, ZonedDateTime endDate) {
		// TODO Auto-generated method stub
		List<UserDTO> userDTOList = new ArrayList<>();
		List<User> userList = new ArrayList<>();

		userList = userDao.getUserList(userId, userName, userFullName, userOrg, userRole, startDate, endDate);

		for (User user : userList) {
			UserDTO dto = new UserDTO();
			dto = (UserDTO) EntityConversion.convertModel(user, dto,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
			userDTOList.add(dto);
		}

		return userDTOList;
	}

	@Override
	public UserDTO getUser(String id) {
		// TODO Auto-generated method stub
		User user = userDao.getUser(id);
		UserDTO dto = null;
		if (user != null) {
			dto = new UserDTO();
			dto = (UserDTO) EntityConversion.convertModel(user, dto,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		}

		return dto;
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();

		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.getTransaction();
			tx.begin();

			user = (User) EntityConversion.convertModel(user, userDTO,
					EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
			user.setCreatedDate(ZonedDateTime.now());
			user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));

			IdGenerator idGenerator = idGeneratorDao.getNewId("USR", session);

			user.setUserId(idGenerator.getIdKey() + "" + idGenerator.getIdValue());

			user = userDao.saveUser(user, session);

			tx.commit();

			userDTO = (UserDTO) EntityConversion.convertModel(user, userDTO,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e.getMessage());
		} finally {
			session.close();
		}

		return userDTO;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();

		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.getTransaction();
			tx.begin();
			user = (User) EntityConversion.convertModel(user, userDTO,
					EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
			user.setModifiedDate(ZonedDateTime.now());

			user = userDao.updateUser(user, session);

			tx.commit();

			userDTO = (UserDTO) EntityConversion.convertModel(user, userDTO,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e.getMessage());
		} finally {
			session.close();
		}
		return userDTO;
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		try {
			userDao.deleteUser(id);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(id);
		}
	}

	@Override
	public UserDTO loginUser(UserDTO userDTO) throws Exception {
		// TODO Auto-generated method stub
		String userpassword = userDTO.getUserPassword();
		User user = userDao.loginUser(userDTO.getUserName());
		UserDTO dto = null;
		if (user != null) {
			dto = new UserDTO();
			dto = (UserDTO) EntityConversion.convertModel(user, dto,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());

			if (!bcryptEncoder.matches(userpassword, dto.getUserPassword())) {
				throw new Exception("User Credentials are not valid");
			}
		}

		return dto;
	}

	@Override
	public UserDTO getUserByName(UserDTO userDTO) throws Exception {
		// TODO Auto-generated method stub
		User user = userDao.loginUser(userDTO.getUserName());
		UserDTO dto = null;
		if (user != null) {
			dto = new UserDTO();
			dto = (UserDTO) EntityConversion.convertModel(user, dto,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());

			if (!userDTO.getUserPassword().equals(dto.getUserPassword())) {
				throw new Exception("User Credentials are not valid");
			}
		}

		return dto;
	}
}
