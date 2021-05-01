package com.midvi.dao;


import java.util.Date;
import java.util.List;

import com.midvi.entity.CartItem;
import com.midvi.entity.Product;

public interface CartItemDAO extends GenericDAO<CartItem, Long> {
	List<CartItem> findByProduct(Product product);
	List<CartItem> findByDate(Date date);
	CartItem updateQty(Long id , int newQty);
	

}
