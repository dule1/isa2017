package com.isa;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.isa.model.korisnici.Ponudjac;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.services.PonudjacServis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantIsaApplication.class)
@WebAppConfiguration
public class PonudjacServisTest {
	
	@Autowired
	PonudjacServis ponudjacServis;

	@Test
	public void testSave() {
		Ponudjac pera = new Ponudjac();
		pera.setEmail("ponudjac@gmail.com");
		pera.setIme("Pera");
		pera.setPrezime("Peric");
		pera.setTipKorisnika(TipKorisnika.KONOBAR);
		pera.setSifra("sifra");
		ponudjacServis.save(pera);
	
		Ponudjac ponudjac = (Ponudjac) ponudjacServis.findOne(pera.getId());
        Assert.assertNotNull(pera);
        Assert.assertNotNull(ponudjac);
        Assert.assertEquals(pera.getId(), ponudjac.getId());
	}
	
	
    @Test
    public void testFindAll() {
        List<Ponudjac> ponudjaci = ponudjacServis.findAll();
        Assert.assertNotNull(ponudjaci);
    }
    
    @Test
    public void testDelete() {
        Ponudjac ponudjac = new Ponudjac();
        ponudjac.setIme("Ponudjac");
        ponudjac.setPrezime("Ponudjacic");
        ponudjac.setTipKorisnika(TipKorisnika.PONUDJAC);
        ponudjac.setEmail("ponudjac@gmail.com");
        ponudjacServis.save(ponudjac);
        ponudjacServis.delete(ponudjac.getId());
        Ponudjac ucitan = (Ponudjac) ponudjacServis.findOne(ponudjac.getId());
        Assert.assertNull(ucitan);
    }
	
}

