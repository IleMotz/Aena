package com.innova4b.aena.persistent;

import java.io.Serializable;

public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idTicket;
	private Long idAirplane;
	private String code;
	private String name;
	private boolean boarded;
			
	public Ticket(){}

	public Long getIdTicket() {return idTicket;}
	protected void setIdTicket(Long idTicket) {this.idTicket = idTicket;}
	public Long getIdAirplane() {return idAirplane;}
	public void setIdAirplane(Long idAirplane) {this.idAirplane = idAirplane;}
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public boolean getBoarded() {return boarded;}
	public void setBoarded(boolean boarded) {this.boarded = boarded;}

	@Override
	public String toString() {
		return "Ticket [idTicket=" + idTicket + ", idAirplane=" + idAirplane
				+ ", code=" + code + ", name=" + name + ", boarded=" + boarded
				+ "]";
	}
	
}
