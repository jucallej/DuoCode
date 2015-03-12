var rutaApp = 'http://localhost:8080/DuoCode/rest/';

var duocodeApp = angular.module('duocodeApp', ['duocodeProviders', 'ngRoute']);

//Creamos el menú de usuarios reusable (como tag): <info-usuario></info-usuario>
duocodeApp.directive('infoUsuario', function() {
  return {
    restrict: 'E',
    templateUrl: 'parts/info-usuario.html'
  };
});

//Le decimos que fragmento insertar a <div ng-view></div> según la ruta
duocodeApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/temas', {
        templateUrl: 'parts/temas.html',
      }).
      when('/temas/:temaID', {
        templateUrl: 'parts/lecciones.html',
      }).
      when('/temas/:temaID/:idLeccion', {
        templateUrl: 'parts/ejercicios.html',
      }).
      otherwise({
        redirectTo: '/temas'
      });
}]);

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

       //Haciendo esto, las siguientes veces que se use idiomasSeleccionadosServicio, tendrá los datos actualizados
       idiomasSeleccionadosServicio.idiomaQueSe = $scope.selectedItem;
    };

    //lo guarda en localstorage para el futuro
    $scope.cambiarLenguajeNOSe = function() {
       $scope.idiomaQueNOSe = $scope.selectedItemNoSe;
       localStorage.setItem(idiomasSeleccionadosServicio.STR_LOCALSTORAGE_IDIOMANOSE, $scope.selectedItemNoSe);

       //Haciendo esto, las siguientes veces que se use idiomasSeleccionadosServicio, tendrá los datos actualizados
       idiomasSeleccionadosServicio.idiomaQueNOSe = $scope.selectedItemNoSe;
    };

    //Ejecuta esto cuando se termina de cargar el get usuarioServicio
    usuarioServicio.then(function(dataCuandoLaFuncionSeEjecute) {
    	$scope.usuario = dataCuandoLaFuncionSeEjecute.data;
    });

    $http.get(rutaApp+'lenguajes').success(function(data) {
        $scope.lenguajes = data.lenguajes;
    });
}]);

duocodeApp.controller('TemasController', ['$scope', '$http', function ($scope, $http) {
	$scope.temas = [];
	$scope.predicate = '+orden'; // para ordenar los temas por el campo orden

	$http.get(rutaApp+'temas').success(function(data) {
	    for (var i = 0; i < data.temas.length; i++) {
	    	$http.get(data.temas[i]).success(function(dataTema) {
	    		$scope.temas.push(dataTema);
			});
	    };
	});
}]);

duocodeApp.controller('LeccionesController', ['$scope', '$http', 'usuarioServicio', '$routeParams', 'idiomasSeleccionadosServicio', 
    function ($scope, $http, usuarioServicio, $routeParams, idiomasSeleccionadosServicio) {

    $scope.lecciones = [];
    $scope.predicate = '+orden'; // para ordenar los temas por el campo orden
    $scope.idTema = $routeParams.temaID;

    var usuario;

    $http.get(rutaApp+'temas/'+$scope.idTema).success(function(data) {
        var lecciones = data.lecciones;
        for (var i = 0; i < lecciones.length; i++) {
            $http.get(lecciones[i]).success(function(dataLecciones) {
                $scope.lecciones.push(dataLecciones);
            });
        };
    });

    //Ejecuta esto cuando se termina de cargar el get de usuarioServicio, necesario para saber que lecciones ha hecho
    usuarioServicio.then(function(dataCuandoLaFuncionSeEjecute) {
        usuario = dataCuandoLaFuncionSeEjecute.data;
    });

    //Nos dirá si una lección esta bloqueda, o es accesible para el usuario
    $scope.leccionBloqueada = function(idLeccion) {
        if (usuario === null)  return true;
        else{
            var bloqueada = true;

            var leccionBuscada = null;
            var i = 0;
            while ( i < $scope.lecciones.length && leccionBuscada === null) {
                if($scope.lecciones[i].id === idLeccion) leccionBuscada = $scope.lecciones[i];
                else i++;
            };

            //Tenemos la leccion en la que estamos y las lecciones que necesitas tener terminadas para desbloquearla
            if (leccionBuscada !== null && leccionBuscada !== undefined){
                var todasCompletadas = true;

                var i = 0;
                //Ahora miramos si ha completado las lecciones que le hacen falta
                while ( i < leccionBuscada.leccionesDesbloqueadoras.length && todasCompletadas) {
                    if(!($scope.leccionTerminada(leccionBuscada.leccionesDesbloqueadoras[i]))) todasCompletadas = false;
                    else i++;
                };

                bloqueada = !todasCompletadas;
            }

            return bloqueada;
        }
    };

    //Comprueba si una lección está terminada
    //Habría que comprobar también el lenguaje que estás aprendiendo (y añadirlo a la BD)
    $scope.leccionTerminada = function(idLeccion) {
        if (usuario === null)  return false;
        else{
            var terminada = false;

            var i = 0;
            while ( i < usuario.leccionesCompletadas.length && !terminada) {
                if(usuario.leccionesCompletadas[i] === idLeccion) terminada = true;
                else i++;
            };

            return terminada;
        }
    };
}]);