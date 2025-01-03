package tsi.daw.museumscheduling.model;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tsi.daw.museumscheduling.interfaces.Messages;

@Entity
public class Museum {
	
	@Id
	@SequenceGenerator(name = "museum_id", sequenceName = "museum_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "museum_id")
	private Long id;
	
	@NotBlank(message = Messages.VALIDATION_NAME_REQUIRED)
	private String name;
	
	@NotNull(message = Messages.VALIDATION_OPENING_TIME_REQUIRED)
	private LocalTime openingTime;
	
	@NotNull(message = Messages.VALIDATION_CLOSING_TIME_REQUIRED)
	private LocalTime closingTime;
	
	@Min(value = 1, message = Messages.VALIDATION_LIMIT_PEOPLE)
	private int limitPeopleByHour;
    
    @OneToMany(mappedBy = "museum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppUser> users;
	
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
	
	public LocalTime getOpeningTime() {
		return openingTime;
	}
	
	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}
	public LocalTime getClosingTime() {
		return closingTime;
	}
	
	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	public int getLimitPeopleByHour() {
		return limitPeopleByHour;
	}

	public void setLimitPeopleByHour(int limitPeopleByHour) {
		this.limitPeopleByHour = limitPeopleByHour;
	}

	public List<AppUser> getUsers() {
		return users;
	}

	public void setUsers(List<AppUser> users) {
		this.users = users;
	}
	
} //class Museum