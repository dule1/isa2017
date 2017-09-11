package com.isa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isa.model.DanUNedelji;
import com.isa.model.IzvestajJelo;
import com.isa.model.Jelo;
import com.isa.model.Pice;
import com.isa.model.Restoran;
import com.isa.model.Smena;
import com.isa.model.SmenaUDanu;
import com.isa.model.Sto;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.Ponudjac;
import com.isa.model.korisnici.Sanker;
import com.isa.pomocni.IzvestajKonobar;
import com.isa.pomocni.IzvestajRestoran;
import com.isa.pomocni.PosecenostIzvestaj;

public interface RestoranServis {

	List<Restoran> findAll();

	Restoran findOne(Long id);

	Restoran save(Restoran restoran);

	void delete(Long id);

	Page<Jelo> izlistajJelovnik(Restoran restoran, Pageable pageable);

	Page<Pice> izlistajKartuPica(Restoran restoran, Pageable pageable);

	Page<Sto> izlistajStolove(Restoran restoran, Pageable pageable);

	void save(Jelo jelo);

	void save(Pice pice);

	void izbrisiJelo(Long id);

	void izbrisiPice(Long id);
	
	void izbrisiPiceUPorudzbini(Long id);
	
	void izbrisiJeloUPorudzbini(Long id);
	
	Page<Sto> kreirajStolove(Restoran restoran, ArrayList<Integer> oznake, Pageable pageable);

	void dodajRedoveIKolone(Restoran rest);

	List<Ponudjac> izlistajPonudjaceVanRestorana(Restoran restoran);

	Ponudjac save(Ponudjac ponudjac);
	
	void dodajPonudjacaURestoran(Restoran restoran, Ponudjac ponudjac);

	List<SmenaUDanu> izlistajSmeneKuvara(Restoran restoran, DanUNedelji danUNedelji);

	List<Smena> izlistajSmene(Restoran restoran);

	void dodajSmenu(Smena smena);

	List<Kuvar> izlistajDostupneKuvare(Restoran restoran, DanUNedelji danUNedelji);

	List<Konobar> izlistajKonobare(Restoran restoran);

	List<Kuvar> izlistajKuvare(Restoran restoran);

	List<Sanker> izlistajSankere(Restoran restoran);

	void dodajKuvaraUSmenuDana(SmenaUDanu smenaUDanu);

	List<SmenaUDanu> izlistajSmeneKonobara(Restoran restoran, DanUNedelji danUNedelji, Sto sto);

	List<Smena> izlistajDostupneSmeneKonobarDan(Restoran restoran, DanUNedelji danUNedelji, Sto sto);

	List<Konobar> izlistajDostupneKonobare(Restoran restoran, DanUNedelji danUNedelji, Sto sto, Smena smena);

	void dodajKonobaraStoSmenaDan(Restoran restoran, DanUNedelji danUNedelji, Konobar konobar, Smena smena, Sto sto);

	List<Sanker> izlistajDostupneSankere(Restoran restoran, DanUNedelji danUNedelji);

	void dodajSankeraUSmenuDana(SmenaUDanu smenaUDanu);

	List<SmenaUDanu> izlistajSmeneSankera(Restoran restoran, DanUNedelji danUNedelji);
	
	List<SmenaUDanu> izlistajSmenePoDanimaKuvara(Kuvar kuvar);

	List<SmenaUDanu> izlistajSmenePoDanimaKonobara(Konobar konobar);
	
	List<SmenaUDanu> izlistajSmenePoDanimaSankera (Sanker sanker);

	Sto izmeniSto(Sto sto);

	Sto izlistajSto(Sto sto);

	List<Sto> izlistajStoloveSmene(SmenaUDanu smenaKonobara);

	IzvestajJelo izlistajIzvestajZaJelo(IzvestajJelo izvestajJelo);

	double izlistajOcenuRestorana(IzvestajRestoran izvestajRestoran);

	double izlistajPrihodRestorana(IzvestajRestoran izvestajRestoran);

	Konobar registrujKonobara(Konobar konobar);

	Kuvar registrujKuvara(Kuvar kuvar);

	Sanker registrujeSankera(Sanker sanker);

	String sima(DanUNedelji dan, Sto sto, Konobar konobar, Restoran restoran);

	double izlistajOcenuKonobara(IzvestajKonobar izvestajKonobar);

	double izlistajPrihodKonobara(IzvestajKonobar izvestajKonobar);

	ArrayList<Double> izracunaPosecenostNedelja(PosecenostIzvestaj posecenostIzvestaj);

	ArrayList<Double> izracunaPosecenostDnevno(PosecenostIzvestaj posecenostIzvestaj);

}
