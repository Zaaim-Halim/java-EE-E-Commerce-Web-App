package com.midvi.dao;

import java.util.List;


public interface GenericDAO<T, ID> {
	List<T> findAll();
	T findById(ID id);
	T save(T entity);
	void delete(T entity);

}
