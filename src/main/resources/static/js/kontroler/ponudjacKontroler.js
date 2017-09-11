var ponudjacKontroler = angular.module('restoranApp.ponudjacKontroler', []);


ponudjacKontroler.controller('ponudjacCtrl', function($location, gostGlavnaStranaServis, $scope, izmeniKonobarServis, ponudjacServisS, $window) {
	
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		$scope.cena = {};
		$scope.garancija = {};
		$scope.rokIsporuke = {};
		if(data.message == "NekoNaSesiji"){
			$scope.ulogovanKorisnik = data.obj;
			$scope.imeIzmena = data.obj.ime;
			$scope.prezimeIzmena = data.obj.prezime;
			$scope.emailIzmena = data.obj.email;
			
			$scope.setTab = function(newTab){
				if($scope.ulogovanKorisnik.logovaoSe == false){
					$scope.tab = 2;
					return;
				}
		    	$scope.tab = newTab;
		    };
		    $scope.setTab(1);
			
			// AKTIVNE PORUDZBINE
			// Ponudjac nije slao ponudu:
			ponudjacServisS.izlistajPorudzbineBezPonude(data.obj).success(function(data) {
				$scope.porudzbineMen = data;
				
			}).error(function(data) {
			});
			
			$scope.prikaziDetalje = function(porudzbina){
				ponudjacServisS.izlistajStavkePorudzbine(porudzbina).success(function(data) {
					$scope.stavke = data;
				}).error(function(data) {
				});
				
				$scope.selektovanaPorudzbinaId = porudzbina.id;
			}
			
			$scope.vidljivosPorudzbine = function(id){
				return $scope.selektovanaPorudzbinaId === id;
			}
			
			$scope.slanjePonude = function(porudzbina){

				var pon = {
					porudzbinamenadzer : porudzbina,
					ponudjac : data.obj,
					cena : $scope.cena[porudzbina.id],
					rokisporuke : $scope.rokIsporuke[porudzbina.id],
					garancija : $scope.garancija[porudzbina.id]
				}
				var str = JSON.stringify(pon);
				ponudjacServisS.posaljiPonudu(str);
			}
			
			// Ponudjac je poslao ponudu ali je moguca izmena:
			
			ponudjacServisS.izlistajPorudzbineSaPonudom(data.obj).success(function(data) {
				$scope.ponudePonudjaca = data;
			}).error(function(data) {
			});
			
			$scope.prikaziDetaljePon = function(porudzbina){
				ponudjacServisS.izlistajStavkePorudzbine(porudzbina).success(function(data) {
					$scope.stavkePon = data;
				}).error(function(data) {
				});
				
				$scope.selektovanaPorudzbinaPonId = porudzbina.id;
			}
			
			$scope.vidljivosPorudzbinePon = function(id){
				return $scope.selektovanaPorudzbinaPonId === id;
			}
			
			$scope.izmenaPonude = function(ponuda){
				var pon = {
					id : ponuda.id,
					porudzbinamenadzer : ponuda.porudzbinamenadzer,
					ponudjac : ponuda.ponudjac,
					cena : ponuda.cena,
					rokisporuke : ponuda.rokisporuke,
					garancija : ponuda.garancija
				}
				var str = JSON.stringify(pon);
				ponudjacServisS.izmeniPonudu(str).success(function(data) {
					if(data.message == "false"){
						alert("Nije moguce izmeni, menadzer restorana je odabrao neku od ponuda!");
					}else if(data.message == "true"){
						alert("Uspesno izmenjeno");
					}
				});
			}
		}else{
			$window.location.href = '/';
		}
	});
	
	$scope.logOut = function(){
		gostGlavnaStranaServis.logOut().success(function(data) {
			if(data.message == "Izlogovan"){
				$window.location.href = '/';
			}else{
			}
		});
	}

    $scope.isSet = function(tabNum){   
    	return $scope.tab === tabNum;
    };
    
	$scope.changedValue = function(item) {
		$scope.zaPrikaz = [];
		for (var i = 0; i<$scope.news.length; i++){
			if ($scope.news[i].type == item)
				$scope.zaPrikaz.push($scope.news[i]);
		}
	};
	
	$scope.isVisible = function(id){
		return $scope.izmeniP === id;
	}
	
	$scope.izmeniPonudu = function(idPonude){		
		$scope.izmeniP = idPonude;
	}
	
	$scope.izmeniPonudjaca = function(){
		
		var ponudjac = {
			id : $scope.ulogovanKorisnik.id,
			ime : $scope.imeIzmena,
			prezime : $scope.prezimeIzmena,
			email : $scope.emailIzmena,
			sifra : $scope.staraLozinka
			// DOPUNITI, PROVERITI...
			//sifra : $scope.novaLozinka
		}
		
		var str = JSON.stringify(ponudjac);
		
		ponudjacServisS.izmeni(str).success(function(data) {
			alert("uspeo!");
		}).error(function(data) {
			alert("nisi uspeo!");
		});
	}
	
	
	$scope.izlistajPonude = function(){
		
		var ponudjac = {
			id : $scope.ulogovanKorisnik.id,
			ime : $scope.imeIzmena,
			prezime : $scope.prezimeIzmena,
			email : $scope.emailIzmena,
			sifra : $scope.staraLozinka
			// DOPUNITI, PROVERITI...
			//sifra : $scope.novaLozinka
		}
		
		var str = JSON.stringify(ponudjac);
		
		ponudjacServisS.izlistaj(str).success(function(data) {
			alert("uspeo izl!");
			$scope.items = data;
		}).error(function(data) {
			alert("nisi uspeo izl!");
		});
	}
	
	// za izmeenu podataka
	$scope.izmeniKonobaraPodaci = function(){
		var gost = {
			ime : $scope.imeIzmena,
			prezime : $scope.prezimeIzmena,
			email : $scope.emailIzmena,
			id : $scope.ulogovanKonobar.id,
		}
		var str = JSON.stringify(gost);
		izmeniKonobarServis.izmeni(str).success(function(data) {
				$scope.ulogovanKonobar = data;
			}).error(function(data) {
				alert("Neuspesne izmene!");
			});
	}
	
	$scope.izmeniLozinku = function (){
		if($scope.novaLozinka == $scope.novaLozinkaPotvrda){
			var gost = {
				id : $scope.ulogovanKorisnik.id,
				sifraStara : $scope.staraLozinka,
				sifra : $scope.novaLozinkaPotvrda
			}
			var str = JSON.stringify(gost);
			
			izmeniKonobarServis.izmeniLozinku(str).success(function (data){
				$scope.staraLozinka = "";
				$scope.novaLozinka = "";
				$scope.novaLozinkaPotvrda = "";
				$scope.ulogovanKorisnik = data;
				alert("Uspesno promenjena lozinka");
				$location.path('/ponudjac');
			}).error(function (data){
				alert("Neuspesne izmene!");
			});
		} else {
			alert ("Ne podudaraju se nove lozinke")
		}
	}
});