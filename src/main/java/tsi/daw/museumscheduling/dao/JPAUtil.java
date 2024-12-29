package tsi.daw.museumscheduling.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("museumscheduling-pu");

	public EntityManager getEntityManager() {
		EntityManager conexao = emf.createEntityManager();
		System.out.println(conexao.toString());
		return conexao;
	}
}