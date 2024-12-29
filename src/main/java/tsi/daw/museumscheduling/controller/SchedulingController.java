package tsi.daw.museumscheduling.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import tsi.daw.museumscheduling.dao.DAO;
import tsi.daw.museumscheduling.dao.ReservationDAO;
import tsi.daw.museumscheduling.model.Museum;
import tsi.daw.museumscheduling.model.Scheduling;

@Controller
public class SchedulingController {

	public String add(@Valid Scheduling scheduling, BindingResult result) {

		if (result.hasFieldErrors("descricao"))
			return null;

		DAO<Scheduling> dao = new DAO<>(Scheduling.class);
		dao.add(scheduling);

		return null;
	}

	@RequestMapping("schedule/{museumId}")
	public String schedule(@PathVariable Long museumId, @RequestParam("date") String date, Model model) {

		DAO<Museum> museumDao = new DAO<>(Museum.class);

		// Obtém o museu
		Museum museum = museumDao.findById(museumId);

		if (museum == null) {
			model.addAttribute("error", "Museu não encontrado");
			return "error_page";
		}

		// Calcula as horas disponíveis
		List<LocalTime> availableHours = getAvailableHours(museum, date, new ReservationDAO());

		model.addAttribute("museum", museum);
		model.addAttribute("availableHours", availableHours);
		model.addAttribute("selectedDate", date);

		return "schedule_page";
	}

	private List<LocalTime> getAvailableHours(Museum museum, String date, ReservationDAO reservationDAO) {

		List<LocalTime> availableHours = new ArrayList<>();
		LocalTime currentTime = museum.getOpeningTime();

		while (currentTime.isBefore(museum.getClosingTime()) || currentTime.equals(museum.getClosingTime())) {

			LocalDate localDate = LocalDate.parse(date);

			int reservedPeople = reservationDAO.getReservedPeople(museum.getId(), localDate, currentTime);

			if (reservedPeople < museum.getLimitPeopleByHour()) {
				availableHours.add(currentTime);
			}

			// Avança para a próxima hora
			currentTime = currentTime.plusHours(1);
		}

		return availableHours;
	}
}
