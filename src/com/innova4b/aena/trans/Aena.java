
package com.innova4b.aena.trans;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import com.innova4b.aena.persistent.Airplane;

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
			
		}
		
//		if ("insertCoche".equalsIgnoreCase(args[0])) {
//			er.insertCoche("1111",5);
//		}
//		
//		if ("insertCamion".equalsIgnoreCase(args[0])) {
//			er.insertCamion("2222",2,1500,2500);
//		}
//		
//		if ("retrieveCoche".equalsIgnoreCase(args[0])) {
//			er.retrieveCoche(args[1]);
//		}
//		System.out.println("** main / HibernateUtil.getSessionFactory().isClosed() = " );
//		System.out.println("** main / HibernateUtil.getSessionFactory().isClosed() = " + HibernateUtil.getSessionFactory().isClosed());
		HibernateUtil.getSessionFactory().close();
	}

	private void retrieveAirplane(String id) {
		
		Long longId = Long.parseLong(id);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		
		try {
			Airplane airplane = (Airplane) session.load(Airplane.class, longId);
				System.out.println("\tairplane " + airplane.toString());
				System.out.println("\tairplane " + airplane.getMatricula());
		} catch (ObjectNotFoundException onfe) {
				System.out.println("\tairplane with id =" + id + " not found");
		} catch (Exception e) {
				System.out.println("\t**ERROR retrieveAirplane **: " + e.getMessage());
		}
		
		

		
		session.getTransaction().commit();	
	}

	private void listAllAirplanes() {
		System.out.println("** listAllAirplanes");
		
		Long id = Long.parseLong("1");
		
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

//	private void retrieveCoche(String id) {
//		
//		Long miId = Long.parseLong(id);
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//			
//		session.beginTransaction();
//
//		Vehiculo coche = (Vehiculo) session.load(Vehiculo.class, miId);
//		System.out.println("coche id:" + coche.getIdVehiculo());
//		System.out.println("coche id:" + coche.toString());	
//		session.getTransaction().commit();
//		
//	}
//
//	private void insertCoche(String matricula, int numeroPlazas) {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		
//		Coche coche = new Coche();
//		coche.setMatricula(matricula);
//		coche.setNumeroPlazas(numeroPlazas);
//		System.out.println("coche id:" + coche.getIdVehiculo());
//		
//		session.beginTransaction();
//		
//		session.persist(coche);
//		
//		System.out.println("coche id:" + coche.getIdVehiculo());
//		
//		session.getTransaction().commit();
//	}
//
//	private void insertCamion(String matricula, int numeroPlazas, float tara, float mna) {
//		
//		Camion camion = new Camion();
//		camion.setMatricula(matricula);
//		camion.setNumeroPlazas(numeroPlazas);
//		camion.setMna(mna);
//		camion.setTara(tara);
//		System.out.println("camion id:" + camion.getIdVehiculo());
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		
//		session.beginTransaction();
//		session.persist(camion);
//		
//		System.out.println("coche id:" + camion.getIdVehiculo());
//		session.getTransaction().commit();
//	}
	
}

