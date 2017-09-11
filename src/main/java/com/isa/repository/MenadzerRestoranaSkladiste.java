package com.isa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.Restoran;
import com.isa.model.korisnici.MenadzerRestorana;

@Repository
@Transactional
public interface MenadzerRestoranaSkladiste extends JpaRepository<MenadzerRestorana, Long>{

	List<MenadzerRestorana> findAll();
	Page<MenadzerRestorana> findAll(Pageable pageable);
	List<MenadzerRestorana> findById(Long id);
	MenadzerRestorana save(MenadzerRestorana menadzerRestorana);
	void delete(Long id);
	List<MenadzerRestorana> findByRestoran(Restoran restoran);
}
