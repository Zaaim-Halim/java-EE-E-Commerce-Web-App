package com.midvi.webService;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.midvi.dao.CategoryDAO;
import com.midvi.dao.ProductDAO;
import com.midvi.entity.Category;
import com.midvi.entity.Product;

@Stateless
@Path("products")
public class ProductManagerWebService implements IProductManagerWebService {
    @Inject
    private ProductDAO productDao;
    @Inject
    private CategoryDAO categoryDao;
	@Override
	@POST
	@Path("/addP")
	@Consumes("multipart/form-data")
	public Product addProduct(String libelle,int qty, double prix,String description,
			MultipartFormDataInput fileInput) {
		Product p = new Product();
		p.setDateCreation(new Date());p.setLibelle(libelle);
		p.setPrice(prix);p.setQuantity(qty);p.setDescription(description);
		return productDao.save(p);
	}
 
	//see https://stackoverflow.com/questions/26333298/multipartform-how-to-get-the-original-file-name
	
	@Override
	@GET
	@Path("/byCretiria/{cretiria}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsByCreteria(String creteria) {
		return productDao.findByCreteria(creteria);
	}


	@Override
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProductOrderDesc() {
		System.out.println("got here !!");
		return productDao.findAllProductOrderDesc();
	}

	@Override
	@GET
	@Path("/price/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsOfPrice(@PathParam("price") double price) {
		return productDao.findProductsOfPrice(price);
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/betweenprices/{first}/{second}")
	public List<Product> getProductsOfPriceBetween(@PathParam("first") double first,@PathParam("second") double second) {
		return productDao.findProductsOfPriceBetween(first, second);
	}

	
	@Override
	@GET
	@Path("/ofCategory/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsOfCategory(@PathParam("category") String category) {
		return productDao.findProductsOfCategory(category);
	}
	
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/caterogies")
	public List<Category> getAllDistinctCategories() {
		return categoryDao.findAllDistinctCategories();
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/product/{id}")
	public Product getProductById(@PathParam("id") Long id) {
		System.out.println("Product id is : " + id);
		return productDao.findById(id);
	}


}
