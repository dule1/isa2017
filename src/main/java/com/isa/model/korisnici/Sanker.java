package com.isa.model.korisnici;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.SmenaUDanu;

@Entity
@DiscriminatorValue("SNK")
public class Sanker extends Korisnik {

	@Column(name = "konfbroj", columnDefinition = "int default 0")
	private int konfbroj;

	@Column(name = "velobuce", columnDefinition = "int default 0")
	private int velobuce;

	@Column(name = "datumrodj", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date datumrodj;

	@ManyToOne(optional = true)
	private Restoran restoran;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanker")
	private Set<Porudzbina> porudzbina;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanker")
	private Set<SmenaUDanu> smenaudanu;

	public Sanker() {

	}

	public Restoran getRestoran() {
		return restoran;
	}

	public int getKonfbroj() {
		return konfbroj;
	}

	public void setKonfbroj(int konfbroj) {
		this.konfbroj = konfbroj;
	}

	public int getVelobuce() {
		return velobuce;
	}

	public void setVelobuce(int velobuce) {
		this.velobuce = velobuce;
	}

	public Date getDatumrodj() {
		return datumrodj;
	}

	public void setDatumrodj(Date datumrodj) {
		this.datumrodj = datumrodj;
	}

}
