package com.innova4b.aena.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.innova4b.aena.persistent.Airport;
import com.innova4b.aena.persistent.Gate;

public interface AirportDAO {
	public List<Airport> getAll();
	public Airport getById(String airportName);
	public void update(Airport airport);
	public void delete(Airport airport);
	public String gatesAvailableHQL(String airportName);
	public String gatesAvailableCriteria(String airportName);
}
