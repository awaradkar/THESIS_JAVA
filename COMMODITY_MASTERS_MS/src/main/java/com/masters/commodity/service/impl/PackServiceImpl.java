package com.masters.commodity.service.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masters.commodity.dao.PackDao;
import com.masters.commodity.model.Pack;
import com.masters.commodity.model.dto.PackDTO;
import com.masters.commodity.service.PackService;
import com.masters.dao.IdGeneratorDao;
import com.masters.exception.EntityNotFoundException;
import com.masters.helper.EntityConversion;
import com.masters.model.IdGenerator;

@Service
public class PackServiceImpl implements PackService {

	@Autowired
	PackDao packDao;

	@Autowired
	IdGeneratorDao idGeneratorDao;

	@Override
	public List<PackDTO> getPackList(String packId, String packType, ZonedDateTime startDate, ZonedDateTime endDate) {
		// TODO Auto-generated method stub
		List<PackDTO> packDTOs = new ArrayList<>();
		List<Pack> packs = new ArrayList<>();

		packs = packDao.getPacks(packId, packType, startDate, endDate);

		for (Pack pack : packs) {
			PackDTO dto = new PackDTO();
			dto = (PackDTO) EntityConversion.convertModel(pack, dto,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
			packDTOs.add(dto);
		}

		return packDTOs;
	}

	@Override
	public PackDTO getPack(String id) {
		// TODO Auto-generated method stub
		Pack pack = packDao.getPack(id);
		PackDTO dto = null;
		if (pack != null) {
			dto = new PackDTO();
			dto = (PackDTO) EntityConversion.convertModel(pack, dto,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		}

		return dto;
	}

	@Override
	public PackDTO savePack(PackDTO packDTO) throws Exception {
		// TODO Auto-generated method stub
		Pack pack = new Pack();
		pack = (Pack) EntityConversion.convertModel(pack, packDTO,
				EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
		pack.setCreatedDate(ZonedDateTime.now());

		IdGenerator idGenerator = idGeneratorDao.getNewId("PACK");

		pack.setPackId(idGenerator.getIdKey() + "" + idGenerator.getIdValue());
		try {
			pack = packDao.savePack(pack);

			packDTO = (PackDTO) EntityConversion.convertModel(pack, packDTO,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		} catch (HibernateException e) {
			throw new Exception(e.getMessage());
		}

		return packDTO;
	}

	@Override
	public PackDTO updatePack(PackDTO packDTO) throws Exception {
		// TODO Auto-generated method stub
		Pack pack = new Pack();
		pack = (Pack) EntityConversion.convertModel(pack, packDTO,
				EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
		pack.setModifiedDate(ZonedDateTime.now());
		try {
			pack = packDao.updatePack(pack);
			packDTO = (PackDTO) EntityConversion.convertModel(pack, packDTO,
					EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
		} catch (HibernateException e) {
			throw new Exception(e.getMessage());
		}
		return packDTO;
	}

	@Override
	public void deletePack(String id) {
		// TODO Auto-generated method stub
		try {
			packDao.deletePack(id);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(id);
		}
	}

}
