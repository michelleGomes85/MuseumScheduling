package tsi.daw.museumscheduling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("home_page")
	public String homePage() {
		return "home_page";
	}
	
	@RequestMapping("/")
    public String showHomePage() {
        return "home_page";
    }
}
