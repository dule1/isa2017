package com.isa.pomocni;

import com.isa.model.korisnici.Gost;

public class GostPrijatelj {
	
	private Gost gost;
	private Gost prijatelj;
	private String paramPretrageIme;
	private String paramPretragePrz;
	
	public GostPrijatelj(){
	}
	
	public GostPrijatelj(Gost gost, Gost prijatelj){
		this.gost = gost;
		this.prijatelj = prijatelj;
	}
	
	public GostPrijatelj(Gost gost, Gost prijatelj, String paramPretrageIme, String paramPretragePrz){
		this.gost = gost;
		this.prijatelj = prijatelj;
		this.paramPretrageIme = paramPretrageIme;
		this.paramPretragePrz = paramPretragePrz;
	}
	
	public Gost getGost() {
		return gost;
	}

	public void setGost(Gost gost) {
		this.gost = gost;
	}

	public Gost getPrijatelj() {
		return prijatelj;
	}

	public void setPrijatelj(Gost prijatelj) {
		this.prijatelj = prijatelj;
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
