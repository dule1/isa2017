package com.isa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.isa.RestaurantIsaApplication;
import com.isa.model.Restoran;
import com.isa.model.korisnici.MenadzerSistema;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.services.MenadzerSistemaServis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantIsaApplication.class)
@WebAppConfiguration
public class MenadzerSistemaServisTest {

	@Autowired
	MenadzerSistemaServis menadzerSistemaServis;
	
	@Test
	public void saveMenadzerRestorana(){
		
		MenadzerSistema pera = new MenadzerSistema();
		pera.setEmail("pericaperic@gmail.com");
		pera.setIme("Pera");
		pera.setPrezime("Peric");
		pera.setTipKorisnika(TipKorisnika.MENADZER_SISTEMA);
		pera.setSifra("sifra");
		menadzerSistemaServis.saveMenadzerSistema(pera);
	
		MenadzerSistema menadzerSistema = menadzerSistemaServis.findOne(pera.getId());
        Assert.assertNotNull(pera);
        Assert.assertNotNull(menadzerSistema);
        Assert.assertEquals(pera.getId(), menadzerSistema.getId());
	}
	
	@Test
	public void  saveRestoranTest(){
		Restoran res = new Restoran();
		res.setNaziv("Restoran Test");
		res.setOpis("Opis");
		res.setBrojkolona(3);
		res.setBrojredova(4);
		menadzerSistemaServis.saveRestoran(res);
		Restoran restoran = menadzerSistemaServis.pronadjiRestoran(res.getId());
        Assert.assertNotNull(res);
        Assert.assertNotNull(restoran);
        Assert.assertEquals(res.getId(), restoran.getId());
	}
	
	
	
	
}
