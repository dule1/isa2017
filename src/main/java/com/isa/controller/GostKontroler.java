package com.isa.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isa.model.PosetaRestoranu;
import com.isa.model.Restoran;
import com.isa.model.Sto;
import com.isa.model.ZahtevZaPrijateljstvo;
import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Prijatelj;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.pomocni.GostPrijatelj;
import com.isa.pomocni.OcenaPoseta;
import com.isa.pomocni.Poruka;
import com.isa.pomocni.PretragaPrijatelja;
import com.isa.pomocni.PromeniSifru;
import com.isa.pomocni.SendMail;
import com.isa.services.GostServis;
import com.isa.services.MenadzerSistemaServis;
import com.isa.services.RestoranServis;

@Controller
@RequestMapping("/gostKontroler")
public class GostKontroler {
	
	@Autowired
	public GostServis gostServis;
	
	@Autowired
	public RestoranServis restServis;
	
	@Autowired
	public MenadzerSistemaServis menSistemaServis;
	
	//Ako zatreba sortiranje u code-behind
	/*public static final Comparator<Korisnik> IME_ASC_PREZIME_ASC = new Comparator<Korisnik>() {
     public int compare(Korisnik p1, Korisnik p2) {
        int nameOrder = p1.getIme().compareTo(p2.getIme());
        if(nameOrder != 0) {
          return nameOrder;
        }
        return p1.getPrezime().compareTo(p2.getPrezime());
     }
    };*/
	
	//Collections.sort(korisnici, IME_ASC_PREZIME_ASC);
	
	@RequestMapping(value = "/izmeniGosta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gost> izmeniPonudjaca(@RequestBody Gost gost, HttpSession session) {
		Gost originalGost = (Gost) gostServis.findOne(gost.getId());
		
		originalGost.setIme(gost.getIme());
		originalGost.setPrezime(gost.getPrezime());
		originalGost.setEmail(gost.getEmail());
		originalGost.setSifra(gost.getSifra());
		
		originalGost = gostServis.save(originalGost);
		
		session.setAttribute("ulogovanKorisnik", originalGost);
		
		return new ResponseEntity<Gost>(originalGost, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/izmeniGostaSifra", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izmeniGostaSifra(@RequestBody PromeniSifru promeniSifru, HttpSession session) {		
		Gost gost = (Gost) session.getAttribute("ulogovanKorisnik");
		Gost gost2 = (Gost) gostServis.findOne(gost.getId());
		
		if(gost2.getSifra().equals(promeniSifru.getStaraSifra())){			
			if(promeniSifru.getNovaSifra().equals(promeniSifru.getNovaSifra2())){
				gost.setSifra(promeniSifru.getNovaSifra());
				gostServis.save(gost);
				
				return new ResponseEntity<Poruka>(new Poruka("Promenjena", null), HttpStatus.OK);
			}else{
				return new ResponseEntity<Poruka>(new Poruka("NisuIste", null), HttpStatus.OK);
			}	
		}else{
			return new ResponseEntity<Poruka>(new Poruka("NijeStara", null), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/izlistajPrijateljeNeprijatelje", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> izlistajPrijateljeNeprijatelje(@RequestBody Gost gost, HttpSession session) {
		//Gost originalGost = (Gost) gostServis.findOne(gost.getId());	
		Gost originalGost = (Gost) session.getAttribute("ulogovanKorisnik");	
		Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 100));	
		List<Korisnik> korisnici = new ArrayList<>();	
		Iterator<Prijatelj> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pretraziPravePrijatelje", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> pretraziPravePrijatelje(@RequestBody PretragaPrijatelja pretrPrij) {
		
		String ime = "";
		String prezime = "";
		
		if(pretrPrij.getParamPretrageIme() != null)
			ime = pretrPrij.getParamPretrageIme().trim();
			ime = ime.toLowerCase();
		
		if(pretrPrij.getParamPretragePrz() != null)
			prezime = pretrPrij.getParamPretragePrz().trim();
			prezime = prezime.toLowerCase();
		
		if(!ime.equals("") && prezime.equals("")){		
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 100));		
			List<Korisnik> korisnici = new ArrayList<>();		
			Iterator<Prijatelj> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmailPrijatelj());
				if(kor.getIme().toLowerCase().startsWith(ime))			
					korisnici.add(kor); 
			}
			
			return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
			
		}else if(ime.equals("") && !prezime.equals("")){
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 100));		
			List<Korisnik> korisnici = new ArrayList<>();		
			Iterator<Prijatelj> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmailPrijatelj());
				if(kor.getPrezime().toLowerCase().startsWith(prezime))			
					korisnici.add(kor); 
			}
			
			return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
			
		}else if(!ime.equals("") && !prezime.equals("")){
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 100));		
			List<Korisnik> korisnici = new ArrayList<>();		
			Iterator<Prijatelj> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmailPrijatelj());
				if(kor.getIme().toLowerCase().startsWith(ime) && kor.getPrezime().toLowerCase().startsWith(prezime))
					korisnici.add(kor);
			}
					
			return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
		}else{
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());			
			Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 100));			
			List<Korisnik> korisnici = new ArrayList<>();			
			Iterator<Prijatelj> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
			}
			
			return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/pretraziPrijatelje", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Gost>> pretraziPrijatelje(@RequestBody PretragaPrijatelja pretrPrij, HttpSession session) {	
		return neprijatelji(pretrPrij);
	}
	
	@RequestMapping(value = "/izlistajZahteveZaPrijateljstvo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> izlistajZahteveZaPrijateljstvo(@RequestBody Gost gost) {
		Gost originalGost = (Gost) gostServis.findOne(gost.getId());		
		Page<ZahtevZaPrijateljstvo> prijatelji = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 100));		
		List<Korisnik> korisnici = new ArrayList<>();	
		Iterator<ZahtevZaPrijateljstvo> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ukloniPrijatelja", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> izbrisiPrijatelja(@RequestBody GostPrijatelj gostPrij) {	
		gostServis.delete(gostPrij);
		
		Gost originalGost = (Gost) gostServis.findOne(gostPrij.getGost().getId());	
		Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 100));	
		List<Korisnik> korisnici = new ArrayList<>();	
		Iterator<Prijatelj> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/prihvatiZahtev", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> prihvatiZahtev(@RequestBody GostPrijatelj gostPrij) {	
		gostServis.addPrijateljstvo(gostPrij);
		gostServis.removeZahtevZaPrijateljstvo(gostPrij);
		
		Gost originalGost = (Gost) gostServis.findOne(gostPrij.getGost().getId());		
		Page<ZahtevZaPrijateljstvo> prijatelji = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 100));		
		List<Korisnik> korisnici = new ArrayList<>();	
		Iterator<ZahtevZaPrijateljstvo> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/odbijZahtev", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> odbijZahtev(@RequestBody GostPrijatelj gostPrij) {		
		gostServis.removeZahtevZaPrijateljstvo(gostPrij);	
		
		Gost originalGost = (Gost) gostServis.findOne(gostPrij.getGost().getId());		
		Page<ZahtevZaPrijateljstvo> prijatelji = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 100));		
		List<Korisnik> korisnici = new ArrayList<>();	
		Iterator<ZahtevZaPrijateljstvo> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/otkaziZahtev", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Gost>> otkaziZahtev(@RequestBody GostPrijatelj gostPrij) {		
		gostServis.removeZahtevZaPrijateljstvoByGost(gostPrij);	
		
		return neprijatelji(new PretragaPrijatelja(gostPrij.getGost(), gostPrij.getParamPretrageIme(), gostPrij.getParamPretragePrz()));	
	}
	
	@RequestMapping(value = "/dodajPrijatelja", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<List<Gost>>  jatelja(@RequestBody GostPrijatelj gostPrij) {		
		gostServis.addZahtevZaPrijateljstvo(gostPrij, new PageRequest(0, 100));
		return neprijatelji(new PretragaPrijatelja(gostPrij.getGost(), gostPrij.getParamPretrageIme(), gostPrij.getParamPretragePrz()));
	}
	
	public ResponseEntity<List<Gost>> neprijatelji(PretragaPrijatelja pretrPrij){
		String ime = "";
		String prezime = "";
		
		if(pretrPrij.getParamPretrageIme() != null)
			ime = pretrPrij.getParamPretrageIme().trim();
			ime = ime.toLowerCase();
		
		if(pretrPrij.getParamPretragePrz() != null)
			prezime = pretrPrij.getParamPretragePrz().trim();
			prezime = prezime.toLowerCase();
		
		if(!ime.equals("") && prezime.equals("")){		
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Gost> prijatelji = gostServis.izlistajNeprijatelje(originalGost, new PageRequest(0, 100));		
			List<Gost> korisnici = new ArrayList<>();
			Iterator<Gost> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmail());
				if(kor.getIme().toLowerCase().startsWith(ime))
					if(!kor.getEmail().equals(originalGost.getEmail()) && kor.getTipKorisnika().equals(TipKorisnika.GOST)){
						
						Page<Prijatelj> prijateljiKorisnika = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 100));							
						List<Korisnik> korisniciPrij = new ArrayList<>();					
						Iterator<Prijatelj> itr2 = prijateljiKorisnika.iterator();
										
						Page<ZahtevZaPrijateljstvo> zahteviZaPrij = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 100));		
						List<Korisnik> korisniciZaht = new ArrayList<>();	
						Iterator<ZahtevZaPrijateljstvo> itrZaht = zahteviZaPrij.iterator();
								
						boolean canAdd = true;						
						
						while(itr2.hasNext())
							korisniciPrij.add(gostServis.findByEmail(itr2.next().getEmailPrijatelj())); 
						
						while(itrZaht.hasNext())
							korisniciZaht.add(gostServis.findByEmail(itrZaht.next().getEmailPrijatelj())); 			
										
						for(Korisnik korisn: korisniciPrij)
							if(korisn.getEmail().equals(kor.getEmail()))
								canAdd = false;
						
						for(Korisnik korisn: korisniciZaht)
							if(korisn.getEmail().equals(kor.getEmail())){
								Gost gost = (Gost)kor;
								gost.setCanSend(false);
								gost.setCanDecline(false);
								gost.setCanAccept(true);
								korisnici.add(gost);
								canAdd = false;
							}
								
						if(canAdd){
							Gost gost = (Gost)kor;
							if(gostServis.zahteviCount(new GostPrijatelj(originalGost, gost), new PageRequest(0, 100)) > 0){
								gost.setCanSend(false);
								gost.setCanDecline(true);
								gost.setCanAccept(false);
							}else{
								gost.setCanSend(true);
								gost.setCanDecline(false);
								gost.setCanAccept(false);
							}
							korisnici.add(gost); 
						}
					}
			}
			return new ResponseEntity<List<Gost>>(korisnici, HttpStatus.OK);
			
		}else if(ime.equals("") && !prezime.equals("")){
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Gost> prijatelji = gostServis.izlistajNeprijatelje(originalGost, new PageRequest(0, 100));		
			List<Gost> korisnici = new ArrayList<>();		
			Iterator<Gost> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmail());
				if(kor.getPrezime().toLowerCase().startsWith(prezime))
					if(!kor.getEmail().equals(originalGost.getEmail()) && kor.getTipKorisnika().equals(TipKorisnika.GOST)){
						
						Page<Prijatelj> prijateljiKorisnika = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 100));							
						List<Korisnik> korisniciPrij = new ArrayList<>();					
						Iterator<Prijatelj> itr2 = prijateljiKorisnika.iterator();
						
						Page<ZahtevZaPrijateljstvo> zahteviZaPrij = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 100));		
						List<Korisnik> korisniciZaht = new ArrayList<>();	
						Iterator<ZahtevZaPrijateljstvo> itrZaht = zahteviZaPrij.iterator();
						
						boolean canAdd = true;
						
						while(itr2.hasNext())
							korisniciPrij.add(gostServis.findByEmail(itr2.next().getEmailPrijatelj())); 
						
						while(itrZaht.hasNext())
							korisniciZaht.add(gostServis.findByEmail(itrZaht.next().getEmailPrijatelj())); 			
										
						for(Korisnik korisn: korisniciPrij)
							if(korisn.getEmail().equals(kor.getEmail()))
								canAdd = false;
						
						for(Korisnik korisn: korisniciZaht)
							if(korisn.getEmail().equals(kor.getEmail())){
								Gost gost = (Gost)kor;
								gost.setCanSend(false);
								gost.setCanDecline(false);
								gost.setCanAccept(true);
								korisnici.add(gost);
								canAdd = false;
							}
								
						if(canAdd){
							Gost gost = (Gost)kor;
							if(gostServis.zahteviCount(new GostPrijatelj(originalGost, gost), new PageRequest(0, 100)) > 0){
								gost.setCanSend(false);
								gost.setCanDecline(true);
								gost.setCanAccept(false);
							}else{
								gost.setCanSend(true);
								gost.setCanDecline(false);
								gost.setCanAccept(false);
							}
							korisnici.add(gost); 
						}
					}				
			}		
			return new ResponseEntity<List<Gost>>(korisnici, HttpStatus.OK);
			
		}else if(!ime.equals("") && !prezime.equals("")){
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Gost> prijatelji = gostServis.izlistajNeprijatelje(originalGost, new PageRequest(0, 100));		
			List<Gost> korisnici = new ArrayList<>();		
			Iterator<Gost> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmail());
				if(kor.getIme().toLowerCase().startsWith(ime) && kor.getPrezime().toLowerCase().startsWith(prezime))
					if(!kor.getEmail().equals(originalGost.getEmail()) && kor.getTipKorisnika().equals(TipKorisnika.GOST)){
						
						Page<Prijatelj> prijateljiKorisnika = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 100));							
						List<Korisnik> korisniciPrij = new ArrayList<>();					
						Iterator<Prijatelj> itr2 = prijateljiKorisnika.iterator();
						
						Page<ZahtevZaPrijateljstvo> zahteviZaPrij = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 100));		
						List<Korisnik> korisniciZaht = new ArrayList<>();	
						Iterator<ZahtevZaPrijateljstvo> itrZaht = zahteviZaPrij.iterator();
						
						boolean canAdd = true;		
						
						while(itr2.hasNext())
							korisniciPrij.add(gostServis.findByEmail(itr2.next().getEmailPrijatelj())); 
						
						while(itrZaht.hasNext())
							korisniciZaht.add(gostServis.findByEmail(itrZaht.next().getEmailPrijatelj())); 
										
						for(Korisnik korisn: korisniciPrij)
							if(korisn.getEmail().equals(kor.getEmail()))
								canAdd = false;
						
						for(Korisnik korisn: korisniciZaht)
							if(korisn.getEmail().equals(kor.getEmail())){
								Gost gost = (Gost)kor;
								gost.setCanSend(false);
								gost.setCanDecline(false);
								gost.setCanAccept(true);
								korisnici.add(gost);
								canAdd = false;
							}
								
						if(canAdd){
							Gost gost = (Gost)kor;
							if(gostServis.zahteviCount(new GostPrijatelj(originalGost, gost), new PageRequest(0, 100)) > 0){
								gost.setCanSend(false);
								gost.setCanDecline(true);
								gost.setCanAccept(false);
							}else{
								gost.setCanSend(true);
								gost.setCanDecline(false);
								gost.setCanAccept(false);
							}
							korisnici.add(gost); 
						}
					}					
			}		
			return new ResponseEntity<List<Gost>>(korisnici, HttpStatus.OK);
		}
		
		return null;
	}
	
	@RequestMapping(value = "/rezervisiRestoran", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> rezervisiRestoran(@RequestBody List<PosetaRestoranu> rezervacija, HttpSession session) throws ParseException {		

		boolean canAdd = true;
		
		for(PosetaRestoranu poseta: rezervacija){
			Restoran r = restServis.findOne(poseta.getRestoran().getId());
			Sto sto = restServis.izlistajSto(poseta.getSto());
			
			List<PosetaRestoranu> svePosete = gostServis.poseteRestoranAndSto(r, sto);	
			
			System.out.println(svePosete.size() + " SIZE POSETE");
			
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			poseta.setTermin(sdf.format(poseta.getDatumrez()));
			
			Calendar calendarPos = Calendar.getInstance();
			Date datumPocetkaPos = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(poseta.getTermin());
			calendarPos.setTime(datumPocetkaPos);
			calendarPos.add(Calendar.HOUR_OF_DAY, poseta.getBrSati());
			Date datumKrajaPos = calendarPos.getTime();
			
			System.out.println("Datum pocetka pos: " + datumPocetkaPos + " *********** Datum kraja posete: " + datumKrajaPos);
			
			
			
			if(svePosete.size() > 0){			
				for(PosetaRestoranu posetaRestoranu: svePosete){
					
					Calendar calendar = Calendar.getInstance();
					Date datumPocetka = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(posetaRestoranu.getTermin());
					calendar.setTime(datumPocetka);
					calendar.add(Calendar.HOUR_OF_DAY, posetaRestoranu.getBrSati());
					Date datumKraja = calendar.getTime();
					
					System.out.println("Datum posete: " + datumPocetka + " *********** Datum kraja posete: " + datumKraja);
					
					
						
						if(datumKrajaPos.before(datumPocetka))
						{
							
							System.out.println("(datumPocetkaPos.before(datumPocetka)    " + datumPocetkaPos.before(datumPocetka));
							System.out.println("datumKrajaPos.before(datumPocetka)    " + datumKrajaPos.before(datumPocetka));
							System.out.println("(datumPocetkaPos.after(datumKraja)    " + datumPocetkaPos.after(datumKraja));
							System.out.println("(datumKrajaPos.after(datumKraja)    " + datumKrajaPos.after(datumKraja));
							
						}else if (datumPocetkaPos.after(datumKraja)){
							
							System.out.println("(datumPocetkaPos.before(datumPocetka)    " + datumPocetkaPos.before(datumPocetka));
							System.out.println("datumKrajaPos.before(datumPocetka)    " + datumKrajaPos.before(datumPocetka));
							System.out.println("(datumPocetkaPos.after(datumKraja)    " + datumPocetkaPos.after(datumKraja));
							System.out.println("(datumKrajaPos.after(datumKraja)    " + datumKrajaPos.after(datumKraja));
							
						}else{
							
							canAdd = false;
							break;
						}
					}		
			}		
		}
		
		System.out.println("***************" + canAdd);
		
		if(canAdd){
			
			for(PosetaRestoranu poseta: rezervacija){
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				poseta.setTermin(sdf.format(poseta.getDatumrez()));
				
				if(!poseta.getGost().getEmail().equals(((Gost)session.getAttribute("ulogovanKorisnik")).getEmail())){
					
					String emailSubject = "Prijatelj " + poseta.getPozivalac().getIme() + " " + poseta.getPozivalac().getPrezime()
							+ " Vas je pozvao da mu budete gost u restoranu \"" + poseta.getRestoran().getNaziv() + "\", Datum: " + poseta.getTermin()+ "\n\n"
							+ "Ukoliko zelite da prihvatite poziv, kliknite na link:\t" + "http://localhost:9000/gostKontroler/acceptdecline/"+poseta.getRestoran().getId()+"/"
							+ poseta.getPozivalac().getId()+"/"+poseta.getSto().getOznaka()+"/accept/\n"
							+ "Ukoliko zelite da odbijete poziv, kliknite na link:\t" + "http://localhost:9000/gostKontroler/acceptdecline/"+poseta.getRestoran().getId()+"/"
							+ poseta.getPozivalac().getId()+"/"+poseta.getSto().getOznaka()+"/decline/\n";
					
					SendMail sm = new SendMail("nikola9n@gmail.com",emailSubject);
					
					poseta.setAccepted(false);
					
				}else{
					poseta.setAccepted(true);
				}
				
				gostServis.sacuvajPosetu(poseta);
			}
			
			return new ResponseEntity<Poruka>(new Poruka("Rezervisano", null), HttpStatus.ACCEPTED);
		}else{
			return new ResponseEntity<Poruka>(new Poruka("NijeRezervisano", null), HttpStatus.ACCEPTED);
		}
			
	}
	
	@Transactional
	@RequestMapping(value = "/acceptdecline/{idRestorana}/{idPozivaoca}/{oznakaStola}/{acceptDecline}")
	public ResponseEntity<String> activateAccount(@PathVariable String idRestorana, @PathVariable String emailPozivaoca, @PathVariable String oznakaStola, @PathVariable String acceptDecline) {			
		
		try{
			System.out.println(idRestorana + "  " + emailPozivaoca + "  " + oznakaStola + "  " + acceptDecline);
			return new ResponseEntity<String>("Uspesno ste aktivirali nalog!", HttpStatus.ACCEPTED);			
		}catch(Exception ex){}
		return new ResponseEntity<String>("Neuspesna aktivacija naloga.", HttpStatus.ACCEPTED);
		
	}
	
	@RequestMapping(value = "/pretraziRestorane", method = RequestMethod.POST)
	public ResponseEntity<List<Restoran>> izlistajRestorane(@RequestBody Poruka parameter) {
		Page<Restoran> restorani = menSistemaServis.izlistajRestorane(new PageRequest(0, 100));
		List<Restoran> tempLista = restorani.getContent();
		List<Restoran> returnedList = new ArrayList<>();	
		
		for(Restoran rest: tempLista){
			if(rest.getNaziv().toLowerCase().startsWith(parameter.getMessage().toLowerCase())
				|| rest.getOpis().toLowerCase().startsWith(parameter.getMessage().toLowerCase())){
				returnedList.add(rest);
			}
		}
		
		return new ResponseEntity<List<Restoran>>(returnedList, HttpStatus.OK);
	}
	
	
	// SASA
	
	
	@RequestMapping(value = "/ucitajPoseteGosta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PosetaRestoranu>> ucitajPoseteGosta(@RequestBody Gost parametar) {		
		Gost gost = (Gost) gostServis.findOne(parametar.getId());
		List<PosetaRestoranu> posete = gostServis.ucitajPoseteGosta(gost);
		return new ResponseEntity<List<PosetaRestoranu>>(getObavljene(posete), HttpStatus.OK);
	
	}	
	@RequestMapping(value = "/oceniPosetu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PosetaRestoranu>> oceniPosetu(@RequestBody OcenaPoseta parametar) {		
		PosetaRestoranu poseta = gostServis.pronadjiPosetu(parametar.getPoseta().getId());
		poseta.setOcena(parametar.getOcena());
		gostServis.sacuvajPosetu(poseta);
		List<PosetaRestoranu> posete = gostServis.ucitajPoseteGosta((Gost)gostServis.findOne(parametar.getPoseta().getGost().getId()));
		return new ResponseEntity<List<PosetaRestoranu>>(getObavljene(posete), HttpStatus.OK);
	
	}

	@RequestMapping(value = "/oceniObrok", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PosetaRestoranu>> oceniPosetuObrok(@RequestBody OcenaPoseta parametar) {		
		PosetaRestoranu poseta = gostServis.pronadjiPosetu(parametar.getPoseta().getId());
		poseta.setOcenaObroka(parametar.getOcena());
		gostServis.sacuvajPosetu(poseta);
		List<PosetaRestoranu> posete = gostServis.ucitajPoseteGosta((Gost)gostServis.findOne(parametar.getPoseta().getGost().getId()));
		return new ResponseEntity<List<PosetaRestoranu>>(getObavljene(posete), HttpStatus.OK);
	
	}
	@RequestMapping(value = "/oceniUslugu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PosetaRestoranu>> oceniPosetuUsluge(@RequestBody OcenaPoseta parametar) {		
		PosetaRestoranu poseta = gostServis.pronadjiPosetu(parametar.getPoseta().getId());
		poseta.setOcenaUsluge(parametar.getOcena());
		gostServis.sacuvajPosetu(poseta);
		List<PosetaRestoranu> posete = gostServis.ucitajPoseteGosta((Gost)gostServis.findOne(parametar.getPoseta().getGost().getId()));
		return new ResponseEntity<List<PosetaRestoranu>>(getObavljene(posete), HttpStatus.OK);
	
	}

	
	
	
	private List<PosetaRestoranu> getObavljene(List<PosetaRestoranu> posete){
		List<PosetaRestoranu> retVal = new ArrayList<PosetaRestoranu>();
		for (int i = 0; i < posete.size(); i++){
			if(posete.get(i).isObavljena() == true){
				retVal.add(posete.get(i));
			}
		}
		return retVal;
		
		
	}
}
