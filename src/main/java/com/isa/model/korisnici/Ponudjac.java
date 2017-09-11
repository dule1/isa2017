package com.isa.model.korisnici;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.model.Ponuda;
import com.isa.model.Restoran;

@Entity
@DiscriminatorValue("PN")
public class Ponudjac extends Korisnik {

	@JsonIgnore
	@ManyToMany(mappedBy = "ponudjac", cascade = CascadeType.ALL)
	private Set<Restoran> restoran;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ponudjac")
	private Set<Ponuda> ponuda;

	public Ponudjac() {

	}

	public Set<Restoran> getRestoran() {
		return restoran;
	}

	public void setRestoran(Set<Restoran> restoran) {
		this.restoran = restoran;
	}

}
