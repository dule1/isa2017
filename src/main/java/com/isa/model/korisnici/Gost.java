package com.isa.model.korisnici;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.isa.model.PosetaRestoranu;

@Entity
@DiscriminatorValue("G")
public class Gost extends Korisnik{


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gost")
	private Set<PosetaRestoranu> posete;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pozivalac")
	private Set<PosetaRestoranu> posetaRestorana;

	@Column(name = "canSend", nullable = true)
	private Boolean canSend;
	
	@Column(name = "canDecline", nullable = true)
	private Boolean canDecline;
	
	@Column(name = "canAccept", nullable = true)
	private Boolean canAccept;

	
	@Column(name = "isActivated", nullable = true)
	private Boolean isActivated;

	public Gost(){		
	}

	public Boolean isCanDecline() {
		return canDecline;
	}

	public void setCanDecline(Boolean canDecline) {
		this.canDecline = canDecline;
	}

	public Boolean isCanSend() {
		return canSend;
	}

	public void setCanSend(Boolean canSend) {
		this.canSend = canSend;
	}

	public Boolean getCanAccept() {
		return canAccept;
	}

	public void setCanAccept(Boolean canAccept) {
		this.canAccept = canAccept;
	}
	
	public Boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}
}
