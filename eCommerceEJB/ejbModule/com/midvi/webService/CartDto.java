package com.midvi.webService;

import java.util.Set;

import com.midvi.entity.CartItem;

public class CartDto {
	
	private Long id;
	private int itemsNumber;
	private double total;
	private String sessionTokent;
	private Set<CartItem> cartItems;
	
	public CartDto(Long id, int itemsNumber, double total, String sessionTokent) {
		super();
		this.id = id;
		this.itemsNumber = itemsNumber;
		this.total = total;
		this.sessionTokent = sessionTokent;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getItemsNumber() {
		return itemsNumber;
	}
	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getSessionTokent() {
		return sessionTokent;
	}
	public void setSessionTokent(String sessionTokent) {
		this.sessionTokent = sessionTokent;
	}
	public Set<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
}
