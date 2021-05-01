package com.midvi.webService;

import javax.ejb.Local;

import com.midvi.entity.ShoppingCart;

@Local
public interface IShoppingCartWebService {
	public CartDto addCartItemToShoppingCart(Long id, Long pid,int qty,String token);
	public CartDto removeCartItemFromShppingCart(Long id ,Long cartItemId);
	public ShoppingCart updateShoppingCartCartItemProductQty(Long id,Long cartItemID, int newQty);
	public ShoppingCart findShoppingCartBySessionToken(String token);

}
