package com.innova4b.aena.persistent;

public class Coche extends Vehiculo {

	public Coche() {
		super();
	}

	@Override
	public String toString() {
		return "Coche [tipoVehiculo=" + tipoVehiculo + ", matricula="
				+ matricula + ", numeroPlazas=" + numeroPlazas + "]";
	}
	
}
