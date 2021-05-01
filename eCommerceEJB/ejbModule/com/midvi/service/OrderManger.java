package com.midvi.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.midvi.dao.OrderDAO;
import com.midvi.entity.UserOrder;
import com.midvi.entity.User;


@Stateless
@LocalBean
public class OrderManger implements OrderMangerLocal {

    @Inject
    private OrderDAO orderDao;
    public OrderManger() {
    }

	@Override
	public List<UserOrder> getAllOrders() {
		
		return orderDao.findAll();
	}

	@Override
	public List<UserOrder> getAllUserOrders(User user) {
		return orderDao.findAllUserOrders(user);
	}

	@Override
	public void addOrder(UserOrder order) {
		orderDao.saveOrder(order);
	}

	@Override
	public void deleteOrder(Long id) {
		orderDao.deleteOrder(id);	
	 	
	}

	@Override
	public UserOrder changeOrderStatus(Long id, String newStatus) {
	
		return orderDao.changeOrderStatus(id, newStatus);
	}

	@Override
	public List<UserOrder> getAllDeliveredOrUndeliveredOrdersOfUser(User user, String delivered) {
		
		return orderDao.findAllDeliveredOrUndeliveredOrdersOfUser(user, delivered);
	}

	@Override
	public List<UserOrder> getAllDeliveredOrundeliveredOrders(String status) {
		
		return orderDao.findAllDeliveredOrundeliveredOrders(status);
	}

}
