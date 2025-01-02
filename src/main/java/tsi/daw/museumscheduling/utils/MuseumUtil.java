package tsi.daw.museumscheduling.utils;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.ui.Model;

import tsi.daw.museumscheduling.dao.DAO;
import tsi.daw.museumscheduling.model.Museum;
import tsi.daw.museumscheduling.model.Person;
import tsi.daw.museumscheduling.model.Scheduling;

public class MuseumUtil {
	
	public static void listMuseums(Model model) {
		DAO<Museum> dao = new DAO<>(Museum.class);
		List<Museum> museums = dao.listAll();

		model.addAttribute("museums", museums);
	}
	
	public static void sendEmail(Scheduling scheduling) {

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

		for (Person person : scheduling.getPeople())
			content.append("<tr><td>").append(person.getName()).append("</td><td>").append(person.getCpf()).append("</td></tr>");

		content.append("</table>").append("<br><p><strong>Código de Confirmação:</strong> ")
				.append(scheduling.getConfirmationCode()).append("</p>").append("</body></html>");

		SendEmailUtils.sendEmail(scheduling.getResponsibleEmail(), subject, content);
	}
}
