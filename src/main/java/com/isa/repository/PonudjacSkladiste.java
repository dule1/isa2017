package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.Restoran;
import com.isa.model.korisnici.Ponudjac;

@Repository
@Transactional
public interface PonudjacSkladiste extends JpaRepository<Ponudjac, Long> {

	List<Ponudjac> findAll();
	List<Ponudjac> findById(Long id);
	List<Ponudjac> findByRestoran(Restoran restoran);
	Ponudjac save(Ponudjac ponudjac);
	void delete(Long id);
	
}
