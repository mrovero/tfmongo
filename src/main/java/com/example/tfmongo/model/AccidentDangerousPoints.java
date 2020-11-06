package com.example.tfmongo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accidentes")
public class AccidentDangerousPoints {
    public String id;

    public Float startLat;

    public Float startLng;

    private Integer cont;

    public AccidentDangerousPoints(String id,
                                   Integer cont) {
        this.setId(id);
        this.setStartLat(Float.parseFloat(id.substring(17,26)));
        this.setStartLng(Float.parseFloat(id.substring(29,37)));
        this.setCont(cont);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getStartLat() {return this.startLat;	}

    public void setStartLat(Float startLat) { this.startLat = startLat; }

    public Float getStartLng() {return this.startLng;	}

    public void setStartLng(Float startLng) { this.startLng = startLng; }

    public Integer getCont() {
        return cont;
    }

    public void setCont(Integer cont) {
        this.cont = cont;
    }
}
