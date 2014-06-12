package uk.commonline.weather.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.commonline.weather.geo.client.jaxrs.GeoLocationClient;
import uk.commonline.weather.man.client.jaxrs.WeatherManClient;
import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;

@Controller
//@RequestMapping(value = "/history")
public class HistoryController {

	@Inject
	private GeoLocationClient geoLocationClient;

	@Inject
	private WeatherManClient weatherManClient;
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String zip = request.getParameter("zip");
		Location location = geoLocationClient.findByZip(zip);
		
		//List<Weather> weathers = weatherManClient.getRecentWeather(location);
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put( "location", location );
		//model.put( "weathers", weathers );
		
		return new ModelAndView("history", model);
	}
	
	public WeatherManClient getWeatherManService() {
		return weatherManClient;
	}

	public void setWeatherManService(WeatherManClient weatherManClient) {
		this.weatherManClient = weatherManClient;
	}	
	
	public GeoLocationClient getGeoLocationService() {
		return geoLocationClient;
	}

	public void setGeoLocationService(GeoLocationClient geoLocationClient) {
		this.geoLocationClient = geoLocationClient;
	}

}