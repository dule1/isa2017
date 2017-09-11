package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.DanUNedelji;
import com.isa.model.Jelo;
import com.isa.model.JeloUPorudzbini;
import com.isa.model.Pice;
import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.PosetaRestoranu;
import com.isa.model.RacunKonobar;
import com.isa.model.Restoran;
import com.isa.model.SmenaUDanu;
import com.isa.model.Sto;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Korisnik;
import com.isa.repository.JeloSkladiste;
import com.isa.repository.JeloUPorudzbiniSkladiste;
import com.isa.repository.KonobarSkladiste;
import com.isa.repository.PiceSkladiste;
import com.isa.repository.PiceUPorudzbiniSkladiste;
import com.isa.repository.PorudzbinaSkladiste;
import com.isa.repository.PoseteSkladiste;
import com.isa.repository.RacunSkladiste;
import com.isa.repository.RestoranSkladiste;
import com.isa.repository.SmeneUDanuSkladiste;

@Service
public class KonobarServisImpl implements KonobarServis {

	@Autowired
	private KonobarSkladiste konobarSkladiste;
	@Autowired
	private RestoranSkladiste restoranSkladiste;
	@Autowired
	private PorudzbinaSkladiste porudzbinaSkladiste;
	@Autowired
	private JeloSkladiste jeloSkladiste;
	@Autowired
	private PiceSkladiste piceSkladiste;
	@Autowired
	private PiceUPorudzbiniSkladiste piceUPorudzbiniSkladiste;
	@Autowired
	private JeloUPorudzbiniSkladiste jeloUPorudzbiniSkladiste;
	@Autowired
	private RacunSkladiste racunSkladiste;
	@Autowired
	private PoseteSkladiste poseteSkladiste;
	@Autowired
	private SmeneUDanuSkladiste smeneUDanuSkladiste;

	@Override
	public Korisnik findOne(Long id) {
		return konobarSkladiste.findOne(id);
	}

	@Override
	public Konobar save(Konobar kuvar) {
		return konobarSkladiste.save(kuvar);
	}

	@Override
	public Korisnik delete(Long id) {
		Korisnik gost = konobarSkladiste.findOne(id);
		if (gost == null) {
			return null;
		} else {
			konobarSkladiste.delete((Konobar) gost);
			return gost;
		}
	}

	@Override
	public Korisnik findByEmail(String email) {
		try {
			return konobarSkladiste.findByEmail(email).get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Restoran izlistajRestoran(Konobar konobar) {
		return restoranSkladiste.findByKonobar(konobar);
	}

	@Override
	public void savePorudzbina(Porudzbina porudzbina) {
		porudzbinaSkladiste.save(porudzbina);
	}

	@Override
	public Jelo pronadjiJelo(Long i) {
		return jeloSkladiste.findById(i);
	}

	@Override
	public Pice pronadjiPice(Long i) {
		return piceSkladiste.findById(i);
	}

	@Override
	public void saveJeloUPorudzbini(JeloUPorudzbini jeloUPorudzbini) {
		jeloUPorudzbiniSkladiste.save(jeloUPorudzbini);
	}

	@Override
	public void savePiceUPorudzbini(PiceUPorudzbini piceUPorudzbini) {
		piceUPorudzbiniSkladiste.save(piceUPorudzbini);
	}

	@Override
	public Porudzbina pronadjiPorudzbinu(Long id) {
		return porudzbinaSkladiste.findById(id);
	}

	@Override
	public Page<Porudzbina> izlistajPorudzbine(Konobar konobar,
			Pageable pageable) {
		return porudzbinaSkladiste.findByKonobar(konobar, pageable);
	}

	@Override
	public Page<JeloUPorudzbini> izlistajJelaPorudzbine(Porudzbina porudzbina,
			Pageable pageable) {
		return jeloUPorudzbiniSkladiste.findByPorudzbina(porudzbina, pageable);
	}

	@Override
	public Page<PiceUPorudzbini> izlistajPicaPorudzbine(Porudzbina porudzbina,
			Pageable pageable) {
		return piceUPorudzbiniSkladiste.findByPorudzbina(porudzbina, pageable);
	}

	@Override
	public Page<JeloUPorudzbini> izlistajJelaPorudzbineIJela(
			Porudzbina porudzbina, Jelo jelo, Pageable pageable) {
		return jeloUPorudzbiniSkladiste.findByPorudzbinaAndJelo(porudzbina,
				jelo, pageable);
	}

	@Override
	public Page<PiceUPorudzbini> izlistajPicaPorudzbineIPica(
			Porudzbina porudzbina, Pice pice, Pageable pageable) {
		return piceUPorudzbiniSkladiste.findByPorudzbinaAndPice(porudzbina,
				pice, pageable);
	}

	@Override
	public void saveRacun(RacunKonobar racun) {
		racunSkladiste.save(racun);
	}

	@Override
	public List<Konobar> findAll() {
		return konobarSkladiste.findAll();
	}

	@Override
	public List<PosetaRestoranu> izlistajPosetePoStolu(Sto sto) {
		return poseteSkladiste.findBySto(sto);
	}

	@Override
	public SmenaUDanu izlistajSmenuUDanu(Konobar konobar, DanUNedelji dan) {
		Restoran res = restoranSkladiste.findOne(konobar.getRestoran().getId());
		return smeneUDanuSkladiste.findByRestoranAndDanUNedeljiAndKonobar(res,
				dan, konobar);
	}

	@Override
	public List<Porudzbina> izlistajPorudzbineStola(Sto sto) {
		return porudzbinaSkladiste.findBySto(sto);
	}

}
