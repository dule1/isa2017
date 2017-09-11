package com.isa.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.isa.model.IzvestajJelo;
import com.isa.model.Jelo;
import com.isa.model.Namirnica;
import com.isa.model.Pice;
import com.isa.model.Ponuda;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.Restoran;
import com.isa.model.Smena;
import com.isa.model.SmenaUDanu;
import com.isa.model.Sto;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.model.korisnici.Ponudjac;
import com.isa.model.korisnici.Sanker;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.pomocni.IzvestajKonobar;
import com.isa.pomocni.IzvestajRestoran;
import com.isa.pomocni.ListaStavki;
import com.isa.pomocni.Poruka;
import com.isa.pomocni.PosecenostIzvestaj;
import com.isa.pomocni.RestoranPonudjac;
import com.isa.pomocni.SendMail;
import com.isa.services.MenadzerRestoranaServis;
import com.isa.services.RestoranServis;

@Controller
@RequestMapping("/menadzerRestoranaKontroler")
public class MenadzerRestoranaKontroler {

	@Autowired
	public MenadzerRestoranaServis menadzerRestoranaServis;
	
	@Autowired
	public RestoranServis restoranServirs;

	@RequestMapping(value = "/izlistajRestoran", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restoran> izlistajRestoran(@RequestBody MenadzerRestorana menadzerRestorana) {

		MenadzerRestorana tempMenadzerRestorana = menadzerRestoranaServis.findOne(menadzerRestorana.getId());
		
		Restoran restoran = menadzerRestoranaServis.izlistajRestoran(tempMenadzerRestorana);
		
		return new ResponseEntity<Restoran>(restoran, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/izlistajJela", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Jelo>> izlistajJela(@RequestBody Restoran restoran) {
		
		Page<Jelo> jela = restoranServirs.izlistajJelovnik(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Jelo>>(jela.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajPica", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pice>> izlistajPica(@RequestBody Restoran restoran) {
		
		Page<Pice> pice = restoranServirs.izlistajKartuPica(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Pice>>(pice.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodajJelo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Jelo> dodajJelo(@RequestBody Jelo jelo) {
		
		restoranServirs.save(jelo);
		
		return new ResponseEntity<Jelo>(jelo, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/dodajPice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pice> dodajPice(@RequestBody Pice pice) {
		
		restoranServirs.save(pice);
		
		return new ResponseEntity<Pice>(pice, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/izmeniNazivRestorana", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restoran> izmeniNazivRestorana(@RequestBody Restoran restoran) {
		Restoran originalRestoran = restoranServirs.findOne(restoran.getId());
		
		originalRestoran.setNaziv(restoran.getNaziv());
		
		restoranServirs.save(originalRestoran);
		
		return new ResponseEntity<Restoran>(originalRestoran, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniOpisRestorana", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restoran> izmeniOpisRestorana(@RequestBody Restoran restoran) {
		Restoran originalRestoran = restoranServirs.findOne(restoran.getId());
		
		originalRestoran.setOpis(restoran.getOpis());
		
		restoranServirs.save(originalRestoran);
		
		return new ResponseEntity<Restoran>(originalRestoran, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izbrisiJelo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void izbrisiJelo(@RequestBody Long id) {
		
		restoranServirs.izbrisiJelo(id);
		
		//return new ResponseEntity<Restoran>(originalRestoran, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izbrisiPice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void izbrisiPice(@RequestBody Long id) {	
		restoranServirs.izbrisiPice(id);

	}
	
	@RequestMapping(value = "/napraviStolove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sto>> napraviStolove(@RequestBody int[] oznakeStolova) {	
		Restoran restoran = restoranServirs.findOne((long)oznakeStolova[0]);
		ArrayList<Integer> oznake = new ArrayList<>();
		for(int i=1; i<oznakeStolova.length; i++){
			oznake.add(oznakeStolova[i]);
		}
		Page<Sto> stolovi = restoranServirs.kreirajStolove(restoran, oznake, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Sto>>(stolovi.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajStolove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sto>> izlistajStolove(@RequestBody Restoran restoran) {
		
		Page<Sto> stolovi = restoranServirs.izlistajStolove(restoran, new PageRequest(0, 100));
		
		return new ResponseEntity<List<Sto>>(stolovi.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajSto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izlistajSto(@RequestBody Sto sto) {
		Poruka p = new Poruka();
		Sto s = restoranServirs.izlistajSto(sto);
		
		p.setMessage(s.getSegment());
		p.setObj(s);
		return new ResponseEntity<Poruka>(p, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajSveNamirnice", method = RequestMethod.POST)
	public ResponseEntity<List<Namirnica>> izlistajSveNamirnice() {	
		
		List<Namirnica> namirnice = menadzerRestoranaServis.izlistajSveNamirnice();
		
		return new ResponseEntity<List<Namirnica>>(namirnice, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajSvaPica", method = RequestMethod.POST)
	public ResponseEntity<List<Pice>> izlistajSvaPica() {	
	
		List<Pice> pice = menadzerRestoranaServis.izlistajSvaPica();
		
		return new ResponseEntity<List<Pice>>(pice, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodajStavke", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void dodajStavke(@RequestBody ListaStavki listaStavki) {	
	
		System.out.println(listaStavki);
		listaStavki.getPorudzbinaMenadzer().setAktivna(true);
		menadzerRestoranaServis.dodajPorudzbinu(listaStavki.getPorudzbinaMenadzer(), listaStavki.getStavke());

	}
	
	@RequestMapping(value = "/brojeviRedovaIKolona", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void brojeviRedovaIKolona(@RequestBody Restoran restoran) {	
	
		Restoran rest = restoranServirs.findOne(restoran.getId());
		rest.setBrojredova(restoran.getBrojredova());
		rest.setBrojkolona(restoran.getBrojkolona());
		restoranServirs.dodajRedoveIKolone(rest);

	}
	
	@RequestMapping(value = "/izlistajDostupnePonudjace", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ponudjac>> izlistajDostupnePonudjace(@RequestBody Restoran restoran) {	
	
		List<Ponudjac> ponudjac = restoranServirs.izlistajPonudjaceVanRestorana(restoran);
		
		return new ResponseEntity<List<Ponudjac>>(ponudjac, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodajPonudjaca", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void dodajPonudjaca(@RequestBody RestoranPonudjac restPon) {	
		restoranServirs.dodajPonudjacaURestoran(restPon.getRestoran(), restPon.getPonudjac());

	}
	
	@RequestMapping(value = "/registrujIDodajPonudjaca", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void registrujIDodajPonudjaca(@RequestBody RestoranPonudjac restPon) {	
		restPon.getPonudjac().setTipKorisnika(TipKorisnika.PONUDJAC);		// TODO: Aca ~ proveriti..
		restPon.getPonudjac().setLogovaoSe(false);
		Ponudjac ponudjac = restoranServirs.save(restPon.getPonudjac());
		restoranServirs.dodajPonudjacaURestoran(restPon.getRestoran(), ponudjac);

	}

	@RequestMapping(value = "/izlistajSmeneKuvara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmenaUDanu>> izlistajSmeneKuvara(@RequestBody SmenaUDanu smenaUDanu) {
		
		List<SmenaUDanu> smene = restoranServirs.izlistajSmeneKuvara(smenaUDanu.getRestoran(), smenaUDanu.getDanUNedelji());
		
		return new ResponseEntity<List<SmenaUDanu>>(smene, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajSmeneSankera", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmenaUDanu>> izlistajSmeneSankera(@RequestBody SmenaUDanu smenaUDanu) {
		
		List<SmenaUDanu> smene = restoranServirs.izlistajSmeneSankera(smenaUDanu.getRestoran(), smenaUDanu.getDanUNedelji());
		
		return new ResponseEntity<List<SmenaUDanu>>(smene, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajSmene", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Smena>> izlistajSmene(@RequestBody Restoran restoran) {
		
		List<Smena> smena = restoranServirs.izlistajSmene(restoran);
		
		return new ResponseEntity<List<Smena>>(smena, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodajSmenu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void dodajSmenu(@RequestBody Smena smena) {
		
		restoranServirs.dodajSmenu(smena);
		
	}
	
	@RequestMapping(value = "/dostupniKuvari", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Kuvar>> dostupniKuvari(@RequestBody SmenaUDanu smenaUDanu) {
		
		List<Kuvar> smene = restoranServirs.izlistajDostupneKuvare(smenaUDanu.getRestoran(), smenaUDanu.getDanUNedelji());
		
		return new ResponseEntity<List<Kuvar>>(smene, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodajKuvaraUSmenuDana", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void dodajKuvaraUSmenuDana(@RequestBody SmenaUDanu smenaUDanu) {
		
		restoranServirs.dodajKuvaraUSmenuDana(smenaUDanu);
		
	}
	
	@RequestMapping(value = "/dostupniSankeri", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sanker>> dostupniSankeri(@RequestBody SmenaUDanu smenaUDanu) {
		
		List<Sanker> smene = restoranServirs.izlistajDostupneSankere(smenaUDanu.getRestoran(), smenaUDanu.getDanUNedelji());
		
		return new ResponseEntity<List<Sanker>>(smene, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodajSankeraUSmenuDana", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void dodajSankeraUSmenuDana(@RequestBody SmenaUDanu smenaUDanu) {
		
		restoranServirs.dodajSankeraUSmenuDana(smenaUDanu);
		
	}
	
	@RequestMapping(value = "/izlistajSmeneKonobar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmenaUDanu>> izlistajSmeneKonobar(@RequestBody SmenaUDanu smenaUDanu) {
		List<SmenaUDanu> smene = null;

		while(smenaUDanu.getSto().iterator().hasNext()){
			Sto sto = smenaUDanu.getSto().iterator().next();
			if(sto != null){
				smene = restoranServirs.izlistajSmeneKonobara(smenaUDanu.getRestoran(), smenaUDanu.getDanUNedelji(), sto);
				break;
			}
		}
		
		return new ResponseEntity<List<SmenaUDanu>>(smene, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajDostupneKonobare", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Konobar>> izlistajDostupneKonobare(@RequestBody SmenaUDanu smenaUDanu) {
		List<Konobar> konobari = null;
		
		while(smenaUDanu.getSto().iterator().hasNext()){
			Sto sto = smenaUDanu.getSto().iterator().next();
			if(sto != null){
				konobari = restoranServirs.izlistajDostupneKonobare(smenaUDanu.getRestoran(), smenaUDanu.getDanUNedelji(), sto, smenaUDanu.getSmena());
				break;
			}
		}

		
		return new ResponseEntity<List<Konobar>>(konobari, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajDostupneSmeneKonobarDan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izlistajDostupneSmeneKonobarDan(@RequestBody SmenaUDanu smenaUDanu) {
		List<Smena> smene = null;
		Poruka poruka = new Poruka();
		List<Object> listaObjekata = new ArrayList<>();
		while(smenaUDanu.getSto().iterator().hasNext()){
			Sto sto = smenaUDanu.getSto().iterator().next();
			if(sto != null){
				listaObjekata.add(sto.getOznaka());
				if(restoranServirs.izlistajSto(sto).getSegment().equals("nijesto") || restoranServirs.izlistajSto(sto).getSegment() == null){
					poruka.setMessage("Nije sto");
				}else{
					smene = restoranServirs.izlistajDostupneSmeneKonobarDan(smenaUDanu.getRestoran(), smenaUDanu.getDanUNedelji(), sto);
					if(smene == null || smene.isEmpty()){
						poruka.setMessage("Popunjane smene");
					}else{
						poruka.setMessage("Nisu popunjene smene");
					}
				}
				
				break;
			}
		}
		listaObjekata.add(smene);
		
		poruka.setObj(listaObjekata);
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/dodajKonobaraStoSmenaDan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void dodajKonobaraStoSmenaDan(@RequestBody SmenaUDanu smenaUDanu) {
		
		while(smenaUDanu.getSto().iterator().hasNext()){
			Sto sto = smenaUDanu.getSto().iterator().next();
			if(sto != null){
				restoranServirs.dodajKonobaraStoSmenaDan(smenaUDanu.getRestoran(), smenaUDanu.getDanUNedelji(), smenaUDanu.getKonobar(), smenaUDanu.getSmena(), sto);
				return;
			}
		}
	}
	
	@RequestMapping(value = "/izmeniSto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izmeniSto(@RequestBody Sto sto) {
		Poruka poruka = new Poruka();
		Sto retSto = restoranServirs.izmeniSto(sto);
		
		if(retSto == null){
			poruka.setMessage("false");
		}else{
			poruka.setMessage(retSto.getSegment());
			poruka.setObj(retSto);
		}
		
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
	
	
	// izvestaji
	@RequestMapping(value = "/izvestajZaJelo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izvestajZaJelo(@RequestBody IzvestajJelo izvestajJelo) {
		Poruka poruka = new Poruka();
		
		IzvestajJelo izv = restoranServirs.izlistajIzvestajZaJelo(izvestajJelo);
		
		poruka.setObj(izv);
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izvestajZaRestoran", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izvestajZaRestoran(@RequestBody IzvestajRestoran izvestajRestoran) {
		Poruka poruka = new Poruka();
		
		double ocena = restoranServirs.izlistajOcenuRestorana(izvestajRestoran);
		
		poruka.setObj(ocena);
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izvestajPrihodaRestorana", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izvestajPrihodaRestorana(@RequestBody IzvestajRestoran izvestajRestoran) {
		Poruka poruka = new Poruka();
		
		double prihod = restoranServirs.izlistajPrihodRestorana(izvestajRestoran);
		
		poruka.setObj(prihod);
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
	
	// porudzbine
	@RequestMapping(value = "/izlistajAktivnePorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izlistajAktivnePorudzbine(@RequestBody MenadzerRestorana menRest) {
		Poruka poruka = new Poruka();
		
		List<PorudzbinaMenadzer> porudzbine = menadzerRestoranaServis.izlistajPorudzbineMenadzera(menRest);
		
		poruka.setObj(porudzbine);
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prikaziPonud", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> prikaziPonud(@RequestBody PorudzbinaMenadzer porudzbinaMenadzer) {
		Poruka poruka = new Poruka();
		
		List<Ponuda> ponude = menadzerRestoranaServis.izlistajPonude(porudzbinaMenadzer);
		
		poruka.setObj(ponude);
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prihvatiPonudu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> prihvatiPonudu(@RequestBody Ponuda ponuda) {
		Poruka poruka = new Poruka();
		
		PorudzbinaMenadzer porudzb = menadzerRestoranaServis.izlistajPorudzbinu(ponuda.getPorudzbinamenadzer().getId());
		List<Ponuda> ponude = menadzerRestoranaServis.izlistajPonude(porudzb);
		for(Ponuda p : ponude){
			if(p.getId().equals(ponuda.getId())){
				String email = p.getPonudjac().getEmail();
				// email umesto mog emaila
				SendMail sm = new SendMail("acasm94@gmail.com", "Vasa ponuda, sa oznakom" + p.getId() + ", restoranu " + ponuda.getPorudzbinamenadzer().getMenadzerrestorana().getRestoran().getNaziv() + 
						", je prihvacena!");
			}else{
				String email = p.getPonudjac().getEmail();
				// email umesto mog emaila
				SendMail sm = new SendMail("acasm94@gmail.com", "Zao name je, Vasa ponuda, sa oznakom" + p.getId() + ", restoranu " + ponuda.getPorudzbinamenadzer().getMenadzerrestorana().getRestoran().getNaziv() + 
						", nije prihvacena!");
			}
		}
		
		List<PorudzbinaMenadzer> preostalePorudzbine = menadzerRestoranaServis.prihvatiPonudu(ponuda);
		
		poruka.setObj(preostalePorudzbine);
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
	
	// Reg. radnika
	@RequestMapping(value = "/registrujKonobara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> registrujKonobara(@RequestBody Konobar konobar) {
		Poruka p = new Poruka();
		konobar.setLogovaoSe(false);
		Konobar k = restoranServirs.registrujKonobara(konobar);
		
		p.setObj(k);
		return new ResponseEntity<Poruka>(p, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/registrujKuvara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> registrujKuvara(@RequestBody Kuvar kuvar) {
		Poruka p = new Poruka();
		kuvar.setLogovaoSe(false);
		Kuvar k = restoranServirs.registrujKuvara(kuvar);
		
		p.setObj(k);
		return new ResponseEntity<Poruka>(p, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/registrujSankera", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> registrujSankera(@RequestBody Sanker sanker) {
		Poruka p = new Poruka();
		sanker.setLogovaoSe(false);
		Sanker s = restoranServirs.registrujeSankera(sanker);
		
		p.setObj(s);	
		return new ResponseEntity<Poruka>(p, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/izlistajBojuStola", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izlistajBojuStola(@RequestBody Sto sto) {
		Poruka poruka = new Poruka();
		Sto sto2 = restoranServirs.izlistajSto(sto);
		
		if(sto2.getSegment().equals("nijesto") || sto2.getSegment() == null){
			poruka.setMessage("nijesto");
		}else if(sto2.getSegment().equals("pusacki")){
			poruka.setMessage("pusacki");
		}else if(sto2.getSegment().equals("nepusacki")){
			poruka.setMessage("nepusacki");
		}else if(sto2.getSegment().equals("unutrasnji")){
			poruka.setMessage("unutrasnji");
		}else if(sto2.getSegment().equals("bastaOtv")){
			poruka.setMessage("bastaOtv");
		}else if(sto2.getSegment().equals("bastaZat")){
			poruka.setMessage("bastaZat");
		}
		
		poruka.setObj(sto.getOznaka());
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/izlistajBojuStola1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izlistajBojuStola1(@RequestBody Sto sto) {
		Poruka poruka = new Poruka();
		Sto sto2 = restoranServirs.izlistajSto(sto);
		
		if(sto2.getSegment().equals("nijesto") || sto2.getSegment() == null){
			poruka.setMessage("nijesto");
		}else {
			poruka.setMessage("nepusacki");
		}
		
		poruka.setObj(sto.getOznaka());
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}

	@RequestMapping(value = "/izvestajZaKonobara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izvestajZaKonobara(@RequestBody IzvestajKonobar izvestajKonobar) {
		Poruka poruka = new Poruka();
		
		double ocena = restoranServirs.izlistajOcenuKonobara(izvestajKonobar);
		
		poruka.setObj(ocena);
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izvestajPrihodaKonobara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> izvestajPrihodaKonobara(@RequestBody IzvestajKonobar izvestajKonobar) {
		Poruka poruka = new Poruka();
		
		double prihod = restoranServirs.izlistajPrihodKonobara(izvestajKonobar);
		
		poruka.setObj(prihod);
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prikaziGrafikPosecenosti", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> prikaziGrafikPosecenosti(@RequestBody PosecenostIzvestaj posecenostIzvestaj) {
		Poruka poruka = new Poruka();
		List<Object> listaObjekata = new ArrayList<>();
		ArrayList<Double> procenti = new ArrayList<>();
		
		if(posecenostIzvestaj.getNivo().equals("dnevni")){
			procenti = restoranServirs.izracunaPosecenostDnevno(posecenostIzvestaj);
		}else{
			procenti = restoranServirs.izracunaPosecenostNedelja(posecenostIzvestaj);
		}
		//double prihod = restoranServirs.izlistajPrihodKonobara(izvestajKonobar);
		
		for(Double p : procenti){
			listaObjekata.add(p);
		}
		
		poruka.setObj(listaObjekata);
		return new ResponseEntity<Poruka>(poruka, HttpStatus.OK);
	}
}
