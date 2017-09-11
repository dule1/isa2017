package com.isa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.Sanker;

@Entity
@Table(name = "smenaudanu")
public class SmenaUDanu implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "dan")
	private DanUNedelji danUNedelji;

	@ManyToOne(optional = true)
	private Restoran restoran;

	@ManyToOne(optional = true)
	private Smena smena;

	@ManyToOne(optional = true)
	private Kuvar kuvar;

	@ManyToOne(optional = true)
	private Sanker sanker;

	@ManyToOne(optional = true)
	private Konobar konobar;

	@ManyToMany(targetEntity = Sto.class, cascade = CascadeType.ALL)
	@JoinTable(name="smenadan_sto", joinColumns = {@JoinColumn(name = "smenaudanu_id")}, inverseJoinColumns = {@JoinColumn(name = "sto_id")})
	private Set<Sto> sto;

	public SmenaUDanu() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DanUNedelji getDanUNedelji() {
		return danUNedelji;
	}

	public void setDanUNedelji(DanUNedelji danUNedelji) {
		this.danUNedelji = danUNedelji;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Smena getSmena() {
		return smena;
	}

	public void setSmena(Smena smena) {
		this.smena = smena;
	}

	public Kuvar getKuvar() {
		return kuvar;
	}

	public void setKuvar(Kuvar kuvar) {
		this.kuvar = kuvar;
	}

	public Sanker getSanker() {
		return sanker;
	}

	public void setSanker(Sanker sanker) {
		this.sanker = sanker;
	}

	public Konobar getKonobar() {
		return konobar;
	}

	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}

	public Set<Sto> getSto() {
		return sto;
	}

	public void setSto(Set<Sto> sto) {
		this.sto = sto;
	}

}
