var rutaApp = 'http://localhost:8080/DuoCode/rest/';
var umbralValido = 7; //sobre 10
var numeroMaximoEJ = 10;

var duocodeApp = angular.module('duocodeApp', ['duocodeProviders', 'ngRoute', 'hljs']);

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
      when('/', {
        templateUrl: 'parts/index.html',
      }).
      when('/candidatos', {
        templateUrl: 'parts/candidatos.html',
      }).
      when('/misfavoritos', {
        templateUrl: 'parts/misfavoritos.html',
      }).
      when('/miscandidatos', {
        templateUrl: 'parts/miscandidatos.html',
      }).
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
        redirectTo: '/'
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
                if(usuario.leccionesCompletadas[i].idLeccion === idLeccion 
                    && usuario.leccionesCompletadas[i].lenguaje === idiomasSeleccionadosServicio.idiomaQueNOSe) terminada = true;
                else i++;
            };

            return terminada;
        }
    };
}]);

duocodeApp.controller('FavoritoController', ['$scope', '$http', 'usuarioServicio', function($scope, $http, usuarioServicio) {

        //Ejecuta esto cuando se termina de cargar el get de usuarioServicio, necesario para saber que lecciones ha hecho
        usuarioServicio.then(function(dataCuandoLaFuncionSeEjecute) {
            $scope.usuario = dataCuandoLaFuncionSeEjecute.data;
            $scope.ejercicios = [];
            $scope.enunciados = [];

            for (var i = 0; i < $scope.usuario.favoritos.length; i++) {
                var fav = $scope.usuario.favoritos[i];

                $http.get(rutaApp + 'ejercicios/' + $scope.usuario.favoritos[i].idEjercicio).success(function(dataEjercicios) {
                    $scope.ejercicios.push(dataEjercicios);

                    for (var j = 0; j < dataEjercicios.enunciados.length; j++) {
                        $http.get(dataEjercicios.enunciados[j]).success(function(dataEnunciado) {

                            if (dataEnunciado.nombreLenguaje == fav.lenguajeOrigen || dataEnunciado.nombreLenguaje == fav.lenguajeDestino)
                                $scope.enunciados.push(dataEnunciado);
                        });

                    }

                });

            }
        });

        $scope.nombreEj = function(idejercicio) {
            if ($scope.ejercicios === null) return 'cargando';
            else {
                var terminada = false;
                var nombre = 'no encontrado';

                var i = 0;
                while (i < $scope.ejercicios.length && !terminada) {
                    if ($scope.ejercicios[i].id === idejercicio) {
                        terminada = true;
                        nombre = $scope.ejercicios[i].nombre;
                    } else i++;
                };

                return nombre;
            }
        };

        $scope.enunciadoCompleto = function(idejercicio, lenguaje) {
            if ($scope.enunciados === null) return {};
            else {
                var terminada = false;
                var nombre = {};

                var i = 0;
                while (i < $scope.enunciados.length && !terminada) {
                    if ($scope.enunciados[i].idDelEjercicioQueResuelve == idejercicio && $scope.enunciados[i].nombreLenguaje == lenguaje) {
                        terminada = true;
                        nombre = $scope.enunciados[i];
                    } else i++;
                };

                return nombre;
            }
        };

        $scope.eliminarFav = function(indiceFav, fav) {
            $scope.usuario.favoritos.splice(indiceFav, 1);
            var req = {
                method: 'PUT',
                url: rutaApp + 'favoritos',
                headers: {
                    'userID': $scope.usuario.ID
                },
                data: {
                    'favoritos': $scope.usuario.favoritos
                }
            }

            $http(req).success(function(posibleError) {
                console.log(posibleError);
            });
        };
    }
]);

duocodeApp.controller('EjerciciosController', ['$scope', '$http', 'usuarioServicio', 'idiomasSeleccionadosServicio', '$routeParams',
 function($scope, $http, usuarioServicio, idiomasSeleccionadosServicio, $routeParams) {
 	$scope.leccion = {};
 	$scope.vidas = 3;
 	$scope.ejercicios = [];
    $scope.enunciados = [];
 	$scope.ejerciciosTotales = 10;
 	$scope.ejerciciosRestantes = 10;

	$scope.idLeccion = $routeParams.idLeccion;
	$scope.idTema = $routeParams.temaID;

	$scope.cargando = true;
    $scope.correcto = false;
    $scope.incorrecto = false;
    $scope.sinVidas = false;
    $scope.terminadoAcertando = false;
    $scope.candEnviado = false;

    $scope.idiomaQueSe = idiomasSeleccionadosServicio.idiomaQueSe;
    $scope.idiomaQueNoSe = idiomasSeleccionadosServicio.idiomaQueNOSe;

    var usuario;

    //Ejecuta esto cuando se termina de cargar el get de usuarioServicio, necesario para saber que lecciones ha hecho
    usuarioServicio.then(function(dataCuandoLaFuncionSeEjecute) {
        usuario = dataCuandoLaFuncionSeEjecute.data;
    });

    var init = function(){

        $scope.cargando = true;
        $scope.correcto = false;
        $scope.incorrecto = false;
        $scope.sinVidas = false;
        $scope.terminadoAcertando = false;
        $scope.vidas = 3;

        $http.get(rutaApp+'lecciones/'+$scope.idLeccion).success(function(leccion) {
            $scope.leccion = leccion;
            $scope.ejerciciosTotales = $scope.leccion.ejercicios.length;

            if ($scope.ejerciciosTotales > numeroMaximoEJ) $scope.ejerciciosTotales = numeroMaximoEJ;

            $scope.ejerciciosRestantes = $scope.ejerciciosTotales;

            for (var i = 0; i < $scope.leccion.ejercicios.length; i++) {
            	$http.get($scope.leccion.ejercicios[i]).success(function(ejercicio) {
            		$scope.ejercicios.push(ejercicio);
                    //console.log(ejercicio);
                    for (var i = 0; i < ejercicio.enunciados.length; i++) {
                        $http.get(ejercicio.enunciados[i]).success(function(enunciado) {
                            //console.log(enunciado);
                            if (enunciado.nombreLenguaje === idiomasSeleccionadosServicio.idiomaQueSe || enunciado.nombreLenguaje === idiomasSeleccionadosServicio.idiomaQueNOSe)
                                $scope.enunciados.push(enunciado);
                        });
                    };
            	});
            };

            //http://stackoverflow.com/questions/6274339/how-can-i-shuffle-an-array-in-javascript

            for(var j, x, i = $scope.ejercicios.length; i; j = Math.floor(Math.random() * i), x = $scope.ejercicios[--i], $scope.ejercicios[i] = $scope.ejercicios[j], $scope.ejercicios[j] = x);

            $scope.cargando = false;
        });

    };

    init();

    $scope.EjActual= function() {
    	return $scope.ejercicios[0];
    };

    //Para reutilizar
    var verSiEjercicioEstaMarcadoComoFavorito = function(idEjercicio){
        for (var i = 0; i < usuario.favoritos.length; i++) {
            if (usuario.favoritos[i].idEjercicio === idEjercicio 
                && usuario.favoritos[i].lenguajeDestino === idiomasSeleccionadosServicio.idiomaQueNOSe 
                && usuario.favoritos[i].lenguajeOrigen === idiomasSeleccionadosServicio.idiomaQueSe) return i;
        };

        return -1;
    };

    var ejFallado = function(saltado){ //saltado para ver si mostramos la vista de ej incorrecto
        $scope.vidas -= 1;
        if (!saltado)
            $scope.incorrecto = true;

        //hay que resetearlo, sino el botón solo lo puedes pulsar la priera vez
        $scope.candEnviado = false;
    };

    var corregirEjercicio = function(idEj, codigo){
        var req = {
            method: 'PUT',
            url: rutaApp + 'envios',
            data: {
                'codigo': codigo,
                'idUsuario': usuario.ID,
                'idEjercicio':  idEj,
                'lenguajeOrigen': idiomasSeleccionadosServicio.idiomaQueSe,
                'lenguajeDestino': idiomasSeleccionadosServicio.idiomaQueNOSe
            }
        }

        $scope.corrigiendo = true;

        $http(req).success(function(respuesta) {
            //console.log(respuesta);
            if (respuesta.error === 'no'){
                //console.log(usuario);
                var date = new Date();
                var ejercicioCorregido = {
                    'codigo': codigo,
                    'fecha': date.toISOString(),
                    'id': respuesta.id,
                    'idEjercicio':  idEj,
                    'idUsuario': usuario.ID,
                    'lenguajeOrigen': idiomasSeleccionadosServicio.idiomaQueSe,
                    'lenguajeDestino': idiomasSeleccionadosServicio.idiomaQueNOSe,
                    'puntuacion': 2//respuesta.puntuacion
                };

                usuario.historialEjercicios.push(ejercicioCorregido);

                $scope.ultimaRespuesta = ejercicioCorregido;
                $scope.ultimoEj = $scope.EjActual();

                $scope.ejercicios.splice(0, 1);
                $scope.textoEscrito = undefined;
                //console.log($scope.ultimoEj);
                //console.log(usuario);
            }

            if (umbralValido <= ejercicioCorregido.puntuacion){
                $scope.ejerciciosRestantes -= 1;
                $scope.correcto = true;

                if ($scope.ejerciciosRestantes <= 0){
                    console.log("sin hacer, hay que marcar la lección como terminada");
                }
            }
            else{
                ejFallado(false);
            }
        });
    };

    $scope.ejMarcadoFavorito= function(idEjercicio) {
        if (usuario === undefined) return '';
        if (verSiEjercicioEstaMarcadoComoFavorito(idEjercicio) != -1) return 'marcado';
        else return '';
    };


    $scope.colorRojoVidas = function(indiceCorazones) {
    	if ($scope.vidas > indiceCorazones) return 'rojo';
    	else return '';
    };

     $scope.porcentajeCompletado = function() {
     	return 100 - (($scope.ejerciciosRestantes / $scope.ejerciciosTotales) * 100);
    };

    $scope.saltar = function() {
        if ($scope.correcto || $scope.incorrecto){//Si estamos mostrando algo de correcto/incorrecto, lo dejamos de mostrar, y el ej ya está "avanzado"
            $scope.correcto = false;
            $scope.incorrecto = false;
            $scope.corrigiendo = false;

            if ($scope.vidas <= 0){
                $scope.sinVidas = true;
            }
            else if ($scope.ejerciciosRestantes <= 0){
                $scope.terminadoAcertando = true;
            }
        }
        else{
        	if ($scope.textoEscrito === undefined || $scope.textoEscrito === '' || $scope.textoEscrito === null) {
        	//No ha escrito nada, y se lo salta quitandole una vida
                $scope.ejercicios.splice(0, 1);
        		ejFallado(true);
                if ($scope.vidas <= 0){
                    $scope.sinVidas = true;
                }
        	}
        	else{//Ha escrito algo y se puede corregir
                corregirEjercicio($scope.EjActual().id, $scope.textoEscrito);
        	}
        }
    };

    $scope.favorito = function() {
        var indiceFav = verSiEjercicioEstaMarcadoComoFavorito($scope.ejercicios[0].id);
        if (indiceFav == -1){
        	usuario.favoritos.push({
        		idEjercicio: $scope.ejercicios[0].id,
        		idUsuario: usuario.ID,
        		lenguajeDestino: idiomasSeleccionadosServicio.idiomaQueNOSe,
        		lenguajeOrigen: idiomasSeleccionadosServicio.idiomaQueSe
        	});
            var req = {
                method: 'PUT',
                url: rutaApp + 'favoritos',
                headers: {
                    'userID': usuario.ID
                },
                data: {
                    'favoritos': usuario.favoritos
                }
            }

            $http(req).success(function(posibleError) {
                //console.log(posibleError);
            });
        }
        else{
            usuario.favoritos.splice(indiceFav, 1);
            var req = {
                method: 'PUT',
                url: rutaApp + 'favoritos',
                headers: {
                    'userID': usuario.ID
                },
                data: {
                    'favoritos': usuario.favoritos
                }
            }

            $http(req).success(function(posibleError) {
                //console.log(posibleError);
            });
        }
    };

    $scope.enunciadoCompleto = function(idejercicio, lenguaje) {
            if ($scope.enunciados === null) return {};
            else {
                var terminada = false;
                var nombre = {};

                var i = 0;
                while (i < $scope.enunciados.length && !terminada) {
                    if ($scope.enunciados[i].idDelEjercicioQueResuelve == idejercicio && $scope.enunciados[i].nombreLenguaje == lenguaje) {
                        terminada = true;
                        nombre = $scope.enunciados[i];
                    } else i++;
                };
                //console.log(nombre);

                return nombre;
            }
    };

    $scope.botonCorregir = function(){
        if ($scope.textoEscrito !== undefined && $scope.textoEscrito !== '' && $scope.textoEscrito !== null) {
            corregirEjercicio($scope.EjActual().id, $scope.textoEscrito);
        }
    };

    $scope.reiniciar = function(){
        init();
    };

    $scope.anhadirCandidato = function(){
        console.log("sin hacer: añadir candidato   " + $scope.textoEscrito);
        console.log($scope.ultimoEj);
        //El id es el del $scope.ultimoEj porque al corregirlo quitamos el primer elemento del array de ejercicios y 
        //guardamos el ejercicio borrado en "ultimoEj" (líneas 392 y 394 de duocode.js)

        //Algo similar para el codigo. se pone a undefined  $scope.textoEscrito (linea 407), para que cuando pases al siguiente ej la caja de texto donde escribes
        //esté vacia, y te ponga el tooltip, sino tine lo que hayas escrito antes y no se "vacía". Por esto se guarda en $scope.ultimaRespuesta.
        //También resetado en la linea 367 $scope.candEnviado
        var candidatoAProponer = {
        		codigo: $scope.ultimaRespuesta.codigo,
                idUsuario: usuario.ID,
                idEjercicio: $scope.ultimoEj.id,
                lenguajeOrigen: idiomasSeleccionadosServicio.idiomaQueSe,
        		lenguajeDestino: idiomasSeleccionadosServicio.idiomaQueNOSe
        		
        };
        usuario.candidatosPropuestos.push(candidatoAProponer);
            var req = {
                method: 'POST',
                url: rutaApp + 'candidatos',
                data: candidatoAProponer
            }

            $http(req).success(function(posibleError) {
                console.log(posibleError);
            });
        $scope.candEnviado = true;
    };

}]);


duocodeApp.controller('CandController', ['$scope', '$http', 'usuarioServicio', function($scope, $http, usuarioServicio) {
    
    
    usuarioServicio.then(function(dataCuandoLaFuncionSeEjecute) {
            $scope.usuario = dataCuandoLaFuncionSeEjecute.data;
            $scope.ejercicios = [];
            $scope.enunciados = []; 
            for (var i = 0; i < $scope.usuario.candidatosPropuestos.length; i++) {
                $http.get(rutaApp + 'ejercicios/' + $scope.usuario.candidatosPropuestos[i].idEjercicio).success(function(dataEjerciciosCand) {
                    $scope.ejercicios.push(dataEjerciciosCand);
                    for (var j = 0; j < dataEjerciciosCand.enunciados.length; j++) {
                        $http.get(dataEjerciciosCand.enunciados[j]).success(function(dataEnunciadoCand) {

                            var ind = 0;
                            var encontrado = false;
                            while(ind < $scope.usuario.candidatosPropuestos.length && !encontrado){
                                if (dataEnunciadoCand.idDelEjercicioQueResuelve == $scope.usuario.candidatosPropuestos[ind].idEjercicio && dataEnunciadoCand.nombreLenguaje == $scope.usuario.candidatosPropuestos[ind].lenguajeOrigen){
                                    $scope.enunciados.push(dataEnunciadoCand);
                                    encontrado = true;
                                }else
                                    ind++;
                            }

                        });

                    }

                });

            }
        });

        $scope.nombreEj = function(idejercicio) {
            if ($scope.ejercicios === null) return 'cargando';
            else {
                var terminada = false;
                var nombre = 'no encontrado';

                var i = 0;
                while (i < $scope.ejercicios.length && !terminada) {
                    if ($scope.ejercicios[i].id === idejercicio) {
                        terminada = true;
                        nombre = $scope.ejercicios[i].nombre;
                    } else i++;
                };

                return nombre;
            }
        };

        $scope.enunciadoCompleto = function(idejercicio, lenguaje) {
            if ($scope.enunciados === null) return {};
            else {
                var terminada = false;
                var nombre = {};

                var i = 0;
                while (i < $scope.enunciados.length && !terminada) {
                    if ($scope.enunciados[i].idDelEjercicioQueResuelve == idejercicio && $scope.enunciados[i].nombreLenguaje == lenguaje) {
                        terminada = true;
                        nombre = $scope.enunciados[i];
                    } else i++;
                };

                return nombre;
            }
        };
    
        $scope.eliminarCand = function(indice,idCand) {

            $scope.usuario.candidatosPropuestos.splice(indice, 1);
           var req = {
                method: 'DELETE',
                url: rutaApp + 'candidatos/' + idCand
            }

            $http(req).success(function(posibleError) {
                console.log(posibleError);
            });
            
            
        };
}]);

duocodeApp.controller('VotarCandidatosController', ['$scope', '$http', 'usuarioServicio', function($scope, $http, usuarioServicio) {
    $scope.candidatos = [];
    var usuario;
    var enunciados = [];
    var ejercicios = [];
    $scope.todoEvaluado = false;

    var hayQuePuntuarCandidato = function(candidato){
        return true; //ajustar según el candidato y el usuario
    }

    $scope.candidatoActual = function(){
        return $scope.candidatos[0];
    }

    $scope.enunciado = function(idEj, lenguaje){
        return enunciados[0];//hacer que recorra enunciados buscando esto
    }

    $scope.ejercicio = function(idEj){
        return ejercicios[0];//hacer que recorra ejercicios buscando esto
    }

    $scope.saltar = function(){       
        if ($scope.candidatos.length <= 1){
            $scope.todoEvaluado = true;
        }
        else{
            $scope.candidatos.splice(0, 1);
        }
    }

    $scope.votosPos = function(candidato){
        var votos = 0;
        if(candidato != undefined)
            votos = candidato.votosPos.length;
        return votos;
    }

    $scope.votosNeg = function(candidato){
        var votos = 0;
        if(candidato != undefined)
            votos = candidato.votosNeg.length;
        return votos;
    }

    $scope.tantoPorCientoPos = function(candidato){
        return ($scope.votosPos(candidato) / ($scope.votosNeg(candidato)+$scope.votosPos(candidato))) * 100;
    }

    $scope.tantoPorCientoNeg = function(candidato){
        return ($scope.votosNeg(candidato) / ($scope.votosNeg(candidato)+$scope.votosPos(candidato))) * 100;
    }

    usuarioServicio.then(function(dataCuandoLaFuncionSeEjecute) {
        usuario = dataCuandoLaFuncionSeEjecute.data;
        $http.get(rutaApp + "candidatos").success(function(dataCandidatos) {
            for (var i = 0; i < dataCandidatos.candidatos.length; i++) {
                $http.get(dataCandidatos.candidatos[i]).success(function(dataCandidato) {
                    if (hayQuePuntuarCandidato(dataCandidato)){
                        $scope.candidatos.push(dataCandidato);
                        $http.get(rutaApp + "ejercicios/"+dataCandidato.idEjercicio).success(function(dataEjercicios) {
                            ejercicios.push(dataEjercicios);
                            for (var i = 0; i < dataEjercicios.enunciados.length; i++) {
                                $http.get(dataEjercicios.enunciados[i]).success(function(dataEnunciado) {
                                    //console.log(dataEnunciado);
                                    enunciados.push(dataEnunciado);
                                });
                            };
                        });
                    }
                    //console.log($scope.candidatos);
                });
            };
        });
    });
    
}]);