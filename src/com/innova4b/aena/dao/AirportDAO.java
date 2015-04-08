package com.innova4b.aena.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.innova4b.aena.persistent.Airport;
import com.innova4b.aena.persistent.Gate;

public class AirportDAO {

	public List<Airport> getAllAirports() {
		List<Airport> airports= new ArrayList<Airport>();
		return airports;
	}
	
	public Airport getAirportByName(String airportName) {
		
		Airport airport = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Airport.class);
		criteria.add(Restrictions.like("name", airportName));
		List<Airport> airports = (List<Airport>) criteria.list();
		session.getTransaction().commit();
		
		if (airports.size() == 1) {
			airport = airports.get(0);
		}
		return airport;
	}

	public void update(Airport airport) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.merge(airport);
		for(Gate gate: airport.getGates()) {
			session.merge(gate);
		}
		session.getTransaction().commit();	
	}
}
