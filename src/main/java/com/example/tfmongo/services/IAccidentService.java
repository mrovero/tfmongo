package com.example.tfmongo.services;

import java.util.Collection;

import com.example.tfmongo.dto.AccidentCommonTwilightDTO;
import com.example.tfmongo.dto.AccidentDTO;
import com.example.tfmongo.dto.AccidentDangerousPointsDTO;

public interface IAccidentService {

	Collection<AccidentDTO> getAccidents();

	Collection<AccidentDTO> getAccidentsDatesBetween(String pDesde, String pHasta);

	Collection<AccidentCommonTwilightDTO> getCommonConditions();

	Collection<AccidentDTO>getAccidentsInRadius(float pLng, float pLat, float pRad);

	Collection<AccidentDangerousPointsDTO> getAccidentsDangerousPoints(Float pLat, Float pLng, Integer pRad);

	Double getAverageDistance();

	/**public void saveAccident(String description);*/

}
