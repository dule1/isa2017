package com.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.model.korisnici.Konobar;

@Entity
@Table(name = "racun")
public class RacunKonobar implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@JsonIgnore
	@OneToOne(optional = true)
	private Porudzbina porudzbina;

	@ManyToOne(optional = true)
	private Konobar konobar;

	@Column(name = "ukupno")
	private Float ukupno;

	public RacunKonobar() {

	}

	public Long getId() {
		return id;
	}

	public Konobar getKonobar() {
		return konobar;
	}

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}

	public Float getUkupno() {
		return ukupno;
	}

	public void setUkupno(Float ukupno) {
		this.ukupno = ukupno;
	}

}
