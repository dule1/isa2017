package com.isa.pomocni;

import java.util.ArrayList;

import com.isa.model.Porudzbina;

public class MogucePrihvacene {

	private ArrayList<Porudzbina> mogucePorudzbine;
	private ArrayList<Porudzbina> prihvacenePorudzbine;

	public MogucePrihvacene() {
		// TODO Auto-generated constructor stub
	}
	
	

	public MogucePrihvacene(ArrayList<Porudzbina> mogucePorudzbine,
			ArrayList<Porudzbina> prihvacenePorudzbine) {
		super();
		this.mogucePorudzbine = mogucePorudzbine;
		this.prihvacenePorudzbine = prihvacenePorudzbine;
	}



	public ArrayList<Porudzbina> getMogucePorudzbine() {
		return mogucePorudzbine;
	}

	public ArrayList<Porudzbina> getPrihvacenePorudzbine() {
		return prihvacenePorudzbine;
	}

	public void setPrihvacenePorudzbine(ArrayList<Porudzbina> prihvacenePorudzbine) {
		this.prihvacenePorudzbine = prihvacenePorudzbine;
	}

	public void setMogucePorudzbine(ArrayList<Porudzbina> mogucePorudzbine) {
		this.mogucePorudzbine = mogucePorudzbine;
	}

}
