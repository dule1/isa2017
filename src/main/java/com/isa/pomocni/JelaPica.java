package com.isa.pomocni;

import com.isa.model.Sto;
import com.isa.model.korisnici.Konobar;

public class JelaPica {

	private PomocniJelo[] svaJela;
	private PomocniPice[] svaPica;
	private Konobar konobar;
	private Sto sto;
	
	public JelaPica() {
		// TODO Auto-generated constructor stub
	}
	
	public JelaPica(PomocniJelo[] svaJela, PomocniPice[] svaPica, Konobar konobar, Sto sto) {
		super();
		this.svaJela = svaJela;
		this.svaPica = svaPica;
		this.konobar = konobar;
		this.sto = sto;
	}

	public PomocniJelo[] getSvaJela() {
		return svaJela;
	}
	
	public PomocniPice[] getSvaPica() {
		return svaPica;
	}
	
	public void setSvaJela(PomocniJelo[] svaJela) {
		this.svaJela = svaJela;
	}
	
	public void setSvaPica(PomocniPice[] svaPica) {
		this.svaPica = svaPica;
	}
	
	public Konobar getKonobar() {
		return konobar;
	}
	
	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}
	
	public Sto getSto() {
		return sto;
	}
	public void setSto(Sto sto) {
		this.sto = sto;
	}
	
}
