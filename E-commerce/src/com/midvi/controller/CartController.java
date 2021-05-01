package com.midvi.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.midvi.entity.CartItem;
import com.midvi.entity.Product;
import com.midvi.entity.ShoppingCart;
import com.midvi.entity.User;
import com.midvi.service.ProductManagerLocal;
import com.midvi.service.ShoppingCartManagerLocal;
import com.midvi.web.GenericResponse;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ShoppingCartManagerLocal shoppingartManager;
	@EJB
	private ProductManagerLocal productManager;

	// Local var******************
	private String action = null;
	private String page = null;

	// ***************************
	public CartController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		action = request.getParameter("action");
		page = request.getParameter("page");
		Long id;
		HttpSession session = request.getSession();
		switch (action) {

		case "add":
			id = Long.parseLong(request.getParameter("id"));
			Product product = productManager.getProductById(id);
			CartItem cartItem = new CartItem();
			cartItem.setDate(new Date());
			cartItem.setProduct(product);
			cartItem.setQty(Integer.parseInt(request.getParameter("qty")));

			if (session.getAttribute("token") != null && session.getAttribute("user") == null) {
				ShoppingCart shoppingCart = shoppingartManager
						.findShoppingCartBySessionToken((String) session.getAttribute("token"));
				shoppingartManager.addCartItemToShoppingCart(shoppingCart.getId(), cartItem);

			}
			if (session.getAttribute("token") == null && session.getAttribute("user") != null) {
				ShoppingCart shoppingCart = shoppingartManager
						.findShoppingCartOfUser((User) session.getAttribute("user"));
				if(shoppingCart == null)
				{
					Set<CartItem> cartItems = new HashSet<CartItem>();
					cartItems.add(cartItem);
					shoppingCart = new ShoppingCart();
					String token = UUID.randomUUID().toString();
					shoppingCart.setSessionTokent(token);
					shoppingCart.setCartItems(cartItems);
					shoppingartManager.saveShoppingCart(shoppingCart);
					session.setAttribute("token", token);
					
				}
				else
				  shoppingartManager.addCartItemToShoppingCart(shoppingCart.getId(), cartItem);

			}

			if (session.getAttribute("token") == null && session.getAttribute("user") == null) {
				Set<CartItem> cartItems = new HashSet<CartItem>();
				cartItems.add(cartItem);
				ShoppingCart shoppingCart = new ShoppingCart();
				String token = UUID.randomUUID().toString();
				shoppingCart.setSessionTokent(token);
				shoppingCart.setCartItems(cartItems);
				shoppingartManager.saveShoppingCart(shoppingCart);
				session.setAttribute("token", token);
			}
			break;

		case "show":
			request.setAttribute("categories", productManager.getAllDistinctCategories());
			request.setAttribute("shoppingCart", this.getDesiredShoppingCart(request));
			break;
		case "delete":
			id = Long.parseLong(request.getParameter("id"));
			shoppingartManager.deteleShoppingCart(id);
			request.setAttribute("shoppingCart", this.getDesiredShoppingCart(request));

			break;
		case "deleteItem":
			id = Long.parseLong(request.getParameter("id"));
			Long cartItemId = Long.parseLong(request.getParameter("itemId"));
			shoppingartManager.removeCartItemFromShppingCart(id, cartItemId);
			request.setAttribute("shoppingCart", this.getDesiredShoppingCart(request));
			break;

		}

		// decide which page to show
		this.routeToDesiredPage(request, response, page);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		action = request.getParameter("action");
		page = request.getParameter("page");
		Long id;
		//HttpSession session = request.getSession();
		switch (action) {
		case "updateQty":
			id = Long.parseLong(request.getParameter("id"));
			int qty = Integer.parseInt(request.getParameter("qty"));
			Long cartItemId = Long.parseLong(request.getParameter("itemId"));
			shoppingartManager.updateShoppingCartCartItemProductQty(id, cartItemId, qty);
			break;

		}
		request.setAttribute("products", productManager.getAllProductOrderDesc());
		request.setAttribute("categories", productManager.getAllDistinctCategories());
		// decide where to gooo
		this.routeToDesiredPage(request, response, page);
	}

	// NON API
	protected ShoppingCart getDesiredShoppingCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = null;
		if (session.getAttribute("user") == null && session.getAttribute("token") != null) {
			shoppingCart = shoppingartManager.findShoppingCartBySessionToken((String) session.getAttribute("token"));
		}
		if (session.getAttribute("user") != null && session.getAttribute("token") == null) {
			shoppingCart = shoppingartManager.findShoppingCartOfUser((User) session.getAttribute("user"));
		}
		if (session.getAttribute("user") == null && session.getAttribute("token") == null) {
			shoppingCart = null;
		}

		return shoppingCart;
	}

	private void routeToDesiredPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {

		switch (page) {
		case "index":
			request.setAttribute("products", productManager.getAllProductOrderDesc());
			request.setAttribute("categories", productManager.getAllDistinctCategories());
			request.setAttribute("shoppingCart",this.getDesiredShoppingCart(request));
			request.getRequestDispatcher("WEB-INF/templates/Public/index.jsp").forward(request, response);
			break;
		case "panier":
			request.setAttribute("shoppingCart", this.getDesiredShoppingCart(request));
			request.getRequestDispatcher("WEB-INF/templates/Public/pannier.jsp").forward(request, response);
			break;
		case "detail":
			request.getRequestDispatcher("WEB-INF/templates/Public/details.jsp").forward(request, response);

			break;
		default:
			GenericResponse resp = new GenericResponse("action or page not found", 404);
			request.setAttribute("response", resp);
			request.getRequestDispatcher("WEB-INF/templates/Public/error.jsp").forward(request, response);
			// other routing to be added ....

		}
	}

}
