package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.DanUNedelji;
import com.isa.model.Restoran;
import com.isa.model.Smena;
import com.isa.model.SmenaUDanu;
import com.isa.model.Sto;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.Sanker;


public interface SmeneUDanuSkladiste extends JpaRepository<SmenaUDanu, Long>{

	SmenaUDanu save(SmenaUDanu smenaUDanu);
	
	List<SmenaUDanu> findByRestoranAndDanUNedeljiAndStoAndKonobarNotNull(Restoran restoran, DanUNedelji danUNedelji, Sto sto);
	
	List<SmenaUDanu> findByRestoranAndDanUNedeljiAndKuvarNotNull(Restoran restoran, DanUNedelji danUNedelji);
	
	List<SmenaUDanu> findByRestoranAndDanUNedeljiAndSankerNotNull(Restoran restoran, DanUNedelji danUNedelji);
	
	List<SmenaUDanu> findByRestoranAndDanUNedeljiAndSmenaAndKuvarNotNull(Restoran restoran, DanUNedelji danUNedelji, Smena smena);
	
	List<SmenaUDanu> findByRestoranAndDanUNedeljiAndSmenaAndSankerNotNull(Restoran restoran, DanUNedelji danUNedelji, Smena smena);
	
	SmenaUDanu findByRestoranAndDanUNedeljiAndStoAndKonobarAndSmena(Restoran restoran, DanUNedelji danUNedelji, Sto sto, Konobar konobar, Smena smena);
	
	SmenaUDanu findByRestoranAndDanUNedeljiAndKonobarAndSmena(Restoran restoran, DanUNedelji danUNedelji, Konobar konobar, Smena smena);
	
	SmenaUDanu findByRestoranAndDanUNedeljiAndKonobar(Restoran restoran, DanUNedelji danUNedelji, Konobar konobar);

	List<SmenaUDanu> findByKuvar(Kuvar kuvar);

	List<SmenaUDanu> findByKonobar(Konobar konobar);
	
	List<SmenaUDanu> findBySanker(Sanker sanker);

	SmenaUDanu findByKonobarAndDanUNedeljiAndRestoranAndSto(Konobar konobar,DanUNedelji dan, Restoran restoran, Sto s);

}
