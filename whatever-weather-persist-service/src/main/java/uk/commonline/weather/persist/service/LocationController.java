package uk.commonline.weather.persist.service;

import javax.inject.Inject;
import javax.ws.rs.Path;

import uk.commonline.data.access.Dao;
import uk.commonline.data.service.jaxrs.AbstractCrudController;
import uk.commonline.weather.model.Location;
import uk.commonline.weather.persist.LocationDAO;

/**
 * Location controller.
 * 
 */
@Path("/location")
public class LocationController extends AbstractCrudController<Location> {

	@Inject
	LocationDAO service;
	
	@Override
	protected Dao<Location> getService() {
		return service;
	}

}
