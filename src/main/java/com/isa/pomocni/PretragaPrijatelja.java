package com.isa.pomocni;

import com.isa.model.korisnici.Gost;

public class PretragaPrijatelja {

	private Gost gost;
	private String paramPretrageIme;
	private String paramPretragePrz;
	
	public PretragaPrijatelja(){
	}
	
	public PretragaPrijatelja(Gost gost, String paramPretrageIme, String paramPretragePrz){
		this.gost = gost;
		this.paramPretrageIme = paramPretrageIme;
		this.paramPretragePrz = paramPretragePrz;
	}
	
	public Gost getGost() {
		return gost;
	}
	
	public void setGost(Gost gost) {
		this.gost = gost;
	}
	
	public String getParamPretrageIme() {
		return paramPretrageIme;
	}
	
	public void setParamPretrageIme(String paramPretrageIme) {
		this.paramPretrageIme = paramPretrageIme;
	}
	
	public String getParamPretragePrz() {
		return paramPretragePrz;
	}
	
	public void setParamPretragePrz(String paramPretragePrz) {
		this.paramPretragePrz = paramPretragePrz;
	}
	
}
