package com.masters.organization.dao;

import java.time.ZonedDateTime;
import java.util.List;

import com.masters.organization.model.Organization;

public interface OrganizationDao {

	List<Organization> getOrganizations(String organizationId, String organizationName, String organizationCity,
			ZonedDateTime startDate, ZonedDateTime endDate);

	Organization getOrganization(String id);

	Organization saveOrganization(Organization organization);

	Organization updateOrganization(Organization organization);

	void deleteOrganization(String id);

}
