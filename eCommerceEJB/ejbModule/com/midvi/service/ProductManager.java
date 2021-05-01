package com.midvi.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.midvi.dao.CategoryDAO;
import com.midvi.dao.ProductDAO;
import com.midvi.entity.Album;
import com.midvi.entity.Category;
import com.midvi.entity.Product;

@Stateless
@LocalBean
public class ProductManager implements ProductManagerRemote, ProductManagerLocal {
   
	@Inject
    private ProductDAO productDao;
	@Inject
	private CategoryDAO categoryDao;
	
    public ProductManager() {
    	
    }
    
	@Override
	public Product addProduct(Product p) {
		return productDao.save(p);
	}

	@Override
	public Product editProduct(Long id ,Product p) {
		return productDao.editProduct(id,p);
	}

	@Override
	public Product updateProductPrice(Long id, double newPrice) {
		return productDao.updatePrice(id, newPrice);
	}

	@Override
	public Product updateProductQuantity(Long id, int newQty) {
		
		return productDao.updateQuantity(id, newQty);
	}

	@Override
	public Product getProductByLibelle(String libelle) {
		return productDao.findByLibelle(libelle);
	}

	@Override
	public Product updateProductOldPrice(Long id, double newPrice) {
		return productDao.updateOldPrice(id, newPrice);
	}

	@Override
	public List<Product> getProductsByCreteria(String creteria) {
		return productDao.findByCreteria(creteria);
	}

	@Override
	public List<Product> getAllProductOrderDesc() {
		return productDao.findAllProductOrderDesc();
	}

	@Override
	public List<Product> getProductsPeageable(int pageSize) {
		return productDao.findProductPeageable(pageSize);
	}

	@Override
	public List<Product> getProductsOfPriceBetween(double first, double second) {
		return productDao.findProductsOfPriceBetween(first, second);
	}

	@Override
	public List<Product> getProductsOfCategory(String category) {
		return productDao.findProductsOfCategory(category);
	}

	@Override
	public List<Product> getProductsOfCategories(String ...categories) {
		return productDao.findProductsOfCategories(categories);
	}

	@Override
	public Product deleteProductById(Long id) {
		Product p = productDao.findById(id);
		if(p != null)
		{
			 productDao.delete(p);
		}
		  
		return p; 
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.findAll();
	}

	@Override
	public Category getAllCategoryByName(String name) {
		return categoryDao.findCategoryByName(name);
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryDao.save(category);
		
	}

	@Override
	public Product getProductById(Long id) {
		
		return productDao.findById(id);
	}

	@Override
	public List<Category> getAllDistinctCategories() {
		return categoryDao.findAllDistinctCategories();
	}

	@Override
	public void addImageToProductAlbum(Long id ,Album album) {
		productDao.addImage(id,album);
		
	}

	@Override
	public void deleteImageFromProductAlbum(Long product_id,Long album_id) {
		productDao.deleteImageFromAlbum(product_id,album_id);
		
	}

	@Override
	public List<Product> getProductsOfPrice(double price)  {
		return productDao.findProductsOfPrice(price);
	}

	@Override
	public List<Product> getProductsByCategoryCreteria(String creteria) {
		return productDao.findByCategoryCreteria(creteria);
	}

	@Override
	public List<Product> findByCategoryCreteria(String creteria) {
		return null;
	}

	@Override
	public List<Album> getAlbumOfApProduct(Long id_p) {
		
		return productDao.getAlbum(id_p);
	}

}
