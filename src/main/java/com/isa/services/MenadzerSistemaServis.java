package com.isa.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isa.model.Restoran;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.model.korisnici.MenadzerSistema;


public interface MenadzerSistemaServis {

	public Restoran saveRestoran(Restoran restoran);
	public Restoran pronadjiRestoran(Long id);
	public Page<Restoran> izlistajRestorane(Pageable pageable);
	public MenadzerRestorana saveMenadzerRestorana(MenadzerRestorana menadzerRestorana);
	public MenadzerSistema saveMenadzerSistema(MenadzerSistema menadzerSistema);
	public MenadzerSistema findOne(Long id);
	

	
}
