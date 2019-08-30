package com.masters.organization.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.masters.organization.model.dto.WarehouseCommmodityMapDTO;

public interface WareCommMapService {

	List<WarehouseCommmodityMapDTO> getWareCommList(String warehouseCode, String warehouseName, String commodityCode,
			String commodityName, ZonedDateTime startDate, ZonedDateTime endDate);

	WarehouseCommmodityMapDTO getWareCommMap(String id);

	WarehouseCommmodityMapDTO saveWareCommMap(WarehouseCommmodityMapDTO wareCommmodityMapDTO) throws Exception;

	void deleteWareCommMap(String id);

	WarehouseCommmodityMapDTO updateWareCommMap(WarehouseCommmodityMapDTO warehouseCommmodityMapDTO) throws Exception;

}
