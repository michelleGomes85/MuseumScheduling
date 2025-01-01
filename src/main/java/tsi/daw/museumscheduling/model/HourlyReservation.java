package tsi.daw.museumscheduling.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import tsi.daw.museumscheduling.interfaces.Messages;

@Entity
public class HourlyReservation {

	@Id
	@SequenceGenerator(name = "hourly_reservation_id", sequenceName = "hourly_reservation_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hourly_reservation_id")
	private Long id;

	@NotNull(message = Messages.VALIDATION_DATE_REQUIRED)
	private LocalDate date;

	@NotNull(message = Messages.VALIDATION_TIME_REQUIRED)
	private LocalTime time;

	@Min(value = 0, message = Messages.VALIDATION_RESERVED_PEOPLE)
	private int reservedPeople;
	
	@Min(value = 0, message = Messages.VALIDATION_PEOPLE_PRESENT)
	private int peoplePresent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public int getReservedPeople() {
		return reservedPeople;
	}

	public void setReservedPeople(int reservedPeople) {
		this.reservedPeople = reservedPeople;
	}

	public int getPeoplePresent() {
		return peoplePresent;
	}

	public void setPeoplePresent(int peoplePresent) {
		this.peoplePresent = peoplePresent;
	}
}
