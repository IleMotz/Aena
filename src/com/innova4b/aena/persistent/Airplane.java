
package com.innova4b.aena.persistent;

import java.io.Serializable;

public class Airplane implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idAirplane;
	protected String plateNumber;
	
	public Airplane(){}

	public Long getIdAirplane() {return idAirplane;}
	public void setIdAirplane(Long idAirplane) {this.idAirplane = idAirplane;}
	public String getPlateNumber() {return plateNumber;}
	public void setPlateNumber(String plateNumber) {this.plateNumber = plateNumber;}
	@Override
	public String toString() {return "Airplane [idAirplane=" + idAirplane + ", plateNumber=" + plateNumber + "]";}
	
}
