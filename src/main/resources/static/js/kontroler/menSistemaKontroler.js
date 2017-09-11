var menSistemaKontroler = angular.module ('restoranApp.menSistemaKontroler', []);

menSistemaKontroler.controller('menSistemaCtrl', function (gostGlavnaStranaServis, $scope, menSistemaServis, $window){

	// REGISTROVANJE MENADZERA SISTEMA - POSEBNI
	
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		if(data.message == "NekoNaSesiji"){
			$scope.ulogovanKorisnik = data.obj;
			$scope.jeGlavni = function (){
				if ($scope.ulogovanKorisnik.glavni == true)
					return true;
				else 
					return false;
			}
		}else{
			$window.location.href = '/';
		}
	});
	
	//za selektovanje tabova
	
    $scope.setTab = function(newTab){
    	if (newTab == 1){
    		menSistemaServis.izlistajRestorane().success(function(data){
    			$scope.items = data;
    			if(data.length == 0){
    				alert("Nema raspolozivih restorana");
    				$scope.setTab(0);
    			}
    		}).error (function (data){
    			alert("Neuspesno ucitavanje restorana");
    		});
    	}
    	$scope.tab = newTab;
    };

    $scope.isSet = function(tabNum){   
    	return $scope.tab === tabNum;
    };
    
	$scope.setTab(0);
	
	// REGISTROVANJE RESTORANA
	$scope.registrujRestoran = function(){
		var restoran = {
			naziv: $scope.nazRes,
			opis: $scope.opisRes
		}
		var str = JSON.stringify(restoran);
		menSistemaServis.registrujRestoran(str).success(function (data){	
			alert("Uspesno registrovanje restorana")
		}).error(function (data){
			//alert("Neuspesno registrovanje, moguce da postoji korisnik sa tim email-om")
		});
	}
	
	// REGISTROVANJE MENADZERA RESTORANA
	$scope.registrovanjeMenadzeraRestorana = function(){
		var korisnik = {
			ime : $scope.imeMR,
			prezime : $scope.prezimeMR,
			email : $scope.emailMR,
			sifra : $scope.sifraMR,
			restoran : $scope.restoranMR
		}
		
		var str = JSON.stringify(korisnik);
		
		menSistemaServis.registrujMenadzeraRestorana(str).success(function (data){	
			alert("Uspesno registrovanje menadzera restorana")
		}).error(function (data){
			alert("Neuspesno registrovanje, moguce da postoji korisnik sa tim email-om")
		});	
	}
	
	$scope.registrovanjeMenadzeraSistema = function(){
		var korisnik = {
			ime : $scope.imeMS,
			prezime : $scope.prezimeMS,
			email : $scope.emailMS,
			sifra : $scope.sifraMS,
		}
		
		var str = JSON.stringify(korisnik);
		
		menSistemaServis.registrujMenadzeraSistema(str).success(function (data){	
			alert("Uspesno registrovanje menadzera sistema")
		}).error(function (data){
			alert("Neuspesno registrovanje, moguce da postoji korisnik sa tim email-om")
		});		
	}
	
	$scope.logOut = function(){
		gostGlavnaStranaServis.logOut().success(function(data) {
			if(data.message == "Izlogovan"){
				$window.location.href = '/';
			}else{
			}
		});
	}
});