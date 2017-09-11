package com.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.model.korisnici.Kuvar;

@Entity
@Table(name = "jelouporudzbini")
public class JeloUPorudzbini {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional = false)
	private Jelo jelo;

	@ManyToOne(optional = true)
	private Kuvar kuvar;	
	
	@ManyToOne(optional = false)
	private Porudzbina porudzbina;
	
	@Column(name = "kolicina")
	private int kolicina;

	@Column(name = "spremno")
	private boolean spremno;
	
	public int getKolicina() {
		return kolicina;
	}
	
	public Jelo getJelo() {
		return jelo;
	}
	
	public Porudzbina getPorudzbina() {
		return porudzbina;
	}
	
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}
	
	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
	
	public Kuvar getKuvar() {
		return kuvar;
	}
	public void setKuvar(Kuvar kuvar) {
		this.kuvar = kuvar;
	}
	public boolean isSpremno() {
		return spremno;
	}
	public void setSpremno(boolean spremno) {
		this.spremno = spremno;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}



}
