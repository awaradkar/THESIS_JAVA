package com.masters.organization.dao;

import java.time.ZonedDateTime;
import java.util.List;

import com.masters.organization.model.WarehouseCommmodityMap;

public interface WareCommMapDao {

	List<WarehouseCommmodityMap> getWareCommMapList(String warehouseCode, String warehouseName, String commodityCode,
			String commodityName, ZonedDateTime startDate, ZonedDateTime endDate);

	WarehouseCommmodityMap getWarehouseCommmodityMap(String id);

	WarehouseCommmodityMap saveWareCommMap(WarehouseCommmodityMap warehouseCommmodityMap);

	WarehouseCommmodityMap updateWareCommMap(WarehouseCommmodityMap warehouseCommmodityMap);

	void deleteWareCommMap(String id);

}
