package uk.commonline.weather.persist;

import java.util.List;

import uk.commonline.data.access.Dao;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.model.WeatherForecast;

public interface WeatherForecastDAO extends Dao<WeatherForecast> {

    List<WeatherForecast> recentForRegion(final long region);

}