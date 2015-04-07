package com.innova4b.aena.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Airport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idAirport;
	private String name;
	private List<Gate> gates = new ArrayList<Gate>();
	
	private int numGatesAvailable;
	private int numGatesNoAvailable;

	private String gatesAvailable;
	private String gatesNoAvailable;
	
	public Airport(){}

	public Long getIdAirport() {return idAirport;}
	public void setIdAirport(Long idAirport) {this.idAirport = idAirport;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public List<Gate> getGates() {return gates;}
	public void setGates(List<Gate> gates) {this.gates = gates;}
	
	public void addGate(Gate gate) {
		gates.add(gate);
	}
	
	public int getNumGatesAvailable() {
		numGatesAvailable = 0;
		gatesAvailable = "";

		Iterator<Gate> itgates = gates.iterator();
		Gate g;
		while (itgates.hasNext()) {
			g = itgates.next();
			if ("libre".equalsIgnoreCase(g.getStatus())) {
				numGatesAvailable ++;
				gatesAvailable += g.getNumber() + " / ";
			}											
		}
		if (gatesAvailable.length()> 3)
			gatesAvailable = gatesAvailable.substring(0, gatesAvailable.length() - 3);
		return numGatesAvailable;
	}

	public void setNumGatesAvailable(int numGatesAvailable) {
		this.numGatesAvailable = numGatesAvailable;
	}

	public int getNumGatesNoAvailable() {
		numGatesNoAvailable = 0;
		gatesNoAvailable = "";
		Iterator<Gate> itgates = gates.iterator();
		Gate g;
		while (itgates.hasNext()) {
			g = itgates.next();
			if (!"libre".equalsIgnoreCase(g.getStatus())) {
				numGatesNoAvailable ++;
				gatesNoAvailable += g.getNumber() + " / ";
			}											
		}		
		if (gatesNoAvailable.length()> 3)
			gatesNoAvailable = gatesNoAvailable.substring(0, gatesNoAvailable.length() - 3);
		return numGatesNoAvailable;
	}

	public String getGatesAvailable() {return gatesAvailable;}

	public void setGatesAvailable(String gatesAvailable) {this.gatesAvailable = gatesAvailable;}

	public String getGatesNoAvailable() {return gatesNoAvailable;}

	public void setGatesNoAvailable(String gatesNoAvailable) {this.gatesNoAvailable = gatesNoAvailable;}

	public void setNumGatesNoAvailable(int numGatesNoAvailable) {this.numGatesNoAvailable = numGatesNoAvailable;}
	
	@Override
	public String toString() {return "Airport [idAirport=" + idAirport + ", name=" + name + ", numGates=" + gates.size() + "]";}
		
}
