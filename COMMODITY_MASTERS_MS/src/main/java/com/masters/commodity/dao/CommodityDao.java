package com.masters.commodity.dao;

import java.time.ZonedDateTime;
import java.util.List;

import com.masters.commodity.model.Commodity;

public interface CommodityDao {

	List<Commodity> getCommodities(String commodityId, String commodityName, ZonedDateTime startDate,
			ZonedDateTime endDate);

	Commodity getCommodity(String id);

	Commodity saveCommodity(Commodity commodity);

	Commodity updateCommodity(Commodity commodity);

	void deleteCommodity(String id);

}
