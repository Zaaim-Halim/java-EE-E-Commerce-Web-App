package com.midvi.entity;

import java.io.Serializable;
import java.lang.Long;
import java.util.Set;

import javax.persistence.*;

import org.wildfly.common.annotation.Nullable;


@Entity
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "shoppingCart_id")
	private Set<CartItem> cartItems;
    @Nullable
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
    // track shoppingCart from a session only 
    // if the user is not logged in yet !! 
    @Nullable
    private String sessionTokent;
   
    @Transient
    private double total;
    
    @Transient
    private int itemsNumber;
    
	public Double getTotal() {
		double sum = 0.0;
		for(CartItem c : cartItems)
		{
			sum = sum + c.getProduct().getPrice()*c.getQty();
		}
		return sum;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getSessionTokent() {
		return sessionTokent;
	}
	public void setSessionTokent(String sessionTokent) {
		this.sessionTokent = sessionTokent;
	}
	public ShoppingCart() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Set<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public void addCartItem(CartItem cartItem)
	{
		cartItems.add(cartItem);
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getItemsNumber() {
		return cartItems.size();
	}
	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}
	
   
}
