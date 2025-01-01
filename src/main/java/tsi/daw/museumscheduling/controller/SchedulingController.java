package tsi.daw.museumscheduling.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import tsi.daw.museumscheduling.dao.DAO;
import tsi.daw.museumscheduling.dao.HourlyReservationDAO;
import tsi.daw.museumscheduling.model.HourlyReservation;
import tsi.daw.museumscheduling.model.Museum;
import tsi.daw.museumscheduling.model.Person;
import tsi.daw.museumscheduling.model.Scheduling;

@Controller
public class SchedulingController {

	@RequestMapping("scheduling_page")
	public String schedulingPage(Model model) {
		
		MuseumControl.listMuseums(model);
		
		return "scheduling_page";
	}
	
	@ResponseBody
	@RequestMapping(value = "getAvailableTimes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getAvailableTimes(@RequestParam("museum") Long museumId, @RequestParam("date") String date) {
	    
	    LocalDate localDate = LocalDate.parse(date);
	    
		try (HourlyReservationDAO dao = new HourlyReservationDAO()) {
	        
	    	DAO<Museum> daoMuseum = new DAO<>(Museum.class);
	        Museum museum = daoMuseum.findById(museumId);

	        LocalTime openingTime = museum.getOpeningTime();
	        LocalTime closingTime = museum.getClosingTime();

	        List<String> availableTimes = new ArrayList<>();

	        for (LocalTime time = openingTime; time.isBefore(closingTime); time = time.plusHours(1)) {
	            int reservedPeople = dao.getReservedPeople(museumId, localDate, time);
	            if (reservedPeople < museum.getLimitPeopleByHour()) {
	                availableTimes.add(time.toString());
	            }
	        }

	        return availableTimes;
	    }
	}
	
	@ResponseBody
	@RequestMapping(value = "getLimitForTime", produces = MediaType.APPLICATION_JSON_VALUE)
	public int getLimitForTime(
	        @RequestParam("museum") Long museumId, 
	        @RequestParam("date") String date, 
	        @RequestParam("time") String time) {
	    
	    LocalDate localDate = LocalDate.parse(date);
	    LocalTime localTime = LocalTime.parse(time);

	    try (HourlyReservationDAO dao = new HourlyReservationDAO()) {
	    	
	        int reservedPeople = dao.getReservedPeople(museumId, localDate, localTime);

	        DAO<Museum> daoMuseum = new DAO<>(Museum.class);
	        Museum museum = daoMuseum.findById(museumId);

	        return museum.getLimitPeopleByHour() - reservedPeople;
	    }
	}
	
	@RequestMapping("scheduleVisit")
	public String scheduleVisit(@Valid Scheduling scheduling, BindingResult result, Model model) {
		
		MuseumControl.listMuseums(model);
		
		for (Person person : scheduling.getPeople())
			System.out.println(person.getName());
		
		HourlyReservation hourlyReservation = scheduling.getHourlyReservation();
		
		System.out.println(hourlyReservation.getDate());
		System.out.println(hourlyReservation.getTime());
		System.out.println(hourlyReservation.getReservedPeople());
		
		System.out.println("Passei aqui");
		
		if (result.hasErrors()) {
			
			List<String> errorMessages = new ArrayList<>();
			
			for (ObjectError error : result.getAllErrors())
				errorMessages.add(error.getDefaultMessage());
			
			model.addAttribute("messageReturn", errorMessages);
			return "scheduling_page";
		}

		DAO<Scheduling> dao = new DAO<>(Scheduling.class);
		dao.add(scheduling);

		model.addAttribute("messageReturn", "Cadastro realizado com sucesso!");
		return "scheduling_page";
	}
}
