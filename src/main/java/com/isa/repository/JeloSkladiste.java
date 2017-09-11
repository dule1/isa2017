package com.isa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Jelo;
import com.isa.model.Restoran;

public interface JeloSkladiste extends JpaRepository<Jelo, Long> {

	Jelo findById (Long id);
	
	Page<Jelo> findByRestoran(Restoran restoran, Pageable pageable);
	
	Jelo save(Jelo jelo);

	Jelo findByRestoranAndNaziv(Restoran restoran, String naziv);

	List<Jelo> findByNaziv(String string);
	
}