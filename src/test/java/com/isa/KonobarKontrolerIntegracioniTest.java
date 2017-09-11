package com.isa;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.isa.model.Jelo;
import com.isa.model.JeloUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Konobar;
import com.isa.repository.KonobarSkladiste;
import com.isa.services.KonobarServis;
import com.isa.services.RestoranServis;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;


@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
@Transactional
public class KonobarKontrolerIntegracioniTest {

	@Mock
	KonobarServis konobarServis;
	
	@Mock
	RestoranServis restoranServis;
	
	@MockBean
	KonobarSkladiste konobarSkladiste;
	
	
	
	@Test
	public void ucitajKonobareRestoranaTest() {
		RestAssured.when().get("/ucitajKonobareRestorana").then()
				.statusCode(HttpStatus.SC_OK).contentType(ContentType.JSON)
				.body("id", CoreMatchers.hasItems(4));
	}
	
	@Test
	public void dodajPorudzbinuTest(){
		Porudzbina porudzbina = new Porudzbina();
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		porudzbina.setVremePrimanja(currentTime);
		porudzbina.setPorudzbinaPrihvacena(true);
		Restoran restoran = restoranServis.findOne(1L);
		porudzbina.setRestoran(restoran);
		Konobar konobar = new Konobar();
		konobar.setIme("Sima");
		porudzbina.setSanker(null);
		porudzbina.setKonobar(konobar);
		
		Jelo jelo = new Jelo();
		jelo.setNaziv("Punjene paprike");
		jelo.setOpis("Opis jela 1");
		jelo.setCena(30F);
		JeloUPorudzbini jeloUPorudzbini = new JeloUPorudzbini();
		jeloUPorudzbini.setKolicina(3);
		jeloUPorudzbini.setJelo(jelo);
		jeloUPorudzbini.setPorudzbina(porudzbina);	
		restoranServis.save(jelo);
		konobarServis.saveJeloUPorudzbini(jeloUPorudzbini);	
		konobarServis.savePorudzbina(porudzbina);	
		
		
		
		
		List<Konobar> listaK = new ArrayList<>();
		when(this.konobarServis.findAll()).thenReturn(listaK);
		List<Konobar> newList = this.konobarServis.findAll();
		assertEquals(newList.size(), 0);
	}
}

