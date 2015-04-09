package com.innova4b.aena.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.innova4b.aena.persistent.Airplane;

public class AirplaneDAO {

	public List<Airplane> getAll() {
		List<Airplane> airplanes= new ArrayList<Airplane>();
		return airplanes;
	}
	
	public Airplane getById(String plateNumber) {
		
		Airplane airplane = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Airplane.class);
		criteria.add(Restrictions.like("plateNumber", plateNumber));
		List<Airplane> airplanes = (List<Airplane>) criteria.list();
		session.getTransaction().commit();
		
		if (airplanes.size() == 1) {
			airplane = airplanes.get(0);
		}
		return airplane;
	}

	public void update(Airplane airplane) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.merge(airplane);
		session.getTransaction().commit();	
	}
	
	public void delete(Airplane airplane) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(airplane);
		session.getTransaction().commit();	
	}
	
}
