package com.masters.organization.controller;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masters.exception.EntityNotFoundException;
import com.masters.organization.model.dto.WarehouseCommmodityMapDTO;
import com.masters.organization.service.WareCommMapService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/wareCommMap")
public class WareCommMapController {

	@Autowired
	WareCommMapService wareCommMapService;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<WarehouseCommmodityMapDTO>> getWareCommList(
			@RequestParam(required = false) String warehouseCode, @RequestParam(required = false) String warehouseName,
			@RequestParam(required = false) String commodityCode, @RequestParam(required = false) String commodityName,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
		List<WarehouseCommmodityMapDTO> warehouseCommmodityMapDTOs = new ArrayList<>();

		warehouseCommmodityMapDTOs = wareCommMapService.getWareCommList(warehouseCode, warehouseName, commodityCode,
				commodityName, startDate, endDate);

		return new ResponseEntity<>(warehouseCommmodityMapDTOs, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Object> getWarehouseCommodityMap(@PathVariable("id") String id) {
		WarehouseCommmodityMapDTO wareCommDTO = wareCommMapService.getWareCommMap(id);

		if (wareCommDTO != null) {
			return new ResponseEntity<>(wareCommDTO, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("" + id);
		}

	}

	@RequestMapping(method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<WarehouseCommmodityMapDTO> saveWareCommMap(
			@RequestBody WarehouseCommmodityMapDTO wareCommmodityMapDTO) throws Exception {
		wareCommmodityMapDTO = wareCommMapService.saveWareCommMap(wareCommmodityMapDTO);
		return new ResponseEntity<>(wareCommmodityMapDTO, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = { "application/json" })
	public ResponseEntity<WarehouseCommmodityMapDTO> updateWareCommMap(
			@RequestBody WarehouseCommmodityMapDTO warehouseCommmodityMapDTO) throws Exception {
		warehouseCommmodityMapDTO = wareCommMapService.updateWareCommMap(warehouseCommmodityMapDTO);
		return new ResponseEntity<>(warehouseCommmodityMapDTO, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<Object> deleteCommodity(@PathVariable("id") String id) {
		try {
			wareCommMapService.deleteWareCommMap(id);
			return new ResponseEntity<>(new HashMap().put("msg", "Record Deleted Successfully"), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(id);
		}
	}

}
