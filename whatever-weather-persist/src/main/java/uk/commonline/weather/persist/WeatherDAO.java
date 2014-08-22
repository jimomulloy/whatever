package uk.commonline.weather.persist;

import java.util.Date;
import java.util.List;

import uk.commonline.data.access.Dao;
import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.model.WeatherForecast;

public interface WeatherDAO extends Dao<Weather> {

    List<Weather> recentForRegion(final long region);
    
    List<Weather> getRange(final long region, final Date fromTime, final int hours, final int count);
}