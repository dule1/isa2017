package com.isa.pomocni;

public class PromeniSifru {
	
	private String staraSifra;
	private String novaSifra;
	private String novaSifra2;
	
	public PromeniSifru(){
		
	}
		
	public PromeniSifru(String staraSifra, String novaSifra, String novaSifra2) {
		this.staraSifra = staraSifra;
		this.novaSifra = novaSifra;
		this.novaSifra2 = novaSifra2;
	}
	
	public String getStaraSifra() {
		return staraSifra;
	}
	public void setStaraSifra(String staraSifra) {
		this.staraSifra = staraSifra;
	}
	public String getNovaSifra() {
		return novaSifra;
	}
	public void setNovaSifra(String novaSifra) {
		this.novaSifra = novaSifra;
	}
	public String getNovaSifra2() {
		return novaSifra2;
	}
	public void setNovaSifra2(String novaSifra2) {
		this.novaSifra2 = novaSifra2;
	}
	
	
}
