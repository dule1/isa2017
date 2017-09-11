var konobarServis = angular.module('restoranApp.konobarServis', []);

konobarServis.factory('izmeniKonobarServis', function($http) {
	
	var temp = {};
	
	temp.izmeni = function(konobar) {
		return $http.post('/korisnikKontroler/izmeniKorisnika', konobar);
	}
	
	temp.izmeniLozinku = function (konobar){
		return $http.post('/korisnikKontroler/izmeniLozinkuKorisnika', konobar);
	}
	
	temp.izlistajJela = function (konobar){
		return $http.post('/konobarKontroler/ucitajJelaKonobara', konobar);
	}

	temp.izlistajPica = function (konobar){
		return $http.post('/konobarKontroler/ucitajPicaKonobara', konobar);
	}
	
	temp.izlistajStolove = function (konobar){
		return $http.post('/konobarKontroler/izlistajStolove', konobar);
	}
	
	temp.dodajPorudzbinu = function (jelaPica){
		return $http.post('/konobarKontroler/dodajPorudzbinu', jelaPica);
	}
	
	temp.potvrdiIzmene = function(jelaPica){
		return $http.post('/konobarKontroler/potvrdiIzmene', jelaPica);
	}

	temp.kreirajRacun = function(porKon){
		return $http.post('/konobarKontroler/kreirajRacun', porKon);
	}
	
	temp.kreiraj = function(porKon){
		return $http.post('/konobarKontroler/kreiraj', porKon);
	}

	temp.ucitajJelaPorudzbine = function(porudzbina){
		return $http.post('/konobarKontroler/ucitajJelaPorudzbine', porudzbina);
	}

	temp.ucitajPicaPorudzbine = function(porudzbina){
		return $http.post('/konobarKontroler/ucitajPicaPorudzbine', porudzbina);
	}
	
	temp.ucitajPorudzbine = function(konobar){
		return $http.post('/konobarKontroler/ucitajPorudzbine', konobar);
	}


	temp.ucitajPorudzbine = function(konobar){
		return $http.post('/konobarKontroler/ucitajPorudzbine', konobar);
	}
	
	
	temp.izmeniPorudzbinu = function(izmeniPorudzbinuPrikaz){
		return $http.post('/konobarKontroler/izmeniPorudzbinu', izmeniPorudzbinuPrikaz);
	}	
	
	temp.ucitajKonobareRestorana = function(konobar){
		return $http.post('/konobarKontroler/ucitajKonobareRestorana', konobar);
	}	

	temp.ucitajKalendarKonobara = function(konobar){
		return $http.post('/konobarKontroler/ucitajKalendarKonobara', konobar);
	}	
	
	temp.prihvatiPorudzbinu = function(porKon){
		return $http.post('/konobarKontroler/prihvatiPorudzbinu', porKon);
	}	
	
	temp.izlistajDostupneSmeneKonobarDan = function (param){
		return $http.post('/konobarKontroler/izlistajDostupneSmeneKonobarDan', param);
	}
	
	
	
	return temp;
	
})