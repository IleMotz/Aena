package com.innova4b.aena.trans;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.innova4b.aena.persistent.Vehiculo;

public class VehiculoInterceptor extends EmptyInterceptor {


	private static final long serialVersionUID = 1L;
	
	public boolean onLoad(Object entity, Serializable id, 
						  Object[] state, 
						  String [] propertyNames, 
						  Type[] types) {
		
		System.out.println("Interceptando session.load() ... ");
		
		if (entity instanceof Vehiculo) {
			System.out.println("Vehiculo ... ");
			Vehiculo vehiculo = (Vehiculo) entity;
			
			System.out.println("getMatricula=" + vehiculo.getMatricula());
			System.out.println("getNumeroPlazas=" + vehiculo.getNumeroPlazas());
			System.out.println("getTipoVehiculo=" + vehiculo.getTipoVehiculo());
			System.out.println("getIdVehiculo=" + vehiculo.getIdVehiculo());
			
			System.out.println("id=" + id);
			System.out.println("state=" + state);
			
			for (int i = 0; i < state.length; i++) {
				System.out.println("state[" + i + "]:" + state[i] + 
						         ", propertyNames[" + i + propertyNames[i] + 
						         ", types[" + i + "]:" + types[i]);
			}
			return true;
		}
		
		return false;
	}

}

