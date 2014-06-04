package uk.commonline.weather.persist.service;

import javax.inject.Inject;
import javax.ws.rs.Path;

import uk.commonline.data.access.Dao;
import uk.commonline.data.service.jaxrs.AbstractCrudController;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.persist.WeatherDAO;

/**
 * Location controller.
 * 
 */
@Path("/location")
public class WeatherController extends AbstractCrudController<Weather> {

	@Inject
	WeatherDAO service;
	
	@Override
	protected Dao<Weather> getService() {
		return service;
	}

}
