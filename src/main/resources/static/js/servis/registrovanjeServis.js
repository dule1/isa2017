var registrovanjeServis = angular.module('restoranApp.registrovanjeServis', []);

registrovanjeServis.factory('registrovanjeServis', function($http) {
	
	var temp = {};
	
	temp.registrujKorisnika = function(korisnik) {
		return $http.post('/contr/create', korisnik);
	}
	return temp;
	
})
