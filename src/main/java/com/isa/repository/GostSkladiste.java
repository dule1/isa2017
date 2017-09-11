package com.isa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;


@Repository
@Transactional
public interface GostSkladiste extends JpaRepository<Gost, Long> {

	List<Gost> findById(Long gid);
	
	List<Gost> findByEmail(String email);
	
	Page<Gost> findByEmailNotLike(String email, Pageable pageable);
	
}
