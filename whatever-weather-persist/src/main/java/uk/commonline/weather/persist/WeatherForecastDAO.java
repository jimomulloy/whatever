package uk.commonline.weather.persist;

import java.util.Date;
import java.util.List;

import uk.commonline.data.access.Dao;
import uk.commonline.weather.model.WeatherForecast;

public interface WeatherForecastDAO extends Dao<WeatherForecast> {

    List<WeatherForecast> recentForRegion(final long region);
    
    List<WeatherForecast> getRange(final long region, final Date fromTime, final int hours, final int count);
    
    List<WeatherForecast> getRetro(final long region, final Date fromTime, final Date forecastTime, final int hours, final int count);

}