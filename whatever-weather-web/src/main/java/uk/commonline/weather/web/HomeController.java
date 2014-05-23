package uk.commonline.weather.web;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.commonline.weather.model.WeatherUserLogin;
import uk.commonline.weather.service.WeatherService;

@Controller()
//@RequestMapping(value = "/home")
public class HomeController {

	@Inject
	private WeatherService weatherService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHomePage(Map<String, Object> model) {
		WeatherUserLogin weatherUser = new WeatherUserLogin();
		model.put("weatherUser", weatherUser);
		return "home";
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

}
