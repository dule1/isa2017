var kuvarServis = angular.module('restoranApp.kuvarServis', []);

kuvarServis.factory('izmeniKuvarServis', function($http) {
	
	var temp = {};
	
	temp.izmeni = function(gost) {
		return $http.post('/korisnikKontroler/izmeniKorisnika', gost);
	}
	
	temp.izmeniLozinku = function (gost){
		return $http.post('/korisnikKontroler/izmeniLozinkuKorisnika', gost);
	}
	
	temp.ucitajPorudzbine = function(kuvar){
		return $http.post('/kuvarKontroler/ucitajPorudzbine', kuvar);
	}
	
	temp.ucitajPorudzbineKlasifikovane = function(kuvar){
		return $http.post('/kuvarKontroler/ucitajPorudzbineKlasifikovane', kuvar);
	}
	
	temp.ucitajJelaPorudzbine = function(porKuv){
		return $http.post('/kuvarKontroler/ucitajJelaPorudzbine', porKuv);
	}
	
	temp.prihvatiPorudzbinu = function (porKuv){
		return $http.post('/kuvarKontroler/prihvatiPorudzbinu', porKuv);
	}
	
	temp.zavrsiPorudzbinu = function (porKuv){
		return $http.post('/kuvarKontroler/zavrsiPorudzbinu', porKuv);
	}
	
	temp.ucitajKuvareRestorana = function(kuvar){
		return $http.post('/kuvarKontroler/ucitajKuvareRestorana', kuvar);
	}	

	temp.ucitajKalendarKuvara = function(kuvar){
		return $http.post('/kuvarKontroler/ucitajKalendarKuvara', kuvar);
	}	

	return temp;
	
})