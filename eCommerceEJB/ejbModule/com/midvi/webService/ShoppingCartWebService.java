package com.midvi.webService;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.midvi.dao.ProductDAO;
import com.midvi.dao.ShoppingCatDAO;
import com.midvi.entity.CartItem;
import com.midvi.entity.Product;
import com.midvi.entity.ShoppingCart;

@Stateless
@Path("/cart")
public class ShoppingCartWebService implements IShoppingCartWebService {
	@Inject
	private ShoppingCatDAO shoppingCartDao;
	
	@Inject
	private ProductDAO productDao;

	@Override
	@GET
	@Path("/add/{id}/{pid}/{qty}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	

	public CartDto addCartItemToShoppingCart(@PathParam("id") Long id,@PathParam("pid") Long pid,
			@PathParam("qty") int qty,@PathParam("token") String token) {
		
		
		System.out.println("added shopping cart : " + "id : " + id + "  , pid = " + pid + "   ,qty = " + qty
				+ "  ,token=" + token);
		
		CartItem cartItem = new CartItem();
		Product p = productDao.findById(pid);
		cartItem.setProduct(p);
		cartItem.setDate(new Date());
		cartItem.setQty(qty);
		ShoppingCart cart ;
		if(token.equals("NaN"))
		{
			System.out.println("int the first timee !!!!");
			ShoppingCart shoppingCart = new ShoppingCart();
			Set<CartItem> cartItems = new HashSet<CartItem>();
			cartItems.add(cartItem);
			shoppingCart.setCartItems(cartItems);
			shoppingCart.setSessionTokent(UUID.randomUUID().toString());
			cart = shoppingCartDao.save(shoppingCart);
			CartDto carDto = new CartDto(cart.getId(), cart.getItemsNumber(), cart.getTotal(),cart.getSessionTokent());
			carDto.setCartItems(removeAlbumsFromCart(cart) );
			return carDto;
		}
		System.out.println("int the second time  !!!!");
		cart = shoppingCartDao.addCartItemToShoppinCart(id, cartItem);
		CartDto carDto = new CartDto(cart.getId(), cart.getItemsNumber(), cart.getTotal(),cart.getSessionTokent());
		carDto.setCartItems(removeAlbumsFromCart(cart) );
		return carDto;
		

	}

	private Set<CartItem> removeAlbumsFromCart(ShoppingCart cart) {
		Set<CartItem> items = cart.getCartItems();
		Set<CartItem> itemtoreturn = new HashSet<CartItem>();
		Iterator<CartItem> itr = items.iterator();
		while(itr.hasNext())
		{
			
			CartItem item = itr.next();
			//item.getProduct().getAlbums().clear();
			itemtoreturn.add(item);
			
		}
		
		return itemtoreturn;
	}

	@Override
	@GET
	@Path("/remove/{id}/{cartItemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public CartDto removeCartItemFromShppingCart(@PathParam("id") Long id,
			@PathParam("cartItemId") Long cartItemId) {
		ShoppingCart cart = shoppingCartDao.deleteCartItemFromShoppingCart(id, cartItemId);
		CartDto carDto = new CartDto(cart.getId(), cart.getItemsNumber(), cart.getTotal(),cart.getSessionTokent());
		carDto.setCartItems(removeAlbumsFromCart(cart) );
		return carDto;
	}

	@Override
	@GET
	@Path("/update/{id}/{cartItemId}/{qty}")
	@Produces(MediaType.APPLICATION_JSON)
	public ShoppingCart updateShoppingCartCartItemProductQty(@PathParam("id") Long id,
			@PathParam("cartItemId") Long cartItemId, @PathParam("qty") int newQty) {

		return shoppingCartDao.updateCartItemQty(id, cartItemId, newQty);
	}

	@Override
	@GET
	@Path("/find/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ShoppingCart findShoppingCartBySessionToken(@PathParam("token") String token) {

		return shoppingCartDao.findBySessionToken(token);
	}

}
