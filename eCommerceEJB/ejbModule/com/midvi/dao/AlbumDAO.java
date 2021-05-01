package com.midvi.dao;

import java.util.List;

import com.midvi.entity.Album;

public interface AlbumDAO extends GenericDAO<Album, Long>{
	void saveImageToAlbum(Album album);
	void deleteImageById(Long id);
	List<Album> findAlbum(Long id_p);

}
