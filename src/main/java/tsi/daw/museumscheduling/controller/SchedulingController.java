package tsi.daw.museumscheduling.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import tsi.daw.museumscheduling.dao.service.HourlyReservationService;
import tsi.daw.museumscheduling.dao.service.SchedulingService;
import tsi.daw.museumscheduling.model.Museum;
import tsi.daw.museumscheduling.model.Scheduling;
import tsi.daw.museumscheduling.utils.MuseumUtil;
import tsi.daw.museumscheduling.utils.SchedulingUtils;

@Controller
public class SchedulingController {

	@RequestMapping("scheduling_page")
	public String schedulingPage(Model model) {
		
		MuseumUtil.listMuseums(model);
		
		return "scheduling_page";
	}
	
	@RequestMapping("cancel_page")
	public String cancelPage() {
		return "cancel_scheduling";
	}
	
	@ResponseBody
	@RequestMapping(value = "getAvailableTimes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getAvailableTimes(@RequestParam("museum") Long museumId, @RequestParam("date") String date) {
	    
	    LocalDate localDate = LocalDate.parse(date);
	    
		try (HourlyReservationService dao = new HourlyReservationService()) {
	        
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
	public int getLimitForTime(@RequestParam("museum") Long museumId, @RequestParam("date") String date, @RequestParam("time") String time) {
	    
	    LocalDate localDate = LocalDate.parse(date);
	    LocalTime localTime = LocalTime.parse(time);

	    try (HourlyReservationService dao = new HourlyReservationService()) {
	    	
	        int reservedPeople = dao.getReservedPeople(museumId, localDate, localTime);

	        DAO<Museum> daoMuseum = new DAO<>(Museum.class);
	        Museum museum = daoMuseum.findById(museumId);

	        return museum.getLimitPeopleByHour() - reservedPeople;
	    }
	}
	
	@RequestMapping("scheduleVisit")
	public String scheduleVisit(@Valid Scheduling scheduling, BindingResult result, Model model) {
		
		MuseumUtil.listMuseums(model);
		
		if (result.hasErrors()) {
			
			List<String> errorMessages = new ArrayList<>();
			
			for (ObjectError error : result.getAllErrors())
				errorMessages.add(error.getDefaultMessage());
			
			model.addAttribute("messageReturn", errorMessages);
			return "scheduling_page";
		}
		
		String uniqueCode = SchedulingUtils.generateUniqueCode(scheduling);
		scheduling.setConfirmationCode(uniqueCode);

		DAO<Scheduling> dao = new DAO<>(Scheduling.class);
		dao.add(scheduling);

		model.addAttribute("messageReturn", "Cadastro realizado com sucesso!");
		
		MuseumUtil.sendEmail(scheduling);
		
		return "scheduling_page";
	}
	
	@RequestMapping("listSchedulingByCode")
	public String getSchedulingByCode(@RequestParam("responsibleEmail") String email, @RequestParam("confirmationCode") String code, Model model) {
		
		try (SchedulingService schedulingService = new SchedulingService()) {
			
			Scheduling scheduling = schedulingService.findSchedulingByEmailAndCode(email, code);
			
			if (scheduling != null) {
				Date date = Date.from(scheduling.getHourlyReservation().getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
				model.addAttribute("scheduling", scheduling);
				model.addAttribute("formattedDate", date);

				return "scheduling_details";
			} else {
				model.addAttribute("messageReturn", "Agendamento não encontrado");
				return "cancel_scheduling";
			}
		}
	}
}
