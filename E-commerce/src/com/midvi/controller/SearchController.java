package com.midvi.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.midvi.entity.Product;
import com.midvi.service.ProductManagerLocal;
import com.midvi.service.ShoppingCartManagerLocal;

@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    private ProductManagerLocal productManager;
    @EJB
    private ShoppingCartManagerLocal shoppingCartManager;
	//******************** var ****************
    private String action = null;
    //*****************************************
    public SearchController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
		String token = null;
		List<Product> products;
		HttpSession session = request.getSession();
		if(session != null)
		{
			if(session.getAttribute("token") != null)
			{
				token = (String) session.getAttribute("token");
			}
		}
		switch(action)
		{
		case "category":
			String category = request.getParameter("name");
			products = productManager.getProductsOfCategory(category);
			request.setAttribute("products", products);
			break;
		case "Byprice":
			double price = Double.parseDouble( request.getParameter("price"));
			products = productManager.getProductsOfPrice(price);
			request.setAttribute("products", products);
			break;
		case "btnprice":
			double price1 = Double.parseDouble( request.getParameter("price1"));
			double price2 = Double.parseDouble( request.getParameter("price2"));
			products = productManager.getProductsOfPriceBetween(price1, price2);
			request.setAttribute("products", products);
			break;
		case "byCretiria":
			String creteria = request.getParameter("creteria");
			products = productManager.getProductsByCreteria(creteria);
			request.setAttribute("products", products);
			break;
		case "byCategoryCretiria":
			String creteria1 = request.getParameter("creteria");
			products = productManager.getProductsByCategoryCreteria(creteria1);
			request.setAttribute("products", products);
			break;
		}
		if(token != null)
		     request.setAttribute("shoppingCart", shoppingCartManager.findShoppingCartBySessionToken(token));
		//else
		//	 request.setAttribute("shoppingCart", shoppingCartManager.findShoppingCartBySessionToken("not-gonna-find-anything-for-sure"));
	
		
		request.setAttribute("categories", productManager.getAllDistinctCategories());
		request.getRequestDispatcher("WEB-INF/templates/Public/search.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
