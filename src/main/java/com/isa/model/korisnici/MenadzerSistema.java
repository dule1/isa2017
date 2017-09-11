package com.isa.model.korisnici;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MENSIS")
public class MenadzerSistema extends Korisnik{

	@Column(name = "Glavni", nullable=true)
	private boolean glavni;
	
	public MenadzerSistema() {

	}

	public boolean isGlavni() {
		return glavni;
	}

	public void setGlavni(boolean glavni) {
		this.glavni = glavni;
	}
}
