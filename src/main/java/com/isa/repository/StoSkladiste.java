package com.isa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Restoran;
import com.isa.model.SmenaUDanu;
import com.isa.model.Sto;

public interface StoSkladiste extends JpaRepository<Sto, Long>{

	Page<Sto> findByRestoran(Restoran restoran, Pageable pageable);
	
	List<Sto> findByRestoran(Restoran restoran);
	
	List<Sto> findByRestoranAndZauzetTrue(Restoran restoran);
	
	Sto findByRestoranAndOznaka(Restoran restoran, int oznaka);
	
	Sto save(Sto sto);

	List<Sto> findBySmenaudanu(SmenaUDanu smenaKonobara);
	
}
