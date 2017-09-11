var logovanjeServis = angular.module('restoranApp.logovanjeServis', []);

logovanjeServis.factory('logovanjeServis', function($http) {
	
	var temp = {};
	
	temp.ulogujKorisnika = function(korisnik) {
		return $http.post('/contr/login', korisnik);
	}
	
	return temp;
	
})