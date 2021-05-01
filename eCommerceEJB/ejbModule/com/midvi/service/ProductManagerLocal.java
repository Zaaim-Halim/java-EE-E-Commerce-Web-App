package com.midvi.service;

import java.util.List;

import javax.ejb.Local;

import com.midvi.entity.Album;
import com.midvi.entity.Category;
import com.midvi.entity.Product;

@Local
public interface ProductManagerLocal {
	 public Product addProduct(Product p);
	 public Product editProduct(Long id ,Product p);
	 public Product updateProductPrice(Long id, double newPrice);
	 public	Product updateProductQuantity(Long id, int newQty);
	 public	Product getProductByLibelle(String libelle);
	 public Product updateProductOldPrice(Long id, double newPrice);
	 public Product deleteProductById(Long id);
	//search and filters
	 List<Product> getProductsByCreteria(String creteria);
	 List<Product> getProductsByCategoryCreteria(String creteria);
	 List<Product> getAllProductOrderDesc();
	 List<Product> getProductsPeageable(int pageSize);
	 List<Product> getProductsOfPrice(double price);
	 List<Product> getProductsOfPriceBetween(double first, double second); 
	 List<Product> getProductsOfCategory(String category);
	 List<Product> getProductsOfCategories(String ...categories);
	 Product getProductById(Long id);
	// Categories 
	 List<Category> getAllCategories();
	 Category getAllCategoryByName(String name);
     Category saveCategory(Category category);
     List<Category> getAllDistinctCategories();
     
   //Album 
     void addImageToProductAlbum(Long id, Album album);
     void deleteImageFromProductAlbum(Long product_id, Long id);
     List<Album> getAlbumOfApProduct(Long id_p);
}
