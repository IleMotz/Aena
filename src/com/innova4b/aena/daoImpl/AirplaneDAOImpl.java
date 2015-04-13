package com.innova4b.aena.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.innova4b.aena.dao.AirplaneDAO;
import com.innova4b.aena.persistent.Airplane;
import com.innova4b.aena.persistent.Airport;

public class AirplaneDAOImpl implements AirplaneDAO{

	public List<Airplane> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Airport.class);
		List<Airplane> airplanes = (List<Airplane>) criteria.list();
		session.getTransaction().commit();
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
