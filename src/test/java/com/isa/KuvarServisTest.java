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
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.services.KuvarServis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantIsaApplication.class)
@WebAppConfiguration
public class KuvarServisTest {

	@Autowired
	private KuvarServis kuvarServis;
	
	@Test
	public void testSave() {
		Kuvar pera = new Kuvar();
		pera.setEmail("pericaperic@gmail.com");
		pera.setIme("Pera");
		pera.setPrezime("Peric");
		pera.setTipKorisnika(TipKorisnika.KUVAR);
		pera.setSifra("sifra");
		kuvarServis.save(pera);
	
		Kuvar kuvar = (Kuvar) kuvarServis.findOne(pera.getId());
        Assert.assertNotNull(pera);
        Assert.assertNotNull(kuvar);
        Assert.assertEquals(pera.getId(), kuvar.getId());
	}
	
	@Test
	public void testFindByEmail(){
		// TODO: Promeniti na normalan email
		Korisnik korisnik = kuvarServis.findByEmail("ks1");
		Assert.assertNotNull(korisnik);
		Assert.assertEquals(korisnik.getEmail(),"ks1");
	}
	
    @Test
    public void testFindAll() {
        List<Kuvar> kuvari = kuvarServis.findAll();
        Assert.assertNotNull(kuvari);
    }
    
    @Test
    public void testDelete() {
        Kuvar kuvar = new Kuvar();
        kuvar.setIme("Petar");
        kuvar.setPrezime("Petrovic");
        kuvar.setTipKorisnika(TipKorisnika.KUVAR);
        kuvar.setEmail("kuvar@gmail.com");
        kuvarServis.save(kuvar);
        kuvarServis.delete(kuvar.getId());
        Kuvar ucitan = (Kuvar) kuvarServis.findOne(kuvar.getId());
        Assert.assertNull(ucitan);
    }
	
}
