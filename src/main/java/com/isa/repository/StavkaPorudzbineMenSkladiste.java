package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.StavkaPorudzbineMenadzera;

public interface StavkaPorudzbineMenSkladiste extends JpaRepository<StavkaPorudzbineMenadzera, Long>{
	
	StavkaPorudzbineMenadzera save(StavkaPorudzbineMenadzera stavkaPorudzbineMenadzera);
	
	List<StavkaPorudzbineMenadzera> findByPorudzbinamenadzer(PorudzbinaMenadzer porudzbinaMenadzer);
	
}
