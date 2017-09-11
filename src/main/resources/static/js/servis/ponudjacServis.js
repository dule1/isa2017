var ponudjacServis = angular.module('restoranApp.ponudjacServis', []);

ponudjacServis.factory('ponudjacServisS', function($http) {
	
	var temp = {};
	
	temp.izmeni = function(ponudjac) {
		return $http.post('/ponudjacKontroler/izmeniPonudjaca', ponudjac);
	}
	
	temp.izlistaj = function(ponudjac) {
		return $http.post('/ponudjacKontroler/izlistajPonude', ponudjac);
	}
	
	temp.izlistajPorudzbineBezPonude = function(ponudjac) {
		return $http.post('/ponudjacKontroler/izlistajPorudzbineBezPonude', ponudjac);
	}
	
	temp.izlistajStavkePorudzbine = function(porudzbina) {
		return $http.post('/ponudjacKontroler/izlistajStavkePorudzbine', porudzbina);
	}
	
	temp.posaljiPonudu = function(ponuda) {
		return $http.post('/ponudjacKontroler/posaljiPonudu', ponuda);
	}
	
	temp.izlistajPorudzbineSaPonudom = function(ponudjac) {
		return $http.post('/ponudjacKontroler/izlistajPorudzbineSaPonudom', ponudjac);
	}
	
	temp.izmeniPonudu = function(ponuda) {
		return $http.post('/ponudjacKontroler/izmeniPonudu', ponuda);
	}
	
	return temp;
	
})