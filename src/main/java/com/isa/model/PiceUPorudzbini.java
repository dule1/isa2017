package com.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "piceuporudzbini")
public class PiceUPorudzbini {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional = false)
	private Pice pice;
	
	@ManyToOne(optional = false)
	private Porudzbina porudzbina;

		
	@Column(name = "kolicina")
	private int kolicina;
	
	
	public int getKolicina() {
		return kolicina;
	}
	
	public Pice getPice() {
		return pice;
	}

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}
	
	public void setPice(Pice pice) {
		this.pice = pice;
	}
	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}
