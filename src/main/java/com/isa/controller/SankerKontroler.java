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

import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.SmenaUDanu;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Sanker;
import com.isa.pomocni.MogucePrihvacene;
import com.isa.pomocni.PorudzbinaSanker;
import com.isa.pomocni.SendMail;
import com.isa.services.KonobarServis;
import com.isa.services.RestoranServis;
import com.isa.services.SankerServis;

@Controller
@RequestMapping("/sankerKontroler")
public class SankerKontroler {

	@Autowired
	public SankerServis sankerServis;
	
	@Autowired
	public KonobarServis konobarServis;
	
	@Autowired
	public RestoranServis restoranServis;

	@RequestMapping(value = "/ucitajPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> ucitajPorudzbine(@RequestBody Sanker sanker){
		Restoran restoran = sanker.getRestoran();
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));

		return new ResponseEntity<List<Porudzbina>> (porudzbine.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajPorudzbineKlasifikovane", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MogucePrihvacene> ucitajPorudzbineKlasifikovane(@RequestBody Sanker sanker){
		Restoran restoran = sanker.getRestoran();
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));

		return new ResponseEntity<MogucePrihvacene> (vratiPorudzbine(porudzbine, sanker), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/ucitajPicaPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PiceUPorudzbini>> ucitajPicaPorudzbine(@RequestBody Porudzbina porudzbina){
		Page<PiceUPorudzbini> picaUPorudzbini = sankerServis.izlistajPicaPorudzbine(porudzbina, new PageRequest(0, 10));

		return new ResponseEntity<List<PiceUPorudzbini>> (picaUPorudzbini.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prihvatiPorudzbinu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MogucePrihvacene> prihvatiPorudzbinu(@RequestBody PorudzbinaSanker poSa){

		Porudzbina porudzbina = konobarServis.pronadjiPorudzbinu(poSa.getPorudzbina().getId());
		if(porudzbina.getSanker()!= null){
			return new ResponseEntity<MogucePrihvacene> (HttpStatus.NOT_MODIFIED);
		}
		porudzbina.setSanker(poSa.getSanker());
		konobarServis.savePorudzbina(porudzbina);
		
		
		Restoran restoran = poSa.getSanker().getRestoran();
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<MogucePrihvacene> (vratiPorudzbine(porudzbine, poSa.getSanker()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zavrsiPorudzbinu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MogucePrihvacene> zavrsiPorudzbinu(@RequestBody Porudzbina porudzbina1){
		Porudzbina porudzbina = konobarServis.pronadjiPorudzbinu(porudzbina1.getId());
		porudzbina.setSpremnaPica(true);
		
		if(porudzbina.isSpremnaJela() && porudzbina.isSpremnaPica()){
			SendMail sm = new SendMail(porudzbina.getKonobar().getEmail(), "Porudzbina " + porudzbina.getId() + " je spremna");	
		}
		
		konobarServis.savePorudzbina(porudzbina);	
		Restoran restoran = porudzbina.getRestoran();
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		return new ResponseEntity<MogucePrihvacene> (vratiPorudzbine(porudzbine, porudzbina.getSanker()), HttpStatus.OK);
	}
	
	private MogucePrihvacene vratiPorudzbine (Page<Porudzbina> porudzbine, Sanker sanker){

		List<Porudzbina> listaSvihPorudzbina = porudzbine.getContent();
		ArrayList<Porudzbina> listaPrihvacenihPorudzbina = new ArrayList<Porudzbina>();
		ArrayList<Porudzbina> listaMogucihPorudzbina = new ArrayList<Porudzbina>();
		
		for(int i = 0; i < listaSvihPorudzbina.size(); i++){
			List<PiceUPorudzbini> picaUPorudzbini = konobarServis.izlistajPicaPorudzbine(listaSvihPorudzbina.get(i),new PageRequest(0, 10)).getContent();
			if (!picaUPorudzbini.isEmpty()) {
				if (listaSvihPorudzbina.get(i).getSanker() != null) {
					if (listaSvihPorudzbina.get(i).getSanker().getId() == sanker.getId()) {
						if (listaSvihPorudzbina.get(i).isSpremnaPica() == false) {
							listaPrihvacenihPorudzbina.add(listaSvihPorudzbina.get(i));
						}
					}
				}
			}
		}
		
		for (int i = 0; i < listaSvihPorudzbina.size(); i++) {
			List<PiceUPorudzbini> picaUPorudzbini = konobarServis.izlistajPicaPorudzbine(listaSvihPorudzbina.get(i),new PageRequest(0, 10)).getContent();
			if (!picaUPorudzbini.isEmpty()) {
				if (listaSvihPorudzbina.get(i).getSanker() == null) {
					if (listaSvihPorudzbina.get(i).isSpremnaPica() == false) {
						listaMogucihPorudzbina.add(listaSvihPorudzbina.get(i));
					}
				}
			}
		}
		MogucePrihvacene moPri = new MogucePrihvacene();
		moPri.setPrihvacenePorudzbine(izbaciNeprihvacene(listaPrihvacenihPorudzbina));
		moPri.setMogucePorudzbine(izbaciNeprihvacene(listaMogucihPorudzbina));
		
		return moPri;
	}
	
	private ArrayList<Porudzbina> izbaciNeprihvacene(List<Porudzbina> parametar){
		ArrayList<Porudzbina> retVal = new ArrayList<Porudzbina>();
		
		for(int i = 0; i < parametar.size(); i++){
			if(parametar.get(i).isPorudzbinaPrihvacena()){
				retVal.add(parametar.get(i));
			}
		}
		
		return retVal;
	}
	
	@RequestMapping(value = "/ucitajSankereRestorana", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sanker>> ucitajSankereRestorana(@RequestBody Sanker sanker){
		Restoran restoran = sankerServis.izlistajRestoran(sanker);
		List<Sanker> retVal = restoranServis.izlistajSankere(restoran);
		return new ResponseEntity<List<Sanker>>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajKalendarSankera", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmenaUDanu>> ucitajKalendarSankera(@RequestBody Sanker parametar){
		Sanker Sanker = (Sanker) sankerServis.findOne(parametar.getId());
		List<SmenaUDanu> retVal = restoranServis.izlistajSmenePoDanimaSankera(Sanker);
		return new ResponseEntity<List<SmenaUDanu>>(retVal, HttpStatus.OK);
	}
	
	
	
	
}