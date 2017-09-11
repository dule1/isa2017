package com.isa.pomocni;

import java.util.ArrayList;

import com.isa.model.JeloUPorudzbini;
import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;

public class IzmeniPorudzbinuPrikaz {

	private ArrayList<JeloUPorudzbini> dodataJela;
	private ArrayList<PiceUPorudzbini> dodataPica;
	private boolean smeDaBriseJela;
	private boolean smeDaBrisePica;
	private boolean smeDaDodaJela;
	private boolean smeDaDodaPica;

	public IzmeniPorudzbinuPrikaz() {
		// TODO Auto-generated constructor stub
	}

	public IzmeniPorudzbinuPrikaz(ArrayList<JeloUPorudzbini> dodataJela,
			ArrayList<PiceUPorudzbini> dodataPica, boolean smeDaBriseJela,
			boolean smeDaBrisePica, boolean smeDaDodaJela, boolean smeDaDodaPica) {
		super();
		this.dodataJela = dodataJela;
		this.dodataPica = dodataPica;
		this.smeDaBriseJela = smeDaBriseJela;
		this.smeDaBrisePica = smeDaBrisePica;
		this.smeDaDodaJela = smeDaDodaJela;
		this.smeDaDodaPica = smeDaDodaPica;
	}

	public ArrayList<JeloUPorudzbini> getDodataJela() {
		return dodataJela;
	}

	public ArrayList<PiceUPorudzbini> getDodataPica() {
		return dodataPica;
	}

	public boolean isSmeDaBriseJela() {
		return smeDaBriseJela;
	}

	public boolean isSmeDaBrisePica() {
		return smeDaBrisePica;
	}

	public void setSmeDaBriseJela(boolean smeDaBriseJela) {
		this.smeDaBriseJela = smeDaBriseJela;
	}

	public void setSmeDaBrisePica(boolean smeDaBrisePica) {
		this.smeDaBrisePica = smeDaBrisePica;
	}

	public void setDodataJela(ArrayList<JeloUPorudzbini> dodataJela) {
		this.dodataJela = dodataJela;
	}

	public void setDodataPica(ArrayList<PiceUPorudzbini> dodataPica) {
		this.dodataPica = dodataPica;
	}

	public boolean isSmeDaDodaJela() {
		return smeDaDodaJela;
	}

	public boolean isSmeDaDodaPica() {
		return smeDaDodaPica;
	}

	public void setSmeDaDodaJela(boolean smeDaDodaJela) {
		this.smeDaDodaJela = smeDaDodaJela;
	}

	public void setSmeDaDodaPica(boolean smeDaDodaPica) {
		this.smeDaDodaPica = smeDaDodaPica;
	}
	
}
