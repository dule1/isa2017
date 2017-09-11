package com.isa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.model.korisnici.Ponudjac;
import com.isa.model.korisnici.Sanker;

@Entity
@Table(name = "restoran")
public class Restoran implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "naziv")
	private String naziv;

	@Column(name = "opis")
	private String opis;

	@Column(name = "brojredova", columnDefinition = "int default 0")
	private int brojredova;
	

	@Column(name = "brojkolona", columnDefinition = "int default 0")
	private int brojkolona;

	// Jelovnik - vise jela...
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<Jelo> jelovnik;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran", cascade = CascadeType.MERGE)
	private Set<Konobar> konobar;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<Kuvar> kuvar;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<Sanker> sanker;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<Porudzbina> porudzbina;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<PosetaRestoranu> posete;

	
	// Karta pica - vise pica

	// Konfiguracija sedenja
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<Sto> stolovi;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<MenadzerRestorana> menadzerrestorana;
	
	@ManyToMany(targetEntity = Ponudjac.class, cascade = {CascadeType.ALL})
	@JoinTable(name="restoran_ponudjac", joinColumns = {@JoinColumn(name = "restoran_id")}, inverseJoinColumns = {@JoinColumn(name = "ponudjac_id")})
	private Set<Ponudjac> ponudjac;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<Smena> smena;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<SmenaUDanu> smenaudanu;
	
	public Restoran() {

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

	public int getBrojredova() {
		return brojredova;
	}

	public void setBrojredova(int brojredova) {
		this.brojredova = brojredova;
	}

	public int getBrojkolona() {
		return brojkolona;
	}

	public void setBrojkolona(int brojkolona) {
		this.brojkolona = brojkolona;
	}

	public Set<Ponudjac> getPonudjac() {
		return ponudjac;
	}
	
	public void setPonudjac(Set<Ponudjac> ponudjac) {
		this.ponudjac = ponudjac;
	}
	
}
