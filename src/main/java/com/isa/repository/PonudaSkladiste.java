package com.isa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Ponuda;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.korisnici.Ponudjac;

public interface PonudaSkladiste extends JpaRepository<Ponuda, Long> {

	Page<Ponuda> findByPonudjac(Ponudjac ponudjac, Pageable pageable);
	List<Ponuda> findByPonudjac(Ponudjac ponudjac);
	Ponuda save(Ponuda ponuda);
	List<Ponuda> findByPorudzbinamenadzer(PorudzbinaMenadzer porudzbinaMenadzer);
	
}