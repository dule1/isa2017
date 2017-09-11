package com.isa.pomocni;

import com.isa.model.PosetaRestoranu;

public class OcenaPoseta {
	
	private int ocena;
	private PosetaRestoranu poseta;
	
	public OcenaPoseta() {
		// TODO Auto-generated constructor stub
	}

	public OcenaPoseta(int ocena, PosetaRestoranu poseta) {
		super();
		this.ocena = ocena;
		this.poseta = poseta;
	}
	
	public int getOcena() {
		return ocena;
	}
	public PosetaRestoranu getPoseta() {
		return poseta;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public void setPoseta(PosetaRestoranu poseta) {
		this.poseta = poseta;
	}

}
