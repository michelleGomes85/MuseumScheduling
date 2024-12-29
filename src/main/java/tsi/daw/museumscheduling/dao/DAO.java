package tsi.daw.museumscheduling.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

public class DAO<T> {
	
	private Class<T> classe;
	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	public void add(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
	}
	
	public T findById(Long id) {
		EntityManager em = new JPAUtil().getEntityManager();
		return em.find(classe, id);
	}
	
	public List<T> listAll(){
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query =
				em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> list = em.createQuery(query).getResultList();
		return list;
	}
}

