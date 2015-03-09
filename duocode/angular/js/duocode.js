var rutaApp = 'http://localhost:8080/DuoCode/rest/';

var duocodeApp = angular.module('duocodeApp', ['duocodeProviders']);

duocodeApp.controller('UsuarioController', ['$scope', '$http', 'usuarioServicio', 'idiomasSeleccionadosServicio', function ($scope, $http, usuarioServicio, idiomasSeleccionadosServicio) {
	$scope.usuario = {};
    $scope.lenguajes = [];
    $scope.lenguajeQueSe = idiomasSeleccionadosServicio.idiomaQueSe;
    $scope.idiomaQueNOSe = idiomasSeleccionadosServicio.idiomaQueNOSe;

    //Si no se ponen sale una opcion en blanco en el select
    $scope.selectedItem = idiomasSeleccionadosServicio.idiomaQueSe;
    $scope.selectedItemNoSe = idiomasSeleccionadosServicio.idiomaQueNOSe;    

	$scope.puntuacion = function() {
	 if ($scope.usuario.historialEjercicios === undefined) return 'cargando';
	 else{
		 var sumaPuntuaciones = 0;
	      for (var i = 0; i < $scope.usuario.historialEjercicios.length; i++) {
	      	sumaPuntuaciones = sumaPuntuaciones + $scope.usuario.historialEjercicios[i].puntuacion;
	      };
	      return sumaPuntuaciones;
	  }
    };

    $scope.numCandidatos = function() {
    	if ($scope.usuario.candidatosPropuestos === undefined) return 'cargando';
    	else return $scope.usuario.candidatosPropuestos.length;
    };

    $scope.numFavoritos = function() {
    	if ($scope.usuario.favoritos === undefined) return 'cargando';
    	else return $scope.usuario.favoritos.length;
    };

    //lo guarda en localstorage para el futuro
    $scope.cambiarLenguajeSe = function() {
       $scope.lenguajeQueSe = $scope.selectedItem;
       localStorage.setItem(idiomasSeleccionadosServicio.STR_LOCALSTORAGE_IDIOMASE, $scope.selectedItem);
    };

    //lo guarda en localstorage para el futuro
    $scope.cambiarLenguajeNOSe = function() {
       $scope.idiomaQueNOSe = $scope.selectedItemNoSe;
       localStorage.setItem(idiomasSeleccionadosServicio.STR_LOCALSTORAGE_IDIOMANOSE, $scope.selectedItemNoSe);
    };

    //Ejecuta esto cuando se termina de cargar el get usuarioServicio
    usuarioServicio.then(function(dataCuandoLaFuncionSeEjecute) {
    	$scope.usuario = dataCuandoLaFuncionSeEjecute.data;
    });

    $http.get(rutaApp+'lenguajes').success(function(data) {
        $scope.lenguajes = data.lenguajes;
    });
}]);

duocodeApp.controller('TemasController', ['$scope', '$http', 'usuarioServicio', function ($scope, $http, usuarioServicio) {
	$scope.temas = [];
	$scope.predicate = '+orden'; // para ordenar los temas por el campo orden

	$http.get(rutaApp+'temas').success(function(data) {
	    for (var i = 0; i < data.temas.length; i++) {
	    	$http.get(data.temas[i]).success(function(dataTema) {
	    		$scope.temas.push(dataTema);
			});
	    };
	});

    //Usado para prueba de concepto, borrar en algún momento
    $scope.setEmail = function() {
    	$scope.usuario.correo = 'correoRaro';
    };

    //Ejecuta esto cuando se termina de cargar el get de usuarioServicio
	usuarioServicio.then(function(dataCuandoLaFuncionSeEjecute) {
    	$scope.usuario = dataCuandoLaFuncionSeEjecute.data;
    });
}]);