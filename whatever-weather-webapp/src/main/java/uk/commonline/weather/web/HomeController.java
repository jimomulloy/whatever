package uk.commonline.weather.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.commonline.weather.model.WeatherUserLogin;

@Controller
public class HomeController {
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model) {			
		WeatherUserLogin weatherUser = new WeatherUserLogin();		
		model.addAttribute("weatherUser", weatherUser);
		return "home";
	}
	
}
