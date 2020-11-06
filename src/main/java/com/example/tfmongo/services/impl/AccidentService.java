package com.example.tfmongo.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.example.tfmongo.dto.AccidentDTO;
import com.example.tfmongo.dto.AccidentCommonTwilightDTO;
import com.example.tfmongo.dto.AccidentDangerousPointsDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tfmongo.repository.IAccidentRepository;
import com.example.tfmongo.services.IAccidentService;
import com.example.tfmongo.repository.IAccidentRepositoryDev;

@Service
@Transactional
public class AccidentService implements IAccidentService {

	@Inject
	public IAccidentRepository accidentRepository;

	@Inject
	public IAccidentRepositoryDev accidentRepositoryMongo;

	@Override
	public Collection<AccidentDTO> getAccidents() {
		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
		this.getAccidentRepositoryMongo().findAll().stream().map(a -> new AccidentDTO(a))
				.collect(Collectors.toCollection(() -> result));
		return result;
	}


	/** 1 - Devolver todos los accidentes ocurridos entre 2 fechas dadas - METODO MONGO*/
	@Override
	public Collection<AccidentDTO> getAccidentsDatesBetween(String pDesde, String pHasta) {
		long inicio = new Date().getTime();
		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
		this.getAccidentRepositoryMongo().findByStartTimeBetween(pDesde, pHasta).stream().map(a -> new AccidentDTO(a))
				.collect(Collectors.toCollection(() -> result));
		long fin = new Date().getTime();
		System.out.println("Tiempo Consulta N 1 - getAccidentsDatesBetween(): " + (fin - inicio) + " ms");
		return result;
	}


	@Override
	public Collection<AccidentCommonTwilightDTO> getCommonConditions() {
        long inicio = new Date().getTime();
		Collection<AccidentCommonTwilightDTO> result = new ArrayList<AccidentCommonTwilightDTO>();
		this.getAccidentRepository().findCommonTwilight().stream().map(a -> new AccidentCommonTwilightDTO(a))
				.collect(Collectors.toCollection(() -> result));
        long fin = new Date().getTime();
        System.out.println("Tiempo Consulta N 2 - getCommonConditions(): " + (fin - inicio) + " ms");
		return result;
	}

	@Override
	public Collection<AccidentDTO> getAccidentsInRadius(float pLng, float pLat, float pRad) {
        long inicio = new Date().getTime();
		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
		this.getAccidentRepository().findAccidentsInRadius(pLng, pLat, pRad).stream().map(a -> new AccidentDTO(a))
				.collect(Collectors.toCollection(() -> result));
        long fin = new Date().getTime();
        System.out.println("Tiempo Consulta N 3 - getAccidentsInRadius(): " + (fin - inicio) + " ms");
		return result;
	}

	@Override
	public Collection<AccidentDangerousPointsDTO> getAccidentsDangerousPoints(Float pLat, Float pLng, Integer pRad) {
        long inicio = new Date().getTime();
	    Collection<AccidentDangerousPointsDTO> result = new ArrayList<AccidentDangerousPointsDTO>();
		this.getAccidentRepository().findAccidentsDangerousPoints(pLat, pLng, pRad).stream().map(a -> new AccidentDangerousPointsDTO(a))
				.collect(Collectors.toCollection(() -> result));
        long fin = new Date().getTime();
        System.out.println("Tiempo Consulta N 4 - getAccidentsDangerousPoints(): " + (fin - inicio) + " ms");
		return result;
	}

	@Override
	public Double getAverageDistance() {
		return this.getAccidentRepositoryMongo().getAverageDistance() * 1609;
	}


	/**@Override
	public void saveAccident(String aDescription) {
		Accident newAccident = new Accident(new Date(), aDescription);
		this.getAccidentRepositoryMongo().save(newAccident);

	}*/

	public IAccidentRepository getAccidentRepository() {
		return this.accidentRepository;
	}

	public void setAccidentRepository(IAccidentRepository aRepository) {
		this.accidentRepository = aRepository;
	}

	public IAccidentRepositoryDev getAccidentRepositoryMongo() {
		return this.accidentRepositoryMongo;
	}

	public void setAccidentRepository(IAccidentRepositoryDev aRepositoryMongo) { 	this.accidentRepositoryMongo = aRepositoryMongo;
	}

}
