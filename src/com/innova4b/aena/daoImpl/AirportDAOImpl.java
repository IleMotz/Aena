package com.innova4b.aena.daoImpl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.innova4b.aena.dao.AirportDAO;
import com.innova4b.aena.persistent.Airport;
import com.innova4b.aena.persistent.Gate;

public class AirportDAOImpl implements AirportDAO {
	@Override
	public List<Airport> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Airport.class);
		List<Airport> airports = (List<Airport>) criteria.list();
		session.getTransaction().commit();
		return airports;
	}
	@Override
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
	@Override
	public void update(Airport airport) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.merge(airport);
		session.getTransaction().commit();
	}
	@Override
	public void delete(Airport airport) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(airport);
		session.getTransaction().commit();
	}

	@Override
	public String gatesAvailableHQL(String airportName) {

		List<Gate> gates = null;
		
		String message = "Gates available @ " + airportName + ": ";
		String query = "FROM Gate WHERE " + "status like 'libre' AND "
							+ "idAirport IN (FROM Airport WHERE name like '" + airportName+ "')";
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query hqlGates = session.createQuery(query);
		gates = hqlGates.list();
		session.getTransaction().commit();
		
		message += gates.size() + " -> ";
		for (Gate gate : gates) {
			System.out.println(gate.toString());
			message += gate.getNumber() + " # ";
		}
		return message;
	}

	@Override
	public String gatesAvailableCriteria(String airportName) {

		List<Gate> gates = null;
		String message = "Gates available @ " + airportName + ": ";
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		DetachedCriteria dcAirport = DetachedCriteria.forClass(Airport.class);
		dcAirport.setProjection(Projections.property("idAirport"));
		dcAirport.add(Restrictions.like("name", airportName));
		
		Criteria criGate = session.createCriteria(Gate.class);
		criGate = criGate.add(Restrictions.and(Restrictions.like("status", "libre"), Property.forName("idAirport").in(dcAirport)));
					
		gates = criGate.list();
		session.getTransaction().commit();
		message += gates.size() + " -> ";
		for (Gate gate : gates) {
			System.out.println(gate.toString());
			message += gate.getNumber() + " # ";
		}

		return message;
	}

}
