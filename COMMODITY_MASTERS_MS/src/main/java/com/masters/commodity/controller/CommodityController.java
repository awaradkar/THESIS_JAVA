package com.masters.commodity.controller;

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

import com.masters.commodity.model.Commodity;
import com.masters.commodity.model.dto.CommodityDTO;
import com.masters.commodity.service.CommodityService;
import com.masters.exception.EntityNotFoundException;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/commodity")
public class CommodityController {

	@Autowired
	CommodityService commService;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<Commodity>> commodityList(@RequestParam(required = false) String commodityId,
			@RequestParam(required = false) String commodityName,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
		List<Commodity> commoditys = new ArrayList<>();

		commoditys = commService.getCommList(commodityId, commodityName, startDate, endDate);

		return new ResponseEntity<>(commoditys, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Object> getCommodity(@PathVariable("id") String id) {
		Commodity commodity = commService.getCommodity(id);

        if (commodity != null) {
            return new ResponseEntity<>(commodity, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("" + id);
        }

    }
	
	@RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<CommodityDTO> saveCommodity(@RequestBody CommodityDTO commodity) throws Exception {
        commodity = commService.saveCommodity(commodity);
        return new ResponseEntity<>(commodity, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<CommodityDTO> updateCommodity(@RequestBody CommodityDTO commodity) throws Exception {
        commodity = commService.updateCommodity(commodity);
        return new ResponseEntity<>(commodity, HttpStatus.OK);
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    public ResponseEntity<Object> deleteCommodity(@PathVariable("id") String id) {
        try {
            commService.deleteCommodity(id);
            return new ResponseEntity<>(new HashMap().put("msg", "Record Deleted Successfully"), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(id);
        }
    }
}
