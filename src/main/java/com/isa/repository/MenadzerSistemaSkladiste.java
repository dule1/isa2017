package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.korisnici.MenadzerSistema;

@Repository
@Transactional
public interface MenadzerSistemaSkladiste extends JpaRepository<MenadzerSistema, Long> {
	MenadzerSistema save(MenadzerSistema menSistema);
	
}
