package com.midvi.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.midvi.entity.Album;
import com.midvi.entity.Category;
import com.midvi.entity.Product;
import com.midvi.service.ProductManagerLocal;
import com.midvi.web.GenericResponse;

@WebServlet("/Admin-Produit")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 10, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class AdminProduitController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@EJB
	private ProductManagerLocal productManager;
	
	
    public AdminProduitController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = productManager.getAllCategories();
		request.setAttribute("categories", categories);
		request.setAttribute("products", productManager.getAllProductOrderDesc());
		request.getRequestDispatcher("WEB-INF/templates/Admin/ajouterP.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action)
		{
		  case "addP":
			  Part filePart = request.getPart("file");
				//String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
				InputStream fileContent = filePart.getInputStream();
				 byte[] b = new byte[(int) filePart.getSize()];
				 b = fileContent.readAllBytes();
				
				Product p = new Product();
				p.setLibelle(request.getParameter("libelle"));
				p.setPrice(Double.parseDouble(request.getParameter("prix")));
				p.setOldPrice(Double.parseDouble(request.getParameter("prix")));
				p.setQuantity(Integer.parseInt(request.getParameter("qty")));
				p.setDescription(request.getParameter("description"));
				p.setImage(java.util.Base64.getEncoder().encodeToString(b));
				p.setDateCreation(new Date());
				p.setCategories(this.prepareProductCategories(request.getParameterValues("category")));
				if(productManager.addProduct(p) != null)
				{
					request.setAttribute("response", new GenericResponse("Produit ajouter avec succes !",200));
				}
				else {
					request.setAttribute("response", new GenericResponse("please try again !",500));
				}
			break;
		  case "addAlbum":
			    Long id = Long.parseLong(request.getParameter("product-id"));
			    Part filePart1 = request.getPart("file");
			    InputStream fileContent1 = filePart1.getInputStream();
				 byte[] b1 = new byte[(int) filePart1.getSize()];
				 b1 = fileContent1.readAllBytes();
				 Album album = new Album();
				 album.setImage(java.util.Base64.getEncoder().encodeToString(b1));
				 album.setName(filePart1.getSubmittedFileName());
				 productManager.addImageToProductAlbum(id,album);
	
				break;
		  case "addCategory":
			   String name = request.getParameter("name");
			   Category category = new Category();
			   category.setName(name);
			   if(productManager.saveCategory(category) != null)
			   {
				   request.setAttribute("response", new GenericResponse("category ajouter avec succes !",200));
			   }
			   else {
				   request.setAttribute("response", new GenericResponse("try agin !",500));
				   
			   }
			   break;
		}
		 request.setAttribute("products", productManager.getAllProductOrderDesc());
		 request.setAttribute("categories", productManager.getAllCategories());
		 request.getRequestDispatcher("WEB-INF/templates/Admin/ajouterP.jsp").forward(request, response);
		
	}
	protected List<Category> prepareProductCategories(String ...categories)
	{
		List<Category> cat = new ArrayList<Category>();
		
		int i = 0 ;
		for(i = 0 ; i< categories.length ; i++)
		{
			if(productManager.getAllCategoryByName(categories[i]) != null)
			{
				cat.add(productManager.getAllCategoryByName(categories[i]));
			}
		}
		return cat;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
		

}
