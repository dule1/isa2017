package com.isa.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.Jelo;
import com.isa.model.JeloUPorudzbini;
import com.isa.model.Porudzbina;

public interface JeloUPorudzbiniSkladiste extends JpaRepository<JeloUPorudzbini, Serializable> {
	
	JeloUPorudzbini save(JeloUPorudzbini jeloUPorudzbini);

	Page<JeloUPorudzbini> findByPorudzbina(Porudzbina porudzbina,Pageable pageable);
	
	@Transactional(readOnly = true)
	void deleteById(Long id);

	Page<JeloUPorudzbini> findByPorudzbinaAndJelo(Porudzbina porudzbina, Jelo jelo, Pageable pageable);

	JeloUPorudzbini findByPorudzbinaAndJelo(Porudzbina porudz, Jelo jelo);
}
