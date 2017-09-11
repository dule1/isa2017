package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.Restoran;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Korisnik;

@Repository
@Transactional
public interface KonobarSkladiste extends JpaRepository<Konobar, Long> {
	
	Konobar save(Konobar konobar);
	
	List<Korisnik> findById(Long gid);
	
	List<Korisnik> findByEmail(String email);

	List<Konobar> findByRestoran(Restoran restoran);

	Konobar findByRestoranAndEmail(Restoran restoran, String email);
	
}
