package tsi.daw.museumscheduling.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import tsi.daw.museumscheduling.dao.DAO;
import tsi.daw.museumscheduling.model.AppUser;
import tsi.daw.museumscheduling.model.Museum;

@Controller
public class UserController {

	
	@RequestMapping("employee_registration")
	public String showHomePage(Model model) {

		listMuseums(model);
		
		return "employee_registration";
	}
	
	private void listMuseums(Model model) {
		DAO<Museum> dao = new DAO<>(Museum.class);
		List<Museum> museums = dao.listAll();

		model.addAttribute("museums", museums);
	}

	@RequestMapping("registerAppUser")
	public String registerAppUser(@Valid AppUser user, BindingResult result, Model model) {
		
		listMuseums(model);
		
		if (result.hasErrors()) {
			
			List<String> errorMessages = new ArrayList<>();
			
			for (ObjectError error : result.getAllErrors())
				errorMessages.add(error.getDefaultMessage());
			
			model.addAttribute("messageReturn", errorMessages);
			return "employee_registration";
		}

		DAO<AppUser> dao = new DAO<>(AppUser.class);
		dao.add(user);

		model.addAttribute("messageReturn", "Cadastro realizado com sucesso!");
		return "employee_registration";
	}
}
