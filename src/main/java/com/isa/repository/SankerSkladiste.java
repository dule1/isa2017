package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Restoran;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Sanker;

public interface SankerSkladiste extends JpaRepository<Sanker, Long> {
	
	Sanker save(Sanker sanker);
	
	List<Korisnik> findById(Long gid);
	
	List<Korisnik> findByEmail(String email);

	List<Sanker> findByRestoran(Restoran restoran);

}
