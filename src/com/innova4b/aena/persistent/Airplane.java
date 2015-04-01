
package com.innova4b.aena.persistent;

import java.io.Serializable;

public class Airplane implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idAirplane;
	
	protected String matricula;
	
	public Airplane() {	}

	public Long getIdAirplane() {return idAirplane;}
	public void setIdAirplane(Long idAirplane) {this.idAirplane = idAirplane;}
	public String getMatricula() {return matricula;}
	public void setMatricula(String matricula) {this.matricula = matricula;}

	@Override
	public String toString() {
		return "Airplane [idAirplane=" + idAirplane + ", matricula="
				+ matricula + "]";
	}
	
}
