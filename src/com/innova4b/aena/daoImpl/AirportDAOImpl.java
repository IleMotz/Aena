package com.innova4b.aena.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.innova4b.aena.dao.AirportDAO;
import com.innova4b.aena.persistent.Airport;
import com.innova4b.aena.persistent.Gate;

public class AirportDAOImpl implements AirportDAO {

	public List<Airport> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Airport.class);
		List<Airport> airports = (List<Airport>) criteria.list();
		session.getTransaction().commit();
		return airports;
	}
	
	public Airport getById(String airportName) {
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
		session.getTransaction().commit();	
	}

	public void delete(Airport airport) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(airport);
		session.getTransaction().commit();		
	}

	@Override
	public String gatesAvailableHQL(String airportName) {
		List<Airport> airports = null;
		List<Gate> gates = null;
		String message = "Gates available @ " + airportName + ": " ;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hqlAirportQuery = "from Airport where name like '" + airportName + "'";
		Query queAirport = session.createQuery(hqlAirportQuery);
	    airports = queAirport.list();
		
	    Boolean airportFound= false;
	    if (airports.size() == 1) {
	    	airportFound= true;
			String hqlGateQuery = "from Gate where status like 'libre' and idAirport = " + airports.get(0).getIdAirport();
		    Query queGate = session.createQuery(hqlGateQuery);
		    gates = queGate.list();
	    }	
		session.getTransaction().commit();
		
		if (airportFound) {
			for(Gate gate : gates) {
				message += gate.getNumber() + " # ";
			}
		} else {
			message += "** ERROR: airport '" + airportName + "' not found";
		}
		return message;
	}

	@Override
	public String gatesAvailableCriteria(String airportName) {
		
		List<Gate> gates = null;
		List<Airport> airports = null;
		String message = "Gates available @ " + airportName + ": " ;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
				
	    Criteria criAirport = session.createCriteria(Airport.class);
	    criAirport = criAirport.add(Restrictions.like("name", airportName));
	    airports = criAirport.list();
	    
	    Boolean airportFound= false;
	    if (airports.size() == 1) {
	    	airportFound= true;
	    	Criteria criGate = session.createCriteria(Gate.class);
	    	criGate = criGate.add(Restrictions.eq("idAirport", (long) airports.get(0).getIdAirport()));
	    	criGate = criGate.add(Restrictions.like("status", "libre"));
			gates = criGate.list();
	    }	
		session.getTransaction().commit();
		
		if (airportFound) {
			for(Gate gate : gates) {
				message += gate.getNumber() + " # ";
			}
		} else {
			message += "** ERROR: airport '" + airportName + "' not found";
		}
		
		return message;
	}


}
