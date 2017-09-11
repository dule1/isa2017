package com.isa.model;

import java.util.Date;
import java.util.List;

public class IzvestajJelo {

	private Date datumod;
	private Date datumdo;
	private double ocena;
	private Jelo jelo;
	private List<IzvestajKuvarJelo> izvestajKuvarJelo;
	
	public IzvestajJelo() {

	}

	public Date getDatumod() {
		return datumod;
	}

	public void setDatumod(Date datumod) {
		this.datumod = datumod;
	}

	public Date getDatumdo() {
		return datumdo;
	}

	public void setDatumdo(Date datumdo) {
		this.datumdo = datumdo;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double d) {
		this.ocena = d;
	}

	public Jelo getJelo() {
		return jelo;
	}

	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}

	public List<IzvestajKuvarJelo> getIzvestajKuvarJelo() {
		return izvestajKuvarJelo;
	}

	public void setIzvestajKuvarJelo(List<IzvestajKuvarJelo> izvestajKuvarJelo) {
		this.izvestajKuvarJelo = izvestajKuvarJelo;
	}

}
