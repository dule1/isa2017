var menRestoranaKontroler = angular.module('restoranApp.menRestoranaKontroler', []);

menRestoranaKontroler.controller('menadzerRestoranaCtrl', function(gostGlavnaStranaServis, $scope, menRestoranaServisS, $window) {
	$scope.radniciTip = ["", "Konobar", "Kuvar", "Sanker"];
	$scope.tipRadnika = "";
	$scope.tipoviJela = ["Peceno", "Kuvano", "Salata"];
	$scope.selektovanTip = "";
	$scope.restoran = null;
	var danString = "";
	$scope.odabranaPorudz = null;
	
	// Pokusaj
	//$scope.nijesto = {};
	//$scope.popnunjen = {};
	//$scope.nijePopunjen = {};
	$scope.pokusaj = [];
	$scope.pojedSto = [];
	$scope.ponN = [];
	$scope.utoN = [];
	$scope.sreN = [];
	$scope.cetN = [];
	$scope.petN = [];
	$scope.subN = [];
	$scope.nedN = [];
	
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		$scope.brRedova = [];
		$scope.brKolona = [];
		$scope.listaStavki = [];
		$scope.brojRedova = 0;
		$scope.brojKolona = 0;
		
		if(data.message == "NekoNaSesiji"){
			var menRest = {
				id : data.obj.id,
				ime : data.obj.ime,
				prezime : data.obj.prezime,
				email : data.obj.email,
				sifra : data.obj.sifra
			}
			
			
			var str = JSON.stringify(menRest);
			
			menRestoranaServisS.izlistajRestoran(str).success(function(data) {
				$scope.restoran = data;
				
				menRestoranaServisS.izlistajJela(data).success(function(data) {
					// TODO: Aca ~ Refesh
					$scope.jela = data;
				}).error(function(data) {
					// TODO: Aca ~ Uraditi slucaj ako nema jela
					alert("Neuspesno izlistavanje jelovnika!");
				});
				
				menRestoranaServisS.izlistajPica(data).success(function(data) {
					// TODO: Aca ~ Refesh
					$scope.pica = data;
				}).error(function(data) {
					// TODO: Aca ~ Uraditi slucaj ako nema pica
					alert("Neuspesno izlistavanje pica!");
				});
				
				menRestoranaServisS.izlistajDostupnePonudjace(data).success(function(data) {
					$scope.moguciPonudjaci = data;
				}).error(function(data) {
					alert("Neuspesno izlistavanje dostupnih ponudjaca!");
				});
				
				if($scope.restoran.brojredova != 0 && $scope.restoran.brojkolona != 0){
					$scope.brojRedova = $scope.restoran.brojredova;
					$scope.brojKolona = $scope.restoran.brojkolona;

					for(z=0; z<$scope.brojRedova*$scope.brojKolona; z++){
						
						var smenaDan = {
							oznaka : z,
							restoran : $scope.restoran,
						}
						var str = JSON.stringify(smenaDan);
						menRestoranaServisS.izlistajBojuStola(str).success(function(aaa) {
							$scope.pojedSto[aaa.obj] = aaa.message;
						}).error(function(data) {
							$scope.pojedSto[aaa.obj] = "nijesto";
						});
					}
					
					for(i=0; i<$scope.brojRedova; i++){
						$scope.brRedova.push(i);
					}
					for(i=0; i<$scope.brojKolona; i++){
						$scope.brKolona.push(i);
					}
					
					$scope.promenaRadnika = function(x){
						alert(x);
					}
				}
				
				menRestoranaServisS.izlistajSmene($scope.restoran).success(function(data) {
					$scope.smeneRestorana = data;
				}).error(function(data) {
				});
			}).error(function(data) {
				alert("Neuspesno izlistavanje restorana!");
			});
			
			$scope.dodajSmenu = function(){

				var smenaa = {
					vremeod : $scope.vremeSmeneOd,
					vremedo : $scope.vremeSmeneDo,
					restoran : $scope.restoran
				}
				
				var str = JSON.stringify(smenaa);
				menRestoranaServisS.dodajSmenu(str);
			}
			
			$scope.promenjenTip = function(){	//aaa
				$scope.selektovanTip = $scope.tipRadnika;
				
				if($scope.prikaziDan == 1){
					danString = "PONEDELJAK";
				}else if($scope.prikaziDan == 2){
					danString = "UTORAK";
				}else if($scope.prikaziDan == 3){
					danString = "SREDA";
				}else if($scope.prikaziDan == 4){
					danString = "CETVRTAK";
				}else if($scope.prikaziDan == 5){
					danString = "PETAK";
				}else if($scope.prikaziDan == 6){
					danString = "SUBOTA";
				}else if($scope.prikaziDan == 0){
					danString = "NEDELJA";
				}
				
				if($scope.selektovanTip == "Kuvar"){
					var smenaUDanu = {
						restoran : $scope.restoran,
						danUNedelji : danString,
					}
						
					var str = JSON.stringify(smenaUDanu);
					menRestoranaServisS.izlistajSmeneKuvara(str).success(function(data) {
						$scope.smeneZaKuvare = data;
						
						menRestoranaServisS.dostupniKuvari(str).success(function(data1) {
							if($scope.prikaziDan == 1){
								$scope.dostupniKuvPonedeljak = data1;
							}else if($scope.prikaziDan == 2){
								$scope.dostupniKuvUtorak = data1;
							}else if($scope.prikaziDan == 3){
								$scope.dostupniKuvSreda = data1;
							}else if($scope.prikaziDan == 4){
								$scope.dostupniKuvCetvrtak = data1;
							}else if($scope.prikaziDan == 5){
								$scope.dostupniKuvPetak = data1;
							}else if($scope.prikaziDan == 6){
								$scope.dostupniKuvSubota = data1;
							}else if($scope.prikaziDan == 0){
								$scope.dostupniKuvNedelja = data1;
							}
						});
					}).error(function(data) {
					});
				}else if($scope.selektovanTip == "Sanker"){
					var smenaUDanu = {
						restoran : $scope.restoran,
						danUNedelji : danString,
					}
						
					var str = JSON.stringify(smenaUDanu);
					menRestoranaServisS.izlistajSmeneSankera(str).success(function(data) {
						$scope.smeneZaSankere = data;
						menRestoranaServisS.dostupniSankeri(str).success(function(data1) {
							if($scope.prikaziDan == 1){
								$scope.dostupniSanPonedeljak = data1;
							}else if($scope.prikaziDan == 2){
								$scope.dostupniSanUtorak = data1;
							}else if($scope.prikaziDan == 3){
								$scope.dostupniSanSreda = data1;
							}else if($scope.prikaziDan == 4){
								$scope.dostupniSanCetvrtak = data1;
							}else if($scope.prikaziDan == 5){
								$scope.dostupniSanPetak = data1;
							}else if($scope.prikaziDan == 6){
								$scope.dostupniSanSubota = data1;
							}else if($scope.prikaziDan == 0){
								$scope.dostupniSanNedelja = data1;
							}
						});
					}).error(function(data) {
					});
				}else if($scope.selektovanTip == "Konobar"){
					for(q=0; q<$scope.brojRedova*$scope.brojKolona; q++){
						tempSto = [{oznaka : q, restoran : $scope.restoran}];		// Mozda 1 i <=
						
						var smenaDan = {
							sto : tempSto,
							restoran : $scope.restoran,
							danUNedelji : danString
						}
						var str = JSON.stringify(smenaDan);
						menRestoranaServisS.izlistajDostupneSmeneKonobarDan(str).success(function(aaa) {
							if(aaa.message == "Nije sto"){
								$scope.pokusaj[aaa.obj[0]] = "nijesto";
							}else if(aaa.message == "Popunjane smene"){
								$scope.pokusaj[aaa.obj[0]] = "popnunjen";
							}else if(aaa.message == "Nisu popunjene smene"){
								$scope.pokusaj[aaa.obj[0]] = "nijePopunjen";
							}
						}).error(function(data) {
							$scope.pokusaj[aaa.obj[0]] = "nijesto";
						});
					}
				}
			}
			
			$scope.dodajKuvaraUSmenuDana = function(){
				var smenaDan = null;
				var dostKuv = null;
				
				if($scope.prikaziDan == 1){
					smenaDan = $scope.smenaKuvarPon;
					dostKuv = $scope.dostupniKuvariPon;
				}else if($scope.prikaziDan == 2){
					smenaDan = $scope.smenaKuvarUto;
					dostKuv = $scope.dostupniKuvariUto;
				}else if($scope.prikaziDan == 3){
					smenaDan = $scope.smenaKuvarSre;
					dostKuv = $scope.dostupniKuvariSre;
				}else if($scope.prikaziDan == 4){
					smenaDan = $scope.smenaKuvarCet;
					dostKuv = $scope.dostupniKuvariCet;
				}else if($scope.prikaziDan == 5){
					smenaDan = $scope.smenaKuvarPet;
					dostKuv = $scope.dostupniKuvariPet;
				}else if($scope.prikaziDan == 6){
					smenaDan = $scope.smenaKuvarSub;
					dostKuv = $scope.dostupniKuvariSub;
				}else if($scope.prikaziDan == 0){
					smenaDan = $scope.smenaKuvarNed;
					dostKuv = $scope.dostupniKuvariNed;
				}
				
				var smenaUDanu = {
					restoran : $scope.restoran,
					kuvar : dostKuv,
					smena : smenaDan,
					danUNedelji : danString
				}
				
				var str = JSON.stringify(smenaUDanu);
				menRestoranaServisS.dodajKuvaraUSmenuDana(str);
				
			}
			
			//Sanker
			$scope.dodajSankeraUSmenuDana = function(){
				var smenaDan = null;
				var dostSan = null;
				
				if($scope.prikaziDan == 1){
					smenaDan = $scope.smenaSankerPon;
					dostSan = $scope.dostupniSankeriPon;
				}else if($scope.prikaziDan == 2){
					smenaDan = $scope.smenaSankerUto;
					dostSan = $scope.dostupniSankeriUto;
				}else if($scope.prikaziDan == 3){
					smenaDan = $scope.smenaSankerSre;
					dostSan = $scope.dostupniSankeriSre;
				}else if($scope.prikaziDan == 4){
					smenaDan = $scope.smenaSankerCet;
					dostSan = $scope.dostupniSankeriCet;
				}else if($scope.prikaziDan == 5){
					smenaDan = $scope.smenaSankerPet;
					dostSan = $scope.dostupniSankeriPet;
				}else if($scope.prikaziDan == 6){
					smenaDan = $scope.smenaSankerSub;
					dostSan = $scope.dostupniSankeriSub;
				}else if($scope.prikaziDan == 0){
					smenaDan = $scope.smenaSankerNed;
					dostSan = $scope.dostupniSankeriNed;
				}
				
				var smenaUDanu = {
					restoran : $scope.restoran,
					sanker : dostSan,
					smena : smenaDan,
					danUNedelji : danString
				}
				
				var str = JSON.stringify(smenaUDanu);
				menRestoranaServisS.dodajSankeraUSmenuDana(str);
				
			}
			
			// Konobar
			var tempSto = null;
			$scope.prikaziStoloveKonobara = function(oznaka){
				$scope.dostupniKonPonedeljak = {};
				$scope.dostupneSmenePon = {};
				$scope.prikaz = true;
				
				tempSto = [{oznaka : oznaka, restoran : $scope.restoran}];
				var smenaDan = {
					sto : tempSto,
					restoran : $scope.restoran,
					danUNedelji : danString
				}
				var str = JSON.stringify(smenaDan);
				menRestoranaServisS.izlistajSmeneKonobar(str).success(function(data) {
					$scope.smeneZaKonobare = data;
				}).error(function(data) {
				});
				
				menRestoranaServisS.izlistajDostupneSmeneKonobarDan(str).success(function(data2) {
					if(data2.obj[1] != null){
						if($scope.prikaziDan == 1){
							$scope.dostupneSmenePon = data2.obj[1];
						}else if($scope.prikaziDan == 2){
							$scope.dostupneSmeneUto = data2.obj[1];
						}else if($scope.prikaziDan == 3){
							$scope.dostupneSmeneSre = data2.obj[1];
						}else if($scope.prikaziDan == 4){
							$scope.dostupneSmeneCet = data2.obj[1];
						}else if($scope.prikaziDan == 5){
							$scope.dostupneSmenePet = data2.obj[1];
						}else if($scope.prikaziDan == 6){
							$scope.dostupneSmeneSub = data2.obj[1];
						}else if($scope.prikaziDan == 0){
							$scope.dostupneSmeneNed = data2.obj[1];
						}
					}
				}).error(function(data) {
				});
			}
			
			$scope.promenaSmeneKonobar = function(){
				var tempSmenaa = null;
				if($scope.prikaziDan == 1){
					tempSmenaa = $scope.smenaKonobarPon;
				}else if($scope.prikaziDan == 2){
					tempSmenaa = $scope.smenaKonobarUto;
				}else if($scope.prikaziDan == 3){
					tempSmenaa = $scope.smenaKonobarSre;
				}else if($scope.prikaziDan == 4){
					tempSmenaa = $scope.smenaKonobarCet;
				}else if($scope.prikaziDan == 5){
					tempSmenaa = $scope.smenaKonobarPet;
				}else if($scope.prikaziDan == 6){
					tempSmenaa = $scope.smenaKonobarSub;
				}else if($scope.prikaziDan == 0){
					tempSmenaa = $scope.smenaKonobarNed;
				}
				
				var sud = {
					restoran : $scope.restoran,
					sto : tempSto,
					danUNedelji : danString,
					smena : tempSmenaa
				}
				var str = JSON.stringify(sud);
				menRestoranaServisS.izlistajDostupneKonobare(str).success(function(data1) {
					if($scope.prikaziDan == 1){
						$scope.dostupniKonPonedeljak = data1;
					}else if($scope.prikaziDan == 2){
						$scope.dostupniKonUtorak = data1;
					}else if($scope.prikaziDan == 3){
						$scope.dostupniKonSreda = data1;
					}else if($scope.prikaziDan == 4){
						$scope.dostupniKonCetvrtak = data1;
					}else if($scope.prikaziDan == 5){
						$scope.dostupniKonPetak = data1;
					}else if($scope.prikaziDan == 6){
						$scope.dostupniKonSubota = data1;
					}else if($scope.prikaziDan == 0){
						$scope.dostupniKonNedelja = data1;
					}
				}).error(function(data) {
				});
			}
			
			$scope.dodajKonobaraUSmenuDana = function(){
				var tempSme = null;
				var tempKon = null
				if($scope.prikaziDan == 1){
					tempSme = $scope.smenaKonobarPon;
					tempKon = $scope.dostupniKonobariPon;
				}else if($scope.prikaziDan == 2){
					tempSme = $scope.smenaKonobarUto;
					tempKon = $scope.dostupniKonobariUto;
				}else if($scope.prikaziDan == 3){
					tempSme = $scope.smenaKonobarSre;
					tempKon = $scope.dostupniKonobariSre;
				}else if($scope.prikaziDan == 4){
					tempSme = $scope.smenaKonobarCet;
					tempKon = $scope.dostupniKonobariCet;
				}else if($scope.prikaziDan == 5){
					tempSme = $scope.smenaKonobarPet;
					tempKon = $scope.dostupniKonobariPet;
				}else if($scope.prikaziDan == 6){
					tempSme = $scope.smenaKonobarSub;
					tempKon = $scope.dostupniKonobariSub;
				}else if($scope.prikaziDan == 0){
					tempSme = $scope.smenaKonobarNed;
					tempKon = $scope.dostupniKonobariNed;
				}
				
				var sud = {
					restoran : $scope.restoran,
					sto : tempSto,
					danUNedelji : danString,
					smena : tempSme,
					konobar : tempKon
				}
				var str = JSON.stringify(sud);
				menRestoranaServisS.dodajKonobaraStoSmenaDan(str);
			}
			
			// Porudzbine...
			menRestoranaServisS.izlistajSveNamirnice().success(function(data) {
				$scope.listaNamirnica = data;
			}).error(function(data) {
				alert("Nece namirnice!");
			});
			
			menRestoranaServisS.izlistajSvaPica().success(function(data) {
				$scope.listaPica = data;
			}).error(function(data) {
				alert("Nece pica!");
			});
			
			$scope.objaviPorudzbinu = function(){
				var porMen = {
					menadzerrestorana : data.obj,
					aktivnoOd : $scope.datumOd,
					aktivnoDo : $scope.datumDo
				}
				
				var stav = {
					porudzbinaMenadzer : porMen,
					stavke : $scope.listaStavki
				}
				var str = JSON.stringify(stav);
				menRestoranaServisS.dodajStavke(str).success(function(data) {
				});
			}
			
			
			// Kalendar...
			$scope.danasnjiDatum = new Date();
			$scope.danasnjiDan = $scope.danasnjiDatum.getDay();
			
			$scope.prikaziDan = -1;
			$scope.prikaziSmene = function(index){
				$scope.tipRadnika = "";
				$scope.selektovanTip = "";
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
			
			menRestoranaServisS.izlistajAktivnePorudzbine(str).success(function(data) {
				$scope.porudzbineMan = data.obj;
			}).error(function(data) {
				
			});
			
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
	
	$scope.setTab = function(newTab){
    	$scope.tab = newTab;
    };

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
	
	$scope.setTab(1);
	
	$scope.dodajJelo = function(restoran){
		// TODO: Aca ~ Uraditi validacuju
		var tip = "";
		if($scope.tipJela == "Peceno"){
			tip = "ZA_PECENA_JELA";
		}else if($scope.tipJela == "Kuvano"){
			tip = "ZA_KUVANA_JELA";
		}else if($scope.tipJela == "Salata"){
			tip = "ZA_SALATE";
		}
		
		var jeloo = {
			naziv : $scope.nazivJela,
			opis : $scope.opisJela,
			cena : $scope.cenaJela,
			tipKuvara : tip,
			restoran : restoran
		}
		
		var str = JSON.stringify(jeloo);
		menRestoranaServisS.dodajJelo(str).success(function(data) {
			alert("Uspesno dodato jelo!");
		}).error(function(data) {
			alert("Jelo nije dodato!");
		});
	}
	
	$scope.dodajPice = function(restoran){
		// TODO: Aca ~ Uraditi validacuju
		var picee = {
			naziv : $scope.nazivPica,
			opis : $scope.opisPica,
			cena : $scope.cenaPica,
			restoran : restoran
		}
		
		var str = JSON.stringify(picee);
		menRestoranaServisS.dodajPice(str).success(function(data) {
			alert("Uspesno dodato pice!");
		}).error(function(data) {
			alert("Pice nije dodato!");
		});
	}
	
	$scope.izmenaNazivaRestorana = function(restoran) {
		// TODO: Aca ~ Uraditi validacuju
		var rest = {
			id : restoran.id,
			naziv : restoran.naziv,

		}
		
		var str = JSON.stringify(rest);
		menRestoranaServisS.izmeniNazivRestorana(str).success(function(data) {
			alert("Uspesno izmenjen naziv!");
		}).error(function(data) {
			alert("Naziv nije izmenjen!");
		});
	}
	
	$scope.izmenaOpisaRestorana = function(restoran) {
		// TODO: Aca ~ Uraditi validacuju
		var rest = {
			id : restoran.id,
			opis : restoran.opis,

		}
		
		var str = JSON.stringify(rest);
		menRestoranaServisS.izmeniOpisRestorana(str).success(function(data) {
			alert("Uspesno izmenjen opis!");
		}).error(function(data) {
			alert("Opis nije izmenjen!");
		});
	}
	
	$scope.izmenaJela = function(jeloId){
		// TODO: Tabelarno...
	}
	
	$scope.odbrisiJelo = function(jeloId){
		var str = JSON.stringify(jeloId);
		menRestoranaServisS.izbrisiJelo(str).success(function(data) {
		}).error(function(data) {
		});
	}
	
	$scope.odbrisiPice = function(piceId){
		var str = JSON.stringify(piceId);
		menRestoranaServisS.izbrisiPice(str).success(function(data) {
		}).error(function(data) {	
		});
	}
	
	$scope.napraviKonfiguraciju = function(restoran) {
		$scope.brRedova = [];
		$scope.brKolona = [];
		
		var sizeStolova = {
			id : restoran.id,
			brojredova : $scope.brojRedova,
			brojkolona : $scope.brojKolona
		}
		var str = JSON.stringify(sizeStolova);
		menRestoranaServisS.brojeviRedovaIKolona(str).success(function(data) {
			alert("broj redova i kolona dodat");
		}).error(function(data) {

		});
		
		var tempStolovi = [];
		tempStolovi[0] = restoran.id;
		for(i=0; i<$scope.brojRedova; i++){
			for(j=0; j<$scope.brojKolona; j++){
				tempStolovi[i*$scope.brojKolona + j + 1] = i*$scope.brojKolona + j;
			}
		}
		
		var str = JSON.stringify(tempStolovi);
		menRestoranaServisS.napraviStolove(str).success(function(data) {
			$scope.stolovi = data;
			alert("stolovi napravljeni");
		}).error(function(data) {
			alert("stolovi nisu napravljeni");
		});
		
		for(i=0; i<$scope.brojRedova; i++){
			$scope.brRedova.push(i);
		}
		for(i=0; i<$scope.brojKolona; i++){
			$scope.brKolona.push(i);
		}
		
		for(z=0; z<$scope.brojRedova*$scope.brojKolona; z++){
			
			var smenaDan = {
				oznaka : z,
				restoran : $scope.restoran,
			}
			var str = JSON.stringify(smenaDan);
			menRestoranaServisS.izlistajBojuStola(str).success(function(aaa) {
				$scope.pojedSto[aaa.obj] = aaa.message;
			}).error(function(data) {
				$scope.pojedSto[aaa.obj] = "nijesto";
			});
		}
		
	}
	
	$scope.prikaz = false;
	
	$scope.isVisible = function(){	// Proveriti da li treba oznaka...
		return $scope.prikaz;
	}
	
	$scope.prikaziInformacije = function(oznaka, restoran){
		$scope.prikaz = true;
		
		var sto = {
			oznaka : oznaka,
			restoran : restoran
		}
		var str = JSON.stringify(sto);
		menRestoranaServisS.izlistajSto(str).success(function(data) {
			$scope.sto = data.obj;
			$scope.segmentStola = data.obj.segment;
			
		}).error(function(data) {
		});
	}
	
	// Porudzbine menadzera
	
	$scope.dodajStavkuNamirnicu = function(){
		var stavka = {
			kolicina : $scope.kolNamirnica,
			namirnica : $scope.namirnicaStavka
		}
		$scope.listaStavki.push(stavka);
	}
	
	$scope.dodajStavkuPice = function(){
		var stavka = {
			kolicina : $scope.kolPica,
			pice : $scope.piceStavka
		}
		$scope.listaStavki.push(stavka);
	}
	
	$scope.dodajNovuStavkuNamirnicu = function(){
		var novaNamirnica = {
			naziv : $scope.novaNamirnicaStavka
		}
		
		var stavka = {
			kolicina : $scope.kolNamirnicaNovo,
			namirnica : novaNamirnica
		}
		$scope.listaStavki.push(stavka);
	}
	
	$scope.dodajNovuStavkuPice = function(){
		var novoPice = {
			naziv : $scope.novoPiceStavka
		}
		
		var stavka = {
			kolicina : $scope.kolPicaNovo,
			pice : novoPice
		}
		$scope.listaStavki.push(stavka);
	}
	
	$scope.dodajPonudjaca = function(ponudjac){
		var ponRest = {
			ponudjac : ponudjac,
			restoran : $scope.restoran
		}
		var str = JSON.stringify(ponRest);
		menRestoranaServisS.dodajPonudjaca(str);
	}
	
	$scope.registrujIDodajPonudjaca = function(){
		var pon = {
			ime : $scope.imePonudjaca,
			prezime : $scope.prezimePonudjaca,
			sifra : $scope.lozinkaPonudjaca,
			email : $scope.emailPonudjaca,
		}
		
		var ponRest = {
			ponudjac : pon,
			restoran : $scope.restoran
		}
		var str = JSON.stringify(ponRest);
		menRestoranaServisS.registrujIDodajPonudjaca(str);
		
	}
	
	$scope.izmeniSto = function(){
		var sto = {
			restoran : $scope.restoran,
			oznaka : $scope.sto.oznaka,
			segment : $scope.segmentStola,
			brojmesta : $scope.sto.brojmesta
		}
		var str = JSON.stringify(sto);
		menRestoranaServisS.izmeniSto(str).success(function(data) {
			if(data.message == "false"){
				alert("Sto je rezervisan, nije ga moguce izbaciti iz rasporeda stolova");
			}else{
				$scope.pojedSto[data.obj.oznaka] = data.message;
			}
		}).error(function(data) {
			
		});
		
	}
	
	$scope.dodajRadnika = function(){
		var tipRad = "";
		if($scope.tipRadnikaDodaj == "konobar"){
			tipRad = "KONOBAR";
			var radnik = {
				restoran : $scope.restoran,
				ime : $scope.imeRadnika,
				prezime : $scope.prezimeRadnika,
				email : $scope.emailRadnika,
				sifra : $scope.lozinkaRadnika,
				tipKorisnika : tipRad,
				konfbroj : $scope.konfBrRadnika,
				velobuce : $scope.velObRadnika,
				datumrodj : $scope.datumRadnika
			}
			
			menRestoranaServisS.registrujKonobara(radnik).success(function(data) {
				// TODO: Osvezi stranicu	
			}).error(function(data) {
				
			});
		}else if($scope.tipRadnikaDodaj == "kuvar"){
			tipRad = "KUVAR";
			var radnik = {
				restoran : $scope.restoran,
				ime : $scope.imeRadnika,
				prezime : $scope.prezimeRadnika,
				email : $scope.emailRadnika,
				sifra : $scope.lozinkaRadnika,
				tipKorisnika : tipRad,
				konfbroj : $scope.konfBrRadnika,
				velobuce : $scope.velObRadnika,
				datumrodj : $scope.datumRadnika,
				tipKuvara : $scope.tipKuvaraDodaj
			}
			
			menRestoranaServisS.registrujKuvara(radnik).success(function(data) {
					
			}).error(function(data) {
				
			});
		}else if($scope.tipRadnikaDodaj == "sanker"){
			tipRad = "SANKER";
			var radnik = {
				restoran : $scope.restoran,
				ime : $scope.imeRadnika,
				prezime : $scope.prezimeRadnika,
				email : $scope.emailRadnika,
				sifra : $scope.lozinkaRadnika,
				tipKorisnika : tipRad,
				konfbroj : $scope.konfBrRadnika,
				velobuce : $scope.velObRadnika,
				datumrodj : $scope.datumRadnika
			}
			
			menRestoranaServisS.registrujSankera(radnik).success(function(data) {
				
			}).error(function(data) {
				
			});
		}

	}
	
	$scope.daPrikaze = function(porudz){
		return porudz === $scope.odabranaPorudz;
	}
	
	// PORUDZBNINE - PONUDE
	$scope.prikaziPonudeZa = function(porudz){
		if($scope.odabranaPorudz === porudz){
			$scope.odabranaPorudz = null;
		}else{
			$scope.odabranaPorudz = porudz;
			
			menRestoranaServisS.prikaziPonud(porudz).success(function(data) {
				$scope.ponudePorudz = data.obj;		
			}).error(function(data) {
				
			});
		}
	}
	
	$scope.prihvatiPonudu = function(pon){
		
		menRestoranaServisS.prihvatiPonudu(pon).success(function(data) {
			$scope.porudzbineMan = data.obj;		// DA obj vrati preostale porudzbine
		}).error(function(data) {
			
		});
	}
	
	$scope.obojiSto = function(kriterijum, oznaka){
		return kriterijum == $scope.pokusaj[oznaka];
	}
	
	$scope.obojiStoPrav = function(kriterijum, oznaka){
		return kriterijum == $scope.pojedSto[oznaka];
	}
	
	// IZVESTAJI
	
		// RESTORAN ~ staviti gore da izlista kada se ucita
	$scope.izlistajOcenuRestorana = function(){
		var izvRest = {
			restoran : $scope.restoran,
			odDatum : $scope.datumOdOcenaRest,
			doDatum : $scope.datumDoOcenaRest
		}
		// Ocena
		menRestoranaServisS.izvestajZaRestoran(izvRest).success(function(data) {
			$scope.ocenaRestorana = data.obj;		//message staviti ako jelo ne postoji...
		}).error(function(data) {
			
		});
		
		// Prihod
		menRestoranaServisS.izvestajPrihodaRestorana(izvRest).success(function(data) {
			$scope.prihodRestorana = data.obj;		//message staviti ako jelo ne postoji...
		}).error(function(data) {
			
		});
		
	}
	
		// JELO
	$scope.prikazIzvJ = false;
	
	$scope.prikaziIzvestajJelo = function(){
		$scope.prikazIzvJ = true;
		var jeloTemp = {
			restoran : $scope.restoran,
			naziv : $scope.nazivJela
		}
		
		var izvestaj = {
			jelo : jeloTemp,
			datumod : $scope.datumOdOcenaJela,
			datumdo : $scope.datumDoOcenaJela
		}
		
		var str = JSON.stringify(izvestaj);
		menRestoranaServisS.izvestajZaJelo(str).success(function(data) {
			$scope.izvestajJelo = data.obj;		//message staviti ako jelo ne postoji...
		}).error(function(data) {
			
		});
	}
	
	$scope.prikazIzvJela = function(){
		return $scope.prikazIzvJ;
	}
	
	// KONOBAR
	$scope.prikaziIzvestajKonobar = function(){
		var izvKon = {
			emailKonobara : $scope.nazivKonobara,
			restoran : $scope.restoran,
			odDatum : $scope.datumOdOcenaKonobar,
			doDatum : $scope.datumDoOcenaKonobar
		}
		// Ocena
		var str = JSON.stringify(izvKon);
		menRestoranaServisS.izvestajZaKonobara(str).success(function(data) {
			$scope.ocenaKonobara = data.obj;		//message staviti ako konobar ne postoji...
		}).error(function(data) {
			
		});
		
		// Prihod
		menRestoranaServisS.izvestajPrihodaKonobara(str).success(function(data) {
			$scope.prihodKonobara = data.obj;		//message staviti ako konobar ne postoji...
		}).error(function(data) {
			
		});
		
	}
	$scope.nedTrue = false;
	
	// Graf prikaz poseta
	$scope.prikaziGrafikPosecenosti = function(){
		
		var t= {
			restoran : $scope.restoran,
			nivo : $scope.naKomNivou,
			datum : $scope.datumOdPosecenost
		}
		var str = JSON.stringify(t);
		menRestoranaServisS.prikaziGrafikPosecenosti(str).success(function(data) {
			if($scope.naKomNivou == "dnevni"){
				// obj[0] - broj smena
				// obj[1] - lista pocenata za svaku smenu
			}else if($scope.naKomNivou == "nedeljni"){
				for(i=0; i<data.obj[0]; i++){
					$scope.ponN.push(i);
				}
				for(i=0; i<data.obj[1]; i++){
					$scope.utoN.push(i);
				}
				for(i=0; i<data.obj[2]; i++){
					$scope.sreN.push(i);
				}
				for(i=0; i<data.obj[3]; i++){
					$scope.cetN.push(i);
				}
				for(i=0; i<data.obj[4]; i++){
					$scope.petN.push(i);
				}
				for(i=0; i<data.obj[5]; i++){
					$scope.subN.push(i);
				}
				for(i=0; i<data.obj[6]; i++){
					$scope.nedN.push(i);
				}

				$scope.nedTrue = true;
			}

		}).error(function(data) {
			
		});
	}
	
	$scope.prikaNedeljni = function() {
		return $scope.nedTrue;
	}
	
	$scope.kuvarSelek = false;
	
	$scope.promenaTipa = function(){
		if($scope.tipRadnikaDodaj == "kuvar"){
			$scope.kuvarSelek = true;
		}else{
			$scope.kuvarSelek = false;
		}
	}
	
	$scope.selKuvar = function(){
		return $scope.kuvarSelek;
	}

});