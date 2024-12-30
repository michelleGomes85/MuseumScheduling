package tsi.daw.museumscheduling.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import tsi.daw.museumscheduling.model.AppUser;

public class AppUserDAO {

	EntityManager manager;

	public AppUserDAO() {
		manager = new JPAUtil().getEntityManager();
	}

	public AppUser validateLogin(AppUser user) {
	    if (user == null)
	        throw new IllegalArgumentException("Usuário não deve ser nulo");

	    Query query = manager.createQuery("SELECT u FROM AppUser u WHERE u.name = :name AND u.password = :password", AppUser.class);
	    query.setParameter("name", user.getName());
	    query.setParameter("password", user.getPassword());

	    List<AppUser> users = query.getResultList();

	    if (users.isEmpty())
	        return null; 

	    return users.get(0); 
	}
}
