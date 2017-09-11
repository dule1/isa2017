package com.isa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sto")
public class Sto implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "oznaka")
	private int oznaka;
	
	@Column(name = "segment")
	private String segment;
	
	@Column(name = "zauzetost")
	private Boolean zauzet;
	
	@Column(name = "brojmesta")
	private int brojmesta;
	
	@ManyToOne(optional = false)
	private Restoran restoran;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sto")
	private Set<Porudzbina> porudzbine;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sto")
	private Set<PosetaRestoranu> posete;
	
	//@JsonIgnore
	@ManyToMany(mappedBy = "sto", cascade = CascadeType.ALL)
	private Set<SmenaUDanu> smenaudanu;
	
	public Sto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOznaka() {
		return oznaka;
	}

	public void setOznaka(int oznaka) {
		this.oznaka = oznaka;
	}
	
	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}
	
	public Boolean getZauzet() {
		return zauzet;
	}
	
	public void setZauzet(Boolean zauzet) {
		this.zauzet = zauzet;
	}
	
	public int getBrojmesta() {
		return brojmesta;
	}
	
	public void setBrojmesta(int brojmesta) {
		this.brojmesta = brojmesta;
	}
	
	public Restoran getRestoran() {
		return restoran;
	}
	
	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}
	


}
