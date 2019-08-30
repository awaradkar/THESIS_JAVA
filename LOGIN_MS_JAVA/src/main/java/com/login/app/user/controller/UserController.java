package com.login.app.user.controller;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.login.app.exception.EntityNotFoundException;
import com.login.app.user.model.dto.UserDTO;
import com.login.app.user.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<UserDTO>> userList(@RequestParam(required = false) String userId,
			@RequestParam(required = false) String userName, @RequestParam(required = false) String userFullName,
			@RequestParam(required = false) String userOrg, @RequestParam(required = false) String userRole,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
		List<UserDTO> userList = new ArrayList<>();

		userList = userService.getUserList(userId, userName, userFullName, userOrg, userRole, startDate, endDate);

		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Object> getUser(@PathVariable("id") String id) {
		UserDTO user = userService.getUser(id);

		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("" + id);
		}

	}

	@RequestMapping(method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) throws Exception {
		user = userService.saveUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = { "application/json" })
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) throws Exception {
		user = userService.updateUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<Object> deleteUser(@PathVariable("id") String id) {
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>(new HashMap().put("msg", "Record Deleted Successfully"), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(id);
		}
	}
}
