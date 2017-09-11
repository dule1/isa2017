var sankerKontroler = angular.module('restoranApp.sankerKontroler', []);

sankerKontroler.controller('sankerCtrl', function($window, $scope, $route, $location, logovanjeServis, gostGlavnaStranaServis, izmeniSankerServis){
	
	$scope.osveziPrikazZaIzmenu = function (sanker){
		$scope.imeIzmena = sanker.ime;
		$scope.prezimeIzmena = sanker.prezime
		$scope.emailIzmena = sanker.email
	}
	
	
	
	//TODO: Kada porudzbina nema pica ne prikazati je
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		console.log(data.ime + "ULOGOVANI SANKER");
		if(data.message == "NekoNaSesiji"){
			$scope.ulogovanSanker = data.obj;
			$scope.osveziPrikazZaIzmenu($scope.ulogovanSanker);
			
			$scope.setTab = function(newTab){
				if($scope.ulogovanSanker.logovaoSe == false){
					$scope.tab = 3;
					return;
				}
		    	$scope.tab = newTab;
		    };

		    $scope.isSet = function(tabNum){  
		    	return $scope.tab === tabNum;
		    };
			
			$scope.setTab(0);
			
			// za izmeenu podataka
			$scope.izmeniSankeraPodaci = function(){
				var gost = {
					ime : $scope.imeIzmena,
					prezime : $scope.prezimeIzmena,
					email : $scope.emailIzmena,
					id : $scope.ulogovanSanker.id,
				}
				var str = JSON.stringify(gost);
				izmeniSankerServis.izmeni(str).success(function(data) {
						$scope.ulogovanSanker = data;
						$scope.osveziPrikazZaIzmenu($scope.ulogovanSanker);
					}).error(function(data) {
						alert("Neuspesne izmene!");
					});
			}
			
			
			/// ODLOGUJ SE
			$scope.logOut = function(){

				gostGlavnaStranaServis.logOut().success(function(data) {
					if(data.message == "Izlogovan"){
						$window.location.href = '/';
					}else{
					}
				});
			}
			// za izmenu lozinke
			
			$scope.izmeniLozinku = function (){
				if($scope.novaLozinka == $scope.novaLozinkaPotvrda){
					var gost = {
						id : $scope.ulogovanSanker.id,
						sifraStara : $scope.staraLozinka,
						sifra : $scope.novaLozinkaPotvrda
					}
					var str = JSON.stringify(gost);
					
					izmeniSankerServis.izmeniLozinku(str).success(function (data){
						$scope.staraLozinka = "";
						$scope.novaLozinka = "";
						$scope.novaLozinkaPotvrda = "";
						$scope.ulogovanSanker = data;
						alert("Uspesno promenjena lozinka");
						$location.path('/sanker');
					}).error(function (data){
						alert("Neuspesne izmene!");
					});
				} else {
					alert ("Ne podudaraju se nove lozinke")
				}
				
			}
			
			

			// UCITAVANJE PORUDZBINA
			$scope.porudzbine = [];
			izmeniSankerServis.ucitajPorudzbine($scope.ulogovanSanker).success(function(data) {
				$scope.porudzbine = data;
				if ($scope.porudzbine.length == 0){
					alert("Nema raspolozivih porudzbina");
					$scope.setTab(0);
				}
				
			}).error(function (data){
				alert("Neuspelo ucitavanje porudzbina");
			});
			
			// UCITAVANJE MOGUCIH PORUDZBINA
			$scope.klasifikovanePorudzbine = [];
			izmeniSankerServis.ucitajPorudzbineKlasifikovane($scope.ulogovanSanker).success(function(data) {
				$scope.klasifikovanePorudzbine = data;	
				//TODO: Hendluj ako je prazna neka od lista
			}).error(function (data){
				alert("Neuspelo ucitavanje mogucih porudzbina");
			});
			
			// Kliknuce na detalji			
			$scope.picaKliknutePorudzbine = [];
			$scope.kliknuoNaDetalji = function (porudzbina){
				izmeniSankerServis.ucitajPicaPorudzbine(porudzbina).success(function(data){
					$scope.picaKliknutePorudzbine = data;
					if ($scope.show == porudzbina.id)
						$scope.show = -1;
					else
						$scope.show = porudzbina.id;
					
				}).error(function (data){
					alert("Neuspelo ucitavanje detalja");
				});
			}
			
			// Kliknuce na detalji moguce		
			$scope.picaKliknuteMogucePorudzbine = [];
			$scope.kliknuoNaDetaljiMoguce = function (porudzbina){
				izmeniSankerServis.ucitajPicaPorudzbine(porudzbina).success(function(data){
					$scope.picaKliknuteMogucePorudzbine = data;
					if ($scope.showMoguce == porudzbina.id)
						$scope.showMoguce = -1;
					else
						$scope.showMoguce = porudzbina.id;
					
				}).error(function (data){
					alert("Neuspelo ucitavanje detalja");
				});
			}

			// Kliknuce na detalji prihvacene		
			$scope.picaKliknutePrihvacenePorudzbine = [];
			$scope.kliknuoNaDetaljiPrihvacene = function (porudzbina){
				izmeniSankerServis.ucitajPicaPorudzbine(porudzbina).success(function(data){
					$scope.picaKliknutePrihvacenePorudzbine = data;
					if ($scope.showPrihvacene == porudzbina.id)
						$scope.showPrihvacene = -1;
					else
						$scope.showPrihvacene = porudzbina.id;
					
				}).error(function (data){
					alert("Neuspelo ucitavanje detalja");
				});
			}
			
			// PROMENJEN Sanker
			$scope.promenjenSanker = function(){
	    		$scope.ponedeljakSankera = null;
    			$scope.utorakSankera = null;	
    			$scope.sredaSankera = null;	
				$scope.cetvrtakSankera = null;	
				$scope.petakSankera = null;	
    			$scope.subotaSankera = null;	
    			$scope.nedeljaSankera = null;	
			
		    	$scope.odabranSanker = $scope.selektovaniSanker;
		    	if($scope.odabranSanker != null){
		    		izmeniSankerServis.ucitajKalendarSankera($scope.odabranSanker).success(function (data){
		    			$scope.kalendarOdabranogSankera = data;
		    			for (var i = 0; i < $scope.kalendarOdabranogSankera.length; i++){
		    				if ($scope.kalendarOdabranogSankera[i].danUNedelji == "PONEDELJAK"){
				    			$scope.ponedeljakSankera = $scope.kalendarOdabranogSankera[i].smena;
		    				} else if ($scope.kalendarOdabranogSankera[i].danUNedelji == "UTORAK"){
				    			$scope.utorakSankera = $scope.kalendarOdabranogSankera[i].smena;	
		    				} else if ($scope.kalendarOdabranogSankera[i].danUNedelji == "SREDA"){
				    			$scope.sredaSankera = $scope.kalendarOdabranogSankera[i].smena;	
		    				} else if ($scope.kalendarOdabranogSankera[i].danUNedelji == "CETVRTAK"){
				    			$scope.cetvrtakSankera = $scope.kalendarOdabranogSankera[i].smena;	
		    				} else if ($scope.kalendarOdabranogSankera[i].danUNedelji == "PETAK"){
				    			$scope.petakSankera = $scope.kalendarOdabranogSankera[i].smena;	
		    				} else if ($scope.kalendarOdabranogSankera[i].danUNedelji == "SUBOTA"){
				    			$scope.subotaSankera = $scope.kalendarOdabranogSankera[i].smena;	
		    				} else if ($scope.kalendarOdabranogSankera[i].danUNedelji == "NEDELJA"){
				    			$scope.nedeljaSankera = $scope.kalendarOdabranogSankera[i].smena;	
		    				} 
		    			}
		    			
		    		}).error(function (data){
		    			
		    		});
		    	} else {
		    		alert("Niste odabrali Sankera");
		    	}
		    }
			

			
			// UCITAJ Sankere RESTORANA
			$scope.sankeriRestorana = [];
			izmeniSankerServis.ucitajSankerRestorana($scope.ulogovanSanker).success(function(data) {

				$scope.sankeriRestorana = data;
				}).error(function (data){
				alert("Neuspelo ucitavanje sankera");
			});

			// Kliknuo prihvati
			$scope.prihvati = function (porudzbina){
				var sanKon = {
					sanker: $scope.ulogovanSanker,
					porudzbina: porudzbina	
				}
				izmeniSankerServis.prihvatiPorudzbinu(sanKon).success(function(data){
					$scope.klasifikovanePorudzbine = data;	
				}).error(function(data){
					alert("Neuspesno prihvacena ponuda.");
				});

			}
			// kliknuo zavrsi
			$scope.zavrsi = function (porudzbina){
				izmeniSankerServis.zavrsiPorudzbinu(porudzbina).success(function(data){
					$scope.klasifikovanePorudzbine = data;	
				}).error(function(data){
					alert("PorudzbinaNeuspesnoZavrsena")
				});
				
			}
			
			
			
			
			
		}else{

			$window.location.href = '/';
		}
	});
})