package uk.commonline.weather.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@XmlRootElement
public class WeatherReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date date;

    private long region;

    private Double latitude;

    private Double longitude;

    private Map<String, WeatherSourceData> sourceMap = new HashMap<String, WeatherSourceData>();

    public WeatherReport() {
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public Map<String, WeatherSourceData> getSourceMap() {
	return sourceMap;
    }

    public void setSourceMap(Map<String, WeatherSourceData> sourceMap) {
	this.sourceMap = sourceMap;
    }

    public long getRegion() {
	return region;
    }

    public void setRegion(long region) {
	this.region = region;
    }

    public Double getLatitude() {
	return latitude;
    }

    public void setLatitude(Double latitude) {
	this.latitude = latitude;
    }

    public Double getLongitude() {
	return longitude;
    }

    public void setLongitude(Double longitude) {
	this.longitude = longitude;
    }

    public void clearBackReferences() {
	for (WeatherSourceData wsd : sourceMap.values()) {
	    List<Weather> ws = wsd.getRecordings();
	    for (Weather w : ws) {
		w.clearBackReferences();
	    }
	    List<WeatherForecast> fs = wsd.getForecasts();
	    for (WeatherForecast f : fs) {
		f.clearBackReferences();
	    }
	}
    }

    public void setBackReferences() {
	for (WeatherSourceData wsd : sourceMap.values()) {
	    List<Weather> ws = wsd.getRecordings();
	    for (Weather w : ws) {
		w.setBackReferences();
	    }
	    List<WeatherForecast> fs = wsd.getForecasts();
	    for (WeatherForecast f : fs) {
		f.setBackReferences();
	    }
	}
    }

    public class WeatherSourceData {

	private List<WeatherForecast> forecasts = new ArrayList<WeatherForecast>();

	private List<Weather> recordings = new ArrayList<Weather>();

	public List<WeatherForecast> getForecasts() {
	    return forecasts;
	}

	public void setForecasts(List<WeatherForecast> forecasts) {
	    this.forecasts = forecasts;
	}
	
	public List<Weather> getRecordings() {
	    return recordings;
	}

	public void setRecordings(List<Weather> recordings) {
	    this.recordings = recordings;
	}

	public Weather getCurrentWeather() {
	    Weather currentWeather = null;
	    for (Weather w : recordings) {
		if(currentWeather == null || w.getSourceTime().after(currentWeather.getSourceTime())){
		    currentWeather = w;
		}
	    }
	    return currentWeather;
	}
	
	public List<WeatherForecast> getCurrentForecasts() {
	    Weather currentWeather = getCurrentWeather();
	    List<WeatherForecast> currentForecasts = new ArrayList<WeatherForecast>();
	    if(currentWeather == null){
		return currentForecasts;
	    }
	    for (WeatherForecast f : forecasts) {
		if(f.getSourceTime().after(currentWeather.getSourceTime())){
		    currentForecasts.add(f);
		}
	    }
	    return currentForecasts;
	}

	public List<WeatherForecast> getRetrospectiveForecasts() {
	    Weather currentWeather = getCurrentWeather();
	    List<WeatherForecast> retrospectiveForecasts = new ArrayList<WeatherForecast>();
	    if(currentWeather == null){
		return retrospectiveForecasts;
	    }
	    for (WeatherForecast f : forecasts) {
		if(f.getSourceTime().before(currentWeather.getSourceTime())){
		    retrospectiveForecasts.add(f);
		}
	    }
	    return retrospectiveForecasts;
	}
	
	public List<Weather> getHistoricWeather() {
	    Weather currentWeather = getCurrentWeather();
	    List<Weather> historicWeather = new ArrayList<Weather>();
	    if(currentWeather == null){
		return historicWeather;
	    }
	    for (Weather w : recordings) {
		if(w.getSourceTime().before(currentWeather.getSourceTime())){
		    historicWeather.add(w);
		}
	    }
	    return historicWeather;
	}
    }

}