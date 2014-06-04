package uk.commonline.weather.persist.service;

import static org.mockito.Mockito.when;

import org.mockito.Mock;

import uk.commonline.weather.model.Location;
import uk.commonline.weather.persist.LocationDAO;

/**
 */
public class LocationCrudControllerTests extends AbstractCrudControllerTests<Location> {
	
	// Dependencies
	@Mock private LocationDAO service;
	
	// Test objects
	@Mock private Location location;
	
	/* (non-Javadoc)
	 * @see com.springinpractice.ch11.web.controller.AbstractCrudControllerTests#doSetUp()
	 */
	@Override
	protected void doSetUp() throws Exception {
		when(service.findByZip("")).thenReturn(location);
	}
}
