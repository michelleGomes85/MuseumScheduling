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

	private static StringBuilder generateEmailContent(Scheduling scheduling, String type) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = scheduling.getHourlyReservation().getDate().format(formatter);

		StringBuilder content = new StringBuilder();
		content.append("<html><body>").append("<h2>Olá,</h2>");

		switch (type) {
		
		case "CONFIRMATION":
			content.append("<p>Seu agendamento foi confirmado! Aqui estão os detalhes:</p>").append("<ul>")
					.append("<li><strong>Dia e Horário do Agendamento:</strong> ").append(formattedDate).append(" às ")
					.append(scheduling.getHourlyReservation().getTime()).append("</li>")
					.append("<li><strong>Quantidade de Pessoas:</strong> ")
					.append(scheduling.getHourlyReservation().getReservedPeople()).append("</li>")
					.append("<li><strong>Pessoa(s) Agendada(s):</strong></li><br>")
					.append("<table border='1' cellpadding='5' cellspacing='0'>")
					.append("<tr><th>Nome</th><th>CPF</th></tr>");

			for (Person person : scheduling.getPeople()) {
				content.append("<tr><td>").append(person.getName()).append("</td><td>").append(person.getCpf())
						.append("</td></tr>");
			}
			
			content.append("</table>").append("<br><p><strong>Código de Confirmação:</strong> ")
					.append(scheduling.getConfirmationCode()).append("</p>");
			
			break;

		case "UPDATE":
			content.append("<p>Seu agendamento foi alterado. Aqui estão os novos detalhes:</p>").append("<ul>")
					.append("<li><strong>Dia e Horário do Agendamento:</strong> ").append(formattedDate).append(" às ")
					.append(scheduling.getHourlyReservation().getTime()).append("</li>")
					.append("<li><strong>Quantidade de Pessoas:</strong> ")
					.append(scheduling.getHourlyReservation().getReservedPeople()).append("</li>")
					.append("<li><strong>Pessoa(s) Agendada(s):</strong></li><br>")
					.append("<table border='1' cellpadding='5' cellspacing='0'>")
					.append("<tr><th>Nome</th><th>CPF</th></tr>");

			for (Person person : scheduling.getPeople()) {
				content.append("<tr><td>").append(person.getName()).append("</td><td>").append(person.getCpf())
						.append("</td></tr>");
			}
			
			content.append("</table>");
			break;

		case "CANCEL":
			content.append("<p>O seu agendamento foi cancelado. Aqui estão os detalhes:</p>").append("<ul>")
					.append("<li><strong>Dia e Horário do Agendamento:</strong> ").append(formattedDate).append(" às ")
					.append(scheduling.getHourlyReservation().getTime()).append("</li>")
					.append("<li><strong>Nome do Museu:</strong> ").append(scheduling.getMuseum().getName())
					.append("</li>").append("</ul>");
			break;
		}

		content.append("</body></html>");
		return content;
	}

	public static void sendEmailVisit(Scheduling scheduling) {
		DAO<Museum> dao = new DAO<>(Museum.class);
		Museum museum = dao.findById(scheduling.getMuseum().getId());

		String subject = "Confirmação de Agendamento - " + museum.getName();
		StringBuilder content = generateEmailContent(scheduling, "CONFIRMATION");
		SendEmailUtils.sendEmail(scheduling.getResponsibleEmail(), subject, content);
	}

	public static void sendEmailUpdateVisit(Scheduling scheduling) {
		DAO<Museum> dao = new DAO<>(Museum.class);
		Museum museum = dao.findById(scheduling.getMuseum().getId());

		String subject = "Agendamento Alterado - " + museum.getName();
		StringBuilder content = generateEmailContent(scheduling, "UPDATE");
		SendEmailUtils.sendEmail(scheduling.getResponsibleEmail(), subject, content);
	}

	public static void sendEmailCancelVisit(Scheduling scheduling) {
	    DAO<Museum> dao = new DAO<>(Museum.class);
	    Museum museum = dao.findById(scheduling.getMuseum().getId());

	    String subject = "Cancelamento Visita - " + museum.getName();
	    StringBuilder content = generateEmailContent(scheduling, "CANCEL");
	    SendEmailUtils.sendEmail(scheduling.getResponsibleEmail(), subject, content);
	}
}
