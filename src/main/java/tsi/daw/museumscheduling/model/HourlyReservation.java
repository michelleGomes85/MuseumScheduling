package tsi.daw.museumscheduling.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@NotNull(message = Messages.VALIDATION_MUSEUM)
	@ManyToOne
	@JoinColumn(name = "museum_id", nullable = false)
	private Museum museum;

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

	public Museum getMuseum() {
		return museum;
	}

	public void setMuseum(Museum museum) {
		this.museum = museum;
	}
}
