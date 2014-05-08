package uk.commonline.weather.persist.service;

import java.util.List;

import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;

public interface WeatherDataService {

	Weather updateForecast(String zip) throws Exception ;
    
	List<Weather> getRecentWeather(Location location) throws Exception;

	Weather retrieveForecast(String zip) throws Exception;
}
