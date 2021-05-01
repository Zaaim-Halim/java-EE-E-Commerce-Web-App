package com.midvi.dao.impl;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.Query;

import com.midvi.dao.GenericDAOImpl;
import com.midvi.dao.OrderDAO;
import com.midvi.entity.UserOrder;
import com.midvi.entity.User;

@Singleton
public class OrderDaoImpl extends GenericDAOImpl<UserOrder,Long> 
       implements OrderDAO{

	protected OrderDaoImpl() {
		super(UserOrder.class);
	}

	@Override
	public UserOrder findById(Long id) {
		
		return em.find(UserOrder.class, id);
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<UserOrder> findAllOrders() {
		Query  query= em.createQuery("SELECT o FROM Order o");
		return query.getResultList();
	}

	@Override
	public List<UserOrder> findAllUserOrders(User user) {
		
		return null;
	}

	@Override
	public void saveOrder(UserOrder order) {
	        save(order);
		
	}

	@Override
	public void deleteOrder(Long id) {
		UserOrder order = findById(id);
		delete(order);
		
	}

	@Override
	public UserOrder changeOrderStatus(Long id, String newStatus) {
		UserOrder order = findById(id);
		order.setStatus(newStatus);
		em.merge(order);
		return order;
	}

	@Override
	public List<UserOrder> findAllDeliveredOrUndeliveredOrdersOfUser(User user, String delivered) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserOrder> findAllDeliveredOrundeliveredOrders(String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
