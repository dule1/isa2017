package com.isa.model.korisnici;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "listaPrijatelja")
public class Prijatelj {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "emailGosta")
	private String emailGosta;
	
	@Column(name = "emailPrijatelja")
	private String emailPrijatelj;

	public Prijatelj(){
	}
	
	public Prijatelj(String emailGosta, String emailPrijatelja) {
		super();
		this.emailGosta = emailGosta;
		this.emailPrijatelj = emailPrijatelja;
	}
	
	public String getEmailGosta() {
		return emailGosta;
	}
	
	public void setEmailGosta(String emailGosta) {
		this.emailGosta = emailGosta;
	}
	
	public String getEmailPrijatelj() {
		return emailPrijatelj;
	}
	
	public void setEmailPrijatelj(String emailPrijatelj) {
		this.emailPrijatelj = emailPrijatelj;
	}

}
