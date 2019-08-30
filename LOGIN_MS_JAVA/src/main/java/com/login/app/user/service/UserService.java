package com.login.app.user.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.login.app.user.model.dto.UserDTO;

public interface UserService {

	List<UserDTO> getUserList(String userId, String userName, String userFullName, String userOrg, String userRole,
			ZonedDateTime startDate, ZonedDateTime endDate);

	UserDTO getUser(String id);

	UserDTO saveUser(UserDTO user) throws Exception;

	UserDTO updateUser(UserDTO user) throws Exception;

	void deleteUser(String id);

	UserDTO loginUser(UserDTO user) throws Exception;

	UserDTO getUserByName(UserDTO user) throws Exception;

}
