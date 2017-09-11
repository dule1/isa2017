package com.isa.model.korisnici;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.Restoran;

@Entity
@DiscriminatorValue("MENRES")
public class MenadzerRestorana extends Korisnik{

	@ManyToOne(optional = true)
	private Restoran restoran;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menadzerrestorana")
	private Set<PorudzbinaMenadzer> porudzbinamenadzer;
	
	public MenadzerRestorana(){
		
	}
	
	public Restoran getRestoran() {
		return restoran;
	}
	
	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}
}
