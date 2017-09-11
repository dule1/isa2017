package com.isa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.Restoran;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.model.korisnici.Ponudjac;
import com.isa.model.korisnici.Sanker;

@Repository
@Transactional
public interface RestoranSkladiste extends JpaRepository<Restoran,Long> {
	List<Restoran> findAll();
	List<Restoran> findById(Long gid);
	Page<Restoran> findAll(Pageable pageable);
	Restoran findByMenadzerrestorana(MenadzerRestorana menadzerRestorana);
	Restoran findByKonobar(Konobar konobar);
	Restoran findByKuvar(Kuvar kuvar);
	Restoran findBySanker(Sanker sanker);
	Restoran save(Restoran restoran);
	List<Restoran> findByPonudjac(Ponudjac ponudjac);

}
