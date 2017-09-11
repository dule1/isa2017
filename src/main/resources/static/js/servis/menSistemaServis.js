var menSistemaServis = angular.module('restoranApp.menSistemaServis', []);

menSistemaServis.factory('menSistemaServis', function($http){
	
	var temp = {};
	
	temp.registrujRestoran = function(restoran){
		return $http.post ('/menSistemaKontroler/registrujRestoran', restoran);
	}
	
	temp.izlistajRestorane = function(){
		return $http.post ('/menSistemaKontroler/izlistajRestorane')
	}
	
	temp.registrujMenadzeraRestorana = function (menadzer){
		return $http.post ('/menSistemaKontroler/registrujMenadzeraRestorana', menadzer);
	}
	
	temp.registrujMenadzeraSistema = function (menadzer){
		return $http.post ('/menSistemaKontroler/registrujMenadzeraSistema', menadzer);
	}
	
	return temp;
	
})