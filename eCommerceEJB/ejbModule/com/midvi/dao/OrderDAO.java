package com.midvi.dao;

import java.util.List;

import com.midvi.entity.UserOrder;
import com.midvi.entity.User;

public interface OrderDAO extends GenericDAO<UserOrder, Long> {
	List<UserOrder> findAllOrders();
	List<UserOrder> findAllUserOrders(User user);
	void saveOrder(UserOrder order);
	void deleteOrder(Long id);
	UserOrder changeOrderStatus(Long id, String newStatus);
	List<UserOrder> findAllDeliveredOrUndeliveredOrdersOfUser(User user, String delivered);
	List<UserOrder> findAllDeliveredOrundeliveredOrders(String status);
	  
}
