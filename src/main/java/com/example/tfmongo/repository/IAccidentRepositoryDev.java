package com.example.tfmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tfmongo.model.Accident;

import java.util.Date;
import java.util.List;

public interface IAccidentRepositoryDev extends MongoRepository<Accident, String>{
    /** 1 - Devolver todos los accidentes ocurridos entre 2 fechas dadas */
    @Query("{Start_Time : {$gte:?0, $lte:?1}}")
    List<Accident> findByStartTimeBetween(String pDesde, String pHasta);

    /** 5 - Obtener la distancia promedio desde el inicio al fin del accidente */
    @Aggregation("{'$group':{'_id':null, 'avg':{'$avg':'$Distance(mi)'}}}")
    Double getAverageDistance();
}
