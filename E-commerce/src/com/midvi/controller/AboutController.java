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

import com.midvi.entity.Category;
import com.midvi.service.ProductManagerLocal;


@WebServlet("/about")
public class AboutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
    private ProductManagerLocal productManger;
	
    public AboutController() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = productManger.getAllDistinctCategories();
		categories.stream().limit(5).collect(Collectors.toList());
		request.setAttribute("categories",categories);
		request.getRequestDispatcher("WEB-INF/templates/Public/about.jsp").forward(request, response);
	}

}
