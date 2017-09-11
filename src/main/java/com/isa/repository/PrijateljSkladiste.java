package com.isa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.korisnici.Prijatelj;

@Repository
@Transactional
public interface PrijateljSkladiste extends JpaRepository<Prijatelj, Long> {

	List<Prijatelj> findById(Long gid);
	
	Page<Prijatelj> findByEmailGosta(String email, Pageable pageable);
	
	Page<Prijatelj> findByEmailGostaNotLike(String email, Pageable pageable);
	
	@Modifying
	@Transactional
	@Query("delete from Prijatelj where email_gosta = :gEmail and email_prijatelja = :pEmail")
	void deleteGostPrij(@Param("gEmail") String gEmail, @Param("pEmail") String pEmail);
	
	@Modifying
	@Transactional
	@Query("delete from Prijatelj where email_gosta = :pEmail and email_prijatelja = :gEmail")
	void deletePrijGost(@Param("pEmail") String pEmail, @Param("gEmail") String gEmail);
	
	@Modifying
	@Transactional
	@Query(value = "insert into Prijatelj (email_gosta, email_prijatelja) values (:gEmail, :pEmail)", nativeQuery = true)
	void addGostPrij(@Param("gEmail") String gEmail, @Param("pEmail") String pEmail);
	
	
	
	
}
