package com.innova4b.aena.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Airplane implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idAirplane;
	private String plateNumber;

	private int numSeatsBooked;
	private int numSeatsConfirmed;
	private int totalNumSeatsToBook;
	private int totalNumSeats;

	private List<Boardingpass> Boardingpasses = new ArrayList<Boardingpass>();

	public Airplane() {
	}

	public Long getIdAirplane() {
		return idAirplane;
	}

	protected void setIdAirplane(Long idAirplane) {
		this.idAirplane = idAirplane;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public int getNumSeatsBooked() {
		return numSeatsBooked;
	}

	public void setNumSeatsBooked(int numSeatsBooked) {
		this.numSeatsBooked = numSeatsBooked;
	}

	public int getNumSeatsConfirmed() {
		return numSeatsConfirmed;
	}

	public void setNumSeatsConfirmed(int numSeatsConfirmed) {
		this.numSeatsConfirmed = numSeatsConfirmed;
	}

	public int getTotalNumSeatsToBook() {
		return totalNumSeatsToBook;
	}

	public void setTotalNumSeatsToBook(int totalNumSeatsToBook) {
		this.totalNumSeatsToBook = totalNumSeatsToBook;
	}

	public int getTotalNumSeats() {
		return totalNumSeats;
	}

	public void setTotalNumSeats(int totalNumSeats) {
		this.totalNumSeats = totalNumSeats;
	}

	public List<Boardingpass> getBoardingpasses() {
		return Boardingpasses;
	}

	public void setBoardingpasses(List<Boardingpass> Boardingpasses) {
		this.Boardingpasses = Boardingpasses;
	}

	@Override
	public String toString() {
		return "Airplane [idAirplane=" + idAirplane + ", plateNumber="
				+ plateNumber + ", \r\nnumSeatsBooked=" + numSeatsBooked
				+ ", \r\nnumSeatsConfirmed=" + numSeatsConfirmed
				+ ", \r\ntotalNumSeatsToBook=" + totalNumSeatsToBook
				+ ", \r\ntotalNumSeats=" + totalNumSeats + ", \r\nnumBoardingpass="
				+ Boardingpasses.size() + "]";
	}

}
