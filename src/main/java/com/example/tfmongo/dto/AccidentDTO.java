package com.example.tfmongo.dto;

import com.example.tfmongo.model.Accident;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class AccidentDTO {

	public String id;
	public String city;
	public String description;
	public String startTime;
	public Integer severity;
	public Float startLat;
	public Float startLng;
	public String location;
	public String weatherCondition;
	public String twilight;


	public AccidentDTO(Accident anAccident) {
		this.setId(anAccident.getId());
		this.setCity(anAccident.getCity());
		this.setDescription(anAccident.getDescription());
		this.setStartTime(anAccident.getStartTime());
		this.setSeverity(anAccident.getSeverity());
		this.setStartLat(anAccident.getStartLat());
		this.setStartLng(anAccident.getStartLng());
		this.setLocation(anAccident.getLocation().toString());
		this.setWeatherCondition(anAccident.getWeatherCondition());
		this.setTwilight(anAccident.getTwilight());
	}

	public String getId() {
		return this.id;
	}

	public void setId(String anId) {
		this.id = anId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String aCity) {
		this.city = aCity;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String aDescription) { this.description = aDescription;	}

	public String getStartTime() { return this.startTime; }

	public void setStartTime(String aStartTime) { this.startTime = aStartTime;}

	public Integer getSeverity() { return this.severity; }

	public void setSeverity(Integer aSeverity) { this.severity = aSeverity;	}

	public Float getStartLat() {return this.startLat;	}

	public void setStartLat(Float aStartLat) { this.startLat = aStartLat; }

	public Float getStartLng() {return this.startLng;	}

	public void setStartLng(Float aStartLng) { this.startLng = aStartLng; }

	public String getLocation() { return this.location;	}

	public void setLocation(String aLocation) { this.location = aLocation;}

	public String getWeatherCondition() { return this.weatherCondition;	}

	public void setWeatherCondition(String aWeatherCondition) {	this.weatherCondition = aWeatherCondition;	}

	public String getTwilight() { return this.twilight; }

	public void setTwilight(String aTwilight) { this.twilight = aTwilight; }
}
