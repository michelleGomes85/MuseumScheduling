package tsi.daw.museumscheduling.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import tsi.daw.museumscheduling.interfaces.Messages;

@Entity
public class Scheduling {
	
	@Id
	@SequenceGenerator(name = "scheduling_id", sequenceName = "scheduling_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scheduling_id")
	private Long id;
	
    @NotBlank(message = Messages.VALIDATION_EMAIL_REQUIRED)
    @Email(message = Messages.VALIDATION_EMAIL_INVALID)
	private String responsibleEmail;
	
    @ManyToOne
    @JoinColumn(name = "museum_id", nullable = false)
    private Museum museum;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hourly_reservation_id", referencedColumnName = "id")
    private HourlyReservation hourlyReservation;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "scheduling_person",
        joinColumns = @JoinColumn(name = "scheduling_id"),
        inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> people;
    
	private String confirmationCode;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getResponsibleEmail() {
		return responsibleEmail;
	}
	
	public void setResponsibleEmail(String responsibleEmail) {
		this.responsibleEmail = responsibleEmail;
	}
	
	public String getConfirmationCode() {
		return confirmationCode;
	}
	
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public Museum getMuseum() {
		return museum;
	}

	public void setMuseum(Museum museum) {
		this.museum = museum;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public HourlyReservation getHourlyReservation() {
		return hourlyReservation;
	}

	public void setHourlyReservation(HourlyReservation hourlyReservation) {
		this.hourlyReservation = hourlyReservation;
	}
}
