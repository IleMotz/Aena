
package com.innova4b.aena.trans;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import com.innova4b.aena.dao.AirplaneDAO;
import com.innova4b.aena.dao.AirportDAO;
import com.innova4b.aena.dao.HibernateUtil;
import com.innova4b.aena.persistent.Airplane;
import com.innova4b.aena.persistent.Airport;
import com.innova4b.aena.persistent.Gate;
import com.innova4b.aena.persistent.Ticket;

public class Aena {

	public static void main(String[] args) {
		
		Aena aena = new Aena();

		System.out.println("** main / args.length = " + args.length);
		
		if (args.length > 0 ) {

			System.out.println("args[0]:" + args[0]);

			// Airplane
			if ((args.length == 1) && ("listAllAirplanes".equalsIgnoreCase(args[0]))) {
				aena.listAllAirplanes();
			}
			
			if ((args.length == 2) && ("retrieveAirplane".equalsIgnoreCase(args[0]))) {
				aena.retrieveAirplane(args[1]);
			}

			if ((args.length == 2) && ("listAirplaneByPlateNumber".equalsIgnoreCase(args[0]))) {
				System.out.println("args[1]:" + args[1]);
				aena.listAirplaneByPlateNumber(args[1]);
			}
			
			if ((args.length == 4) && ("setAirplaneTotalNumSeats".equalsIgnoreCase(args[0]))) {
				System.out.println("args[1]:" + args[1]);
				aena.setAirplaneTotalNumSeats(args[1],args[2],args[3]);
			}
			
			if ((args.length == 2) && ("bookSeat".equalsIgnoreCase(args[0]))) {
				System.out.println("args[1]:" + args[1]);
				aena.bookSeat(args[1]);
			}
			
			if ((args.length == 2) && ("confirmSeat".equalsIgnoreCase(args[0]))) {
				System.out.println("args[1]:" + args[1]);
				aena.confirmSeat(args[1]);
			}
			
			// Airports
			if ((args.length == 1) && ("listAllAirports".equalsIgnoreCase(args[0]))) {
				aena.listAllAirports();
			}
			
			if ((args.length == 2) && ("listAirportByName".equalsIgnoreCase(args[0]))) {
				System.out.println("args[1]:" + args[1]);
				aena.listAirportByName(args[1]);
			}
			
			if ((args.length == 2) && ("addAirport".equalsIgnoreCase(args[0]))) {
				aena.addAirport(args[1]);
			}

			if ((args.length == 2) && ("addGateToAirport".equalsIgnoreCase(args[0]))) {
				aena.addGateToAirport(args[1]);
			}
			
			if ((args.length == 3) && ("changeGateStatusFromAirport".equalsIgnoreCase(args[0]))) {
				aena.changeGateStatusFromAirport(args[1], args[2]);
			}
			
			// Gates
			if ((args.length == 1) && ("listAllGates".equalsIgnoreCase(args[0]))) {
				aena.listAllGates();
			}
		}
		
		HibernateUtil.getSessionFactory().close();
	}

	private void confirmSeat(String plateNumber) {
		AirplaneDAO airplaneDAO = new AirplaneDAO();
		Airplane airplane = airplaneDAO.getById(plateNumber);
		if (airplane != null) {
			boolean boarded = false;
			for (Ticket ticket : airplane.getTickets()) {
				if (!ticket.getBoarded()) {
					boarded = true;
					ticket.setBoarded(boarded);
					airplane.setNumSeatsConfirmed(airplane.getNumSeatsConfirmed() + 1);
					break;
				}
			}
			if (boarded) {
				airplaneDAO.update(airplane);
				System.out.println("Passenger boarded!!!");
			} else {
				System.out.println("No ticket available!!!");
				System.out.println("\t getTotalNumSeats()="
						+ airplane.getTotalNumSeats());
				System.out.println("\t getNumSeatsConfirmed()="
						+ airplane.getNumSeatsConfirmed());
			}

		} else {
			System.out.println("Airplane with plateNumber '" + plateNumber + "' not found");
		}
	}

	private void bookSeat(String plateNumber) {
		AirplaneDAO airplaneDAO = new AirplaneDAO();
		Airplane airplane = airplaneDAO.getById(plateNumber);
		if (airplane != null) {
			if (airplane.getTotalNumSeatsToBook() > airplane.getNumSeatsBooked()) {
				airplane.setNumSeatsBooked(airplane.getNumSeatsBooked() + 1);
				Ticket ticket = new Ticket();
				ticket.setBoarded(false);
				ticket.setName("test name");
				ticket.setCode("test code");
				ticket.setIdAirplane(airplane.getIdAirplane());
				airplane.getTickets().add(ticket);
				airplaneDAO.update(airplane);
				System.out.println("Seat booked!!!");
			} else {
				System.out.println("Al seats booked");
				System.out.println("\t getTotalNumSeatsToBook=" + airplane.getTotalNumSeatsToBook());
				System.out.println("\t getNumSeatsBooked()=" + airplane.getNumSeatsBooked());
			}
		} else {
			System.out.println("Airplane with plateNumber '" + plateNumber + "' not found");
		}
	}

	private void setAirplaneTotalNumSeats(String plateNumber, String totalNumSeats,
			String totalNumSeatsToBook) {
		
		AirplaneDAO airplaneDAO = new AirplaneDAO();
		Airplane airplane = airplaneDAO.getById(plateNumber);
		if (airplane != null) {
			airplane.setTotalNumSeats(Integer.parseInt(totalNumSeats));
			airplane.setTotalNumSeatsToBook(Integer.parseInt(totalNumSeatsToBook));
			airplaneDAO.update(airplane);
		}	
	}

	private void listAirplaneByPlateNumber(String plateNumber) {
		AirplaneDAO airplaneDAO = new AirplaneDAO();
		Airplane airplane = airplaneDAO.getById(plateNumber);
		if (airplane != null) {
			System.out.println(airplane.toString());
		}		
	}

	private void changeGateStatusFromAirport(String airportName, String gateNumber) {
		AirportDAO airportDAO = new AirportDAO();
		Airport airport = airportDAO.getAirportByName(airportName);
		if (airport != null) {
			for (Gate gate : airport.getGates()) {
				if (gate.getNumber() == Integer.parseInt(gateNumber)) {
					if ("libre".equalsIgnoreCase(gate.getStatus())) {
						gate.setStatus("ocupada");
					} else {
						gate.setStatus("libre");
					}
					break;
				}
			}
			airportDAO.update(airport);
		}	
	}

	private void listAirportByName(String name) {
		AirportDAO airportDAO = new AirportDAO();
		Airport airport = airportDAO.getAirportByName(name);
		if (airport != null) {
			System.out.println(airport.toString());
			System.out.println("\t\tNum gates available = " + airport.getNumGatesAvailable() + " ## " + airport.getGatesAvailable());
			System.out.println("\t\tNum gates no available = " + airport.getNumGatesNoAvailable() + " ## " + airport.getGatesNoAvailable());
		}
	}

	private void addGateToAirport(String name) {
		AirportDAO airportDAO = new AirportDAO();
		Airport airport = airportDAO.getAirportByName(name);
		if (airport != null) {
			Gate newgate = new Gate();
			newgate.setIdAirport(airport.getIdAirport());
			newgate.setStatus("libre");
			newgate.setNumber(airport.getGates().size() + 1);
			if (airport.getGates().add(newgate)) {
				airportDAO.update(airport);
			}
		}
	}

	private void addAirport(String name) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Airport airport = new Airport();
		airport.setName(name);
		System.out.println("IdAirport=" + airport.toString());
		session.save(airport);
		System.out.println("IdAirport=" + airport.toString());
		
		Gate g1 = new Gate();
		g1.setNumber(1);
		g1.setIdAirport(airport.getIdAirport());
		g1.setStatus("libre");
		
		Gate g2 = new Gate();
		g2.setNumber(2);
		g2.setStatus("ocupada");
		g2.setIdAirport(airport.getIdAirport());
		
		List<Gate> gates = new ArrayList<Gate>();
		gates.add(g1);
		gates.add(g2);
		airport.setGates(gates);
		
		for (Gate g : gates) {
			System.out.println("g=" + g.toString());
			session.save(g);
		}
		session.save(airport);
		
		session.save(airport);
		System.out.println("IdAirport=" + airport.toString());
//		Gate g1 = new Gate();
//		g1.setNumber(1);
//		g1.setStatus("libre");
//		g1.setIdAirport(airport.getIdAirport());
//		session.save(g1);
		
		session.getTransaction().commit();		
		
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
		
		List<?> listAirplanes = (List<?>) criteria.list();
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
		List<Airport> airports = (List<Airport>) criteria.list();
		session.getTransaction().commit();
		
		System.out.println("\tNum airports # " + airports.size());
		for (Airport airport : airports) {
			System.out.println("\t\t" + airport.getName() + "(" + airport.getIdAirport() + ")");
			System.out.println("\t\tNum gates = " + airport.getGates().size());
			System.out.println("\t\tNum gates available = " + airport.getNumGatesAvailable() + " ## " + airport.getGatesAvailable());
			System.out.println("\t\tNum gates no available = " + airport.getNumGatesNoAvailable() + " ## " + airport.getGatesNoAvailable());
			System.out.println("");
		}		
	}	
	
	private void listAllGates() {
		System.out.println("** listAllGates");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Gate.class);
		List<Gate> gates = (List<Gate>) criteria.list();
		session.getTransaction().commit();
		
		System.out.println("\tgates #" + gates.size());
		for(Gate g : gates) {System.out.println("\t" +  g.toString());}
	}
	
}

