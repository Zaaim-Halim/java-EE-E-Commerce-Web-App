package com.midvi.dao;

import java.util.List;

import com.midvi.entity.Category;

public interface CategoryDAO extends GenericDAO<Category, Long>{
	Category findCategoryByName(String name);
	List<Category> findAllDistinctCategories();
	

}
