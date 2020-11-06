package com.example.tfmongo.repository;

import com.example.tfmongo.model.AccidentCommonTwilight;
import com.example.tfmongo.model.Accident;
import com.example.tfmongo.model.AccidentDangerousPoints;

import java.util.Collection;

public interface IAccidentRepository{

    Collection<AccidentCommonTwilight> findCommonTwilight();

    Collection<Accident> findAccidentsInRadius(float pLat, float pLng, float pRad);

    Collection<AccidentDangerousPoints> findAccidentsDangerousPoints(Float pLat, Float pLng, Integer pRad);
}
