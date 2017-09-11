package com.isa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pice")
public class Pice implements Serializable{

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "opis")
	private String opis;
	
	@Column(name = "cena")
	private Float cena;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pice")
	private Set<PiceUPorudzbini> picovnik;
	
	@ManyToOne(optional = true)		// proveriti da li je nesto poremetiolo.. (false)
	private Restoran restoran;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pice")
	private Set<StavkaPorudzbineMenadzera> listastavki;
	
	public Pice() {

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
}
