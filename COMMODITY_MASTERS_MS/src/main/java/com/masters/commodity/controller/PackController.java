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

import com.masters.commodity.model.dto.PackDTO;
import com.masters.commodity.service.PackService;
import com.masters.exception.EntityNotFoundException;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/packs")
public class PackController {

	@Autowired
	PackService packService;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<PackDTO>> packList(@RequestParam(required = false) String packId,
			@RequestParam(required = false) String packType,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
		List<PackDTO> packDTOs = new ArrayList<>();

		packDTOs = packService.getPackList(packId, packType, startDate, endDate);

		return new ResponseEntity<>(packDTOs, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Object> getPack(@PathVariable("id") String id) {
		PackDTO packDTO = packService.getPack(id);

		if (packDTO != null) {
			return new ResponseEntity<>(packDTO, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("" + id);
		}

	}

	@RequestMapping(method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<PackDTO> savePack(@RequestBody PackDTO pack) throws Exception {
		pack = packService.savePack(pack);
		return new ResponseEntity<>(pack, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = { "application/json" })
	public ResponseEntity<PackDTO> updatePack(@RequestBody PackDTO pack) throws Exception {
		pack = packService.updatePack(pack);
		return new ResponseEntity<>(pack, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<Object> deletePack(@PathVariable("id") String id) {
		try {
			packService.deletePack(id);
			return new ResponseEntity<>(new HashMap().put("msg", "Record Deleted Successfully"), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(id);
		}
	}

}
