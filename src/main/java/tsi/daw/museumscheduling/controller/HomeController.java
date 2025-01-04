package tsi.daw.museumscheduling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tsi.daw.museumscheduling.interfaces.PagesName;

@Controller
public class HomeController {
	
	@RequestMapping("home_page")
	public String homePage() {
		return PagesName.HOME_PAGE;
	}
	
	@RequestMapping("/")
    public String showHomePage() {
		return PagesName.HOME_PAGE;
    }
}
