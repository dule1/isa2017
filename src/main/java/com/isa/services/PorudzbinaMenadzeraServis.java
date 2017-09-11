package com.isa.services;

import java.util.List;

import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.StavkaPorudzbineMenadzera;

public interface PorudzbinaMenadzeraServis {
	
	List<PorudzbinaMenadzer> findAll();
	
	PorudzbinaMenadzer findOne(Long id);
	
	//PorudzbinaMenadzer save(PorudzbinaMenadzer ponudjac);
	
	void delete(Long id);

	List<StavkaPorudzbineMenadzera> izlistajStavke(PorudzbinaMenadzer porudzbinaMenadzer);
	
	//Page<Namirnica> izlistajNamirnice(PorudzbinaMenadzer ponudjac, Pageable pageable);
	
	//Page<Pice> izlistajPica(PorudzbinaMenadzer ponudjac, Pageable pageable);
	
}
