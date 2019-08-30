package com.masters.organization.controller;

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

import com.masters.exception.EntityNotFoundException;
import com.masters.organization.model.dto.OrganizationDTO;
import com.masters.organization.service.OrganizationService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/organization")
public class OrganizationController {

	@Autowired
	OrganizationService orgService;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<OrganizationDTO>> orgList(@RequestParam(required = false) String organizationId,
			@RequestParam(required = false) String organizationName,
			@RequestParam(required = false) String organizationCity,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
		List<OrganizationDTO> organizationDTOs = new ArrayList<>();

		organizationDTOs = orgService.getOrgList(organizationId, organizationName, organizationCity, startDate,
				endDate);

		return new ResponseEntity<>(organizationDTOs, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Object> getOrganization(@PathVariable("id") String id) {
		OrganizationDTO organizationDTO = orgService.getOrganization(id);

		if (organizationDTO != null) {
			return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("" + id);
		}

	}

	@RequestMapping(method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<OrganizationDTO> saveCommodity(@RequestBody OrganizationDTO organization) throws Exception {
		organization = orgService.saveOrganization(organization);
		return new ResponseEntity<>(organization, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = { "application/json" })
	public ResponseEntity<OrganizationDTO> updateCommodity(@RequestBody OrganizationDTO organization) throws Exception {
		organization = orgService.updateOrganization(organization);
		return new ResponseEntity<>(organization, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<Object> deleteCommodity(@PathVariable("id") String id) {
		try {
			orgService.deleteOrganization(id);
			return new ResponseEntity<>(new HashMap().put("msg", "Record Deleted Successfully"), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(id);
		}
	}
}
