
package com.innova4b.aena.trans;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import com.innova4b.aena.persistent.Airplane;
import com.innova4b.aena.persistent.Airport;
import com.innova4b.aena.persistent.Gate;

public class Aena {

	public static void main(String[] args) {
		
		Aena aena = new Aena();

		System.out.println("** main / args.length = " + args.length);
		
		if (args.length > 0 ) {

			System.out.println("args[0]:" + args[0]);

			if ((args.length == 1) && ("listAllAirplanes".equalsIgnoreCase(args[0]))) {
				aena.listAllAirplanes();
			}
			
			if ((args.length == 2) && ("retrieveAirplane".equalsIgnoreCase(args[0]))) {
				aena.retrieveAirplane(args[1]);
			}
			
			if ((args.length == 1) && ("listAllAirports".equalsIgnoreCase(args[0]))) {
				aena.listAllAirports();
			}
			
			if ((args.length == 1) && ("listAllGates".equalsIgnoreCase(args[0]))) {
				aena.listAllGates();
			}
		}
		
		HibernateUtil.getSessionFactory().close();
	}

	private void retrieveAirplane(String id) {
		
		Long longId = Long.parseLong(id);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		
		try {
			Airplane airplane = (Airplane) session.load(Airplane.class, longId);
				System.out.println("\tairplane " + airplane.toString());
				System.out.println("\tairplane " + airplane.getPlateNumber());
		} catch (ObjectNotFoundException onfe) {
				System.out.println("\tairplane with id =" + id + " not found");
		} catch (Exception e) {
				System.out.println("\t**ERROR retrieveAirplane **: " + e.getMessage());
		}
		
		session.getTransaction().commit();	
	}

	private void listAllAirplanes() {
		System.out.println("** listAllAirplanes");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Airplane.class);
		
		List listAirplanes = (List) criteria.list();
		System.out.println("\tairplanes #" + listAirplanes.size());
		
		for (int cont = 0; cont < listAirplanes.size(); cont++) {
			System.out.println("\t(" + cont + ") " + listAirplanes.get(cont).toString());	
		}
		session.getTransaction().commit();
	}

	private void listAllAirports() {
		System.out.println("** listAllAirports");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Airport.class);
		
		List listAirports = (List) criteria.list();
		System.out.println("\tairports #" + listAirports.size());
		Airport airport;
		for (int cont = 0; cont < listAirports.size(); cont++) {
			airport = (Airport) listAirports.get(cont);
			System.out.println("\t(" + cont + ") " + airport.toString());
			System.out.println("\t(" + cont + ") " + airport.getGates().toString());
		}
		session.getTransaction().commit();
	}
	
	private void listAllGates() {
		System.out.println("** listAllGates");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Gate.class);
		
		List<Gate> listGates = criteria.list();
		System.out.println("\tgates #" + listGates.size());
		Gate gate;
		for (int cont = 0; cont < listGates.size(); cont++) {
			gate = (Gate) listGates.get(cont);
			System.out.println("\t(" + cont + ") " +  gate.toString());
		}
		session.getTransaction().commit();
	}
	
}

