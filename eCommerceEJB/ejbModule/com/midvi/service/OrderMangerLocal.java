package com.midvi.service;

import java.util.List;

import javax.ejb.Local;

import com.midvi.entity.UserOrder;
import com.midvi.entity.User;

@Local
public interface OrderMangerLocal {
	List<UserOrder> getAllOrders();
	List<UserOrder> getAllUserOrders(User user);
	void addOrder(UserOrder order);
	void deleteOrder(Long id);
	UserOrder changeOrderStatus(Long id, String newStatus);
	List<UserOrder> getAllDeliveredOrUndeliveredOrdersOfUser(User user, String delivered);
	List<UserOrder> getAllDeliveredOrundeliveredOrders(String status);
	
}
