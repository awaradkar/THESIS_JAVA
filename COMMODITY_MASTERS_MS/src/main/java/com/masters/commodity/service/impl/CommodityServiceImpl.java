package com.masters.commodity.service.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masters.commodity.dao.CommodityDao;
import com.masters.commodity.model.Commodity;
import com.masters.commodity.model.dto.CommodityDTO;
import com.masters.commodity.service.CommodityService;
import com.masters.dao.IdGeneratorDao;
import com.masters.exception.EntityNotFoundException;
import com.masters.helper.EntityConversion;
import com.masters.model.IdGenerator;

@Service
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	CommodityDao commDao;

	@Autowired
	IdGeneratorDao idGeneratorDao;

	@Override
	public List<Commodity> getCommList(String commodityId, String commodityName, ZonedDateTime startDate,
			ZonedDateTime endDate) {
		// TODO Auto-generated method stub
		// List<CommodityDTO> commodityDTOs = new ArrayList<>();
		List<Commodity> commodities = new ArrayList<>();

		commodities = commDao.getCommodities(commodityId, commodityName, startDate, endDate);

		/*
		 * for (Commodity commodity : commodities) { CommodityDTO dto = new
		 * CommodityDTO(); dto = (CommodityDTO) EntityConversion.convertModel(commodity,
		 * dto, EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		 * commodityDTOs.add(dto); }
		 */

		return commodities;
	}

	@Override
	public Commodity getCommodity(String id) {
		// TODO Auto-generated method stub
		Commodity commodity = commDao.getCommodity(id);
		/*
		 * CommodityDTO dto = null; if (commodity != null) { dto = new CommodityDTO();
		 * dto = (CommodityDTO) EntityConversion.convertModel(commodity, dto,
		 * EntityConversion.ConversionEnum.ENTITYTODTO.ordinal()); }
		 */

		return commodity;
	}

	@Override
	public CommodityDTO saveCommodity(CommodityDTO commodityDTO) throws Exception {
		// TODO Auto-generated method stub
		Commodity commodity = new Commodity();

		commodity = (Commodity) EntityConversion.convertModel(commodity, commodityDTO,
				EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
		commodity.setCreatedDate(ZonedDateTime.now());

		IdGenerator idGenerator = idGeneratorDao.getNewId("COMM");

		commodity.setCommodityId(idGenerator.getIdKey() + "" + idGenerator.getIdValue());
		try {
			commodity = commDao.saveCommodity(commodity);

			commodityDTO = (CommodityDTO) EntityConversion.convertModel(commodity, commodityDTO,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		} catch (HibernateException e) {
			throw new Exception(e.getMessage());
		}

		return commodityDTO;
	}

	@Override
	public CommodityDTO updateCommodity(CommodityDTO commodityDTO) throws Exception {
		// TODO Auto-generated method stub
		Commodity commodity = new Commodity();
		commodity = (Commodity) EntityConversion.convertModel(commodity, commodityDTO,
				EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
		commodity.setModifiedDate(ZonedDateTime.now());
		try {
			commodity = commDao.updateCommodity(commodity);
			commodityDTO = (CommodityDTO) EntityConversion.convertModel(commodity, commodityDTO,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		} catch (HibernateException e) {
			throw new Exception(e.getMessage());
		}
		return commodityDTO;
	}

	@Override
	public void deleteCommodity(String id) {
		// TODO Auto-generated method stub
		try {
			commDao.deleteCommodity(id);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(id);
		}
	}

}
