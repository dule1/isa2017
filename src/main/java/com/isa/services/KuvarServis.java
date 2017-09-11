package com.isa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.isa.model.JeloUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Kuvar;

public interface KuvarServis {

	List<Kuvar> findAll();

    Korisnik findOne(Long id);

    Kuvar save(Kuvar kuvar);

    Korisnik delete(Long id);

    Korisnik findByEmail(String email);

	Page<JeloUPorudzbini> izlistajJelaPorudzbine(Porudzbina porudzbina,
			Pageable pageable);

	void sacuvajJeloUPorudzbini(JeloUPorudzbini jeloUPorudzbini);

	Restoran izlistajRestoran(Kuvar kuvar);
}
