package com.masters.organization.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.masters.organization.model.dto.OrganizationDTO;

public interface OrganizationService {

	List<OrganizationDTO> getOrgList(String organizationId, String organizationName, String organizationCity,
			ZonedDateTime startDate, ZonedDateTime endDate);

	OrganizationDTO getOrganization(String id);

	OrganizationDTO saveOrganization(OrganizationDTO organization) throws Exception;

	OrganizationDTO updateOrganization(OrganizationDTO organization) throws Exception;

	void deleteOrganization(String id);

}
