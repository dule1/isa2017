package com.isa.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.Ponuda;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.Restoran;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.model.korisnici.Ponudjac;
import com.isa.repository.MenadzerRestoranaSkladiste;
import com.isa.repository.PonudaSkladiste;
import com.isa.repository.PonudjacSkladiste;
import com.isa.repository.PorudzbineMenadzeraSkladiste;
import com.isa.repository.RestoranSkladiste;

@Service
public class PonudjacServisImpl implements PonudjacServis{

	@Autowired
	private PonudjacSkladiste ponudjacSkladiste;

	@Autowired
	private PonudaSkladiste ponudaSkladiste;
	
	@Autowired
	private RestoranSkladiste restoranSkladiste;
	
	@Autowired
	private MenadzerRestoranaSkladiste menRestSkladiste;
	
	@Autowired
	private PorudzbineMenadzeraSkladiste porudzMenSkladiste;
	
	@Override
	public List<Ponudjac> findAll() {
		return ponudjacSkladiste.findAll(); 
	}

	@Override
	public Ponudjac findOne(Long id) {
		return ponudjacSkladiste.findOne(id);
	}

	@Override
	public Ponudjac save(Ponudjac ponudjac) {
		return ponudjacSkladiste.save(ponudjac);
	}

	@Override
	public void delete(Long id) {
		ponudjacSkladiste.delete(id);
	}

	@Override
	public Page<Ponuda> izlistajPonude(Ponudjac ponudjac, Pageable pageable) {
		return ponudaSkladiste.findByPonudjac(ponudjac, pageable);
	}

	@Override
	public List<PorudzbinaMenadzer> izlistajPorudzbineBezPonude(Ponudjac ponudjac) {
		List<Restoran> restoraniPonudjaca = restoranSkladiste.findByPonudjac(ponudjac);
		for(Restoran restoran : restoraniPonudjaca){
			List<MenadzerRestorana> menadzeriRestorana = menRestSkladiste.findByRestoran(restoran);
			for(MenadzerRestorana menadzerRestorana : menadzeriRestorana){
				List<PorudzbinaMenadzer> porudzbineMenadzera = porudzMenSkladiste.findByMenadzerrestoranaAndAktivnaTrueAndAktivnoDoAfter(menadzerRestorana, Calendar.getInstance().getTime());
				List<Ponuda> ponude = ponudaSkladiste.findByPonudjac(ponudjac);
				for(Ponuda ponuda : ponude){
					if(porudzbineMenadzera.contains(ponuda.getPorudzbinamenadzer())){
						porudzbineMenadzera.remove(ponuda.getPorudzbinamenadzer());
					}
				}
				return porudzbineMenadzera;
			}
		}
		return null;
	}

	@Override
	public void dodajPonudu(Ponuda ponuda) {
		ponudaSkladiste.save(ponuda);
	}

	@Override
	public boolean izmenaPonude(Ponuda ponuda) {
		Ponuda pon = ponudaSkladiste.findOne(ponuda.getId());
		PorudzbinaMenadzer pm = porudzMenSkladiste.findByPonude(pon);
		if(!pm.getAktivna()){
			return false;
		}
		pon.setCena(ponuda.getCena());
		pon.setGarancija(ponuda.getGarancija());
		pon.setRokisporuke(ponuda.getRokisporuke());
		ponudaSkladiste.save(pon);
		return true;
	}

	@Override
	public List<Ponuda> izlistajPonudePonudjaca(Ponudjac ponudjac) {
		List<Ponuda> ponude = ponudaSkladiste.findByPonudjac(ponudjac);
		List<Ponuda> retVal = ponudaSkladiste.findByPonudjac(ponudjac);
		for(Ponuda ponuda : ponude){
			if(!ponuda.getPorudzbinamenadzer().getAktivna() || Calendar.getInstance().getTime().after(ponuda.getPorudzbinamenadzer().getAktivnoDo())){
				retVal.remove(ponuda);
			}
		}
		
		return retVal;
	}
}
