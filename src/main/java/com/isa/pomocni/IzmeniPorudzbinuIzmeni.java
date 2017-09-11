package com.isa.pomocni;

import org.hamcrest.core.Is;

import com.isa.model.Porudzbina;

public class IzmeniPorudzbinuIzmeni {

	private boolean smeDaBriseJela;
	private boolean smeDaBrisePica;
	private boolean smeDaDodaJela;
	private boolean smeDaDodaPica;

	private PomocniJelo[] svaJela;
	private PomocniPice[] svaPica;

	private Porudzbina porudzbina;

	public IzmeniPorudzbinuIzmeni() {
		// TODO Auto-generated constructor stub
	}

	public IzmeniPorudzbinuIzmeni(boolean smeDaBriseJela,
			boolean smeDaBrisePica, boolean smeDaDodaJela,
			boolean smeDaDodaPica, PomocniJelo[] svaJela,
			PomocniPice[] svaPica, Porudzbina porudzbina) {
		super();
		this.smeDaBriseJela = smeDaBriseJela;
		this.smeDaBrisePica = smeDaBrisePica;
		this.smeDaDodaJela = smeDaDodaJela;
		this.smeDaDodaPica = smeDaDodaPica;
		this.svaJela = svaJela;
		this.svaPica = svaPica;
		this.porudzbina = porudzbina;
	}

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public PomocniJelo[] getSvaJela() {
		return svaJela;
	}

	public PomocniPice[] getSvaPica() {
		return svaPica;
	}

	public boolean isSmeDaBriseJela() {
		return smeDaBriseJela;
	}

	public boolean isSmeDaBrisePica() {
		return smeDaBrisePica;
	}

	public boolean isSmeDaDodaJela() {
		return smeDaDodaJela;
	}

	public boolean isSmeDaDodaPica() {
		return smeDaDodaPica;
	}

	public void setSmeDaBriseJela(boolean smeDaBriseJela) {
		this.smeDaBriseJela = smeDaBriseJela;
	}

	public void setSmeDaBrisePica(boolean smeDaBrisePica) {
		this.smeDaBrisePica = smeDaBrisePica;
	}

	public void setSmeDaDodaJela(boolean smeDaDodaJela) {
		this.smeDaDodaJela = smeDaDodaJela;
	}

	public void setSmeDaDodaPica(boolean smeDaDodaPica) {
		this.smeDaDodaPica = smeDaDodaPica;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}

	public void setSvaPica(PomocniPice[] svaPica) {
		this.svaPica = svaPica;
	}

	public void setSvaJela(PomocniJelo[] svaJela) {
		this.svaJela = svaJela;
	}
}
