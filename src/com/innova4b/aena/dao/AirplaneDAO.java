package com.innova4b.aena.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.innova4b.aena.persistent.Airplane;

public interface AirplaneDAO {

	public List<Airplane> getAll();	
	public Airplane getById(String plateNumber);
	public void update(Airplane airplane);
	public void delete(Airplane airplane) ;
	
}
