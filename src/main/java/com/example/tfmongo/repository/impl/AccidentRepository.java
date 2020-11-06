package com.example.tfmongo.repository.impl;

import com.example.tfmongo.model.Accident;
import com.example.tfmongo.model.AccidentCommonTwilight;
import com.example.tfmongo.model.AccidentDangerousPoints;


import com.example.tfmongo.repository.IAccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class AccidentRepository implements IAccidentRepository {

    @Autowired
    private final MongoTemplate mongoTemplate;

    public AccidentRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /** 2 - Determinar las condiciones más comunes en los accidentes */
    @Override
    public Collection<AccidentCommonTwilight> findCommonTwilight() {
        MatchOperation match0 = match(new Criteria("Astronomical_Twilight").ne(null));
        GroupOperation group = group("Astronomical_Twilight").count().as("cont");
        MatchOperation match1 = match(new Criteria("cont").gt(0));
        SortOperation sort = sort(Sort.Direction.DESC, "cont");
        Aggregation aggregation = Aggregation.newAggregation(match0, group, match1, sort);
        AggregationResults<AccidentCommonTwilight> result = mongoTemplate.aggregate(aggregation, "accidentes", AccidentCommonTwilight.class);
        return result.getMappedResults();
    }

    /** 3 - Dado un punto geográfico y un radio (expresado en kilómetros) devolver todos los accidentes
     * ocurridos dentro del radio
    @Override
    public Collection<Accident> findAccidentsInRadius(Float pLat, Float pLng, Integer pRad) {
        BasicQuery query = new BasicQuery("{start_location:{ $nearSphere: { $geometry: { type: 'Point',coordinates: ["+ pLat +","+ pLng +" ] }, $minDistance: 10, $maxDistance: "+ pRad * 1000 +"}}}");
        query.with(Sort.by(Sort.Direction.DESC, "Severity"));
        return mongoTemplate.find(query.limit(5), Accident.class);
        return mongoTemplate.find(query, Accident.class);
    }*/

    /**La consulta permite obtener los accidentes cuyas coordenadas caen dentro de la circunferencia con centro en
     * las coordenadas y radio indicados (el radio se expresa en Km) */
    @Override
    public Collection<Accident> findAccidentsInRadius(float pLng, float pLat, float pRad) {
        BasicQuery query = new BasicQuery("{start_location: { $geoWithin: { $centerSphere: [ [" + pLng + "," + pLat + "], " + pRad / 6371 + "] }}})");
        return mongoTemplate.find(query, Accident.class);
    }

    /** 4 - Devolver los 5 puntos más peligrosos (definiendo un determinado radio en Km)
    @Override
    public Collection<Accident> getAccidentsDangerousPoints(Float pLat, Float pLng, Integer pRad) {
        BasicQuery query = new BasicQuery("{start_location:{ $nearSphere: { $geometry: { type: 'Point',coordinates: ["+ pLat +","+ pLng +" ] }, $maxDistance: "+ pRad * 1000 +"}}}");
        query.with(Sort.by(Sort.Direction.DESC, "Severity"));
        return mongoTemplate.find(query.limit(5), Accident.class);
    }*/

    @Override
    public Collection<AccidentDangerousPoints> findAccidentsDangerousPoints(Float pLat, Float pLng, Integer pRad) {
        NearQuery nearQuery = NearQuery.near(new Point(pLat, pLng), Metrics.KILOMETERS)
                .maxDistance(pRad)
                .spherical(true);

        GeoNearOperation geoNearOperation = geoNear(nearQuery, "distance.calculated");
        GroupOperation groupOperation = group("start_location").count().as("cont");
        MatchOperation matchOperation = match(new Criteria("cont").gt(0));
        SortOperation sortOperation = sort(Sort.Direction.DESC, "cont");
        LimitOperation limitOperation = limit(5);
        Aggregation aggregation = Aggregation.newAggregation(geoNearOperation, groupOperation, matchOperation, sortOperation, limitOperation);
        AggregationResults<AccidentDangerousPoints> result = mongoTemplate.aggregate(aggregation, "accidentes", AccidentDangerousPoints.class);
        return result.getMappedResults();
    }
}
