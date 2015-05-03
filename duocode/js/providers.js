var rutaApp = 'https://localhost:8443/DuoCode/rest/';

var duocodeProviders = angular.module('duocodeProviders', []);

/*
Segun entiendo esto permite que varias partes (controladores) compartan datos https://docs.angularjs.org/guide/providers
Si cambias en una lado cambia en todos (añadir ejercicios ya hechos al historial del usuario, cambiaría automaticamente la puntuación, 
conseguir el lenguaje de origen y cosas asi)
*/

// https://developers.google.com/identity/sign-in/web/sign-in

duocodeProviders.factory('usuarioServicio', ['$http', '$q', '$window', function ($http, $q, $window) {
  var usuarioData = {};
  usuarioData.idUsuario = -1;
  usuarioData.token = -1;

  var getAuthResponseGoogle = hello( "google" ).getAuthResponse();

  var getAuthResponseFacebook = hello( "facebook" ).getAuthResponse();

  //https://docs.angularjs.org/api/ng/service/$q
  if(getAuthResponseGoogle === null && getAuthResponseFacebook === null){
    return $q(function(resolve, reject) {
      resolve({
        data:{
          id: -1,
          candidatosPropuestos: [],
          correo: 'sin loguear',
          favoritos: [],
          historialEjercicios: [],
          leccionesCompletadas: [],
          votosDeUnUsuario: [],
          nick: 'sin loguear',
          rol: -1,

        }
      });
    });
  }

  else {
    var servicio = 'google';
    var getAuthResponse = getAuthResponseGoogle;
    if (getAuthResponseFacebook !== null){
      servicio = 'facebook';
      getAuthResponse = getAuthResponseFacebook;
    } 

    hello( servicio ).api("me").then(function(json){
        //Todo bien, podemos seguir haciendo peticiones
    }, function(e){ 
    //hay algun fallo con el token
      hello( servicio ).logout();
      localStorage.removeItem("idUser");
      console.log("Whoops! " + e.error.message );
      $window.location.reload();
    })

    usuarioData.token = getAuthResponse.access_token;
    usuarioData.idUsuario = localStorage.getItem("idUser");

    $http.defaults.headers.common.token = usuarioData.token;
    $http.defaults.headers.common.idUsuario = usuarioData.idUsuario;
    $http.defaults.headers.common.network = getAuthResponse.network;

    return $http.get(rutaApp+'usuarios/' + usuarioData.idUsuario);
  }
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