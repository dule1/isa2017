var app = angular.module("restoranApp.rute", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "html/logovanje.html"
    })
    .when("/registrujSe", {
        templateUrl : "html/register.html"
    })
    .when("/gostGlavnaStrana", {
        templateUrl : "html/gost.html"
    })
    .when("/ponudjac", {
        templateUrl : "html/ponudjac.html"
    })
    .when("/kuvar", {
        templateUrl : "html/kuvar.html"
    })
    .when("/menSistema", {
        templateUrl : "html/menadzerSistema.html"
    })
    .when("/menadzerRestorana", {
        templateUrl : "html/menadzerRestorana.html"
    })    
    .when("/konobar", {
        templateUrl : "html/konobar.html"
    })
    .when("/sanker", {
        templateUrl : "html/sanker.html"
    });
});