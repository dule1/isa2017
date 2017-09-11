package com.isa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isa.model.Ponuda;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.korisnici.Ponudjac;

public interface PonudjacServis {

	List<Ponudjac> findAll();
	
	Ponudjac findOne(Long id);
	
	Ponudjac save(Ponudjac ponudjac);
	
	void delete(Long id);
	
	Page<Ponuda> izlistajPonude(Ponudjac ponudjac, Pageable pageable);

	List<PorudzbinaMenadzer> izlistajPorudzbineBezPonude(Ponudjac ponudjac);

	void dodajPonudu(Ponuda ponuda);

	boolean izmenaPonude(Ponuda ponuda);

	List<Ponuda> izlistajPonudePonudjaca(Ponudjac ponudjac);
}
