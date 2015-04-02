package com.innova4b.aena.persistent;

import java.io.Serializable;
import java.util.List;

public class Airport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idAirport;
	private String name;
	private List gates;
	
	public Airport(){}

	public Long getIdAirport() {return idAirport;}
	public void setIdAirport(Long idAirport) {this.idAirport = idAirport;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public List getGates() {return gates;}
	public void setGates(List gates) {this.gates = gates;}

	@Override
	public String toString() {return "Airport [idAirport=" + idAirport + ", name=" + name + "]";}
}
