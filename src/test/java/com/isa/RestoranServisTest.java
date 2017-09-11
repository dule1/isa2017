package com.isa;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.isa.model.Jelo;
import com.isa.model.Restoran;
import com.isa.repository.JeloSkladiste;
import com.isa.services.RestoranServis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantIsaApplication.class)
@WebAppConfiguration
public class RestoranServisTest {

	@Autowired
	private RestoranServis restoranServis;
	@Autowired
	private JeloSkladiste jeloSkladiste;
	
	@Test
	public void testSave() {
		Restoran res = new Restoran();
		res.setNaziv("Restoran Test");
		res.setOpis("Opis");
		res.setBrojkolona(3);
		res.setBrojredova(4);
		restoranServis.save(res);
		Restoran restoran = (Restoran) restoranServis.findOne(res.getId());
        Assert.assertNotNull(res);
        Assert.assertNotNull(restoran);
        Assert.assertEquals(res.getId(), restoran.getId());
	}
	
	@Test
	public void testFindOne(){
		Long id = new Long(1);
		Restoran korisnik = restoranServis.findOne(id);
		Assert.assertNotNull(korisnik);
		Assert.assertEquals(korisnik.getId(),id);
	}
	
    @Test
    public void testFindAll() {
        List<Restoran> restaurants = restoranServis.findAll();
        Assert.assertNotNull(restaurants);
    }
    
    @Test
    public void testDelete() {
        Restoran restoran = new Restoran();
        restoran.setNaziv("Restoran Test");
        restoran.setOpis("Opis");
        restoran.setBrojkolona(3);
        restoran.setBrojredova(4);
        restoranServis.save(restoran);
        restoranServis.delete(restoran.getId());
        Restoran ucitan = (Restoran) restoranServis.findOne(restoran.getId());
        Assert.assertNull(ucitan);
    }
    
	@Test
	public void saveTest() {
		Jelo jelo = new Jelo();
		jelo.setNaziv("ime");
		restoranServis.save(jelo);
		
		List<Jelo> jela = jeloSkladiste.findByNaziv("ime");
		Assert.assertNotEquals(jela.size(),0);
	}
    

	
}
