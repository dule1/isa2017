package com.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isa.model.Ponuda;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.StavkaPorudzbineMenadzera;
import com.isa.model.korisnici.Ponudjac;
import com.isa.pomocni.Poruka;
import com.isa.services.PonudjacServis;
import com.isa.services.PorudzbinaMenadzeraServis;

@Controller
@RequestMapping("/ponudjacKontroler")
public class PonudjacKontroler {
	
	@Autowired
	public PonudjacServis ponudjacServis;
	
	@Autowired
	public PorudzbinaMenadzeraServis porudzbinaMenadzeraServis;
	
	@RequestMapping(value = "/izmeniPonudjaca", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ponudjac> izmeniPonudjaca(@RequestBody Ponudjac ponudjac, HttpSession session) {
		Ponudjac originalPonudjac = ponudjacServis.findOne(ponudjac.getId());
		
		originalPonudjac.setIme(ponudjac.getIme());
		originalPonudjac.setPrezime(ponudjac.getPrezime());
		originalPonudjac.setEmail(ponudjac.getEmail());
		originalPonudjac.setSifra(ponudjac.getSifra());
		
		originalPonudjac = ponudjacServis.save(originalPonudjac);
		session.setAttribute("ulogovanKorisnik", originalPonudjac);
		return new ResponseEntity<Ponudjac>(originalPonudjac, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajPonude", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ponuda>> izlistajPonude(@RequestBody Ponudjac ponudjac) {
		Page<Ponuda> ponude = ponudjacServis.izlistajPonude(ponudjac, new PageRequest(0, 10));

		return new ResponseEntity<List<Ponuda>>(ponude.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajPorudzbineBezPonude", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PorudzbinaMenadzer>> izlistajPorudzbineBezPonude(@RequestBody Ponudjac ponudjac) {
		
		List<PorudzbinaMenadzer> porudzbine = ponudjacServis.izlistajPorudzbineBezPonude(ponudjac);

		return new ResponseEntity<List<PorudzbinaMenadzer>>(porudzbine, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajPorudzbineSaPonudom", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ponuda>> izlistajPorudzbineSaPonudom(@RequestBody Ponudjac ponudjac) {
		
		List<Ponuda> ponuda = ponudjacServis.izlistajPonudePonudjaca(ponudjac);

		return new ResponseEntity<List<Ponuda>>(ponuda, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajStavkePorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StavkaPorudzbineMenadzera>> izlistajStavkePorudzbine(@RequestBody PorudzbinaMenadzer porudzbinaMenadzer) {
		
		List<StavkaPorudzbineMenadzera> stavke = porudzbinaMenadzeraServis.izlistajStavke(porudzbinaMenadzer);

		return new ResponseEntity<List<StavkaPorudzbineMenadzera>>(stavke, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/posaljiPonudu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void posaljiPonudu(@RequestBody Ponuda ponuda) {
		
		ponudjacServis.dodajPonudu(ponuda);

	}
	
	@RequestMapping(value = "/izmeniPonudu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izmeniPonudu(@RequestBody Ponuda ponuda) {
		Poruka poruka = new Poruka();
		
		if(ponudjacServis.izmenaPonude(ponuda)){
			poruka.setMessage("true");
		}else{
			poruka.setMessage("false");
		}
		

		
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
}
