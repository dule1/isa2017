var kuvarKontroler = angular.module('restoranApp.kuvarKontroler', []);

kuvarKontroler.controller('kuvarCtrl', function($window, $scope, $location, logovanjeServis, gostGlavnaStranaServis, izmeniKuvarServis){
	
	
	$scope.odabranKuvar = null;
	$scope.osveziPrikazZaIzmenu = function (kuvar){
		$scope.imeIzmena = kuvar.ime;
		$scope.prezimeIzmena = kuvar.prezime
		$scope.emailIzmena = kuvar.email
	}
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		console.log(data.obj.ime + "ULOGOVANI KUVAR");
		if(data.message == "NekoNaSesiji"){
			$scope.ulogovanKuvar = data.obj;
			$scope.osveziPrikazZaIzmenu($scope.ulogovanKuvar);
			
			$scope.setTab = function(newTab){
				if($scope.ulogovanKuvar.logovaoSe == false){
					$scope.tab = 3;
					return;
				}
		    	$scope.tab = newTab;
		    };
		    $scope.setTab(0);
		    
		    $scope.isSet = function(tabNum){   
		    	return $scope.tab === tabNum;
		    };
			
		    
		    // KLIKNUO NA DETALJI PRIHVACENE
		    
		    
			// za izmeenu podataka
			$scope.izmeniKuvaraPodaci = function(){
				if($scope.emailIzmena == null){
					alert("Los email");
					return;
				}
				var gost = {
					ime : $scope.imeIzmena,
					prezime : $scope.prezimeIzmena,
					email : $scope.emailIzmena,
					id : $scope.ulogovanKuvar.id,
				}
				var str = JSON.stringify(gost);
				izmeniKuvarServis.izmeni(str).success(function(data) {
					$scope.ulogovanKuvar = data;
					alert("Uspesno promenjeni podaci");
					$scope.setTab(0);
					$scope.osveziPrikazZaIzmenu($scope.ulogovanKuvar);	
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
						id : $scope.ulogovanKuvar.id,
						sifraStara : $scope.staraLozinka,
						sifra : $scope.novaLozinkaPotvrda
					}
					var str = JSON.stringify(gost);
					
					izmeniKuvarServis.izmeniLozinku(str).success(function (data){
						$scope.staraLozinka = "";
						$scope.novaLozinka = "";
						$scope.novaLozinkaPotvrda = "";
						$scope.ulogovanKuvar = data;
						alert("Uspesno promenjena lozinka");
						$scope.setTab(0);
					}).error(function (data){
						alert("Neuspesne izmene!");
					});
				} else {
					alert ("Ne podudaraju se nove lozinke")
				}
				
			}
			//TODO: Obrisati funkcije koje su visak iz kuvar kontrolera i servisa
			
			// UCITAVANJE PORUDZBINA
			$scope.porudzbine = [];
			izmeniKuvarServis.ucitajPorudzbine($scope.ulogovanKuvar).success(function(data) {
				
				$scope.porudzbine = data;
				if ($scope.porudzbine.length == 0){
					$scope.setTab(0);
				}
				
			}).error(function (data){
				alert("Neuspelo ucitavanje porudzbina");
			});
			
			// UCITAVANJE KLAS PORUDZBINA
			$scope.klasifikovanePorudzbine = [];
			izmeniKuvarServis.ucitajPorudzbineKlasifikovane($scope.ulogovanKuvar).success(function(data) {
				$scope.klasifikovanePorudzbine = data;	
			}).error(function (data){
				alert("Neuspelo ucitavanje porudzbina");
			});
			
			
			// Kliknuce na detalji moguce
			$scope.jelaKliknuteMogucePorudzbine = [];
			$scope.kliknuoNaDetaljiMoguce = function (porudzbina){
				var porKur = {
					porudzbina: porudzbina,
					kuvar: $scope.ulogovanKuvar
				}
				
				izmeniKuvarServis.ucitajJelaPorudzbine(porKur).success(function(data){
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
			$scope.jelaKliknutePrihvacenePorudzbine = [];
			$scope.kliknuoNaDetaljiPrihvacene = function (porudzbina){
				var porKur = {
					porudzbina: porudzbina,
					kuvar: $scope.ulogovanKuvar
				}
				
				izmeniKuvarServis.ucitajJelaPorudzbine(porKur).success(function(data){
					$scope.picaKliknutePrihvacenePorudzbine = data;
					if ($scope.showPrihvacene == porudzbina.id)
						$scope.showPrihvacene = -1;
					else
						$scope.showPrihvacene = porudzbina.id;
					
				}).error(function (data){
					alert("Neuspelo ucitavanje detalja");
				});
			}
			

		    $scope.promenjenKuvar = function(){
		    	
	    		$scope.ponedeljakKuvara = null;
    			$scope.utorakKuvara = null;	
    			$scope.sredaKuvara = null;	
				$scope.cetvrtakKuvara = null;	
				$scope.petakKuvara = null;	
    			$scope.subotaKuvara = null;	
    			$scope.nedeljaKuvara = null;	
			
		    	$scope.odabranKuvar = $scope.selektovaniKuvar;
		    	if($scope.odabranKuvar != null){
		    		izmeniKuvarServis.ucitajKalendarKuvara($scope.odabranKuvar).success(function (data){
		    			$scope.kalendarOdabranogKuvara = data;
		    			for (var i = 0; i < $scope.kalendarOdabranogKuvara.length; i++){
		    				if ($scope.kalendarOdabranogKuvara[i].danUNedelji == "PONEDELJAK"){
				    			$scope.ponedeljakKuvara = $scope.kalendarOdabranogKuvara[i].smena;
		    				} else if ($scope.kalendarOdabranogKuvara[i].danUNedelji == "UTORAK"){
				    			$scope.utorakKuvara = $scope.kalendarOdabranogKuvara[i].smena;	
		    				} else if ($scope.kalendarOdabranogKuvara[i].danUNedelji == "SREDA"){
				    			$scope.sredaKuvara = $scope.kalendarOdabranogKuvara[i].smena;	
		    				} else if ($scope.kalendarOdabranogKuvara[i].danUNedelji == "CETVRTAK"){
				    			$scope.cetvrtakKuvara = $scope.kalendarOdabranogKuvara[i].smena;	
		    				} else if ($scope.kalendarOdabranogKuvara[i].danUNedelji == "PETAK"){
				    			$scope.petakKuvara = $scope.kalendarOdabranogKuvara[i].smena;	
		    				} else if ($scope.kalendarOdabranogKuvara[i].danUNedelji == "SUBOTA"){
				    			$scope.subotaKuvara = $scope.kalendarOdabranogKuvara[i].smena;	
		    				} else if ($scope.kalendarOdabranogKuvara[i].danUNedelji == "NEDELJA"){
				    			$scope.nedeljaKuvara = $scope.kalendarOdabranogKuvara[i].smena;	
		    				} 
		    			}
		    			
		    		}).error(function (data){
		    			
		    		});
		    	} else {
		    		alert("Niste odabrali kuvara");
		    	}
		    }
		    
			// Kliknuo na detalje kalendar
			
			$scope.danasnjiDatum = new Date();
			$scope.danasnjiDan = $scope.danasnjiDatum.getDay();

			$scope.prikaziDan = -1;
			$scope.prikaziSmene = function(index){
				if ($scope.prikaziDan == index){
					$scope.prikaziDan = -1;
				} else {
					$scope.prikaziDan = index;
				}
				
			}
			
			$scope.stringuj = function(s){
				return s.getDate() + "-" + (s.getMonth()+1) + "-"+ s.getFullYear();
			}
			
			$scope.setuj = function (){
				$scope.datumPonedeljakStr = $scope.stringuj($scope.datumPonedeljak);
				$scope.datumUtorakStr = $scope.stringuj($scope.datumUtorak);
				$scope.datumSredaStr = $scope.stringuj($scope.datumSreda);
				$scope.datumCetvrtakStr = $scope.stringuj($scope.datumCetvrtak);
				$scope.datumPetakStr = $scope.stringuj($scope.datumPetak);
				$scope.datumSubotaStr = $scope.stringuj($scope.datumSubota);
				$scope.datumNedeljaStr = $scope.stringuj($scope.datumNedelja);
			}
			
			var tomorrow = new Date();
			$scope.datumNedelja = new Date();
			$scope.datumSubota = new Date();
			$scope.datumPetak = new Date();
			$scope.datumCetvrtak = new Date();
			$scope.datumSreda = new Date();
			$scope.datumUtorak = new Date();
			$scope.datumPonedeljak = new Date();
			
			
			if ($scope.danasnjiDan == 1){
				$scope.datumPonedeljak = $scope.danasnjiDatum;
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 2){
				$scope.datumUtorak = $scope.danasnjiDatum;
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 3){
				$scope.datumSreda = $scope.danasnjiDatum;
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 4){
				$scope.datumCetvrtak = $scope.danasnjiDatum;
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 5){
				$scope.datumPetak = $scope.danasnjiDatum;
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 6){
				$scope.datumSubota = $scope.danasnjiDatum;
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 0){
				$scope.datumNedelja = $scope.danasnjiDatum;
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} 
			
			// UCITAJ KUVARE RESTORANA
			$scope.kuvariRestorana = [];
			izmeniKuvarServis.ucitajKuvareRestorana($scope.ulogovanKuvar).success(function(data) {
				$scope.kuvariRestorana = data;
				}).error(function (data){
				alert("Neuspelo ucitavanje kuvara");
			});
			
			
			/// Kliknuo prihvati
			$scope.prihvati = function (porudzbina){
				var sanKuv = {
						kuvar: $scope.ulogovanKuvar,
						porudzbina: porudzbina	
				}
				izmeniKuvarServis.prihvatiPorudzbinu(sanKuv).success(function(data){
					$scope.klasifikovanePorudzbine = data;	
				}).error(function(data){
					alert("Nemoguce preuzeti ponudu.");
				});
			}
			
			$scope.zavrsi = function (porudzbina){
				var sanKuv = {
						kuvar: $scope.ulogovanKuvar,
						porudzbina: porudzbina	
				}
				izmeniKuvarServis.zavrsiPorudzbinu(sanKuv).success(function(data){
					$scope.klasifikovanePorudzbine = data;	
				}).error(function(data){
					alert("Nemoguce zavrsiti porudzbinu");
				});
			}
			
			
			
		}else{
			$window.location.href = '/';
		}
	});
})