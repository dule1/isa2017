package com.isa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Ponuda;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.korisnici.MenadzerRestorana;

public interface PorudzbineMenadzeraSkladiste extends JpaRepository<PorudzbinaMenadzer, Long> {

	List<PorudzbinaMenadzer> findByMenadzerrestorana(MenadzerRestorana menadzerRestorana);
	PorudzbinaMenadzer save(PorudzbinaMenadzer porudzbinaMenadzer);
	List<PorudzbinaMenadzer> findByMenadzerrestoranaAndAktivnaTrueAndAktivnoDoAfter(MenadzerRestorana menadzerRestorana, Date date);
	
	PorudzbinaMenadzer findByPonude(Ponuda ponuda);
}