package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Sanker;
import com.isa.repository.PiceUPorudzbiniSkladiste;
import com.isa.repository.PorudzbinaSkladiste;
import com.isa.repository.RestoranSkladiste;
import com.isa.repository.SankerSkladiste;

@Service
public class SankerServisImpl implements SankerServis {
	

	@Autowired
	private SankerSkladiste sankerSkladiste;
	
	@Autowired
	private PorudzbinaSkladiste porudzbineSkladiste;

	@Autowired
	private PiceUPorudzbiniSkladiste piceUPorudzbiniSkladiste;
	
	@Autowired
	private RestoranSkladiste restoranSkladiste;
	
	
	
	@Override
	public List<Sanker> findAll() {
		return sankerSkladiste.findAll();
	}

	@Override
	public Korisnik findOne(Long id) {
		return sankerSkladiste.findOne(id);
	}

	@Override
	public Sanker save(Sanker kuvar) {
		return sankerSkladiste.save(kuvar);
	}

	@Override
	public Korisnik delete(Long id) {
		Korisnik gost = sankerSkladiste.findOne(id);
		if(gost == null){
			return null;
		}else{
			sankerSkladiste.delete((Sanker)gost);
			return gost;
		}
	}

	@Override
	public Korisnik findByEmail(String email) {
		try{
			return sankerSkladiste.findByEmail(email).get(0);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public Page<Porudzbina> izlistajPorudzbine(Restoran restoran,
			Pageable pageable) {
		return porudzbineSkladiste.findByRestoran(restoran, pageable);
	}

	@Override
	public Page<PiceUPorudzbini> izlistajPicaPorudzbine(Porudzbina porudzbina,
			Pageable pageable) {
		return piceUPorudzbiniSkladiste.findByPorudzbina(porudzbina, pageable);
	}

	@Override
	public Restoran izlistajRestoran(Sanker sanker) {
		return restoranSkladiste.findBySanker(sanker);
	}


}
