package com.isa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "smena")
public class Smena implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "vremeod")
	private String vremeod;

	@Column(name = "vremedo")
	private String vremedo;

	@ManyToOne(optional = false)
	private Restoran restoran;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "smena")
	private Set<SmenaUDanu> smenaudanu;

	public Smena() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVremeod() {
		return vremeod;
	}

	public void setVremeod(String vremeod) {
		this.vremeod = vremeod;
	}

	public String getVremedo() {
		return vremedo;
	}

	public void setVremedo(String vremedo) {
		this.vremedo = vremedo;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}
}
