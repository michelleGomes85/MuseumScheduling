package tsi.daw.museumscheduling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tsi.daw.museumscheduling.dao.DAO;
import tsi.daw.museumscheduling.model.Museum;

@Controller
public class MuseumController {
	
	@RequestMapping("scheduling_page")
	public String list(Model model) {
		
		DAO<Museum> dao = new DAO<>(Museum.class);
		model.addAttribute("museums", dao.listAll());
		
		return "scheduling_page";
	}
}
