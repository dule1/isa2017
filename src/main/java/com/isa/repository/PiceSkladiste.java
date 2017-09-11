package com.isa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Pice;
import com.isa.model.Restoran;

public interface PiceSkladiste extends JpaRepository<Pice, Long>{

	List<Pice> findAll();
	
	Pice save(Pice pice);

	Page<Pice> findByRestoran(Restoran restoran, Pageable pageable);

	Pice findById(Long i);

	List<Pice> findByNaziv(String naziv);
	
}