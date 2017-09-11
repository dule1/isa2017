package com.isa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isa.model.PosetaRestoranu;
import com.isa.model.Restoran;
import com.isa.model.Sto;
import com.isa.model.ZahtevZaPrijateljstvo;
import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Prijatelj;
import com.isa.pomocni.GostPrijatelj;
import com.isa.pomocni.Poruka;


public interface GostServis {

    List<Gost> findAll();

    Korisnik findOne(Long id);

    Gost save(Gost gost);

    void delete(Long id);

    Gost findByEmail(String email);
    
    Page<Prijatelj> izlistajPrijatelje(Gost gost, Pageable pageable);

	void delete(GostPrijatelj gostPrij);

	Page<ZahtevZaPrijateljstvo> izlistajZahteveZaPrij(Gost originalGost, Pageable pageable);
	
	void addPrijateljstvo(GostPrijatelj gostPrij);
	
	void removeZahtevZaPrijateljstvo(GostPrijatelj gostPrij);
	
	void removeZahtevZaPrijateljstvoByGost(GostPrijatelj gostPrij);

	Page<Gost> izlistajNeprijatelje(Gost gost, Pageable pageable);

	void addZahtevZaPrijateljstvo(GostPrijatelj gostPrij, Pageable pageable);

	List<PosetaRestoranu> ucitajPoseteGosta(Gost gost);

	PosetaRestoranu pronadjiPosetu(Long id);

	PosetaRestoranu sacuvajPosetu(PosetaRestoranu poseta);
	
	public int zahteviCount(GostPrijatelj gostPrij, Pageable pageable);

	void activateAccount(String email);

	List<PosetaRestoranu> svePosete();

	List<PosetaRestoranu> poseteRestoranAndSto(Restoran rest, Sto sto);

}
