package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.PosetaRestoranu;
import com.isa.model.Restoran;
import com.isa.model.Sto;
import com.isa.model.ZahtevZaPrijateljstvo;
import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Prijatelj;
import com.isa.pomocni.GostPrijatelj;
import com.isa.repository.GostSkladiste;
import com.isa.repository.PoseteSkladiste;
import com.isa.repository.PrijateljSkladiste;
import com.isa.repository.ZahteviZaPrijSkladiste;

@Service
public class GostServisImp implements GostServis {

	@Autowired
	private GostSkladiste gostSkladiste;
	
	@Autowired
	private PrijateljSkladiste prijSkladiste;
	
	@Autowired
	private ZahteviZaPrijSkladiste zahtevSkladiste;
	
	
	// SASA POCETAK
	@Autowired
	private PoseteSkladiste poseteSkladiste;
	// SASA KRAJ
	
	@Override
	public List<Gost> findAll() {
		return gostSkladiste.findAll();
	}

	@Override
	public Korisnik findOne(Long id) {
		return gostSkladiste.findOne(id);
	}

	@Override
	public Gost save(Gost gost) {
		return gostSkladiste.save(gost);
	}

	@Override
	public void delete(GostPrijatelj gostPrij) {

		if(gostPrij != null){
			prijSkladiste.deleteGostPrij(gostPrij.getGost().getEmail(), gostPrij.getPrijatelj().getEmail());
			prijSkladiste.deletePrijGost(gostPrij.getPrijatelj().getEmail(), gostPrij.getGost().getEmail());
		}
	}

	@Override
	public Gost findByEmail(String email) {
		try{
			return gostSkladiste.findByEmail(email).get(0);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public Page<Prijatelj> izlistajPrijatelje(Gost gost, Pageable pageable) {
		try{
			return prijSkladiste.findByEmailGosta(gost.getEmail(), pageable);
		}catch(Exception ex){
			return null;			
		}
	}
	
	@Override
	public Page<Gost> izlistajNeprijatelje(Gost gost, Pageable pageable) {
		try{
			return gostSkladiste.findByEmailNotLike(gost.getEmail(), pageable);
		}catch(Exception ex){
			return null;			
		}
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<ZahtevZaPrijateljstvo> izlistajZahteveZaPrij(Gost originalGost, Pageable pageable) {
		try{
			return zahtevSkladiste.findByEmailGosta(originalGost.getEmail(), pageable);
		}catch(Exception ex){
			return null;			
		}
	}

	@Override
	public void addPrijateljstvo(GostPrijatelj gostPrij) {
		try{
			Prijatelj prij1 = new Prijatelj(gostPrij.getGost().getEmail(), gostPrij.getPrijatelj().getEmail());
			Prijatelj prij2 = new Prijatelj(gostPrij.getPrijatelj().getEmail(), gostPrij.getGost().getEmail());
			prijSkladiste.save(prij1);
			prijSkladiste.save(prij2);
			//prijSkladiste.addGostPrij(gostPrij.getGost().getEmail(), gostPrij.getPrijatelj().getEmail());
		}catch(Exception ex){}
	}

	@Override
	public void removeZahtevZaPrijateljstvo(GostPrijatelj gostPrij) {
		try{
			zahtevSkladiste.deleteZahtev(gostPrij.getGost().getEmail(), gostPrij.getPrijatelj().getEmail());
		}catch(Exception ex){}
	}
	
	@Override
	public void addZahtevZaPrijateljstvo(GostPrijatelj gostPrij, Pageable pageable) {
		try{
			if(zahtevSkladiste.findByEmailGostaAndEmailPrijatelj(gostPrij.getPrijatelj().getEmail(), gostPrij.getGost().getEmail(), pageable).getContent().size() == 0)
				zahtevSkladiste.addZahtev(gostPrij.getPrijatelj().getEmail(), gostPrij.getGost().getEmail());				
		}catch(Exception ex){}
	}
	
	public int zahteviCount(GostPrijatelj gostPrij, Pageable pageable){
		return zahtevSkladiste.findByEmailGostaAndEmailPrijatelj(gostPrij.getPrijatelj().getEmail(), gostPrij.getGost().getEmail(), pageable).getContent().size();
	}

	@Override
	public void removeZahtevZaPrijateljstvoByGost(GostPrijatelj gostPrij) {
		try{
			zahtevSkladiste.deleteZahtev(gostPrij.getPrijatelj().getEmail(), gostPrij.getGost().getEmail());
		}catch(Exception ex){}
	}

	public void activateAccount(String email) {
		Gost gost = (Gost) gostSkladiste.findByEmail(email).get(0);
		gost.setIsActivated(true);
		gostSkladiste.save(gost);
	}
	
	// SASA DODAO DO KRAJA
	@Override
	public List<PosetaRestoranu> ucitajPoseteGosta(Gost gost) {
		return poseteSkladiste.findByGost(gost);
	}

	@Override
	public PosetaRestoranu pronadjiPosetu(Long id) {
		return poseteSkladiste.findOne(id);
	}

	@Override
	public PosetaRestoranu sacuvajPosetu(PosetaRestoranu poseta) {
		return poseteSkladiste.save(poseta);
		
	}

	@Override
	public List<PosetaRestoranu> svePosete() {
		return poseteSkladiste.findAll();
	}
	
	@Override
	public List<PosetaRestoranu> poseteRestoranAndSto(Restoran rest, Sto sto) {
		return poseteSkladiste.findByRestoranAndSto(rest, sto);
	}
	

}
