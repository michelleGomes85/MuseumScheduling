package tsi.daw.museumscheduling.dao.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.EntityManager;
import tsi.daw.museumscheduling.dao.JPAUtil;
import tsi.daw.museumscheduling.model.Scheduling;

public class SchedulingService implements AutoCloseable {
	
	EntityManager manager;
	
	public SchedulingService() {
		manager = new JPAUtil().getEntityManager();
	}
	
	public Scheduling findSchedulingByEmailAndCode(String email, String code) {
	    try {
	    	
	        return manager.createQuery(
	                "SELECT s FROM Scheduling s " +
	                "JOIN FETCH s.museum m " +
	                "JOIN FETCH s.hourlyReservation hr " +
	                "LEFT JOIN FETCH s.people p " +
	                "WHERE s.responsibleEmail = :email AND s.confirmationCode = :code", Scheduling.class)
	                .setParameter("email", email)
	                .setParameter("code", code)
	                .getSingleResult();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public List<Scheduling> findSchedulingByDayAndTime(LocalDate date, LocalTime time, boolean isAdmin, Long museumId) {
		
	    try {
	        String query = "SELECT s FROM Scheduling s " +
	                       "JOIN FETCH s.museum m " +
	                       "JOIN FETCH s.hourlyReservation hr " +
	                       "LEFT JOIN FETCH s.people p " +
	                       "WHERE hr.date = :date AND hr.time = :time";

	        if (!isAdmin)
	            query += " AND m.id = :museumId";

	        var typedQuery = manager.createQuery(query, Scheduling.class)
	                                .setParameter("date", date)
	                                .setParameter("time", time);

	        if (!isAdmin)
	            typedQuery.setParameter("museumId", museumId);

	        return typedQuery.getResultList();
	    } catch (Exception e) {
	        return null;
	    }
	}
	
	@Override
	public void close() {
		if (manager.isOpen())
			manager.close();
	}
}
