package com.masters.organization.service.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masters.dao.IdGeneratorDao;
import com.masters.exception.EntityNotFoundException;
import com.masters.helper.EntityConversion;
import com.masters.model.IdGenerator;
import com.masters.organization.dao.OrganizationDao;
import com.masters.organization.model.Organization;
import com.masters.organization.model.dto.OrganizationDTO;
import com.masters.organization.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	IdGeneratorDao idGeneratorDao;
	
	@Override
	public List<OrganizationDTO> getOrgList(String organizationId, String organizationName, String organizationCity,
			ZonedDateTime startDate, ZonedDateTime endDate) {
		// TODO Auto-generated method stub
		List<OrganizationDTO> organizationDTOs = new ArrayList<>();
		List<Organization> organizations = new ArrayList<>();

		organizations = organizationDao.getOrganizations(organizationId, organizationName, organizationCity, startDate, endDate);

		for (Organization organization : organizations) {
			OrganizationDTO dto = new OrganizationDTO();
			dto = (OrganizationDTO) EntityConversion.convertModel(organization, dto,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
			organizationDTOs.add(dto);
		}

		return organizationDTOs;

	}
	
	@Override
	public OrganizationDTO getOrganization(String id) {
		// TODO Auto-generated method stub
		Organization organization = organizationDao.getOrganization(id);
		OrganizationDTO dto = null;
		if (organization != null) {
			dto = new OrganizationDTO();
			dto = (OrganizationDTO) EntityConversion.convertModel(organization, dto,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		}

		return dto;
	}

	@Override
	public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) throws Exception {
		// TODO Auto-generated method stub
		Organization organization = new Organization();
		organization = (Organization) EntityConversion.convertModel(organization, organizationDTO,
				EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
		organization.setCreatedDate(ZonedDateTime.now());

		IdGenerator idGenerator = idGeneratorDao.getNewId("ORG");

		organization.setOrganizationId(idGenerator.getIdKey() + "" + idGenerator.getIdValue());
		try {
			organization = organizationDao.saveOrganization(organization);

			organizationDTO = (OrganizationDTO) EntityConversion.convertModel(organization, organizationDTO,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		} catch (HibernateException e) {
			throw new Exception(e.getMessage());
		}

		return organizationDTO;
	}

	@Override
	public OrganizationDTO updateOrganization(OrganizationDTO organizationDTO) throws Exception {
		// TODO Auto-generated method stub
		Organization organization = new Organization();
		organization = (Organization) EntityConversion.convertModel(organization, organizationDTO,
				EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
		organization.setModifiedDate(ZonedDateTime.now());
		try {
			organization = organizationDao.updateOrganization(organization);
			organizationDTO = (OrganizationDTO) EntityConversion.convertModel(organization, organizationDTO,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		} catch (HibernateException e) {
			throw new Exception(e.getMessage());
		}
		return organizationDTO;
	}

	@Override
	public void deleteOrganization(String id) {
		// TODO Auto-generated method stub
		try {
			organizationDao.deleteOrganization(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(id);
        }
	}

}
