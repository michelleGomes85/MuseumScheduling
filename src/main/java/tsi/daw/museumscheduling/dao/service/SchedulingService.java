package tsi.daw.museumscheduling.dao.service;

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

	
	@Override
	public void close() {
		if (manager.isOpen())
			manager.close();
	}
}
