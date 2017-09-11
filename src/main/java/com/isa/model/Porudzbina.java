package com.isa.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Sanker;

@Entity
@Table(name = "porudzbina")
public class Porudzbina {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "porudzbina")
	private Set<JeloUPorudzbini> jelovnik;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "porudzbina")
	private Set<PiceUPorudzbini> picovnik;

	@ManyToOne(optional = true, cascade = {CascadeType.ALL})
	private Restoran restoran;
	
	@JsonIgnore
	@ManyToOne(optional = true)
	private Konobar konobar;
	
	@JsonIgnore
	@ManyToOne(optional = true)
	private Konobar konobar1;

	@ManyToOne(optional = true, cascade = {CascadeType.ALL})
	private Sto sto;

	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	private Sanker sanker;

	@JsonIgnore
	@OneToOne(optional = true, cascade = CascadeType.ALL)
	private RacunKonobar racun;

	@Column(name = "spremna_jela")
	private boolean spremnaJela;

	@Column(name = "spremno_bar_jedno_jelo")
	private boolean spremnoJednoJelo;

	@Column(name = "spremna_pica")
	private boolean spremnaPica;
	
	@Column(name = "porudzbina_prihvacena")
	private boolean porudzbinaPrihvacena;

	@Column(name = "vremeprimanja")
	private String vremePrimanja;

	@Column(name = "vremenaplate")
	private String vremeNaplate;

	@Column(name = "datumizrade", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date datumizrade;
	
	public Porudzbina() {

	}

	public String getVremeNaplate() {
		return vremeNaplate;
	}

	public String getVremePrimanja() {
		return vremePrimanja;
	}

	public void setVremeNaplate(String vremeNaplate) {
		this.vremeNaplate = vremeNaplate;
	}

	public void setVremePrimanja(String vremePrimanja) {
		this.vremePrimanja = vremePrimanja;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Long getId() {
		return id;
	}

	public Sanker getSanker() {
		return sanker;
	}

	public void setSanker(Sanker sanker) {
		this.sanker = sanker;
	}

	public boolean isSpremnaJela() {
		return spremnaJela;
	}

	public boolean isSpremnaPica() {
		return spremnaPica;
	}

	public void setSpremnaJela(boolean spremnaJela) {
		this.spremnaJela = spremnaJela;
	}

	public void setSpremnaPica(boolean spremnaPica) {
		this.spremnaPica = spremnaPica;
	}

	public Sto getSto() {
		return sto;
	}

	public void setSto(Sto sto) {
		this.sto = sto;
	}

	public Konobar getKonobar() {
		return konobar;
	}

	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}

	public void setSpremnoJednoJelo(boolean spremnoJednoJelo) {
		this.spremnoJednoJelo = spremnoJednoJelo;
	}

	public boolean isSpremnoJednoJelo() {
		return spremnoJednoJelo;
	}

	public RacunKonobar getRacun() {
		return racun;
	}

	public void setRacun(RacunKonobar racun) {
		this.racun = racun;
	}
	
	public Konobar getKonobar1() {
		return konobar1;
	}
	public void setKonobar1(Konobar konobar1) {
		this.konobar1 = konobar1;
	}
	
	public boolean isPorudzbinaPrihvacena() {
		return porudzbinaPrihvacena;
	}
	public void setPorudzbinaPrihvacena(boolean porudzbinaPrihvacena) {
		this.porudzbinaPrihvacena = porudzbinaPrihvacena;
	}
	
	public Date getDatumizrade() {
		return datumizrade;
	}

	public void setDatumizrade(Date datumizrade) {
		this.datumizrade = datumizrade;
	}
}
