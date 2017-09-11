package com.isa.pomocni;

import java.util.Date;

import com.isa.model.Restoran;

public class IzvestajKonobar {

	private String emailKonobara;
	private Restoran restoran;
	private Date odDatum;
	private Date doDatum;

	public IzvestajKonobar() {

	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Date getOdDatum() {
		return odDatum;
	}

	public void setOdDatum(Date odDatum) {
		this.odDatum = odDatum;
	}

	public Date getDoDatum() {
		return doDatum;
	}

	public void setDoDatum(Date doDatum) {
		this.doDatum = doDatum;
	}

	public String getEmailKonobara() {
		return emailKonobara;
	}

	public void setEmailKonobara(String emailKonobara) {
		this.emailKonobara = emailKonobara;
	}
	
}
