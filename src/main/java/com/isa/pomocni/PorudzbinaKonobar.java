package com.isa.pomocni;

import com.isa.model.Porudzbina;
import com.isa.model.korisnici.Konobar;

public class PorudzbinaKonobar {

	private Porudzbina porudzbina;
	private Konobar konobar;
	
	public PorudzbinaKonobar() {
		// TODO Auto-generated constructor stub
	}
	
	public PorudzbinaKonobar(Porudzbina porudzbina, Konobar konobar) {
		this.konobar = konobar;
		this.porudzbina = porudzbina;
	}	
	
	public Porudzbina getPorudzbina() {
		return porudzbina;
	}
	public Konobar getKonobar() {
		return konobar;
	}
	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}
	
}
