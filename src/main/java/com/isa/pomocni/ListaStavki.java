package com.isa.pomocni;

import java.util.List;

import com.isa.model.Porudzbina;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.StavkaPorudzbineMenadzera;

import scala.annotation.meta.setter;

public class ListaStavki {

	private PorudzbinaMenadzer porudzbinaMenadzer;
	private List<StavkaPorudzbineMenadzera> stavke;
	
	public ListaStavki(){
		
	}
	
	public ListaStavki(List<StavkaPorudzbineMenadzera> stavke){
		this.stavke = stavke;
	}
	
	public List<StavkaPorudzbineMenadzera> getStavke() {
		return stavke;
	}
	
	public void setStavke(List<StavkaPorudzbineMenadzera> stavke) {
		this.stavke = stavke;
	}

	public PorudzbinaMenadzer getPorudzbinaMenadzer() {
		return porudzbinaMenadzer;
	}
	
	public void setPorudzbinaMenadzer(PorudzbinaMenadzer porudzbinaMenadzer) {
		this.porudzbinaMenadzer = porudzbinaMenadzer;
	}
}
