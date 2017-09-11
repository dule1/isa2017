package com.isa;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.services.GostServis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantIsaApplication.class)
@WebAppConfiguration
public class GostServisTest {

	@Autowired
	GostServis gostServis;
	
	@Test
	public void testSave() {
		Gost pera = new Gost();
		pera.setEmail("pericaperic@gmail.com");
		pera.setIme("Pera");
		pera.setPrezime("Peric");
		pera.setTipKorisnika(TipKorisnika.GOST);
		pera.setSifra("sifra");
		gostServis.save(pera);
	
		Gost gost = (Gost) gostServis.findOne(pera.getId());
        Assert.assertNotNull(pera);
        Assert.assertNotNull(gost);
        Assert.assertEquals(pera.getId(), gost.getId());
	}
	
	@Test
	public void testFindByEmail(){
		Korisnik korisnik = gostServis.findByEmail("pera@pera.com");
		Assert.assertNotNull(korisnik);
		Assert.assertEquals(korisnik.getEmail(),"pera@pera.com");
	}
	
    @Test
    public void testFindAll() {
        List<Gost> gosti = gostServis.findAll();
        Assert.assertNotNull(gosti);
    }
    
    @Test
    public void testDelete() {
        Gost gost = new Gost();
        gost.setIme("Petar");
        gost.setPrezime("Petrovic");
        gost.setTipKorisnika(TipKorisnika.GOST);
        gost.setEmail("gost@gmail.com");
        gostServis.save(gost);
        gostServis.delete(gost.getId());
        Gost ucitan = gostServis.findByEmail("gost@gmail.com");
        Assert.assertNull(ucitan);
    }
	
}
