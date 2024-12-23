package tsi.daw.museumscheduling.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import tsi.daw.museumscheduling.enums.TicketType;
import tsi.daw.museumscheduling.enums.UserProfile;

public class Teste {

	public static void main(String[] args) {

		// Criação da fábrica e do gerenciador de entidades
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("museumscheduling-pu");
		EntityManager manager = factory.createEntityManager();

		try {
			// Início da transação
			manager.getTransaction().begin();

			// Criar um Museu
			Museum museum = new Museum();
			museum.setName("Museu Histórico");
			museum.setOpeningTime(LocalTime.of(8, 0));
			museum.setClosingTime(LocalTime.of(18, 0));
			museum.setLimitPeopleByHour(5);
			

			// Criar um Usuário (AppUser)
			AppUser admin = new AppUser();
			admin.setName("Administrador");
			admin.setCpf("12345678901");
			admin.setEmail("admin@museu.com");
			admin.setPassword("password123");
			admin.setUserProfile(UserProfile.ADMIN);
			admin.setMuseum(museum);

			// Associar o usuário ao museu
			List<AppUser> appUsers = new ArrayList<>();
			appUsers.add(admin);
			museum.setUsers(appUsers);
			
			manager.persist(museum);

			// Criar uma Reserva de Horário
			HourlyReservation hourlyReservation = new HourlyReservation();
			hourlyReservation.setMuseum(museum);
			hourlyReservation.setDate(LocalDate.now());
			hourlyReservation.setTime(LocalTime.of(10, 0));
			hourlyReservation.setReservedPeople(3);
			manager.persist(hourlyReservation);

			// Criar o Agendamento
			Scheduling scheduling = new Scheduling();
			scheduling.setNumberPeople(3);
			scheduling.setResponsibleEmail("responsavel@grupo.com");
			scheduling.setConfirmationCode("CONF123");
			scheduling.setMuseum(museum);
			scheduling.setHourlyReservation(hourlyReservation); // Associar à reserva de horário
			manager.persist(scheduling); // Persistir o agendamento

			// Criar Pessoas associadas ao Agendamento
			Person person1 = new Person();
			person1.setName("João Silva");
			person1.setCpf("98765432100");
			person1.setTermConsent(true);
			person1.setTicketType(TicketType.INTEIRO);

			Person person2 = new Person();
			person2.setName("Maria Santos");
			person2.setCpf("87654321000");
			person2.setTermConsent(true);
			person2.setTicketType(TicketType.MEIA_ENTRADA);

			// Associar as pessoas ao agendamento
			List<Person> people = new ArrayList<>();
			people.add(person1);
			people.add(person2);
			scheduling.setPeople(people);

			// Persistir as Pessoas
			manager.persist(person1);
			manager.persist(person2);

			// Commit da transação
			manager.getTransaction().commit();

			System.out.println("Agendamento cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		} finally {
			manager.close();
			factory.close();
		}
	}
}
