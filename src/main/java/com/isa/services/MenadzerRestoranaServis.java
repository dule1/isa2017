package com.isa.services;

import java.util.List;

import com.isa.model.Namirnica;
import com.isa.model.Pice;
import com.isa.model.Ponuda;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.Restoran;
import com.isa.model.StavkaPorudzbineMenadzera;
import com.isa.model.korisnici.MenadzerRestorana;

public interface MenadzerRestoranaServis {

	List<MenadzerRestorana> findAll();
	
	MenadzerRestorana findOne(Long id);
	
	MenadzerRestorana save(MenadzerRestorana ponudjac);
	
	void delete(Long id);
	
	Restoran izlistajRestoran(MenadzerRestorana menadzerRestorana);

	List<Namirnica> izlistajSveNamirnice();

	List<Pice> izlistajSvaPica();

	void dodajPorudzbinu(PorudzbinaMenadzer porudzbina, List<StavkaPorudzbineMenadzera> stavke);

	List<PorudzbinaMenadzer> izlistajPorudzbineMenadzera(MenadzerRestorana menRest);

	List<Ponuda> izlistajPonude(PorudzbinaMenadzer porudzbinaMenadzer);
	
	List<PorudzbinaMenadzer> prihvatiPonudu(Ponuda ponuda);

	PorudzbinaMenadzer izlistajPorudzbinu(Long id);
}
