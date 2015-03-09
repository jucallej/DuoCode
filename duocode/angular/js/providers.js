var rutaApp = 'http://localhost:8080/DuoCode/rest/';

var duocodeProviders = angular.module('duocodeProviders', []);

/*
Segun entiendo esto permite que varias partes (controladores) compartan datos https://docs.angularjs.org/guide/providers
Si cambias en una lado cambia en todos (añadir ejercicios ya hechos al historial del usuario, cambiaría automaticamente la puntuación, 
conseguir el lenguaje de origen y cosas asi)
*/

duocodeProviders.factory('usuarioServicio', ['$http', function ($http) {
  var idUsuario = 1;

  return $http.get(rutaApp+'usuarios/'+idUsuario);
}]);



duocodeProviders.factory('idiomasSeleccionadosServicio', ['$http', function ($http) {
  //Idiomas por defecto, si no ha seleccionado algo antes
  var idiomaQueSeTemp = 'Java';
  var idiomaQueNOSeTemp = 'C++';

  var STR_LOCALSTORAGE_IDIOMANOSABETemp = 'idiomaQueNOSe';
  var STR_LOCALSTORAGE_IDIOMASABETemp = 'idiomaQueSe';

  //Uso localStorage que me ha parecido fácil
  if (localStorage.getItem(STR_LOCALSTORAGE_IDIOMASABETemp) !== null){
  	idiomaQueSeTemp = localStorage.getItem("idiomaQueSe");
  }

  if (localStorage.getItem(STR_LOCALSTORAGE_IDIOMANOSABETemp) !== null){
  	idiomaQueNOSeTemp = localStorage.getItem("idiomaQueNOSe");
  }

  return {
  	idiomaQueSe : idiomaQueSeTemp,
  	idiomaQueNOSe : idiomaQueNOSeTemp,
    STR_LOCALSTORAGE_IDIOMANOSE : STR_LOCALSTORAGE_IDIOMANOSABETemp,
    STR_LOCALSTORAGE_IDIOMASE : STR_LOCALSTORAGE_IDIOMASABETemp
  };
}]);