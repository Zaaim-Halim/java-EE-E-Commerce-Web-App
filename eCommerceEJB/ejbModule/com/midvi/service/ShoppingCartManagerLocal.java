package com.midvi.service;

import java.util.List;

import javax.ejb.Local;

import com.midvi.entity.CartItem;
import com.midvi.entity.ShoppingCart;
import com.midvi.entity.User;

@Local
public interface ShoppingCartManagerLocal {
	public CartItem addCartItem(ShoppingCart shoppinCart, CartItem cartItem);
	public CartItem findCartItemById(Long shoppingCart_id);
	public CartItem findCartItemByProductId(Long id);
	public CartItem deleteCartItem(Long id);
	public ShoppingCart findShoppingCartById(Long id);
	public ShoppingCart findShoppingCartBySessionToken(String token);
	public ShoppingCart findShoppingCartOfUser(User user);
	public ShoppingCart addCartItemToShoppingCart(Long id, CartItem cartItem);
	public ShoppingCart addCartItemToShoppingCartOfUser(Long id,Long user_id, CartItem cartItem);
	public ShoppingCart removeCartItemFromShppingCart(Long id ,Long cartItemId);
	public ShoppingCart updateShoppingCartCartItemProductQty(Long id,Long cartItemID, int newQty);
    public ShoppingCart deteleShoppingCart(Long id);
    public ShoppingCart addItemsToShoppingCart(List<CartItem> cartItems,User user);
	public void saveShoppingCart(ShoppingCart shoppingCart);
	

}
