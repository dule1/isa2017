var app = angular.module('myApp', []);
app.controller('logRegCtrl', function($scope, $http) {
	
	$scope.login = function() {
		var gost = {
			gostIme: $scope.gostIme,
			gostPrezime : $scope.gostPrezime,
			email : $scope.email,
			sifra : $scope.sifra,
		}

		var str = JSON.stringify(gost);
		
		alert(str);
		$http({
			method: "POST",
			url: "contr/create",
			data: str
		});
		
	}
		
});