package com.isa.model;

import com.isa.model.korisnici.Kuvar;

public class IzvestajKuvarJelo {

	private int prosecnaOcena;
	private Kuvar kuvar;

	public IzvestajKuvarJelo() {

	}
	
	public IzvestajKuvarJelo(int prosecnaOcena, Kuvar kuvar){
		this.prosecnaOcena = prosecnaOcena;
		this.kuvar = kuvar;
	}

	public int getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(int prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Kuvar getKuvar() {
		return kuvar;
	}

	public void setKuvar(Kuvar kuvar) {
		this.kuvar = kuvar;
	}
}