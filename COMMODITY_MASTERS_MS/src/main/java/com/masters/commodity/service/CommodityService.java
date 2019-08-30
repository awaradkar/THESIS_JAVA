package com.masters.commodity.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.masters.commodity.model.Commodity;
import com.masters.commodity.model.dto.CommodityDTO;

public interface CommodityService {

	List<Commodity> getCommList(String commodityId, String commodityName, ZonedDateTime startDate,
			ZonedDateTime endDate);

	Commodity getCommodity(String id);

	CommodityDTO saveCommodity(CommodityDTO commodity) throws Exception;

	CommodityDTO updateCommodity(CommodityDTO commodity) throws Exception;

	void deleteCommodity(String id);

}
