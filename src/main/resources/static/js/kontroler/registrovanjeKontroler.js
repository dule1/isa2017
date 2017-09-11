var registarKontroler = angular.module('restoranApp.registrovanjeKontroler', []);

registarKontroler.controller('registrovanjeCtrl', function($scope, registrovanjeServis, $location, $window) {

	$scope.registrovanje = function(){
		var korisnik = {
			ime : $scope.ime,
			prezime : $scope.prezime,
			email : $scope.email,
			sifra : $scope.sifra,
			sifraStara : $scope.gostSifraPotvrda
		}
		
		var str = JSON.stringify(korisnik);
		
		registrovanjeServis.registrujKorisnika(str).success(function(data){
			if(data.message == "ZauzetEmail"){
				alert("Mejl je zauzet");
			}else if(data.message == "Registrovan"){
				alert("Link za aktivaciju naloga Vam je poslat na email.");
				$window.location.href = '/';
			}else if(data.message == "RazliciteSifre"){
				alert("Sifre moraju da se podudaraju.");
			}
		});
		
	}
	
})