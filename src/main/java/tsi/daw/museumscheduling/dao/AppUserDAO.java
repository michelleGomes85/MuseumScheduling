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

	@SuppressWarnings("unchecked")
	public AppUser validateLogin(AppUser user) {
		
	    if (user == null)
	        throw new IllegalArgumentException("Usuário não deve ser nulo");

	    Query query = manager.createQuery("SELECT u FROM AppUser u WHERE u.cpf = :cpf AND u.password = :password", AppUser.class);
	    query.setParameter("cpf", user.getCpf());
	    query.setParameter("password", user.getPassword());

	    List<AppUser> users = query.getResultList();

	    if (users.isEmpty())
	        return null; 

	    return users.get(0); 
	}
}
