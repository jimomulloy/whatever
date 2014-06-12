package uk.commonline.weather.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.commonline.weather.man.client.jaxrs.WeatherManClient;
import uk.commonline.weather.model.Weather;

@Controller
//@RequestMapping(value = "/weather")
public class WeatherController {

	@Inject
	private WeatherManClient weatherManClient;

	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String zip = request.getParameter("zip");
		Weather weather = weatherManClient.updateForecast(zip);
		return new ModelAndView("weather", "weather", weather);
	}

	public WeatherManClient getWeatherManClient() {
		return weatherManClient;
	}

	public void setWeatherManClient(WeatherManClient weatherManClient) {
		this.weatherManClient = weatherManClient;
	}

}