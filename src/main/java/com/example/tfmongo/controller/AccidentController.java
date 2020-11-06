package com.example.tfmongo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;

import com.example.tfmongo.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.tfmongo.services.IAccidentService;

@RestController
public class AccidentController {

	@Inject
	private IAccidentService accidentsService;

	@GetMapping(value = "/api/accident")
	public ResponseEntity<?> getAccidents() {

		ResponseEntity<?> response = null;
		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();

		result.addAll(this.getAccidentsService().getAccidents());

		response = ResponseEntity.ok(result);

		return response;
	}

	@GetMapping(value = "/api/accident/datesBetween")
	public ResponseEntity<?> getAccidentsDatesBetween(@RequestParam String desde, @RequestParam String hasta) {
		ResponseEntity<?> response = null;
		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
		result.addAll(this.getAccidentsService().getAccidentsDatesBetween(desde, hasta));
		response = ResponseEntity.ok(result);

		return response;
	}

	@GetMapping(value = "/api/accident/commonTwilight")
	public ResponseEntity<?> getCommonConditions() {

		ResponseEntity<?> response = null;
		Collection<AccidentCommonTwilightDTO> result = new ArrayList<AccidentCommonTwilightDTO>();
		result.addAll(this.getAccidentsService().getCommonConditions());
		response = ResponseEntity.ok(result);
		return response;
	}

	@GetMapping(value = "/api/accident/accidentsInRadius")
	public ResponseEntity<?> getAccidentsInRadius(@RequestParam float lng, @RequestParam float lat, @RequestParam float rad) {

		ResponseEntity<?> response = null;
		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
		result.addAll(this.getAccidentsService().getAccidentsInRadius(lng, lat, rad));
		response = ResponseEntity.ok(result);

		return response;
	}

	/**@GetMapping(value = "/api/accident/accidentsDangerousPoints")
	public ResponseEntity<?> getAccidentsDangerousPoints(@RequestParam Float lat, @RequestParam Float lng, @RequestParam Integer rad) {

		ResponseEntity<?> response = null;
		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
		result.addAll(this.getAccidentsService().getAccidentsDangerousPoints(lat, lng, rad));
		response = ResponseEntity.ok(result);

		return response;
	}*/

	@GetMapping(value = "/api/accident/accidentsDangerousPoints")
	public ResponseEntity<?> getAccidentsDangerousPoints(@RequestParam Float lat, @RequestParam Float lng, @RequestParam Integer rad) {

		ResponseEntity<?> response = null;
		Collection<AccidentDangerousPointsDTO> result = new ArrayList<AccidentDangerousPointsDTO>();
		result.addAll(this.getAccidentsService().getAccidentsDangerousPoints(lat, lng, rad));
		response = ResponseEntity.ok(result);

		return response;
	}

	@GetMapping(value = "/api/accident/avgDistance")
	public Double getAverageDistance() {
		return this.getAccidentsService().getAverageDistance();
	}

	/**@PostMapping(value = "/api/accident")
	public ResponseEntity<?> saveAccidents(@RequestBody AccidentRequestDTO request) {

		ResponseEntity<?> response = null;

		this.getAccidentsService().saveAccident(request.getDescription());

		response = ResponseEntity.ok().build();

		return response;
	}*/

	public IAccidentService getAccidentsService() {
		return this.accidentsService;
	}

}
