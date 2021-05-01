package com.midvi.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.midvi.dao.CartItemDAO;
import com.midvi.dao.GenericDAOImpl;
import com.midvi.dao.ShoppingCatDAO;
import com.midvi.entity.CartItem;
import com.midvi.entity.Product;
import com.midvi.entity.ShoppingCart;
import com.midvi.entity.User;

@Singleton
public class ShoppingCatDAOImpl extends GenericDAOImpl<ShoppingCart, Long> implements ShoppingCatDAO {
	@Inject
	private CartItemDAO cartItemDAO;

	protected ShoppingCatDAOImpl() {
		super(ShoppingCart.class);

	}

	@Override
	public ShoppingCart findBySessionToken(String sessionToken) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ShoppingCart> q = cb.createQuery(ShoppingCart.class);
		Root<ShoppingCart> c = q.from(ShoppingCart.class);
		q.select(c).where(cb.equal(c.get("sessionTokent"), sessionToken));
		return em.createQuery(q).getSingleResult();
	}

	@Override
	public ShoppingCart findByUser(User user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ShoppingCart> q = cb.createQuery(ShoppingCart.class);
		Root<ShoppingCart> c = q.from(ShoppingCart.class);
		q.select(c).where(cb.equal(c.get("user"), user));
		return em.createQuery(q).getSingleResult();
	}

	@Override
	public void saveShoppingCart(ShoppingCart shoppinCart) {
		save(shoppinCart);
	}

	@Override
	public ShoppingCart addCartItemToShoppinCart(Long id, CartItem cartItem) {
		ShoppingCart shoppingCart = em.find(ShoppingCart.class, id);
		Product p = cartItem.getProduct();
		List<CartItem> listC = new ArrayList<CartItem>(shoppingCart.getCartItems());
		boolean flag = false;
		for (CartItem c : cartItemDAO.findByProduct(p)) {
			for (CartItem c1 : listC) {
				if (c.equals(c1)) {
					flag = true;
					c1.setQty(c1.getQty() + cartItem.getQty());
					em.merge(c1);
					break;
				}
			}

		}
		if (!flag) {
			shoppingCart.getCartItems().add(cartItem);
			em.merge(shoppingCart);
		}

		return shoppingCart;
	}

	@Override
	public ShoppingCart deleteCartItemFromShoppingCart(Long id, Long cartItemId) {
		ShoppingCart shoppingCart = findById(id);
		CartItem cartItem = cartItemDAO.findById(cartItemId);
		List<CartItem> cartItems = new ArrayList<CartItem>(shoppingCart.getCartItems());
		int index = 0;
		for (CartItem c : cartItems) {

			if (c.getId().equals(cartItem.getId())) {

				cartItemDAO.delete(c);
				// cartItems.remove(c); never do this here!!
				// java.util.ConcurrentModificationException
				index = cartItems.indexOf(c);
			}
		}
		cartItems.remove(index);
		Set<CartItem> hSetcartItems = new HashSet<CartItem>();
		for (CartItem x : cartItems)
			hSetcartItems.add(x);

		shoppingCart.setCartItems(hSetcartItems);

		em.merge(shoppingCart);
		return shoppingCart;
	}

	@Override
	public ShoppingCart updateCartItemQty(Long id, Long cartItemId, int newQty) {

		ShoppingCart sh = em.find(ShoppingCart.class, id);
		for (CartItem c : sh.getCartItems()) {
			if (c.getId().equals(cartItemId)) {
				c.setQty(newQty);
				em.merge(c);
			}
		}
		return sh;
	}

}
