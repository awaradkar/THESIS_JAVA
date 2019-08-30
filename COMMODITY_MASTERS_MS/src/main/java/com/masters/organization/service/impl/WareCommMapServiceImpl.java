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
import com.masters.organization.dao.WareCommMapDao;
import com.masters.organization.model.WarehouseCommmodityMap;
import com.masters.organization.model.dto.WarehouseCommmodityMapDTO;
import com.masters.organization.service.WareCommMapService;

@Service
public class WareCommMapServiceImpl implements WareCommMapService {

	@Autowired
	WareCommMapDao wareCommMapDao;

	@Autowired
	IdGeneratorDao idGeneratorDao;

	@Override
	public List<WarehouseCommmodityMapDTO> getWareCommList(String warehouseCode, String warehouseName,
			String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<WarehouseCommmodityMapDTO> wareCommMapDTOs = new ArrayList<>();
		List<WarehouseCommmodityMap> wareCommMapList = new ArrayList<>();

		wareCommMapList = wareCommMapDao.getWareCommMapList(warehouseCode, warehouseName, commodityCode, commodityName,
				startDate, endDate);

		for (WarehouseCommmodityMap warehouseCommmodityMap : wareCommMapList) {
			WarehouseCommmodityMapDTO dto = new WarehouseCommmodityMapDTO();
			dto = (WarehouseCommmodityMapDTO) EntityConversion.convertModel(warehouseCommmodityMap, dto,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
			wareCommMapDTOs.add(dto);
		}

		return wareCommMapDTOs;

	}

	@Override
	public WarehouseCommmodityMapDTO getWareCommMap(String id) {
		// TODO Auto-generated method stub
		WarehouseCommmodityMap warehouseCommmodityMap = wareCommMapDao.getWarehouseCommmodityMap(id);
		WarehouseCommmodityMapDTO dto = null;
		if (warehouseCommmodityMap != null) {
			dto = new WarehouseCommmodityMapDTO();
			dto = (WarehouseCommmodityMapDTO) EntityConversion.convertModel(warehouseCommmodityMap, dto,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		}

		return dto;
	}

	@Override
	public WarehouseCommmodityMapDTO saveWareCommMap(WarehouseCommmodityMapDTO wareCommmodityMapDTO) throws Exception {
		// TODO Auto-generated method stub
		WarehouseCommmodityMap warehouseCommmodityMap = new WarehouseCommmodityMap();
		warehouseCommmodityMap = (WarehouseCommmodityMap) EntityConversion.convertModel(warehouseCommmodityMap,
				wareCommmodityMapDTO, EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
		warehouseCommmodityMap.setCreatedDate(ZonedDateTime.now());

		IdGenerator idGenerator = idGeneratorDao.getNewId("WHCOM");

		warehouseCommmodityMap.setWareCommId(idGenerator.getIdKey() + "" + idGenerator.getIdValue());
		try {
			warehouseCommmodityMap = wareCommMapDao.saveWareCommMap(warehouseCommmodityMap);

			wareCommmodityMapDTO = (WarehouseCommmodityMapDTO) EntityConversion.convertModel(warehouseCommmodityMap,
					wareCommmodityMapDTO, EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		} catch (HibernateException e) {
			throw new Exception(e.getMessage());
		}

		return wareCommmodityMapDTO;
	}

	@Override
	public WarehouseCommmodityMapDTO updateWareCommMap(WarehouseCommmodityMapDTO warehouseCommmodityMapDTO)
			throws Exception {
		// TODO Auto-generated method stub
		WarehouseCommmodityMap warehouseCommmodityMap = new WarehouseCommmodityMap();
		warehouseCommmodityMap = (WarehouseCommmodityMap) EntityConversion.convertModel(warehouseCommmodityMap,
				warehouseCommmodityMapDTO, EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
		warehouseCommmodityMap.setModifiedDate(ZonedDateTime.now());
		try {
			warehouseCommmodityMap = wareCommMapDao.updateWareCommMap(warehouseCommmodityMap);
			warehouseCommmodityMapDTO = (WarehouseCommmodityMapDTO) EntityConversion.convertModel(
					warehouseCommmodityMap, warehouseCommmodityMapDTO,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		} catch (HibernateException e) {
			throw new Exception(e.getMessage());
		}
		return warehouseCommmodityMapDTO;
	}

	@Override
	public void deleteWareCommMap(String id) {
		// TODO Auto-generated method stub
		try {
			wareCommMapDao.deleteWareCommMap(id);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(id);
		}
	}

}
