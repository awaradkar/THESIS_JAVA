package com.masters.commodity.dao;

import java.time.ZonedDateTime;
import java.util.List;

import com.masters.commodity.model.Pack;

public interface PackDao {

	List<Pack> getPacks(String packId, String packType, ZonedDateTime startDate, ZonedDateTime endDate);

	Pack getPack(String id);

	Pack savePack(Pack pack);

	Pack updatePack(Pack pack);

	void deletePack(String id);

}
