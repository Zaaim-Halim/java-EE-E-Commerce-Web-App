package com.midvi.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.midvi.entity.Category;
import com.midvi.entity.ShoppingCart;
import com.midvi.entity.User;
import com.midvi.service.ProductManagerLocal;
import com.midvi.service.ShoppingCartManagerLocal;


@WebServlet({"/index"})
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ProductManagerLocal productManager;
	@EJB
	private ShoppingCartManagerLocal shoppingartManager;

	public IndexController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null)
		{
			if (action.equals("Pdetails")) {
				Long id = Long.parseLong(request.getParameter("Pid"));
				//request.setAttribute("albums", productManager.getAlbumOfApProduct(id));
				request.setAttribute("product", productManager.getProductById(id));
				List<Category> categories =  productManager.getAllDistinctCategories();
				categories.stream().limit(5).collect(Collectors.toList());
				request.setAttribute("categories",categories);
				request.getRequestDispatcher("WEB-INF/templates/Public/details.jsp").forward(request, response);
				return;
			}
			
		}
		request.setAttribute("products", productManager.getAllProductOrderDesc());
		request.setAttribute("categories", productManager.getAllDistinctCategories());
		request.setAttribute("shoppingCart",this.getDesiredShoppingCart(request));
		request.getRequestDispatcher("WEB-INF/templates/Public/index.jsp").forward(request, response);
	}
	
	protected ShoppingCart getDesiredShoppingCart(HttpServletRequest request)
	{
		 HttpSession session = request.getSession();
		 ShoppingCart  shoppingCart = null;
		 if(session.getAttribute("user") == null && session.getAttribute("token") != null )
    	 {
    		 shoppingCart = shoppingartManager.findShoppingCartBySessionToken((String)session.getAttribute("token")); 
    	 }
    	 if(session.getAttribute("user") != null && session.getAttribute("token") == null )
    	 {
    		 shoppingCart = shoppingartManager.findShoppingCartOfUser((User)session.getAttribute("user")); 
    	 }
    	 if(session.getAttribute("user") == null && session.getAttribute("token") == null)
    	 {
    		 shoppingCart = null;
    	 }
    	
		 return  shoppingCart;
	}

}
