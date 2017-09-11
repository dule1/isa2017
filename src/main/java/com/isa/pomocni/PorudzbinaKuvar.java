package com.isa.pomocni;

import com.isa.model.Porudzbina;
import com.isa.model.korisnici.Kuvar;

public class PorudzbinaKuvar {
	
	private Porudzbina porudzbina;
	private Kuvar kuvar;
	
	public PorudzbinaKuvar() {
		// TODO Auto-generated constructor stub
	}
	
	public PorudzbinaKuvar(Porudzbina porudzbina, Kuvar kuvar) {
		this.kuvar = kuvar;
		this.porudzbina = porudzbina;
	}	
	
	public Porudzbina getPorudzbina() {
		return porudzbina;
	}
	public Kuvar getKuvar() {
		return kuvar;
	}
	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
	public void setKuvar(Kuvar kuvar) {
		this.kuvar = kuvar;
	}
	

}
