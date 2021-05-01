package com.midvi.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAOImpl <T , ID extends Serializable> implements 
          GenericDAO<T, ID>{
	@PersistenceContext(name = "E-commerceEJB")
    protected EntityManager em;
	protected final Class<T> entityClass;
	
	protected GenericDAOImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	public T findById(ID id) {
		return em.find(entityClass, id);
	}
	public List<T> findAll() {
		CriteriaQuery<T> c =
		em.getCriteriaBuilder().createQuery(entityClass);
		c.select(c.from(entityClass));
		return em.createQuery(c).getResultList();
	}
	public T save(T entity)
	{
		em.persist(entity);
		return entity;
	}
	public void delete(T entity)
	{
		em.remove(entity);
	}

}
