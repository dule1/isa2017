var sankerServis = angular.module('restoranApp.sankerServis', []);

sankerServis.factory('izmeniSankerServis', function($http) {

	var temp = {};
	
	temp.izmeni = function(gost) {
		return $http.post('/korisnikKontroler/izmeniKorisnika', gost);
	}
	
	temp.izmeniLozinku = function (gost){
		return $http.post('/korisnikKontroler/izmeniLozinkuKorisnika', gost);
	}
	
	temp.ucitajPorudzbine = function(sanker){
		return $http.post('/sankerKontroler/ucitajPorudzbine', sanker);
	}
	
	temp.ucitajPorudzbineKlasifikovane = function(sanker){
		return $http.post('/sankerKontroler/ucitajPorudzbineKlasifikovane', sanker);
	}

	temp.ucitajPicaPorudzbine = function (porudzbina){
		return $http.post('/sankerKontroler/ucitajPicaPorudzbine', porudzbina);
	}
	
	temp.prihvatiPorudzbinu = function (sankon){
		return $http.post('/sankerKontroler/prihvatiPorudzbinu', sankon);
	}
	
	temp.zavrsiPorudzbinu = function (porudzbina){
		return $http.post('/sankerKontroler/zavrsiPorudzbinu', porudzbina);
	}
	
	temp.ucitajSankerRestorana = function(kuvar){
		return $http.post('/sankerKontroler/ucitajSankereRestorana', kuvar);
	}	

	temp.ucitajKalendarSankera = function(kuvar){
		return $http.post('/sankerKontroler/ucitajKalendarSankera', kuvar);
	}	
	
	
	
	return temp;
	
})