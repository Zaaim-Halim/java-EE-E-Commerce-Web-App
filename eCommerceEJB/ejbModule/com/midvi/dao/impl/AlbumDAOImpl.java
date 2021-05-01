package com.midvi.dao.impl;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.Query;

import com.midvi.dao.AlbumDAO;
import com.midvi.dao.GenericDAOImpl;
import com.midvi.entity.Album;

@Singleton
public class AlbumDAOImpl extends GenericDAOImpl<Album, Long>
       implements AlbumDAO{

	protected AlbumDAOImpl() {
		super(Album.class);
	}

	@Override
	public void saveImageToAlbum(Album album) {
		em.persist(album);
		
	}

	@Override
	public void deleteImageById(Long id) {
		Album album = em.find(Album.class, id);
		delete(album);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Album> findAlbum(Long id_p) {
		Query query = em.createQuery("SELECT a From Album a WHERE a.id_product='"+id_p+"'");
		
		return query.getResultList();
	}
	

}
