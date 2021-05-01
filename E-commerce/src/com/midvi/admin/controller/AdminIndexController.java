package com.midvi.admin.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.midvi.service.OrderMangerLocal;
import com.midvi.service.ProductManagerLocal;
import com.midvi.service.UserManagerLocal;


@WebServlet("/Admin-index")
public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private ProductManagerLocal productManager;
    @EJB
    private UserManagerLocal userManager;
    @EJB
    private OrderMangerLocal orderManager;
    
    public AdminIndexController() {
        super();
       
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String idString = request.getParameter("id");
		Long id = null ;
		if(idString != null)
		 id = Long.parseLong(idString);
        
		if(action == null)
		{
			request.setAttribute("products", productManager.getAllProductOrderDesc());
			request.getRequestDispatcher("WEB-INF/templates/Admin/index.jsp").forward(request, response);
			return;
		}
		switch(action)
		{
		 case "modifyP":
			 request.setAttribute("product",productManager.getProductById(id));
			 request.getRequestDispatcher("WEB-INF/templates/Admin/modifierP.jsp").forward(request, response);
			 break;
		
		 case "deleteP":
			 productManager.deleteProductById(id);
			 request.setAttribute("products", productManager.getAllProductOrderDesc());
			 request.getRequestDispatcher("WEB-INF/templates/Admin/index.jsp").forward(request, response);
			 break;
		
		 case "orders":
			 request.setAttribute("orders", orderManager.getAllOrders());
			 request.getRequestDispatcher("WEB-INF/templates/Admin/orders.jsp").forward(request, response);
			 break;
		 case "changetOrderS":
			 orderManager.changeOrderStatus(id,"DILIVERED");
			 request.setAttribute("orders", orderManager.getAllOrders());
			 request.getRequestDispatcher("WEB-INF/templates/Admin/orders.jsp").forward(request, response);
			 break;
         case "users":
        	 request.setAttribute("users",userManager.getAllUsers() );
        	 request.getRequestDispatcher("WEB-INF/templates/Admin/users.jsp").forward(request, response);
			 break;
         case "modifierUserP":
        	 userManager.enableUser(id);
        	 request.setAttribute("users",userManager.getAllUsers() );
        	 request.getRequestDispatcher("WEB-INF/templates/Admin/users.jsp").forward(request, response);
        	 break;
         case "supprimerUser":
        	 userManager.deleteUserById(id);
        	 request.setAttribute("users",userManager.getAllUsers() );
        	 request.getRequestDispatcher("WEB-INF/templates/Admin/users.jsp").forward(request, response);
        	 break;
         
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
