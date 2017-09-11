package com.isa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.model.korisnici.TipKuvara;

@Entity
@Table(name = "jelo")
public class Jelo implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "naziv")
	private String naziv;

	@Column(name = "opis")
	private String opis;

	@Column(name = "cena")
	private Float cena;

	@ManyToOne(optional = false)
	private Restoran restoran;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jelo")
	private Set<JeloUPorudzbini> jelovnik;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipkuvara")
	private TipKuvara tipKuvara;

	@JsonIgnore
	@ManyToMany(mappedBy = "jelo", cascade = CascadeType.ALL)
	private Set<PosetaRestoranu> poseterestoranu;

	public Jelo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Float getCena() {
		return cena;
	}

	public void setCena(Float cena) {
		this.cena = cena;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public TipKuvara getTipKuvara() {
		return tipKuvara;
	}

	public void setTipKuvara(TipKuvara tipKuvara) {
		this.tipKuvara = tipKuvara;
	}

	public Set<PosetaRestoranu> getPoseterestoranu() {
		return poseterestoranu;
	}

	public void setPoseterestoranu(Set<PosetaRestoranu> poseterestoranu) {
		this.poseterestoranu = poseterestoranu;
	}

}
