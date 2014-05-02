package uk.commonline.weather.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import uk.commonline.weather.model.WeatherUserLogin;

@Controller()
@SessionAttributes("student")
public class LoginController {
	
	@RequestMapping(value="login.x", method=RequestMethod.GET)
	public String login(Model model) {			
		WeatherUserLogin studentLogin = new WeatherUserLogin();		
		model.addAttribute("studentLogin", studentLogin);
		return "login";
	}
	
	@RequestMapping(value="login.x", method=RequestMethod.POST)
	public String login(@Valid @ModelAttribute("studentLogin") WeatherUserLogin studentLogin, BindingResult result) {
		if (result.hasErrors()) {
			return "login";
		} else {
			boolean found = true;
			//boolean found = studentService.findByLogin(studentLogin.getUserName(), studentLogin.getPassword());
			if (found) {				
				return "success";
			} else {				
				return "failure";
			}
		}
		
	}
}
