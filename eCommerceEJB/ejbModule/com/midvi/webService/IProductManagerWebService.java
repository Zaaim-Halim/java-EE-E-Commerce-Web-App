package com.midvi.webService;

import java.util.List;

import javax.ejb.Local;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.midvi.entity.Category;
import com.midvi.entity.Product;

@Local
public interface IProductManagerWebService {
	
	public Product addProduct(String libelle,int qty, double prix,String dicription,
			MultipartFormDataInput fileInput);
	public List<Product> getAllProductOrderDesc();
	public List<Product> getProductsOfPrice(double price);
	public List<Product> getProductsOfPriceBetween(double first, double second);
	public List<Product> getProductsOfCategory(String category);
	public List<Category> getAllDistinctCategories();
	public List<Product> getProductsByCreteria(String creteria);
	public Product getProductById(Long id);

}
