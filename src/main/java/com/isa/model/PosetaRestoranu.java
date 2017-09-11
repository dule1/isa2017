package com.isa.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.isa.model.korisnici.Gost;

@Entity
@Table(name = "poseta_restoranu")
public class PosetaRestoranu {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = true)
	private Gost gost;

	@ManyToOne(optional = true)
	private Gost pozivalac;

	@ManyToOne(optional = true)
	private Restoran restoran;

	@ManyToOne(optional = true)
	private Sto sto;

	@Column(name = "obavljena")
	private boolean obavljena;

	@Column(name = "ocena")
	private int ocena;

	@Column(name = "ocena_usluge")
	private int ocenaUsluge;

	@Column(name = "ocena_obroka")
	private int ocenaObroka;

	@Column(name = "termin")
	private String termin;

	@Column(name = "datumrez", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date datumrez;

	@Column(name = "br_sati")
	private int brSati;

	@Column(name = "isAccepted")
	private Boolean isAccepted;

	@ManyToMany(targetEntity = Jelo.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "poseterestoranu_jelo", joinColumns = {
			@JoinColumn(name = "poseta_restoranu_id") }, inverseJoinColumns = { @JoinColumn(name = "jelo_id") })
	private Set<Jelo> jelo;

	private Boolean accepted;
	
	public PosetaRestoranu() {

	}

	public Gost getGost() {
		return gost;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setGost(Gost gost) {
		this.gost = gost;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public boolean isObavljena() {
		return obavljena;
	}

	public void setObavljena(boolean obavljena) {
		this.obavljena = obavljena;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public String getTermin() {
		return termin;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}

	public void setBrSati(int brSati) {
		this.brSati = brSati;
	}

	public int getBrSati() {
		return brSati;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Gost getPozivalac() {
		return pozivalac;
	}

	public void setPozivalac(Gost pozivalac) {
		this.pozivalac = pozivalac;
	}

	public Sto getSto() {
		return sto;
	}

	public void setSto(Sto sto) {
		this.sto = sto;
	}

	public Date getDatumrez() {
		return datumrez;
	}

	public void setDatumrez(Date datumrez) {
		this.datumrez = datumrez;
	}

	public int getOcenaObroka() {
		return ocenaObroka;
	}

	public int getOcenaUsluge() {
		return ocenaUsluge;
	}

	public void setOcenaObroka(int ocenaObroka) {
		this.ocenaObroka = ocenaObroka;
	}

	public void setOcenaUsluge(int ocenaUsluge) {
		this.ocenaUsluge = ocenaUsluge;
	}

	public Set<Jelo> getJelo() {
		return jelo;
	}

	public void setJelo(Set<Jelo> jelo) {
		this.jelo = jelo;
	}
	
	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

}
