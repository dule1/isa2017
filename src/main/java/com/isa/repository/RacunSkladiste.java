package com.isa.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.Porudzbina;
import com.isa.model.RacunKonobar;
import com.isa.model.korisnici.Konobar;

@Repository
@Transactional
public interface RacunSkladiste extends JpaRepository<RacunKonobar, Serializable> {

	RacunKonobar save(RacunKonobar racunKonobar);

	List<RacunKonobar> findByKonobarAndUkupnoNotAndPorudzbina(Konobar konobar, Float i, Porudzbina por);


}
