package com.example.tfmongo.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "accidentes")
public class Accident {

	@Id
	public String id;
	@Field("City")
	public String city;
	@Field("Description")
	public String description;
	@Field("Start_Time")
	public String startTime;
	@Field("Severity")
	public Integer severity;
	@Field("Start_Lat")
	public Float startLat;
	@Field("Start_Lng")
	public Float startLng;
	@Field("start_location")
	public Point location;
	@Field("Weather_Condition")
	public String weatherCondition;
	@Field("Astronomical_Twilight")
	public String twilight;


	public Accident(@Value("city") String aCity, @Value("description") String aDescription,
					@Value("startTime") String aStartTime, @Value("severity") Integer aSeverity,
					@Value("startLat") Float aStartLat, @Value("startLng") Float aStartLng,
					@Value("location") Point aLocation, @Value("weatherCondition") String aWeatherCondition,
					@Value("twilight") String aTwilight) {
		this.setCity(aCity);
		this.setDescription(aDescription);
		this.setStartTime(aStartTime);
		this.setSeverity(aSeverity);
		this.setStartLat(aStartLat);
		this.setStartLng(aStartLng);
		this.setLocation(aLocation);
		this.setWeatherCondition(aWeatherCondition);
		this.setTwilight(aTwilight);
	}

	public String getId() {	return this.id;	}

	public void setId(String anId) {this.id = anId;	}

	public String getCity() {return this.city;	}

	public void setCity(String aCity) {	this.city = aCity;	}

	public String getDescription() {return this.description;	}

	public void setDescription(String aDescription) { this.description = aDescription; }

	public String getStartTime() { return this.startTime;	}

	public void setStartTime(String aStartTime) { this.startTime = aStartTime;}

	public Integer getSeverity() {return this.severity;	}

	public void setSeverity(Integer aSeverity) { this.severity = aSeverity; }

	public Float getStartLat() {return this.startLat;	}

	public void setStartLat(Float aStartLat) { this.startLat = aStartLat; }

	public Float getStartLng() {return this.startLng;	}

	public void setStartLng(Float aStartLng) { this.startLng = aStartLng; }

	public Point getLocation() { return this.location;	}

	public void setLocation(Point aLocation) { this.location = aLocation;}

	public String getWeatherCondition() { return this.weatherCondition;	}

	public void setWeatherCondition(String aWeatherCondition) { this.weatherCondition = aWeatherCondition;	}

	public String getTwilight() { return this.twilight; }

	public void setTwilight(String aTwilight) { this.twilight = aTwilight; }
}
