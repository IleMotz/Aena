package com.innova4b.aena.persistent;

import java.io.Serializable;

public class Gate implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idGate;
	protected int number;
	protected String status;
	protected Long idAirport;
	
	public Gate(){}

	public Long getIdGate() {return idGate;}
	public void setIdGate(Long idGate) {this.idGate = idGate;}
	public int getNumber() {return number;}
	public void setNumber(int number) {this.number = number;}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	public Long getIdAirport() {return idAirport;}
	public void setIdAirport(Long idAirport) {this.idAirport = idAirport;}
	@Override
	public String toString() {return "Gate [idGate=" + idGate + ", number=" + number + ", status=" + status + ", idAirport=" + idAirport + "]";}	
}
