package com.midvi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.Query;

import com.midvi.dao.CategoryDAO;
import com.midvi.dao.GenericDAOImpl;
import com.midvi.entity.Category;

@Singleton
public class CategoryDAOImpl extends GenericDAOImpl<Category, Long> implements CategoryDAO {

	protected CategoryDAOImpl() {
		super(Category.class);

	}

	@Override
	public Category findCategoryByName(String name) {
		Query query = em.createQuery("SELECT c From Category c WHERE c.name='" + name + "'");
		Category category = null;
		if (!query.getResultList().isEmpty()) {
			category = (Category) query.getResultList().get(0);
		}
		return category;

	}

	@Override
	public List<Category> findAllDistinctCategories() {
		List<Category> categories = this.findAll();

		List<Category> categ = categories.stream().reduce(new ArrayList<>(),
				(List<Category> accumulator, Category category) -> {
					if (accumulator.stream().noneMatch(cat -> cat.getName().equals(category.getName()))) {
						accumulator.add(category);
					}
					return accumulator;
				}, (acc1, acc2) -> {
					acc1.addAll(acc2);
					return acc1;
				});
		return categ;
	}

}
