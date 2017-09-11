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

import com.isa.model.ZahtevZaPrijateljstvo;

@Repository
@Transactional
public interface ZahteviZaPrijSkladiste extends JpaRepository<ZahtevZaPrijateljstvo, Long>{
	
	List<ZahtevZaPrijateljstvo> findById(Long gid);
	
	Page<ZahtevZaPrijateljstvo> findByEmailGosta(String email, Pageable pageable);
	
	Page<ZahtevZaPrijateljstvo> findByEmailGostaAndEmailPrijatelj(String emailG, String emailP, Pageable pageable);
	
	@Modifying
	@Transactional
	@Query("delete from ZahtevZaPrijateljstvo where email_gosta = :gEmail and email_prijatelja = :pEmail")
	void deleteZahtev(@Param("gEmail") String gEmail, @Param("pEmail") String pEmail);
	
	@Modifying
	@Transactional
	@Query(value = "insert into zahtev_za_prijateljstvo (email_gosta, email_prijatelja) values (:gEmail, :pEmail)", nativeQuery = true)
	void addZahtev(@Param("gEmail") String gEmail, @Param("pEmail") String pEmail);

}
