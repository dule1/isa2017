package com.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stavka_porudzbine_menadzera")
public class StavkaPorudzbineMenadzera implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "kolicina")
	private int kolicina;

	@ManyToOne(optional = false)
	private PorudzbinaMenadzer porudzbinamenadzer;

	@ManyToOne(optional = true)
	private Pice pice;

	@ManyToOne(optional = true)
	private Namirnica namirnica;

	public StavkaPorudzbineMenadzera() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public PorudzbinaMenadzer getPorudzbinamenadzer() {
		return porudzbinamenadzer;
	}

	public void setPorudzbinamenadzer(PorudzbinaMenadzer porudzbinamenadzer) {
		this.porudzbinamenadzer = porudzbinamenadzer;
	}

	public Pice getPice() {
		return pice;
	}

	public void setPice(Pice pice) {
		this.pice = pice;
	}

	public Namirnica getNamirnica() {
		return namirnica;
	}

	public void setNamirnica(Namirnica namirnica) {
		this.namirnica = namirnica;
	}

}
