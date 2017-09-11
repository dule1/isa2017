package com.isa.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Jelo;
import com.isa.model.PosetaRestoranu;
import com.isa.model.Restoran;
import com.isa.model.Sto;
import com.isa.model.korisnici.Gost;

public interface PoseteSkladiste extends JpaRepository<PosetaRestoranu, Serializable> {

	List<PosetaRestoranu> findByGost(Gost gost);

	List<PosetaRestoranu> findBySto(Sto sto);

	List<PosetaRestoranu> findByRestoranAndOcenaNotAndDatumrezBetween(Restoran restoran, int ocena, Date datumod, Date datumdo);

	List<PosetaRestoranu> findByRestoranAndOcenaNot(Restoran restoran, int i);

	List<PosetaRestoranu> findByRestoranAndOcenaNotAndDatumrezBefore(Restoran restoran, int i, Date doDatum);

	List<PosetaRestoranu> findByRestoranAndOcenaNotAndDatumrezAfter(Restoran restoran, int i, Date odDatum);

	List<PosetaRestoranu> findByRestoranAndDatumrezAndSto(Restoran restoran, Date datumizrade, Sto sto);

	List<PosetaRestoranu> findByRestoranAndOcenaUslugeNotAndDatumrezAndSto(Restoran restoran, int i, Date datumizrade,
			Sto sto);

	List<PosetaRestoranu> findByRestoranAndDatumrez(Restoran restoran, Date datumOd);

	List<PosetaRestoranu> findByRestoranAndDatumrezAndDatumrezBefore(Restoran restoran, Date datumOd, Date danasnjiDan);

	List<PosetaRestoranu> findByRestoranAndJeloAndOcenaObrokaNot(Restoran restoran, Jelo jelo, int i);

	List<PosetaRestoranu> findByRestoranAndJeloAndOcenaObrokaNotAndDatumrezBefore(Restoran restoran, Jelo jelo, int i,
			Date datumdo);

	List<PosetaRestoranu> findByRestoranAndJeloAndOcenaObrokaNotAndDatumrezAfter(Restoran restoran, Jelo jelo, int i,
			Date datumod);

	List<PosetaRestoranu> findByRestoranAndJeloAndOcenaObrokaNotAndDatumrezBetween(Restoran restoran, Jelo jelo, int i,
			Date datumod, Date datumdo);
	
	List<PosetaRestoranu> findByRestoranAndSto(Restoran restoran, Sto sto);
}
