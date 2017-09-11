package com.isa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.Restoran;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.model.korisnici.MenadzerSistema;
import com.isa.repository.MenadzerRestoranaSkladiste;
import com.isa.repository.MenadzerSistemaSkladiste;
import com.isa.repository.RestoranSkladiste;

@Service
public class MenadzerSistemaServisImpl implements MenadzerSistemaServis {

	@Autowired
	private RestoranSkladiste restoranSkladiste;

	@Autowired
	private MenadzerRestoranaSkladiste menadzerRestoranaSkladiste;
	
	@Autowired
	private MenadzerSistemaSkladiste menadzerSistemaSkladiste;
	
	
	@Override
	public Restoran saveRestoran(Restoran restoran) {
		return restoranSkladiste.save(restoran);
	}
	
	
	@Override
	public Page<Restoran> izlistajRestorane(Pageable pageable) {
		return restoranSkladiste.findAll(pageable);
	}

	@Override
	public MenadzerRestorana saveMenadzerRestorana(MenadzerRestorana menadzerRestorana) {
		System.out.println("registrovao je menadzera restorana " + menadzerRestorana.getIme());
		return menadzerRestoranaSkladiste.save(menadzerRestorana);
	}
	
	@Override
	public MenadzerSistema saveMenadzerSistema(MenadzerSistema menadzerSistema) {
		System.out.println("registrovao je menadzera sistema " + menadzerSistema.getIme());
		
		return menadzerSistemaSkladiste.save(menadzerSistema);
		
	}


	@Override
	public MenadzerSistema findOne(Long id) {
		return menadzerSistemaSkladiste.findOne(id);
	}


	@Override
	public Restoran pronadjiRestoran(Long id) {
		return restoranSkladiste.findOne(id);
	}

	
}
