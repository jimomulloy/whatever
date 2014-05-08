package uk.commonline.weather.service;

import java.util.List;

import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;

public interface WeatherService {

	Weather updateForecast(String zip) throws Exception ;
    
	List<Weather> getRecentWeather(Location location) throws Exception;

	Weather retrieveForecast(String zip) throws Exception;
}
