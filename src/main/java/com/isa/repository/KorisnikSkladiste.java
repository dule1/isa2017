package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.korisnici.Korisnik;

@Repository
@Transactional
public interface KorisnikSkladiste extends JpaRepository<Korisnik, Long> {
	List<Korisnik> findByEmail(String email);
}
