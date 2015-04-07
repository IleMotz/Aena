
package com.innova4b.aena.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {
	private static final SessionFactory sf = buildSessionFactory();
	private static SessionFactory buildSessionFactory () {
		try {
			Configuration cfg = new Configuration().configure();
			//Configuration cfg = new Configuration().configure().setInterceptor( new VehiculoInterceptor());
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			return cfg.buildSessionFactory(serviceRegistry);
		} catch (Throwable e) {
			System.out.println("**HibernateUtil Error**"+ e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory () {
		return sf;
	}
}
