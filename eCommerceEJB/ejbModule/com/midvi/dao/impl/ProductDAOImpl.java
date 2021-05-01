package com.midvi.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.Query;

import com.midvi.dao.AlbumDAO;
import com.midvi.dao.CategoryDAO;
import com.midvi.dao.GenericDAOImpl;
import com.midvi.dao.ProductDAO;
import com.midvi.entity.Album;
import com.midvi.entity.Category;
import com.midvi.entity.Product;

@Singleton
public class ProductDAOImpl extends GenericDAOImpl<Product, Long>
       implements ProductDAO{
	@Inject
	private AlbumDAO albumDAO;
	@Inject
	private CategoryDAO categoryDao;
	
	protected ProductDAOImpl() {
		super(Product.class);
	}

	@Override
	public Product updatePrice(Long id, double newPrice) {
		Product p = em.find(Product.class, id);
		em.getTransaction().begin();
		p.setPrice(newPrice);
		em.getTransaction().commit();
		return p;
	}

	@Override
	public Product updateQuantity(Long id, int newQty) {
		Product p = em.find(Product.class, id);
		em.getTransaction().begin();
		p.setQuantity(newQty);
		em.getTransaction().commit();
		return p;
	}

	@Override
	public Product findByLibelle(String libelle) {
		Query query = em.createQuery("SELECT p FROM Product p WHERE p.libelle ='"+libelle+"'");
		return (Product) query.getSingleResult();
	}

	@Override
	public Product updateOldPrice(Long id, double newPrice) {
		Product p = em.find(Product.class, id);
		em.getTransaction().begin();
		p.setOldPrice(newPrice);
		em.getTransaction().commit();
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByCreteria(String creteria) {
		Query query = em.createQuery("SELECT p FROM Product p WHERE p.libelle LIKE ?1");
		query.setParameter(1, "%"+creteria+"%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAllProductOrderDesc() {
		Query query = em.createQuery("SELECT p FROM Product p ORDER BY p.dateCreation");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProductPeageable(int pageSize) {
		Query query = em.createQuery("SELECT p FROM Product p ORDER BY p.dateCreation")
				.setMaxResults(pageSize);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProductsOfPriceBetween(double first, double second) {
		Query query = em.createQuery("SELECT p FROM Product p WHERE p.price BETWEEN :first AND :second");
		query.setParameter("first", first);
		query.setParameter("second", second);
		return query.getResultList();
	}

	@Override
	public Product editProduct(Long id,Product p) {
		Product product = em.find(Product.class, id);
		if(product != null)
		{
			//product.setAlbums(p.getAlbums());
			//product.setCategories(p.getCategories());
			product.setDescription(p.getDescription());
			//product.setImage(p.getImage());
			product.setLibelle(p.getLibelle());
			product.setOldPrice(p.getOldPrice());
			product.setPrice(p.getPrice());
			product.setQuantity(p.getQuantity());
			em.merge(product);
		}
		return product;
	}

	@Override
	public List<Product> findProductsOfCategory(String category) {
	
		  Category cat = categoryDao.findCategoryByName(category);
		  List<Product> pList = new ArrayList<Product>();
		  List<Product> products = findAll();
		  for(Product p : products)
		  {
			  for(Category c : p.getCategories())
			  {
				  if(c.equals(cat))
				  {
					  pList.add(p);
				  }
			  }
		  }
		return pList;
	}

	@Override
	public List<Product> findProductsOfCategories(String ...categories) {
		List<Product> pList = new ArrayList<Product>();
		List<Product> products = findAll();
		for(int i = 0 ; i< categories.length; i++)
		{
			Category cat = categoryDao.findCategoryByName(categories[i]);
			 for(Product p : products)
			  {
				  for(Category c : p.getCategories())
				  {
					  if(c.equals(cat))
					  {
						  pList.add(p);
					  }
				  }
			  }
			
		}
		return pList;
	}

	@Override
	public void addImage(Long id, Album album) {
	Product p = em.find(Product.class, id);
	//em.getTransaction().begin();
	p.getAlbums().add(album);
	//em.getTransaction().commit();
	em.merge(p);
	}

	@Override
	public void deleteImageFromAlbum(Long product_id, Long album_id) {
		albumDAO.deleteImageById(album_id);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProductsOfPrice(double price) {
		Query query = em.createQuery("SELECT p FROM Product p WHERE p.price ='"+price+"'");
		return query.getResultList();
	}

	@Override
	public List<Product> findByCategoryCreteria(String creteria) {
		Query query = em.createQuery("SELECT DISTINCT c FROM Category c WHERE c.name LIKE ?1");
		query.setParameter(1, "%"+creteria+"%");
		@SuppressWarnings("unchecked")
		List<Category> categories = query.getResultList();
		java.util.Set<Product> products = new HashSet<Product>();
		for(Category c : categories)
		{
			//need little bit of optimization
			// filter all occurrence of the products found by a given category 
			products.add(this.findProductsOfCategory(c.getName()).get(0));
		}
		return new ArrayList<Product>(products);
	}

	@Override
	public List<Album> getAlbum(Long id_p) {
		
		return albumDAO.findAlbum(id_p);
	}


}
