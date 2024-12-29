package tsi.daw.museumscheduling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {
	
	@RequestMapping("home_page")
	public String homePage() {
		return "home_page";
	}
}
