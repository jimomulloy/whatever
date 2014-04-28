package uk.commonline.weather.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import uk.commonline.weather.model.Weather;
import uk.commonline.weather.service.WeatherService;

public class WeatherController implements Controller {

	private WeatherService weatherService;

    @Transactional
    public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String zip = request.getParameter("zip");
		Weather weather = weatherService.retrieveForecast(zip);
		return new ModelAndView("weather", "weather", weather);
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

}