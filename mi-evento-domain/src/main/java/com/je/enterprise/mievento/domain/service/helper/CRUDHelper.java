package com.je.enterprise.mievento.domain.service.helper;

import java.io.Serializable;
import java.util.List;

import com.je.enterprise.mievento.domain.dao.BaseEntity;
import com.je.enterprise.mievento.domain.dao.GenericDAO;
import com.mongodb.WriteConcern;

public class CRUDHelper<T extends BaseEntity, K extends Serializable> {
	
	protected GenericDAO<T, K> dao;
	
	public CRUDHelper(GenericDAO<T, K> dao){
		this.dao = dao;
	}
	
	public String create(T entity){
		return this.dao.save(entity,WriteConcern.FSYNC_SAFE).getId().toString();
	}
	
	public void update(T entity){
		if (this.dao.exists(entity)){
			this.dao.save(entity,WriteConcern.FSYNC_SAFE);
		}
	}
	
	public void delete(K key){
		this.dao.deleteById(key);
	}

	public List<T> getAll() {
		return this.dao.findAll();
	}
	
	public GenericDAO<T, K> getDao(){
		return dao;
	}
	
	
}
