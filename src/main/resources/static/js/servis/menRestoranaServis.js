var menRestoranaServis = angular.module('restoranApp.menRestoranaServis', []);

menRestoranaServis.factory('menRestoranaServisS', function($http) {


	var temp = {};
	
	temp.izlistajRestoran = function(menRestorana) {
		return $http.post('/menadzerRestoranaKontroler/izlistajRestoran', menRestorana);
	}
	
	temp.izlistajJela = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/izlistajJela', restoran);
	}
	
	temp.izbrisiJelo = function(jelo) {
		return $http.post('/menadzerRestoranaKontroler/izbrisiJelo', jelo);
	}
	
	temp.dodajJelo = function(jelo) {;
		return $http.post('/menadzerRestoranaKontroler/dodajJelo', jelo);
	}
	
	temp.izlistajPica = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/izlistajPica', restoran);
	}
	
	temp.izbrisiPice = function(pice) {
		return $http.post('/menadzerRestoranaKontroler/izbrisiPice', pice);
	}
	
	temp.dodajPice = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/dodajPice', restoran);
	}
	
	temp.izmeniNazivRestorana = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/izmeniNazivRestorana', restoran);
	}
	
	temp.izmeniOpisRestorana = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/izmeniOpisRestorana', restoran);
	}
	
	temp.napraviStolove = function(nizStolova) {
		return $http.post('/menadzerRestoranaKontroler/napraviStolove', nizStolova);
	}
	
	temp.izlistajSto = function(sto) {
		return $http.post('/menadzerRestoranaKontroler/izlistajSto', sto);
	}
	
	temp.izlistajSveNamirnice = function() {
		return $http.post('/menadzerRestoranaKontroler/izlistajSveNamirnice');
	}
	
	temp.izlistajSvaPica = function() {
		return $http.post('/menadzerRestoranaKontroler/izlistajSvaPica');
	}
	
	temp.dodajStavke = function(porudzbina) {
		return $http.post('/menadzerRestoranaKontroler/dodajStavke', porudzbina);
	}
	
	temp.brojeviRedovaIKolona = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/brojeviRedovaIKolona', restoran);
	}
	
	temp.izlistajStolove = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/izlistajStolove', restoran);
	}
	
	temp.izlistajDostupnePonudjace = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/izlistajDostupnePonudjace', restoran);
	}
	
	temp.dodajPonudjaca = function(restPon) {
		return $http.post('/menadzerRestoranaKontroler/dodajPonudjaca', restPon);
	}
	
	temp.registrujIDodajPonudjaca = function(restPon) {
		return $http.post('/menadzerRestoranaKontroler/registrujIDodajPonudjaca', restPon);
	}
	
	temp.izlistajSmeneKuvara = function(smenaUDanu) {
		return $http.post('/menadzerRestoranaKontroler/izlistajSmeneKuvara', smenaUDanu);
	}
	
	temp.izlistajSmeneSankera = function(smenaUDanu) {
		return $http.post('/menadzerRestoranaKontroler/izlistajSmeneSankera', smenaUDanu);
	}
	
	temp.izlistajSmene = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/izlistajSmene', restoran);
	}
	
	temp.dodajSmenu = function(smena) {
		return $http.post('/menadzerRestoranaKontroler/dodajSmenu', smena);
	}
	
	temp.dostupniKuvari = function(smena) {
		return $http.post('/menadzerRestoranaKontroler/dostupniKuvari', smena);
	}
	
	temp.dostupniSankeri = function(smena) {
		return $http.post('/menadzerRestoranaKontroler/dostupniSankeri', smena);
	}
	
	temp.dodajKuvaraUSmenuDana = function(smenaUDanu) {
		return $http.post('/menadzerRestoranaKontroler/dodajKuvaraUSmenuDana', smenaUDanu);
	}
	
	temp.dodajSankeraUSmenuDana = function(smenaUDanu) {
		return $http.post('/menadzerRestoranaKontroler/dodajSankeraUSmenuDana', smenaUDanu);
	}
	
	temp.izlistajSmeneKonobar = function(smenaUDanu) {
		return $http.post('/menadzerRestoranaKontroler/izlistajSmeneKonobar', smenaUDanu);
	}
	
	temp.izlistajDostupneKonobare = function(smenaUDanu) {
		return $http.post('/menadzerRestoranaKontroler/izlistajDostupneKonobare', smenaUDanu);
	}
	
	temp.izlistajDostupneSmeneKonobarDan = function(smenaUDanu) {
		return $http.post('/menadzerRestoranaKontroler/izlistajDostupneSmeneKonobarDan', smenaUDanu);
	}
	
	temp.dodajKonobaraStoSmenaDan = function(smenaUDanu) {
		return $http.post('/menadzerRestoranaKontroler/dodajKonobaraStoSmenaDan', smenaUDanu);
	}
	
	temp.izmeniSto = function(sto) {
		return $http.post('/menadzerRestoranaKontroler/izmeniSto', sto);
	}
	
	temp.izvestajZaJelo = function(izv) {
		return $http.post('/menadzerRestoranaKontroler/izvestajZaJelo', izv);
	}
	
	temp.izvestajZaRestoran = function(rest) {
		return $http.post('/menadzerRestoranaKontroler/izvestajZaRestoran', rest);
	}
	
	temp.izvestajPrihodaRestorana = function(rest) {
		return $http.post('/menadzerRestoranaKontroler/izvestajPrihodaRestorana', rest);
	}
	
	temp.izlistajAktivnePorudzbine = function(menRest) {
		return $http.post('/menadzerRestoranaKontroler/izlistajAktivnePorudzbine', menRest);
	}
	
	temp.prikaziPonud = function(porudz) {
		return $http.post('/menadzerRestoranaKontroler/prikaziPonud', porudz);
	}
	
	temp.prihvatiPonudu = function(pon) {
		return $http.post('/menadzerRestoranaKontroler/prihvatiPonudu', pon);
	}
	
	temp.registrujKonobara = function(radnik) {
		return $http.post('/menadzerRestoranaKontroler/registrujKonobara', radnik);
	}
	
	temp.registrujKuvara = function(radnik) {
		return $http.post('/menadzerRestoranaKontroler/registrujKuvara', radnik);
	}
	
	temp.registrujSankera = function(radnik) {
		return $http.post('/menadzerRestoranaKontroler/registrujSankera', radnik);
	}
	
	temp.izlistajBojuStola = function(sto) {
		return $http.post('/menadzerRestoranaKontroler/izlistajBojuStola', sto);
	}
	
	temp.izlistajBojuStola1 = function(sto) {
		return $http.post('/menadzerRestoranaKontroler/izlistajBojuStola1', sto);
	}
	
	temp.izvestajZaKonobara = function(izvKon) {
		return $http.post('/menadzerRestoranaKontroler/izvestajZaKonobara', izvKon);
	}
	
	temp.izvestajPrihodaKonobara = function(izvKon) {
		return $http.post('/menadzerRestoranaKontroler/izvestajPrihodaKonobara', izvKon);
	}
	
	temp.prikaziGrafikPosecenosti = function(pos) {
		return $http.post('/menadzerRestoranaKontroler/prikaziGrafikPosecenosti', pos);
	}
	
	return temp;

})