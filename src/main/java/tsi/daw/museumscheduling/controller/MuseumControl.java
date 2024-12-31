package tsi.daw.museumscheduling.controller;

import java.util.List;

import org.springframework.ui.Model;

import tsi.daw.museumscheduling.dao.DAO;
import tsi.daw.museumscheduling.model.Museum;

public class MuseumControl {
	
	public static void listMuseums(Model model) {
		DAO<Museum> dao = new DAO<>(Museum.class);
		List<Museum> museums = dao.listAll();

		model.addAttribute("museums", museums);
	}
}
