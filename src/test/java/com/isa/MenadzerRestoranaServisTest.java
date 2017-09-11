package com.isa;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.isa.model.Ponuda;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.Restoran;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.repository.PorudzbineMenadzeraSkladiste;
import com.isa.services.MenadzerRestoranaServis;
import com.isa.services.PorudzbinaMenadzeraServis;
import com.isa.services.RestoranServis;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantIsaApplication.class)
@WebAppConfiguration
public class MenadzerRestoranaServisTest {

	@Autowired
	MenadzerRestoranaServis menadzerRestoranaServis;
	
	@Autowired
	RestoranServis restoranServirs;
	
	@Autowired
	PorudzbineMenadzeraSkladiste pmSkladiste;
	
	@Test
	public void testSave() {
		MenadzerRestorana pera = new MenadzerRestorana();
		pera.setEmail("pericaperic@gmail.com");
		pera.setIme("Pera");
		pera.setPrezime("Peric");
		pera.setTipKorisnika(TipKorisnika.MENADZER_RESTRORANA);
		pera.setSifra("sifra");
		menadzerRestoranaServis.save(pera);
	
		MenadzerRestorana menadzerRestorana = (MenadzerRestorana) menadzerRestoranaServis.findOne(pera.getId());
        Assert.assertNotNull(pera);
        Assert.assertNotNull(menadzerRestorana);
        Assert.assertEquals(pera.getId(), menadzerRestorana.getId());
	}
	
	
    @Test
    public void testFindAll() {
        List<MenadzerRestorana> menadzerRestoranai = menadzerRestoranaServis.findAll();
        Assert.assertNotNull(menadzerRestoranai);
    }
    
    @Test
    public void testDelete() {
        MenadzerRestorana menadzerRestorana = new MenadzerRestorana();
        menadzerRestorana.setIme("Petar");
        menadzerRestorana.setPrezime("Petrovic");
        menadzerRestorana.setTipKorisnika(TipKorisnika.MENADZER_RESTRORANA);
        menadzerRestorana.setEmail("menadzerRestorana@gmail.com");
        menadzerRestoranaServis.save(menadzerRestorana);
        menadzerRestoranaServis.delete(menadzerRestorana.getId());
        MenadzerRestorana ucitan = menadzerRestoranaServis.findOne(menadzerRestorana.getId());
        Assert.assertNull(ucitan);
    }
    
    @Test
    public void izlistajPorudzbine() {
    	Restoran restoran = new Restoran();
    	restoran.setNaziv("R 1");
    	restoran.setOpis("Op 1");
    	restoran.setBrojkolona(5);
    	restoran.setBrojredova(5);
    	restoranServirs.save(restoran);
    	Restoran rest = restoranServirs.findOne(restoran.getId());
    	
    	MenadzerRestorana menadzerrestorana = new MenadzerRestorana();
    	menadzerrestorana.setEmail("a@a.com");
    	menadzerrestorana.setIme("Imee");
    	menadzerrestorana.setPrezime("Prezz");
    	menadzerrestorana.setTipKorisnika(TipKorisnika.MENADZER_RESTRORANA);
    	menadzerrestorana.setLogovaoSe(true);
    	menadzerrestorana.setSifra("aaa");
    	menadzerrestorana.setRestoran(rest);
    	menadzerRestoranaServis.save(menadzerrestorana);
    	MenadzerRestorana menRest = menadzerRestoranaServis.findOne(menadzerrestorana.getId());
    			
    	PorudzbinaMenadzer porudzbinaMenadzer = new PorudzbinaMenadzer();
    	porudzbinaMenadzer.setAktivna(true);
    	porudzbinaMenadzer.setMenadzerrestorana(menRest);
    	pmSkladiste.save(porudzbinaMenadzer);
    	
    	List<PorudzbinaMenadzer> pormen = pmSkladiste.findAll();
        
        Assert.assertNull(pormen);
    }
	
}
