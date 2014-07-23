package uk.commonline.weather.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import uk.commonline.weather.model.WeatherUserLogin;

@Controller()
// @RequestMapping(value = "/home")
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHomePage(Map<String, Object> model) {
	WeatherUserLogin weatherUser = new WeatherUserLogin();
	model.put("weatherUser", weatherUser);
	return "home";
    }

}
