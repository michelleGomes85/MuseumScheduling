package tsi.daw.museumscheduling.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.EntityManager;
import tsi.daw.museumscheduling.model.HourlyReservation;

public class HourlyReservationDAO implements AutoCloseable  {

	EntityManager manager;

	public HourlyReservationDAO() {
		manager = new JPAUtil().getEntityManager();
	}

	public int getReservedPeople(Long museumId, LocalDate date, LocalTime time) {
		
	    try {
	        String jpql = "SELECT COALESCE(SUM(hr.reservedPeople), 0) " +
	                      "FROM HourlyReservation hr " +
	                      "JOIN Scheduling s ON s.hourlyReservation.id = hr.id " +
	                      "WHERE s.museum.id = :museumId " +
	                      "AND hr.date = :date " +
	                      "AND hr.time = :time";

	        return manager.createQuery(jpql, Long.class)
	                      .setParameter("museumId", museumId)
	                      .setParameter("date", date)
	                      .setParameter("time", time)
	                      .getSingleResult().intValue();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}

	
	public List<HourlyReservation> getReservationsByDateAndMuseum(LocalDate date, Long museumId) {
	    try {
	        String jpql = "SELECT hr FROM HourlyReservation hr " +
	                      "JOIN Scheduling s ON s.hourlyReservation.id = hr.id " +
	                      "WHERE s.museum.id = :museumId " +
	                      "AND hr.date = :date";

	        return manager.createQuery(jpql, HourlyReservation.class)
	                      .setParameter("museumId", museumId)
	                      .setParameter("date", date)
	                      .getResultList();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

    @Override
	public void close() {
		if (manager.isOpen())
			manager.close();
	}
}
