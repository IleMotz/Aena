package com.innova4b.aena.persistent;

import java.io.Serializable;

public class Gate implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idGate;
	private Long idAirport;
	private int number;
	private String status;
	private Airport airport;
			
	public Gate(){}

	public Long getIdGate() {return idGate;}
	public void setIdGate(Long idGate) {this.idGate = idGate;}
	public Long getIdAirport() {return idAirport;}
	public void setIdAirport(Long idAirport) {this.idAirport = idAirport;}

	public int getNumber() {return number;}
	public void setNumber(int number) {this.number = number;}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	public Airport getAirport() {return airport;}
	public void setAirport(Airport airport) {this.airport = airport;}

	@Override
	public String toString() {
		return "Gate [idAirport=" + idAirport + ", idGate=" + idGate + ", number=" + number + ", status=" + status + "]";
	}


}
