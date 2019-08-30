package com.login.app.user.dao;

import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.Session;

import com.login.app.user.model.User;
import com.login.app.user.model.dto.UserDTO;

public interface UserDao {

	List<User> getUserList(String userId, String userName, String userFullName, String userOrg, String userRole,
			ZonedDateTime startDate, ZonedDateTime endDate);

	User getUser(String id);

	User saveUser(User user, Session session);

	User updateUser(User user, Session session);

	void deleteUser(String id);

	User loginUser(String userName);

}
