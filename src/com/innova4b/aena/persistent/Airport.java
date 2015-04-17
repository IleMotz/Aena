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
	
	public Airport(){}

	public Long getIdAirport() {return idAirport;}
	protected void setIdAirport(Long idAirport) {this.idAirport = idAirport;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public List<Gate> getGates() {return gates;}
	public void setGates(List<Gate> gates) {this.gates = gates;}
	
	private String getGatesStatus() {
		String gatesAvailable = "";
		String gatesNotAvailable = "";
		int numGatesAvailable = 0;
		int numGatesNotAvailable = 0;
		for (Gate gate : this.gates) {
			if ("libre".equalsIgnoreCase(gate.getStatus())) {
				numGatesAvailable ++;
				gatesAvailable += gate.getNumber() + " # ";
			} else {
				numGatesNotAvailable ++;
				gatesNotAvailable += gate.getNumber() + " # ";
			}
		}
		return "\r\n\t\t gatesAvailable= " + numGatesAvailable + " -> " + gatesAvailable + "\r\n" + 
				"\r\n\t\t gatesNotAvailable= " + numGatesNotAvailable + " -> " + gatesNotAvailable;
	}
	
	@Override
	public String toString() {return "Airport [idAirport=" + idAirport + ", name=" + name + ", numGates=" + gates.size() + ", getGatesStatus=" + getGatesStatus() + "]";}
		
}
