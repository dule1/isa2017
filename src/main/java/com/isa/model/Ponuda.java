package com.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.model.korisnici.Ponudjac;

@Entity
@Table(name = "ponuda")
public class Ponuda implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = false)
	private Ponudjac ponudjac;

	@ManyToOne(optional = false)
	private PorudzbinaMenadzer porudzbinamenadzer;

	@Column(name = "cena")
	private float cena;

	@Column(name = "rokisporuke")
	private int rokisporuke;

	@Column(name = "garancija")
	private String garancija;

	public Ponuda() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ponudjac getPonudjac() {
		return ponudjac;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public int getRokisporuke() {
		return rokisporuke;
	}

	public void setRokisporuke(int rokisporuke) {
		this.rokisporuke = rokisporuke;
	}

	public String getGarancija() {
		return garancija;
	}

	public void setGarancija(String garancija) {
		this.garancija = garancija;
	}

	public PorudzbinaMenadzer getPorudzbinamenadzer() {
		return porudzbinamenadzer;
	}

	public void setPorudzbinamenadzer(PorudzbinaMenadzer porudzbinamenadzer) {
		this.porudzbinamenadzer = porudzbinamenadzer;
	}

	public void setPonudjac(Ponudjac ponudjac) {
		this.ponudjac = ponudjac;
	}

}
