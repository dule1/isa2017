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

import com.isa.model.JeloUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.SmenaUDanu;
import com.isa.model.korisnici.Kuvar;
import com.isa.pomocni.MogucePrihvacene;
import com.isa.pomocni.PorudzbinaKuvar;
import com.isa.pomocni.SendMail;
import com.isa.services.KonobarServis;
import com.isa.services.KuvarServis;
import com.isa.services.RestoranServis;
import com.isa.services.SankerServis;

@Controller
@RequestMapping("/kuvarKontroler")
public class KuvarKontroler {

	@Autowired
	public KuvarServis kuvarServis;
	@Autowired
	public SankerServis sankerServis;
	@Autowired
	public KonobarServis konobarServis;
	@Autowired
	public RestoranServis restoranServis;
	
	@RequestMapping(value = "/ucitajPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> ucitajPorudzbine(@RequestBody Kuvar kuvar){
		Restoran restoran = kuvar.getRestoran();
		
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		
		
		return new ResponseEntity<List<Porudzbina>> (izbaciNeprihvacene(porudzbine.getContent()), HttpStatus.OK);
	}
	

	
	@RequestMapping(value = "/ucitajPorudzbineKlasifikovane", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MogucePrihvacene> ucitajPorudzbineKlasifikovane(@RequestBody Kuvar kuvar){
		Restoran restoran = kuvar.getRestoran();
		// Uzeo sam ovde sankera jer je vec implementirano
		
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		
		
		return new ResponseEntity<MogucePrihvacene> (vratiPorudzbine(porudzbine, kuvar), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajJelaPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JeloUPorudzbini>> ucitajJelaPorudzbine(@RequestBody PorudzbinaKuvar porudzbinaKuvar){
		Page<JeloUPorudzbini> jelaUPorudzbini = kuvarServis.izlistajJelaPorudzbine(porudzbinaKuvar.getPorudzbina(), new PageRequest(0, 10));
		
		List<JeloUPorudzbini> retVal = new ArrayList<JeloUPorudzbini>();
		
		List<JeloUPorudzbini> jelaLista = jelaUPorudzbini.getContent();
		for (int i = 0; i < jelaLista.size(); i++){
			if(jelaLista.get(i).getJelo().getTipKuvara().equals(porudzbinaKuvar.getKuvar().getTipKuvara())){
				retVal.add(jelaLista.get(i));
			}
		}
		
		
		return new ResponseEntity<List<JeloUPorudzbini>> (retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prihvatiPorudzbinu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MogucePrihvacene> prihvatiPorudzbinu(@RequestBody PorudzbinaKuvar poSa){

		Porudzbina porudzbina = konobarServis.pronadjiPorudzbinu(poSa.getPorudzbina().getId());
		Page<JeloUPorudzbini> jelaUPorudzbini = kuvarServis.izlistajJelaPorudzbine(poSa.getPorudzbina(), new PageRequest(0, 10));
		
		List<JeloUPorudzbini> listaNjegovih = new ArrayList<JeloUPorudzbini>();
		
		List<JeloUPorudzbini> jelaLista = jelaUPorudzbini.getContent();
		for (int i = 0; i < jelaLista.size(); i++){
			if(jelaLista.get(i).getJelo().getTipKuvara().equals(poSa.getKuvar().getTipKuvara())){
				listaNjegovih.add(jelaLista.get(i));
			}
		}

		for (int i = 0; i<listaNjegovih.size(); i++){
			if (listaNjegovih.get(i).getKuvar() != null){
				return new ResponseEntity<MogucePrihvacene> (HttpStatus.NOT_MODIFIED);
			} else {
				listaNjegovih.get(i).setKuvar(poSa.getKuvar());
				kuvarServis.sacuvajJeloUPorudzbini(listaNjegovih.get(i));
			}
			
		}
		Restoran restoran = poSa.getKuvar().getRestoran();
		
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		return new ResponseEntity<MogucePrihvacene> (vratiPorudzbine(porudzbine, poSa.getKuvar()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajKuvareRestorana", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Kuvar>> ucitajKonobareRestorana(@RequestBody Kuvar kuvar){
		Restoran restoran = kuvarServis.izlistajRestoran(kuvar);
		List<Kuvar> retVal = restoranServis.izlistajKuvare(restoran);
		return new ResponseEntity<List<Kuvar>>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajKalendarKuvara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmenaUDanu>> ucitajKalendarKuvara(@RequestBody Kuvar parametar){
		Kuvar kuvar = (Kuvar) kuvarServis.findOne(parametar.getId());
		List<SmenaUDanu> retVal = restoranServis.izlistajSmenePoDanimaKuvara(kuvar);
		return new ResponseEntity<List<SmenaUDanu>>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zavrsiPorudzbinu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MogucePrihvacene> zavrsiPorudzbinu(@RequestBody PorudzbinaKuvar poKu){

		Porudzbina porudzbina = konobarServis.pronadjiPorudzbinu(poKu.getPorudzbina().getId());
		Page<JeloUPorudzbini> jelaUPorudzbini = kuvarServis.izlistajJelaPorudzbine(porudzbina, new PageRequest(0, 10));
		List<JeloUPorudzbini> listaNjegovih = new ArrayList<JeloUPorudzbini>();
		
		List<JeloUPorudzbini> jelaLista = jelaUPorudzbini.getContent();
		for (int i = 0; i < jelaLista.size(); i++){
			if(jelaLista.get(i).getJelo().getTipKuvara().equals(poKu.getKuvar().getTipKuvara())){
				listaNjegovih.add(jelaLista.get(i));
			}
		}
		for (int i = 0; i<listaNjegovih.size(); i++){
			listaNjegovih.get(i).setSpremno(true);
			porudzbina.setSpremnoJednoJelo(true);
			kuvarServis.sacuvajJeloUPorudzbini(listaNjegovih.get(i));			
		}
		
		porudzbina.setSpremnaJela(jelaSpremna(porudzbina));
		if(porudzbina.isSpremnaJela() && porudzbina.isSpremnaPica()){
			SendMail sm = new SendMail(porudzbina.getKonobar().getEmail(), "Porudzbina " + porudzbina.getId() + " je spremna");	
		}
		konobarServis.savePorudzbina(porudzbina);
		
		Restoran restoran = poKu.getKuvar().getRestoran();
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));

		return new ResponseEntity<MogucePrihvacene> (vratiPorudzbine(porudzbine, poKu.getKuvar()), HttpStatus.OK);
	}
	
	private boolean jelaSpremna (Porudzbina porudzbina){
		Page<JeloUPorudzbini> jela = kuvarServis.izlistajJelaPorudzbine(porudzbina, new PageRequest(0, 10));
		List<JeloUPorudzbini> jelaLista = jela.getContent();
		for(int i = 0; i < jelaLista.size(); i++){
			if (jelaLista.get(i).isSpremno() == false){
				return false;
			}
		}
		
		return true;
	}
	
	private MogucePrihvacene vratiPorudzbine (Page<Porudzbina> porudzbine, Kuvar kuvar){
		List<Porudzbina> listaSvihPorudzbina = porudzbine.getContent();
		ArrayList<Porudzbina> listaPrihvacenihPorudzbina = new ArrayList<Porudzbina>();
		ArrayList<Porudzbina> listaMogucihPorudzbina = new ArrayList<Porudzbina>();
		
		for(int i = 0; i < listaSvihPorudzbina.size(); i++){
			List<JeloUPorudzbini> jelaUPorudzbini = konobarServis.izlistajJelaPorudzbine(listaSvihPorudzbina.get(i),new PageRequest(0, 10)).getContent();
			if(!jelaUPorudzbini.isEmpty()){
				boolean imaZaOvog = false;
				if(!listaSvihPorudzbina.get(i).isSpremnaJela()){
					for (int j = 0; j < jelaUPorudzbini.size(); j++){
						if(jelaUPorudzbini.get(j).getJelo().getTipKuvara().equals(kuvar.getTipKuvara())){
							if(!jelaUPorudzbini.get(j).isSpremno()){
								if(jelaUPorudzbini.get(j).getKuvar() != null){
									if(jelaUPorudzbini.get(j).getKuvar().getId() == kuvar.getId()){
										imaZaOvog = true;
									}
								}
							}
						}
					}
					if (imaZaOvog){
						if(!listaSvihPorudzbina.get(i).isSpremnaJela()){
							listaPrihvacenihPorudzbina.add(listaSvihPorudzbina.get(i));
						}
					}
				}
			}
		}
		
		for(int i = 0; i < listaSvihPorudzbina.size(); i++){
			List<JeloUPorudzbini> jelaUPorudzbini = konobarServis.izlistajJelaPorudzbine(listaSvihPorudzbina.get(i),new PageRequest(0, 10)).getContent();
			if(!jelaUPorudzbini.isEmpty()){
				boolean imaZaOvog = false;
				for (int j = 0; j < jelaUPorudzbini.size(); j++){
					if(jelaUPorudzbini.get(j).getJelo().getTipKuvara().equals(kuvar.getTipKuvara())){
						if(jelaUPorudzbini.get(j).getKuvar() == null){
							imaZaOvog = true;
						}
					}
				}
				if (imaZaOvog){
					if(!listaSvihPorudzbina.get(i).isSpremnaJela()){
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
	
	

}
