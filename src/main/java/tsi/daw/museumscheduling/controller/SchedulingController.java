package tsi.daw.museumscheduling.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import tsi.daw.museumscheduling.model.Museum;
import tsi.daw.museumscheduling.model.Person;
import tsi.daw.museumscheduling.model.Scheduling;
import tsi.daw.museumscheduling.utils.SchedulingUtils;
import tsi.daw.museumscheduling.utils.SendEmailUtils;

@Controller
public class SchedulingController {

	@RequestMapping("scheduling_page")
	public String schedulingPage(Model model) {
		
		MuseumControl.listMuseums(model);
		
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
		
		sendEmail(scheduling);
		
		return "scheduling_page";
	}
	
	private void sendEmail(Scheduling scheduling) {

		DAO<Museum> dao = new DAO<>(Museum.class);
		Museum museum = dao.findById(scheduling.getMuseum().getId());

		String subject = "Confirmação de Agendamento - " + museum.getName();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = scheduling.getHourlyReservation().getDate().format(formatter);

		StringBuilder content = new StringBuilder();
		content.append("<html><body>").append("<h2>Olá,</h2>")
				.append("<p>Seu agendamento foi confirmado! Aqui estão os detalhes:</p>").append("<ul>")
				.append("<li><strong>Dia e Horário do Agendamento:</strong> ").append(formattedDate).append(" às ")
				.append(scheduling.getHourlyReservation().getTime()).append("</li>")
				.append("<li><strong>Quantidade de Pessoas:</strong> ")
				.append(scheduling.getHourlyReservation().getReservedPeople()).append("</li>")
				.append("<li><strong>Pessoa(s) Agendada(s):</strong></li><br>")
				.append("<table border='1' cellpadding='5' cellspacing='0'>")
				.append("<tr><th>Nome</th><th>CPF</th></tr>");

		for (Person person : scheduling.getPeople()) {
			content.append("<tr><td>").append(person.getName()).append("</td><td>").append(person.getCpf()).append("</td></tr>");
		}

		content.append("</table>").append("<br><p><strong>Código de Confirmação:</strong> ")
				.append(scheduling.getConfirmationCode()).append("</p>").append("</body></html>");

		SendEmailUtils.sendEmail(scheduling.getResponsibleEmail(), subject, content);
	}
}
