package com.midvi.dao.impl;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.midvi.dao.CartItemDAO;
import com.midvi.dao.GenericDAOImpl;
import com.midvi.entity.CartItem;
import com.midvi.entity.Product;

@Singleton
public class CartItemDAOImpl extends GenericDAOImpl<CartItem, Long>
       implements CartItemDAO{

	protected CartItemDAOImpl() {
		super(CartItem.class);
		
	}

	@Override
	public List<CartItem> findByProduct(Product product) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CartItem> q = cb.createQuery(CartItem.class);
		Root<CartItem> c = q.from(CartItem.class);
		q.select(c).where(cb.equal(c.get("product"),product));
		return em.createQuery(q).getResultList();
	}

	

	@Override
	public CartItem updateQty(Long id, int newQty) {
		CartItem cartItem = em.find(CartItem.class, id);
		em.getTransaction().begin();
		cartItem.setQty(newQty);
		em.getTransaction().commit();
		return cartItem;
		
	}

	@Override
	public List<CartItem> findByDate(java.util.Date date) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CartItem> q = cb.createQuery(CartItem.class);
		Root<CartItem> c = q.from(CartItem.class);
		q.select(c).where(cb.equal(c.get("date"),date));
		return em.createQuery(q).getResultList();
	}

}
