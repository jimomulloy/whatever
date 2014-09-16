package uk.commonline.weather.persist;

import uk.commonline.data.access.Dao;
import uk.commonline.weather.model.Location;

public interface LocationDAO extends Dao<Location> {

    Location findByZip(final String zip);

}