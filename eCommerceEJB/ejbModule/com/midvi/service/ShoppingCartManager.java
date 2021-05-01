package com.midvi.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.midvi.dao.ShoppingCatDAO;
import com.midvi.entity.CartItem;
import com.midvi.entity.ShoppingCart;
import com.midvi.entity.User;


@Stateless
@LocalBean
public class ShoppingCartManager implements ShoppingCartManagerRemote, ShoppingCartManagerLocal {
	
	@Inject
	private ShoppingCatDAO shoppingCartDAO;
	
    public ShoppingCartManager() {
        
    }
	@Override
	public CartItem addCartItem(ShoppingCart shoppingCart, CartItem cartItem) {
		shoppingCartDAO.addCartItemToShoppinCart(shoppingCart.getId(),cartItem);
		return cartItem;
	}
	@Override
	public CartItem findCartItemById(Long shoppingCart_id) {
		
		return null;
	}
	@Override
	public CartItem findCartItemByProductId(Long id) {
		return null;
	}
	@Override
	public CartItem deleteCartItem(Long id) {
		
		return null;
	}
	@Override
	public ShoppingCart findShoppingCartById(Long id) {
		return shoppingCartDAO.findById(id);
	}
	@Override
	public ShoppingCart findShoppingCartBySessionToken(String token) {
			return shoppingCartDAO.findBySessionToken(token);
		
	}
	@Override
	public ShoppingCart findShoppingCartOfUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ShoppingCart addCartItemToShoppingCart(Long id, CartItem cartItem) {
	 	return shoppingCartDAO.addCartItemToShoppinCart(id, cartItem);
	
	}
	@Override
	public ShoppingCart addCartItemToShoppingCartOfUser(Long id, Long user_id, CartItem cartItem) {
		return null;
	}
	@Override
	public ShoppingCart removeCartItemFromShppingCart(Long id , Long cartItemId) {
		
		return shoppingCartDAO.deleteCartItemFromShoppingCart(id,cartItemId);
	}
	@Override
	public ShoppingCart updateShoppingCartCartItemProductQty(Long id,Long cartItemId, int newQty) {
		
		return shoppingCartDAO.updateCartItemQty(id,cartItemId,newQty);
	}
	@Override
	public ShoppingCart deteleShoppingCart(Long id) {
		ShoppingCart sh = findShoppingCartById(id);
		
		shoppingCartDAO.delete(sh);
		return sh;
	}
	@Override
	public ShoppingCart addItemsToShoppingCart(List<CartItem> cartItems, User user) {
		
		return null;
	}
	@Override
	public void saveShoppingCart(ShoppingCart shoppingCart) {
		shoppingCartDAO.saveShoppingCart(shoppingCart);
		
	}
	
	

}
