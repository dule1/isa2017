package com.isa;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Sanker;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.repository.PorudzbinaSkladiste;
import com.isa.services.KonobarServis;
import com.isa.services.KorisnikServis;
import com.isa.services.RestoranServis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantIsaApplication.class)
@WebAppConfiguration
public class KorisnikServisTest {

	@Autowired
	KorisnikServis korisnikServis;
	
	@Autowired
	KonobarServis konobarServis;
	
	@Autowired
	RestoranServis restoranServis;

	@Autowired
	PorudzbinaSkladiste porudzbinaSkladiste;
	
	@Test
	public void testSave() {
		Korisnik pera = new Korisnik();
		pera.setEmail("pericaperic@gmail.com");
		pera.setIme("Pera");
		pera.setPrezime("Peric");
		pera.setTipKorisnika(TipKorisnika.KONOBAR);
		pera.setSifra("sifra");
		korisnikServis.save(pera);
	
		Korisnik korisnik = (Korisnik) korisnikServis.findOne(pera.getId());
        Assert.assertNotNull(pera);
        Assert.assertNotNull(korisnik);
        Assert.assertEquals(pera.getId(), korisnik.getId());
	}
	
	@Test
	public void testFindByEmail(){
		Korisnik korisnik = korisnikServis.findByEmail("korisnik@korisnik.com");
		Assert.assertNotNull(korisnik);
		Assert.assertEquals(korisnik.getEmail(),"korisnik@korisnik.com");
	}
	
    
    @Test
    public void testDelete() {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme("Petar");
        korisnik.setPrezime("Petrovic");
        korisnik.setEmail("korisnik@gmail.com");
        korisnikServis.save(korisnik);
        Korisnik ucitan = (Korisnik) korisnikServis.findOne(korisnik.getId());
        Assert.assertNull(ucitan);
    }
	
    @Test
	public void izlistajRestoranTest(){
    	Restoran res = new Restoran();
		res.setNaziv("Restoran Test");
		res.setOpis("Opis");
		res.setBrojkolona(3);
		res.setBrojredova(4);
		Konobar kon = new Konobar();
		kon.setIme("pera");
		restoranServis.save(res);
		Restoran res2 = konobarServis.izlistajRestoran(kon);
		Assert.assertNotNull(res2);
        Assert.assertNotNull(res2);
        Assert.assertEquals(res2.getId(), res.getId());
	
    }
    
    public void savePorudzbinaTest(){
    	Porudzbina por = new Porudzbina();
    	Konobar kon = new Konobar();
    	kon.setIme("sima");
    	kon.setIme("prezime");
    	
    	por.setKonobar(kon);
    	por.setKonobar1(kon);
    	
    	Sanker san = new Sanker();
    	san.setIme("sanker");
    	por.setSanker(san);
    	konobarServis.savePorudzbina(por);
    	List<Porudzbina> porudzbine = porudzbinaSkladiste.findAll();
    	boolean ima = false;
    	for(int i = 0; i < porudzbine.size(); i++){
    		if(porudzbine.get(i).getKonobar().equals(kon)){
    			ima = true;
    			break;
    		}
    	}
    	Assert.assertNotEquals(porudzbine.size(),0);
    	Assert.assertEquals(ima, true);
    	
    }



}
