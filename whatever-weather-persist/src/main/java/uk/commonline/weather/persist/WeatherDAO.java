package uk.commonline.weather.persist;

import java.util.List;

import uk.commonline.data.access.Dao;
import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;

public interface WeatherDAO extends Dao<Weather> {
	
	List<Weather> recentForLocation(final Location location);

}