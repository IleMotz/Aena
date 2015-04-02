package com.innova4b.aena.persistent;

import java.io.Serializable;

public class Airport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idAirport;
	protected String name;
	
	public Airport(){}

	public Long getIdAirport() {return idAirport;}
	public void setIdAirport(Long idAirport) {this.idAirport = idAirport;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	@Override
	public String toString() {return "Airport [idAirport=" + idAirport + ", name=" + name + "]";}
}
