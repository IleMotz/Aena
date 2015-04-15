package com.innova4b.aena.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.innova4b.aena.dao.AirplaneDAO;
import com.innova4b.aena.persistent.Airplane;
import com.innova4b.aena.persistent.Airport;
import com.innova4b.aena.persistent.Boardingpass;
import com.innova4b.aena.persistent.Gate;

public class AirplaneDAOImpl implements AirplaneDAO{
	@Override
	public List<Airplane> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Airport.class);
		List<Airplane> airplanes = (List<Airplane>) criteria.list();
		session.getTransaction().commit();
		return airplanes;
	}
	@Override
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
	@Override
	public void update(Airplane airplane) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.merge(airplane);
		session.getTransaction().commit();	
	}
	@Override
	public void delete(Airplane airplane) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(airplane);
		session.getTransaction().commit();	
	}

	@Override
	public String numBoardingpassesHQL(String plateNumber, boolean boarded) {
		List<Boardingpass> boardingpasses = null;
		String message = "";
		String query = "FROM Boardingpass ";
				
		query = "FROM Boardingpass WHERE ";
		if (boarded) {
			message = "Boardingpasses confirmed @ ";
			query += "boarded is " + boarded + " AND idAirplane IN (FROM Airplane WHERE plateNumber like '" + plateNumber + "')";
		} else {
			message = "Boardingpasses booked @ ";
			query += "idAirplane IN (FROM Airplane WHERE plateNumber like '" + plateNumber + "')";
		}
		message += plateNumber + ": ";
		System.out.println("numBoardingpassesHQL.query=" + query);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query hqlnumBoardingpassesBooked = session.createQuery(query);
		boardingpasses = hqlnumBoardingpassesBooked.list();
		session.getTransaction().commit();
		
		message += boardingpasses.size() ;

		return message;
	}

	@Override
	public String numBoardingpassesCriteria(String plateNumber, boolean boarded) {
		List<Boardingpass> boardingpasses = null;
		String message = "";
		
		DetachedCriteria dcAirplane = DetachedCriteria.forClass(Airplane.class);
		dcAirplane.setProjection(Projections.property("idAirplane"));
		dcAirplane.add(Restrictions.like("plateNumber", plateNumber));
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criBoardingpass = session.createCriteria(Boardingpass.class);
		
		if (boarded) {
			message = "Boardingpasses confirmed @ ";
			criBoardingpass = criBoardingpass.add(Restrictions.and(Restrictions.eq("boarded", true), Property.forName("idAirplane").in(dcAirplane)));	
		} else {
			message = "Boardingpasses booked @ ";
			criBoardingpass = criBoardingpass.add(Property.forName("idAirplane").in(dcAirplane));
		}
							
		boardingpasses = criBoardingpass.list();
		session.getTransaction().commit();
		
		message += boardingpasses.size() ;

		return message;
	}
	
}
