package com.isa;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.isa.RestaurantIsaApplication;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Sanker;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.services.SankerServis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantIsaApplication.class)
@WebAppConfiguration
public class SankerServisTest {

	@Autowired
	SankerServis sankerServis;
	
	@Test
	public void testSave() {
		Sanker pera = new Sanker();
		pera.setEmail("pericaperic@gmail.com");
		pera.setIme("Pera");
		pera.setPrezime("Peric");
		pera.setTipKorisnika(TipKorisnika.SANKER);
		pera.setSifra("sifra");
		sankerServis.save(pera);
	
		Sanker sanker = (Sanker) sankerServis.findOne(pera.getId());
        Assert.assertNotNull(pera);
        Assert.assertNotNull(sanker);
        Assert.assertEquals(pera.getId(), sanker.getId());
	}
	
	@Test
	public void testFindByEmail(){
		// TODO: Promeniti na normalan email
		Korisnik korisnik = sankerServis.findByEmail("sa");
		Assert.assertNotNull(korisnik);
		Assert.assertEquals(korisnik.getEmail(),"sa");
	}
	
    @Test
    public void testFindAll() {
        List<Sanker> sankeri = sankerServis.findAll();
        Assert.assertNotNull(sankeri);
    }
    
    @Test
    public void testDelete() {
        Sanker sanker = new Sanker();
        sanker.setIme("Petar");
        sanker.setPrezime("Petrovic");
        sanker.setTipKorisnika(TipKorisnika.SANKER);
        sanker.setEmail("sanker@gmail.com");
        sankerServis.save(sanker);
        sankerServis.delete(sanker.getId());
        Sanker ucitan = (Sanker) sankerServis.findOne(sanker.getId());
        Assert.assertNull(ucitan);
    }
    

	
}
