package com.isa.pomocni;

import com.isa.model.Porudzbina;
import com.isa.model.korisnici.Sanker;

public class PorudzbinaSanker {

	private Porudzbina porudzbina;
	private Sanker sanker;
	
	public PorudzbinaSanker() {
		// TODO Auto-generated constructor stub
	}
	
	public PorudzbinaSanker(Porudzbina por, Sanker sa){
		super();
		porudzbina = por;
		sanker = sa;
	}
	public Porudzbina getPorudzbina() {
		return porudzbina;
	}
	public Sanker getSanker() {
		return sanker;
	}
	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
	public void setSanker(Sanker sanker) {
		this.sanker = sanker;
	}
	
}
