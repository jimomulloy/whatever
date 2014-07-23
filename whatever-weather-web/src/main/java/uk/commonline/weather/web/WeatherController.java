package uk.commonline.weather.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.commonline.weather.man.client.jaxrs.WeatherManClient;
import uk.commonline.weather.model.WeatherReport;
import uk.commonline.weather.model.WeatherUserLogin;

@Controller
// @RequestMapping(value = "/weather")
public class WeatherController {

    @Inject
    private WeatherManClient weatherManClient;

    public WeatherManClient getWeatherManClient() {
	return weatherManClient;
    }

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

	String latitude = request.getParameter("latitude");
	String longitude = request.getParameter("longitude");
	WeatherReport report = weatherManClient.updateWeather(Double.parseDouble(latitude), Double.parseDouble(longitude));
	Double latValue = 0.0, longValue = 0.0;
	try {
	    latValue = Double.parseDouble(latitude);
	} catch (Exception ex) {

	}
	try {
	    longValue = Double.parseDouble(longitude);
	} catch (Exception ex) {

	}
	report.setLatitude(latValue);
	report.setLongitude(longValue);
	WeatherUserLogin weatherUser = new WeatherUserLogin();
	weatherUser.setLatitude(Double.parseDouble(latitude));
	weatherUser.setLongitude(Double.parseDouble(longitude));
	ModelAndView mav = new ModelAndView("home", "report", report);
	mav.addObject("weatherUser", weatherUser);
	return mav;
    }

    public void setWeatherManClient(WeatherManClient weatherManClient) {
	this.weatherManClient = weatherManClient;
    }

}