package com.midvi.dao;

import com.midvi.entity.CartItem;
import com.midvi.entity.ShoppingCart;
import com.midvi.entity.User;

public interface ShoppingCatDAO extends GenericDAO<ShoppingCart, Long> {
	ShoppingCart findBySessionToken(String sessionToken);
	ShoppingCart findByUser(User user);
	void saveShoppingCart(ShoppingCart shoppinCart);
	ShoppingCart addCartItemToShoppinCart(Long id, CartItem cartItem);
	ShoppingCart deleteCartItemFromShoppingCart(Long id, Long cartItemId);
	ShoppingCart updateCartItemQty(Long id, Long cartItemId, int newQty);

}
