package com.masters.commodity.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.masters.commodity.model.dto.PackDTO;

public interface PackService {

	List<PackDTO> getPackList(String packId, String packType, ZonedDateTime startDate, ZonedDateTime endDate);

	PackDTO getPack(String id);

	PackDTO savePack(PackDTO pack) throws Exception;

	PackDTO updatePack(PackDTO pack) throws Exception;

	void deletePack(String id);

}
