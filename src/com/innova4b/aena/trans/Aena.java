package com.innova4b.aena.trans;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import com.innova4b.aena.dao.AirplaneDAO;
import com.innova4b.aena.dao.AirportDAO;
import com.innova4b.aena.daoImpl.AirportDAOImpl;
import com.innova4b.aena.daoImpl.HibernateUtil;
import com.innova4b.aena.daoImpl.AirplaneDAOImpl;
import com.innova4b.aena.persistent.Airplane;
import com.innova4b.aena.persistent.Airport;
import com.innova4b.aena.persistent.Boardingpass;
import com.innova4b.aena.persistent.Gate;

public class Aena {

	public static void main(String[] args) {

		Aena aena = new Aena();

		System.out.println("** main / args.length = " + args.length);
		int argNum = 0;
		for (String arg : args) {
			System.out.println("** arg[" + argNum++ + "] = " + arg);
		}

		if (args.length > 0) {

			System.out.println("args[0]:" + args[0]);
			//
			// Airplane
			//
			if ((args.length == 1)
					&& ("getAllAirplanes".equalsIgnoreCase(args[0]))) {
				aena.getAllAirplanes();
			}

			if ((args.length == 2) && ("newAirplane".equalsIgnoreCase(args[0]))) {
				aena.newAirplane(args[1]);
			}

			if ((args.length == 2)
					&& ("deleteAirplaneById".equalsIgnoreCase(args[0]))) {
				aena.deleteAirplaneById(args[1]);
			}

			if ((args.length == 2)
					&& ("getAirplaneById".equalsIgnoreCase(args[0]))) {
				aena.getAirplaneById(args[1]);
			}

			if ((args.length == 4)
					&& ("configureAirplaneById".equalsIgnoreCase(args[0]))) {
				System.out.println("args[1]:" + args[1]);
				aena.configureAirplaneById(args[1], args[2], args[3]);
			}

			if ((args.length == 2)
					&& ("bookSeatAtAirplaneById".equalsIgnoreCase(args[0]))) {
				aena.bookSeatAtAirplaneById(args[1]);
			}

			if ((args.length == 2)
					&& ("confirmSeatAtAirplaneById".equalsIgnoreCase(args[0]))) {
				aena.confirmSeatAtAirplaneById(args[1]);
			}

			//
			// Airports
			//

			if ((args.length == 1)
					&& ("getAllAirports".equalsIgnoreCase(args[0]))) {
				aena.getAllAirports();
			}

			if ((args.length == 2)
					&& ("getAirportById".equalsIgnoreCase(args[0]))) {
				aena.getAirportById(args[1]);
			}

			if ((args.length == 2) && ("newAirport".equalsIgnoreCase(args[0]))) {
				aena.newAirport(args[1]);
			}
			
			if ((args.length == 2) && ("deleteAirport".equalsIgnoreCase(args[0]))) {
				aena.deleteAirport(args[1]);
			}

			if ((args.length == 2)
					&& ("addGateToAirport".equalsIgnoreCase(args[0]))) {
				aena.addGateToAirport(args[1]);
			}
			
			if ((args.length == 3)
					&& ("deleteGateFromAirport".equalsIgnoreCase(args[0]))) {
				aena.deleteGateFromAirport(args[1], args[2]);
			}

			if ((args.length == 3)
					&& ("changeGateStatusFromAirport".equalsIgnoreCase(args[0]))) {
				aena.changeGateStatusFromAirport(args[1], args[2]);
			}
			
			//
			// Consultas
			//
			
			// a Dado un aeropuerto, determinar el número de puertas de embarque disponibles. 
			if ((args.length == 3)
					&& ("gatesAvailableAtAirport".equalsIgnoreCase(args[0]))) {
				aena.gatesAvailableAtAirport(args[1], args[2]);
			}
			
			
		}
	}

	private void gatesAvailableAtAirport(String airportName, String metodo) {
		
		AirportDAO airportDAO = new AirportDAOImpl();
		String gatesStatus ="n/d";
		if ("HQL".equalsIgnoreCase(metodo)) {
			gatesStatus = airportDAO.gatesAvailableHQL(airportName);
		} else {
			gatesStatus = airportDAO.gatesAvailableCriteria(airportName);
		}
		System.out.println("gatesAvailableAtAirport" + " " + airportName + " " + metodo);
		System.out.println(gatesStatus);
	}

	private void confirmSeatAtAirplaneById(String plateNumber) {
		AirplaneDAO airplaneDAO = new AirplaneDAOImpl();
		Airplane airplane = airplaneDAO.getById(plateNumber);
		if (airplane != null) {
			;
			if (airplane.getTotalNumSeats() > airplane.getNumSeatsConfirmed()) {
				if (airplane.getBoardingpasses().size() == 0) {
					System.out.println("No tickets booked!");
					System.out.println(airplane.toString());
					return;
				}
				boolean boarded = false;
				for (Boardingpass ticket : airplane.getBoardingpasses()) {
					if (!ticket.getBoarded()) {
						boarded = true;
						ticket.setBoarded(boarded);
						airplane.setNumSeatsConfirmed(airplane
								.getNumSeatsConfirmed() + 1);
						break;
					}
				}
				if (boarded) {
					airplaneDAO.update(airplane);
					System.out.println("Passenger boarded!");
				} else {
					System.out.println("No ticket available to confirm!");
					System.out.println(airplane.toString());
				}
			} else {
				System.out.println("all seats boarded!");
				System.out.println(airplane.toString());
			}
		} else {
			System.out.println("Airplane with plateNumber '" + plateNumber
					+ "' not found");
		}
	}

	private void bookSeatAtAirplaneById(String plateNumber) {
		AirplaneDAO airplaneDAO = new AirplaneDAOImpl();
		Airplane airplane = airplaneDAO.getById(plateNumber);
		if (airplane != null) {
			if (airplane.getTotalNumSeatsToBook() > airplane
					.getNumSeatsBooked()) {
				Boardingpass ticket = new Boardingpass();
				ticket.setBoarded(false);
				ticket.setName("test name");
				ticket.setCode("test code");
				ticket.setIdAirplane(airplane.getIdAirplane());
				airplane.getBoardingpasses().add(ticket);
				airplane.setNumSeatsBooked(airplane.getNumSeatsBooked() + 1);
				airplaneDAO.update(airplane);
				System.out.println("Seat booked!");
			} else {
				System.out.println("All seats booked.");
				System.out.println(airplane.toString());
			}
		} else {
			System.out.println("Airplane with plateNumber '" + plateNumber
					+ "' not found");
		}
	}

	private void configureAirplaneById(String plateNumber,
			String totalNumSeats, String totalNumSeatsToBook) {

		AirplaneDAO airplaneDAO = new AirplaneDAOImpl();
		Airplane airplane = airplaneDAO.getById(plateNumber);
		if (airplane != null) {
			airplane.getBoardingpasses().clear();
			airplane.setNumSeatsBooked(0);
			airplane.setNumSeatsConfirmed(0);
			airplane.setTotalNumSeats(Integer.parseInt(totalNumSeats));
			airplane.setTotalNumSeatsToBook(Integer
					.parseInt(totalNumSeatsToBook));
			airplaneDAO.update(airplane);
			System.out.println("Airplane with plateNumber '" + plateNumber
					+ "' configured.");
			System.out.println(airplane.toString());
		} else {
			System.out.println("Airplane with plateNumber '" + plateNumber
					+ "' not found!");
		}
	}

	private void getAirplaneById(String plateNumber) {
		AirplaneDAO airplaneDAO = new AirplaneDAOImpl();
		Airplane airplane = airplaneDAO.getById(plateNumber);
		if (airplane != null) {
			System.out.println(airplane.toString());
		} else {
			System.out.println("Airplane with plateNumber '" + plateNumber
					+ "' not found!");
		}
	}

	private void deleteAirplaneById(String plateNumber) {
		AirplaneDAO airplaneDAO = new AirplaneDAOImpl();
		Airplane airplane = airplaneDAO.getById(plateNumber);
		if (airplane != null) {
			airplaneDAO.delete(airplane);
			System.out.println("Airplane with plateNumber '" + plateNumber
					+ "' deleted!");
		} else {
			System.out.println("Airplane with plateNumber '" + plateNumber
					+ "' not found!");
		}
	}

	public void newAirplane(String plateNumber) {
		AirplaneDAO airplaneDAO = new AirplaneDAOImpl();
		Airplane airplane = airplaneDAO.getById(plateNumber);
		if (airplane == null) {
			airplane = new Airplane();
			airplane.setPlateNumber(plateNumber);
			airplaneDAO.update(airplane);
			System.out.println("New airplane created.");
		} else {
			System.out.println("Airplane with plateNumber '" + plateNumber
					+ "' already exits!");
		}
	}

	private void changeGateStatusFromAirport(String airportName,
			String gateNumber) {
		AirportDAO airportDAO = new AirportDAOImpl();
		Airport airport = airportDAO.getById(airportName);
		if (airport != null) {
			boolean gateUpdated = false;
			for (Gate gate : airport.getGates()) {
				if (gate.getNumber() == Integer.parseInt(gateNumber)) {
					if ("libre".equalsIgnoreCase(gate.getStatus())) {
						gate.setStatus("ocupada");
					} else {
						gate.setStatus("libre");
					}
					gateUpdated = true;
					break;
				}
			}
			if (gateUpdated) {
				airportDAO.update(airport);
				System.out.println("Gate number'" + gateNumber + "' @ Airport with name '" + airportName  + "' status changed.");
				System.out.println(airport.toString());
			} else {
				System.out.println("Gate number'" + gateNumber + "' not found @ Airport with name '" + airportName  + "'");
			}
			airportDAO.update(airport);
		} else {
			System.out.println("Airport with name '" + airportName
					+ "' not found!");
		}
	}

	private void getAirportById(String name) {
		AirportDAO airportDAO = new AirportDAOImpl();
		Airport airport = airportDAO.getById(name);
		if (airport != null) {
			System.out.println(airport.toString());
		} else {
			System.out.println("Airport with name '" + name
					+ "' not found!");
		}
	}

	private void addGateToAirport(String name) {
		AirportDAO airportDAO = new AirportDAOImpl();
		Airport airport = airportDAO.getById(name);
		if (airport != null) {
			Gate newgate = new Gate();
			newgate.setIdAirport(airport.getIdAirport());
			newgate.setStatus("libre");
			newgate.setNumber(airport.getGates().size() + 1);
			if (airport.getGates().add(newgate)) {
				airportDAO.update(airport);
				System.out.println("Gate number'" + newgate.getNumber() + "' added to Airport with name '" + name  + "'");	
				System.out.println(airport.toString());
			} else {
				System.out.println("Gate number'" + newgate.getNumber() + "' not added to Airport with name '" + name  + "'");
			}
		} else {
			System.out.println("Airport with name '" + name
					+ "' not found!");			
		}
	}

	private void deleteGateFromAirport(String name, String gate) {
		AirportDAO airportDAO = new AirportDAOImpl();
		Airport airport = airportDAO.getById(name);
		if (airport != null) {
			boolean gateDeleted = false;
			for (Gate g : airport.getGates()) {
				if (g.getNumber() == Integer.parseInt(gate)) {
					if (airport.getGates().remove(g)) {
						gateDeleted = true;
						airportDAO.update(airport);
						break;	
					}
				}
			}
			if (gateDeleted) {
				System.out.println("Gate number'" + gate + "' @ Airport with name '" + name
						+ "' deleted!");		
			} else {
				System.out.println("Gate number'" + gate + "' @ Airport with name '" + name
						+ "' not found!");
			}
			
		} else {
			System.out.println("Airport with name '" + name
					+ "' not found!");			
		}
	}
	
	private void newAirport(String name) {
		AirportDAO airportDAO = new AirportDAOImpl();
		Airport airport = airportDAO.getById(name);
		if (airport == null) {
			airport = new Airport();
			airport.setName(name);
			airportDAO.update(airport);
			System.out.println("New airport created.");
			System.out.println(airport.toString());
		} else {
			System.out.println("Airport with name '" + name
					+ "' already exits!");
		}
	}

	private void deleteAirport(String name) {
		AirportDAO airportDAO = new AirportDAOImpl();
		Airport airport = airportDAO.getById(name);
		if (airport != null) {
			airportDAO.delete(airport);
			System.out.println("Airport '" + name +"' deleted.");
		} else {
			System.out.println("Airport with name '" + name
					+ "' does not exits!");
		}
	}
	
	private void getAllAirplanes() {
		System.out.println("** getAllAirplanes");

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Airplane.class);

		List<?> listAirplanes = (List<?>) criteria.list();
		System.out.println("\tairplanes #" + listAirplanes.size());

		for (int cont = 0; cont < listAirplanes.size(); cont++) {
			System.out.println("\t(" + cont + ") "
					+ listAirplanes.get(cont).toString());
		}
		session.getTransaction().commit();
	}

	private void getAllAirports() {

		AirportDAO airportDAO = new AirportDAOImpl();
		List<Airport> airports = airportDAO.getAll();

		System.out.println("\tNum airports # " + airports.size());
		System.out.println("");
		for (Airport airport : airports) {
			System.out.println("\t" + airport.toString());
			System.out.println("");
		}
	}
}
