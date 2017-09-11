package com.isa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.isa.model.korisnici.MenadzerRestorana;

@Entity
@Table(name = "porudzbina_menadzer")
public class PorudzbinaMenadzer implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "od", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date aktivnoOd;

	@Column(name = "do", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date aktivnoDo;

	@Column(name = "aktivna")
	private Boolean aktivna;

	@ManyToOne(optional = false)
	private MenadzerRestorana menadzerrestorana;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "porudzbinamenadzer")
	private Set<StavkaPorudzbineMenadzera> listastavki;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "porudzbinamenadzer")
	private Set<Ponuda> ponude;

	public PorudzbinaMenadzer() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAktivnoOd() {
		return aktivnoOd;
	}

	public void setAktivnoOd(Date aktivnoOd) {
		this.aktivnoOd = aktivnoOd;
	}

	public Date getAktivnoDo() {
		return aktivnoDo;
	}

	public void setAktivnoDo(Date aktivnoDo) {
		this.aktivnoDo = aktivnoDo;
	}

	public MenadzerRestorana getMenadzerrestorana() {
		return menadzerrestorana;
	}

	public void setMenadzerrestorana(MenadzerRestorana menadzerrestorana) {
		this.menadzerrestorana = menadzerrestorana;
	}

	public Boolean getAktivna() {
		return aktivna;
	}

	public void setAktivna(Boolean aktivna) {
		this.aktivna = aktivna;
	}

}
