package com.isa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isa.model.korisnici.Korisnik;
import com.isa.services.KorisnikServis;

@Controller
@RequestMapping("/korisnikKontroler")
public class KorisnikKontroler {
	
	@Autowired
	public KorisnikServis korisnikServis;
	
	@RequestMapping(value = "/izmeniKorisnika", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> izmeniKorisnika(@RequestBody Korisnik gost, HttpSession request) {
		Korisnik originalKorisnik = (Korisnik) korisnikServis.findOne(gost.getId());	
		originalKorisnik.setIme(gost.getIme());
		originalKorisnik.setPrezime(gost.getPrezime());
		originalKorisnik.setEmail(gost.getEmail());
		originalKorisnik = korisnikServis.save(originalKorisnik);
		request.setAttribute("ulogovanKorisnik", originalKorisnik);
		return new ResponseEntity<Korisnik>(originalKorisnik, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniLozinkuKorisnika", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> izmeniLozinkuKorisnika(@RequestBody Korisnik gost,HttpSession request) {
		Korisnik originalKorisnik = (Korisnik) korisnikServis.findOne(gost.getId());	
		if (!originalKorisnik.getSifra().equals(gost.getSifraStara())){
			return new ResponseEntity<Korisnik>(HttpStatus.NOT_MODIFIED);
		}
		originalKorisnik.setSifra(gost.getSifra());
		originalKorisnik.setLogovaoSe(true);
		originalKorisnik = korisnikServis.save(originalKorisnik);
		request.setAttribute("ulogovanKorisnik", originalKorisnik);
		return new ResponseEntity<Korisnik>(originalKorisnik, HttpStatus.OK);
	}


}
