package tsi.daw.museumscheduling.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import tsi.daw.museumscheduling.enums.TicketType;
import tsi.daw.museumscheduling.interfaces.Messages;

@Entity
public class Person {
	
	@Id
	@SequenceGenerator(name = "person_id", sequenceName = "person_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id")
	private Long id;
	
	@NotBlank(message = Messages.VALIDATION_NAME_REQUIRED)
	private String name;
	
    @NotBlank(message = Messages.VALIDATION_CPF_REQUIRED)
    @Pattern(regexp = Messages.REGEX_EXPRESSION_CPF, message = Messages.VALIDATION_CPF_FORMAT)
	private String cpf;
    
    @AssertTrue(message = Messages.VALIDATION_TERM_CONSENT_REQUIRED)
	private boolean termConsent;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = Messages.VALIDATION_TICKET_TYPE_REQUIRED)
	private TicketType ticketType;
	
    @ManyToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private List<Scheduling> schedulings;
    
    private boolean present;
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public boolean isTermConsent() {
		return termConsent;
	}
	
	public void setTermConsent(boolean termConsent) {
		this.termConsent = termConsent;
	}
	
	public TicketType getTicketType() {
		return ticketType;
	}
	
	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public List<Scheduling> getSchedulings() {
		return schedulings;
	}

	public void setSchedulings(List<Scheduling> schedulings) {
		this.schedulings = schedulings;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}
}