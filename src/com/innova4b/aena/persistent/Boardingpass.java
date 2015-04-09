package com.innova4b.aena.persistent;

import java.io.Serializable;

public class Boardingpass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idBoardingpass;
	private Long idAirplane;
	private String code;
	private String name;
	private boolean boarded;
			
	public Boardingpass(){}

	public Long getIdBoardingpass() {return idBoardingpass;}
	protected void setIdBoardingpass(Long idBoardingpass) {this.idBoardingpass = idBoardingpass;}
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
		return "BoardingPass [idBoardingpass=" + idBoardingpass + ", idAirplane=" + idAirplane
				+ ", code=" + code + ", name=" + name + ", boarded=" + boarded
				+ "]";
	}
	
}
