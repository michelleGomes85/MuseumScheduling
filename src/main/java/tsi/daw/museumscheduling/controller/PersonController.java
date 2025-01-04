package tsi.daw.museumscheduling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tsi.daw.museumscheduling.dao.DAO;
import tsi.daw.museumscheduling.model.Person;
import tsi.daw.museumscheduling.model.Scheduling;

@Controller
public class PersonController {

	@ResponseBody
	@RequestMapping("updatePresence")
	public void updatePresence(
			@RequestParam("personId") Long personId, 
			@RequestParam("schedulingId") Long schedulingId,
			@RequestParam("isPresent") boolean isPresent) {

		DAO<Person> daoPerson = new DAO<>(Person.class);
		DAO<Scheduling> daoScheduling = new DAO<>(Scheduling.class);

		Person person = daoPerson.findById(personId);
		Scheduling scheduling = daoScheduling.findById(schedulingId);

		person.setPresent(isPresent);

		if (isPresent)
			scheduling.getHourlyReservation().setPeoplePresent(scheduling.getHourlyReservation().getPeoplePresent() + 1);
		else
			scheduling.getHourlyReservation().setPeoplePresent(scheduling.getHourlyReservation().getPeoplePresent() - 1);

		daoPerson.update(person);
		daoScheduling.update(scheduling);
	}
}
