package com.isa.model.korisnici;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.isa.model.JeloUPorudzbini;
import com.isa.model.Restoran;
import com.isa.model.SmenaUDanu;

@Entity
@DiscriminatorValue("KUV")
public class Kuvar extends Korisnik {

	@Column(name = "konfbroj", columnDefinition = "int default 0")
	private int konfbroj;

	@Column(name = "velobuce", columnDefinition = "int default 0")
	private int velobuce;

	@Column(name = "datumrodj", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date datumrodj;

	@ManyToOne(optional = true)
	private Restoran restoran;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipkuvara")
	private TipKuvara tipKuvara;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "kuvar")
	private Set<JeloUPorudzbini> jelovnik;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "kuvar")
	private Set<SmenaUDanu> smenaudanu;

	public Kuvar() {

	}

	public Restoran getRestoran() {
		return restoran;
	}

	public TipKuvara getTipKuvara() {
		return tipKuvara;
	}

	public void setTipKuvara(TipKuvara tipKuvara) {
		this.tipKuvara = tipKuvara;
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
