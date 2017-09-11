package com.isa.services;

import com.isa.model.korisnici.Korisnik;

public interface KorisnikServis {
    Korisnik save(Korisnik korisnik);
    Korisnik findOne(Long id);
	Korisnik findByEmail(String email);
}
