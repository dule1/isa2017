package com.isa.pomocni;

import java.util.Date;

import com.isa.model.Restoran;

public class PosecenostIzvestaj {

	private Restoran restoran;
	private Date datum;
	private String nivo;

	public PosecenostIzvestaj() {

	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getNivo() {
		return nivo;
	}

	public void setNivo(String nivo) {
		this.nivo = nivo;
	}

}
