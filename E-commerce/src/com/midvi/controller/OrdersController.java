package com.midvi.controller;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.midvi.entity.CartItem;
import com.midvi.entity.UserOrder;
import com.midvi.entity.OrderDetail;
import com.midvi.entity.ShoppingCart;
import com.midvi.entity.User;
import com.midvi.service.OrderMangerLocal;
import com.midvi.service.PasswordAuthenticatorManager;
import com.midvi.service.ProductManagerLocal;
import com.midvi.service.ShoppingCartManagerLocal;
import com.midvi.service.UserManagerLocal;
import com.midvi.web.EmailValidator;
import com.midvi.web.GenericResponse;
import com.midvi.web.PasswordValidator;

@WebServlet("/order")
public class OrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private ShoppingCartManagerLocal shoppingCartManager;
	@EJB
	private UserManagerLocal userManager;
	@EJB
	private ProductManagerLocal productManger;
	@EJB
	private OrderMangerLocal orderManager;
	@EJB
	private PasswordAuthenticatorManager passwordAuthenticatorManager;

	private EmailValidator emailValidator = new EmailValidator();
	private PasswordValidator passwordValidator = new PasswordValidator();

	public OrdersController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id");
		
		String action = request.getParameter("action");
		if(idString.equals("none"))
		{
			request.setAttribute("emptyCart", "you have nothing in your cart to order");
			request.getRequestDispatcher("WEB-INF/templates/Public/orders.jsp").forward(request, response);
			return;
		}
		Long id = Long.parseLong(idString );
		if(action.equals("verify"))
		{
			if(request.getParameter("email").equals("none"))
			{
				request.setAttribute("exist", 0);
				request.setAttribute("canPass", true);
			}
			else
			{
				User user = userManager.findUserByEmail(request.getParameter("email"));
				if(user == null)
				{
					String location = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/order?action=preVerify&id="+id+"&userNotFound=notfound";
					response.sendRedirect(location);
					return;
				}
				else
				{
					 request.setAttribute("user", user);
					 request.setAttribute("exist", 1);
					 request.setAttribute("canPass", true);
				}	 
			}   
		}
		if(action.equals("preVerify"))
		{
			request.setAttribute("canPass", false);
			if(request.getParameter("userNotFound") != null)
			{
				request.setAttribute("userNotFound", "the email you gave does not associate with a user");
			}
		}
		
		request.setAttribute("shoppingCart", shoppingCartManager.findShoppingCartById(id));
		request.setAttribute("categories", productManger.getAllDistinctCategories());
		request.getRequestDispatcher("WEB-INF/templates/Public/orders.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Long id = Long.parseLong(request.getParameter("id"));
		switch (action) {
		case "add":
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirmedPassword = request.getParameter("cpassword");
			String tell = request.getParameter("tel");
			String adress1 = request.getParameter("address1");
			String adress2 = request.getParameter("address2");
			String pays = request.getParameter("pays");
			String ville = request.getParameter("ville");
			String zip = request.getParameter("zip");
			if (userManager.findUserByEmail(email) == null) {
				if (!isPasswordMatch(password, confirmedPassword)) {
					GenericResponse res = new GenericResponse("password doesn't match !", 1);
					request.setAttribute("response", res);
					doGet(request, response);
					return;
				}
				if (!emailValidator.isValid(email)) {
					GenericResponse res = new GenericResponse("not a valid email!", 2);
					request.setAttribute("response", res);
					doGet(request, response);
					return;
				}
				if (!passwordValidator.isValid(password)) {
					GenericResponse res = new GenericResponse("not a valid password!", 3);
					request.setAttribute("response", res);
					doGet(request, response);
					return;

				}
				User user = new User();
				user.setEmail(email);
				user.setNom(nom);
				user.setPrenom(prenom);
				user.setPassword(passwordAuthenticatorManager.generateSecurePassword(password));
				userManager.registerNewUser(user, getAppUrl(request));
			}
			// now create a n order
			ShoppingCart shoppingCart = shoppingCartManager.findShoppingCartById(id);
			for (CartItem c : shoppingCart.getCartItems()) {
				UserOrder order = new UserOrder();
				order.setUser(userManager.findUserByEmail(email));
				order.setProduct(c.getProduct());
				order.setQuantity(c.getQty());
				order.setOrderDate(new Date());
				order.setStatus("NOT DILIVERED");
				OrderDetail orderDetail = new OrderDetail(adress1 + "\n" + adress2, tell, pays, ville, zip);
				order.setOrderDetail(orderDetail);
				// presist the order !!!
				orderManager.addOrder(order);
				// now delete the cart !

			}
			shoppingCartManager.deteleShoppingCart(id);
			request.getSession().removeAttribute("token");
			// add a succes response
			GenericResponse res = new GenericResponse("your order has been added successfuly", 200);
			request.setAttribute("response", res);
			doGet(request, response);
			
			break;
		}
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	private boolean isPasswordMatch(String password, String confirmedPassword) {
		if (password.equals(confirmedPassword))
			return true;
		return false;
	}

}
