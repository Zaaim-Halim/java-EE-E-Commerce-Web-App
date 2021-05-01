package com.midvi.dao;

import java.util.List;

import com.midvi.entity.Album;
import com.midvi.entity.Product;

public interface ProductDAO extends GenericDAO<Product, Long>{
	Product updatePrice(Long id, double newPrice);
	Product updateQuantity(Long id, int newQty);
	Product findByLibelle(String libelle);
	Product updateOldPrice(Long id, double newPrice);
	Product editProduct(Long id,Product p);
	//search and filters
	List<Product> findByCreteria(String creteria);
	List<Product> findByCategoryCreteria(String creteria);
	List<Product> findAllProductOrderDesc();
	List<Product> findProductPeageable(int pageSize);
	List<Product> findProductsOfPriceBetween(double first, double second);
	List<Product> findProductsOfCategory(String category);
	List<Product> findProductsOfCategories(String ...categories);
	void addImage(Long id, Album album);
	void deleteImageFromAlbum(Long product_id, Long album_id);
	List<Product> findProductsOfPrice(double price);
	public List<Album> getAlbum(Long id_p);
	
}
