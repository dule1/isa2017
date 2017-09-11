package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.StavkaPorudzbineMenadzera;
import com.isa.repository.NamirnicaSkladiste;
import com.isa.repository.PiceSkladiste;
import com.isa.repository.PorudzbineMenadzeraSkladiste;
import com.isa.repository.StavkaPorudzbineMenSkladiste;

@Service
public class PorudzbinaMenadzeraServisImpl implements PorudzbinaMenadzeraServis{

	@Autowired
	PorudzbineMenadzeraSkladiste porMenSkladiste;

	@Autowired
	NamirnicaSkladiste namirnicaSkladiste;
	
	@Autowired
	PiceSkladiste piceSkladiste;
	
	@Autowired
	StavkaPorudzbineMenSkladiste stavkaSkladiste;
	
	@Override
	public List<PorudzbinaMenadzer> findAll() {
		return porMenSkladiste.findAll();
	}

	@Override
	public PorudzbinaMenadzer findOne(Long id) {
		return porMenSkladiste.findOne(id);
	}
/*
	@Override
	public PorudzbinaMenadzer save(PorudzbinaMenadzer ponudjac) {
		return porMenSkladiste.save(ponudjac);
	}
*/
	@Override
	public void delete(Long id) {
		porMenSkladiste.delete(id);
	}
/*
	@Override
	public Page<Namirnica> izlistajNamirnice(PorudzbinaMenadzer ponudjac, Pageable pageable) {
		return namirnicaSkladiste.findByPorudzbinaMenadzer(ponudjac, pageable);
	}

	@Override
	public Page<Pice> izlistajPica(PorudzbinaMenadzer ponudjac, Pageable pageable) {
		return piceSkladiste.findByPorudzbinaMenadzer(ponudjac, pageable);
	}
*/

	@Override
	public List<StavkaPorudzbineMenadzera> izlistajStavke(PorudzbinaMenadzer porudzbinaMenadzer) {
		return stavkaSkladiste.findByPorudzbinamenadzer(porudzbinaMenadzer);
	}
}
