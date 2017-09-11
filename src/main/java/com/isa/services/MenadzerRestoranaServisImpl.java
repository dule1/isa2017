package com.isa.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.model.Namirnica;
import com.isa.model.Pice;
import com.isa.model.Ponuda;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.Restoran;
import com.isa.model.StavkaPorudzbineMenadzera;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.repository.MenadzerRestoranaSkladiste;
import com.isa.repository.NamirnicaSkladiste;
import com.isa.repository.PiceSkladiste;
import com.isa.repository.PonudaSkladiste;
import com.isa.repository.PorudzbineMenadzeraSkladiste;
import com.isa.repository.RestoranSkladiste;
import com.isa.repository.StavkaPorudzbineMenSkladiste;

@Service
public class MenadzerRestoranaServisImpl implements MenadzerRestoranaServis{

	@Autowired
	private MenadzerRestoranaSkladiste menadzerRestoranaSkladiste;
	
	@Autowired
	private PorudzbineMenadzeraSkladiste porudzbinaMenadzeraSkladiste;
	
	@Autowired
	private RestoranSkladiste restoranSkladiste;
	
	@Autowired
	private NamirnicaSkladiste namirnicaSkladiste;
	
	@Autowired
	private PiceSkladiste piceSkladiste;
	
	@Autowired
	private StavkaPorudzbineMenSkladiste stavkaPorudzbineMenSkladiste;
	
	@Autowired
	private PonudaSkladiste ponudaSkladiste;
	
	@Override
	public List<MenadzerRestorana> findAll() {
		return menadzerRestoranaSkladiste.findAll();
	}

	@Override
	public MenadzerRestorana findOne(Long id) {
		return menadzerRestoranaSkladiste.findOne(id);
	}

	@Override
	public MenadzerRestorana save(MenadzerRestorana menadzerRestorana) {
		return menadzerRestoranaSkladiste.save(menadzerRestorana);
	}

	@Override
	public void delete(Long id) {
		menadzerRestoranaSkladiste.delete(id);	
	}

	@Override
	public Restoran izlistajRestoran(MenadzerRestorana menadzerRestorana) {
		return restoranSkladiste.findByMenadzerrestorana(menadzerRestorana);
	}

	@Override
	public List<Namirnica> izlistajSveNamirnice() {
		return namirnicaSkladiste.findAll();
	}

	@Override
	public List<Pice> izlistajSvaPica() {
		return piceSkladiste.findAll();
	}

	@Override
	public void dodajPorudzbinu(PorudzbinaMenadzer porudzbinaMenadzer, List<StavkaPorudzbineMenadzera> stavke) {
		PorudzbinaMenadzer pm = porudzbinaMenadzeraSkladiste.save(porudzbinaMenadzer);
		
		for(StavkaPorudzbineMenadzera s : stavke){
			StavkaPorudzbineMenadzera tempStavka = new StavkaPorudzbineMenadzera();
			if(s.getNamirnica() != null){
				List<Namirnica> namirnica = namirnicaSkladiste.findByNaziv(s.getNamirnica().getNaziv());
				if(namirnica != null && !namirnica.isEmpty()){
					tempStavka.setNamirnica(namirnica.get(0));
				}else{
					Namirnica nam = namirnicaSkladiste.save(s.getNamirnica());
					tempStavka.setNamirnica(nam);
				}
			}else if(s.getPice() != null){
				List<Pice> pica = piceSkladiste.findByNaziv(s.getPice().getNaziv());
				if(pica != null && !pica.isEmpty() && pica.size() != 0){
					tempStavka.setPice(pica.get(0));
				}else{
					Pice p = piceSkladiste.save(s.getPice());
					tempStavka.setPice(p);
				}
			}
			tempStavka.setKolicina(s.getKolicina());
			tempStavka.setPorudzbinamenadzer(pm);
			stavkaPorudzbineMenSkladiste.save(tempStavka);
		}
	}

	@Override
	public List<PorudzbinaMenadzer> izlistajPorudzbineMenadzera(MenadzerRestorana menRest) {
		return porudzbinaMenadzeraSkladiste.findByMenadzerrestoranaAndAktivnaTrueAndAktivnoDoAfter(menRest, Calendar.getInstance().getTime());
	}

	@Override
	public List<Ponuda> izlistajPonude(PorudzbinaMenadzer porudzbinaMenadzer) {
		return ponudaSkladiste.findByPorudzbinamenadzer(porudzbinaMenadzer);
	}

	@Override
	public List<PorudzbinaMenadzer> prihvatiPonudu(Ponuda ponuda) {
		PorudzbinaMenadzer porMen = porudzbinaMenadzeraSkladiste.findOne(ponuda.getPorudzbinamenadzer().getId());
		porMen.setAktivna(false);
		porudzbinaMenadzeraSkladiste.save(porMen);
		
		MenadzerRestorana menRes = menadzerRestoranaSkladiste.findOne(porMen.getMenadzerrestorana().getId());
		
		return porudzbinaMenadzeraSkladiste.findByMenadzerrestoranaAndAktivnaTrueAndAktivnoDoAfter(menRes, Calendar.getInstance().getTime());
	}

	@Override
	public PorudzbinaMenadzer izlistajPorudzbinu(Long id) {
		return porudzbinaMenadzeraSkladiste.findOne(id);
	}

}
