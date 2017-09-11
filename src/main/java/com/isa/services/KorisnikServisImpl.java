package com.isa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.model.korisnici.MenadzerSistema;
import com.isa.model.korisnici.Ponudjac;
import com.isa.model.korisnici.Sanker;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.repository.GostSkladiste;
import com.isa.repository.KonobarSkladiste;
import com.isa.repository.KorisnikSkladiste;
import com.isa.repository.KuvarSkladiste;
import com.isa.repository.MenadzerRestoranaSkladiste;
import com.isa.repository.MenadzerSistemaSkladiste;
import com.isa.repository.PonudjacSkladiste;
import com.isa.repository.SankerSkladiste;

@Service
public class KorisnikServisImpl implements KorisnikServis {
	
	@Autowired
	private KorisnikSkladiste korisnikSkladiste;
	@Autowired
	private GostSkladiste gostSkladiste;
	@Autowired
	private KonobarSkladiste konobarSkladiste;
	@Autowired
	private KuvarSkladiste kuvarSkladiste;
	@Autowired
	private MenadzerRestoranaSkladiste menadzerRestoranaSkladiste;
	@Autowired
	private MenadzerSistemaSkladiste menadzerSistemaSkladiste;
	@Autowired
	private PonudjacSkladiste ponudjacSkladiste;
	@Autowired
	private SankerSkladiste sankerSkladiste;
	

	@Override
	public Korisnik save(Korisnik korisnik) {
		if (korisnik.getTipKorisnika() == TipKorisnika.GOST){
			return gostSkladiste.save((Gost)korisnik);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.KONOBAR){
			return konobarSkladiste.save((Konobar)korisnik);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.KUVAR){
			return kuvarSkladiste.save((Kuvar)korisnik);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.MENADZER_RESTRORANA){
			return menadzerRestoranaSkladiste.save((MenadzerRestorana)korisnik);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.MENADZER_SISTEMA){
			return menadzerSistemaSkladiste.save((MenadzerSistema)korisnik);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.PONUDJAC){
			return ponudjacSkladiste.save((Ponudjac)korisnik);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.SANKER){
			return sankerSkladiste.save((Sanker)korisnik);
		} else {
			return korisnik;//mora neki return
		}
	}
	
	@Override
	public Korisnik findOne(Long id) {
		Korisnik korisnik = gostSkladiste.findOne(id);
		
		if (korisnik == null){
			korisnik = konobarSkladiste.findOne(id);
		} 
		if (korisnik == null){
			korisnik = kuvarSkladiste.findOne(id);
		} 
		if (korisnik == null){
			korisnik = menadzerRestoranaSkladiste.findOne(id);
		} 
		if (korisnik == null){
			korisnik = menadzerSistemaSkladiste.findOne(id);
		} 
		if (korisnik == null){
			korisnik = ponudjacSkladiste.findOne(id);
		} 
		if (korisnik == null){
			korisnik = sankerSkladiste.findOne(id);
		} 
		
		if (korisnik.getTipKorisnika() == TipKorisnika.GOST){
			return gostSkladiste.findOne(id);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.KONOBAR){
			return konobarSkladiste.findOne(id);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.KUVAR){
			return kuvarSkladiste.findOne(id);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.MENADZER_RESTRORANA){
			return menadzerRestoranaSkladiste.findOne(id);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.MENADZER_SISTEMA){
			return menadzerSistemaSkladiste.findOne(id);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.PONUDJAC){
			return ponudjacSkladiste.findOne(id);
		} else if (korisnik.getTipKorisnika() == TipKorisnika.SANKER){
			return sankerSkladiste.findOne(id);
		} else {
			return korisnik;//mora neki return
		}
	}

	@Override
	public Korisnik findByEmail(String email) {
		try{
			return korisnikSkladiste.findByEmail(email).get(0);
		}catch(Exception ex){}
		
		return null;
	}
	
	
}
