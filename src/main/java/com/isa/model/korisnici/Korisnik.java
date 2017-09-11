package com.isa.model.korisnici;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "korisnik")
@Component
@Scope("session")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ctype", discriminatorType=DiscriminatorType.STRING)
public class Korisnik{

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "ime")
	private String ime;
	
	@Column(name = "prezime")
	private String prezime;
	
	@Column(name = "sifra")
	private String sifra;
	
	private String sifraStara;
	
	@Column(name = "email")//, unique = true)
	private String email;
	
	@Column(name = "logovao_se", columnDefinition = "boolean default false" )
	private Boolean logovaoSe;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tip")
	private TipKorisnika tipKorisnika;
	
	public Korisnik() {
		
	}

	public Korisnik(Long id, String ime, String prezime, String sifra, String email, TipKorisnika tipKorisnika) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.sifra = sifra;
		this.email = email;
		this.tipKorisnika = tipKorisnika;
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getSifra() {
		return sifra;
	}


	public void setSifra(String sifra) {
		this.sifra = sifra;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}


	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}
	
	public String getSifraStara() {
		return sifraStara;
	}
	public void setSifraStara(String sifraStara) {
		this.sifraStara = sifraStara;
	}
	
	public Boolean isLogovaoSe() {
		return logovaoSe;
	}
	
	public void setLogovaoSe(Boolean logovaoSe) {
		this.logovaoSe = logovaoSe;
	}
	
	
	
}
