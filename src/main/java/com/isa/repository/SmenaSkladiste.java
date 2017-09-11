package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Restoran;
import com.isa.model.Smena;

public interface SmenaSkladiste extends JpaRepository<Smena, Long>{

	Smena save(Smena smena);
	
	List<Smena> findByRestoran(Restoran restoran);

}
