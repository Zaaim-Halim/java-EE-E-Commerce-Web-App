package com.midvi.admin.controller;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.midvi.entity.Product;
import com.midvi.service.ProductManagerLocal;
import com.midvi.web.GenericResponse;


@WebServlet("/Admin-modifierProduit")
public class AdminModifierProduitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB
	private ProductManagerLocal productManager;
	
    public AdminModifierProduitController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Long id = Long.parseLong(request.getParameter("id"));
		Product p = new Product();
		p.setLibelle(request.getParameter("libelle"));
		p.setPrice(Double.parseDouble(request.getParameter("prix")));
		p.setQuantity(Integer.parseInt(request.getParameter("qty")));
		p.setDescription(request.getParameter("description"));
		p.setOldPrice(Double.parseDouble(request.getParameter("oldPrice")));
		//p.setImage(java.util.Base64.getEncoder().encodeToString(b));
		p.setDateCreation(new Date());
		if(productManager.editProduct(id,p) != null)
		{
			request.setAttribute("response", new GenericResponse("Product modified succesfully",200));
		}
		else 
			request.setAttribute("response", new GenericResponse("try again",500));
		
		request.getRequestDispatcher("WEB-INF/templates/Admin/modifierP.jsp").forward(request, response);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
