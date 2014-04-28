package uk.commonline.weather.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.persist.LocationDAO;
import uk.commonline.weather.service.WeatherService;

public class HistoryController implements Controller {

	private LocationDAO locationDAO;

	private WeatherService weatherService;
	
    public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String zip = request.getParameter("zip");
		Location location = locationDAO.findByZip(zip);
		List<Weather> weathers = weatherService.getRecentWeather(location);
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put( "location", location );
		model.put( "weathers", weathers );
		
		return new ModelAndView("history", model);
	}

	public LocationDAO getLocationDAO() {
		return locationDAO;
	}

	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}
	
	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

}