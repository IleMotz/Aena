package com.innova4b.aena.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Airport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idAirport;
	private String name;
	private Set<Gate> gates = new HashSet<Gate>();
	
	public Airport(){}

	public Long getIdAirport() {return idAirport;}
	public void setIdAirport(Long idAirport) {this.idAirport = idAirport;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public Set<Gate> getGates() {return gates;}
	public void setGates(Set<Gate> gates) {this.gates = gates;}

	@Override
	public String toString() {return "Airport [idAirport=" + idAirport + ", name=" + name + "]";}
}
