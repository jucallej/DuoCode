SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;


CREATE TABLE IF NOT EXISTS `candidato` (
`ID` int(20) unsigned NOT NULL,
  `codigo` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  `gestionadoPor` int(20) unsigned DEFAULT NULL,
  `idUsuario` int(20) unsigned NOT NULL,
  `idEjercicio` int(20) unsigned NOT NULL,
  `lenguajeOrigen` varchar(20) NOT NULL,
  `lenguajeDestino` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `desbloqueadapor` (
  `idLeccion` int(20) unsigned NOT NULL,
  `desbloqueadaPor` int(20) unsigned NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `ejercicio` (
`ID` int(20) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

INSERT INTO `ejercicio` (`ID`, `nombre`) VALUES
(1, 'Indicar si es positivo'),
(2, 'Máximo Común Divisor'),
(3, 'Transformación a mayúsculas o minúsculas'),
(4, 'Positivo o negativo'),
(5, 'Mínimo'),
(6, 'Hora correcta'),
(7, 'Cuadrante'),
(8, 'Rotación alfabeto'),
(9, 'Año bisiesto'),
(10, 'Cuadrado perfecto'),
(11, 'Cuadrado perfecto previo'),
(12, 'Ecuación de segundo grado');

CREATE TABLE IF NOT EXISTS `enunciado` (
`ID` int(20) unsigned NOT NULL,
  `texto` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idEjercicio` int(20) unsigned NOT NULL,
  `lenguaje` varchar(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

INSERT INTO `enunciado` (`ID`, `texto`, `fecha`, `idEjercicio`, `lenguaje`) VALUES
(1, 'public boolean positivo(double n) {\r\n	boolean valor = false;\r\n	if (n == Math.abs(n)) {\r\n		valor = true;\r\n	}\r\n	return valor;\r\n}\r\n', '2015-05-28 16:02:23', 1, 'Java'),
(2, 'public int max_comun_divisor(int n, int m) {\r\n	int mcd;\r\n	if (m == 0) {\r\n		mcd = n;\r\n	}\r\n	else {\r\n		mcd = max_comun_divisor(m, n % m);\r\n	}\r\n	return mcd;\r\n}\r\n', '2015-05-28 16:02:23', 2, 'Java'),
(3, 'public String minusculas_o_mayusculas(String cadena, boolean valor) {\r\n	String aux;\r\n	if (valor == true) {\r\n		aux = cadena.toLowerCase();\r\n	}\r\n	else {\r\n		aux = cadena.toUpperCase();\r\n	}\r\n	return aux;\r\n}\r\n', '2015-05-28 16:02:23', 3, 'Java'),
(4, 'public int positivo_o_negativo(int n, boolean valor) {\r\n	int solucion;\r\n	if (valor == true) {\r\n		solucion = Math.abs(n);\r\n	}\r\n	else {\r\n		solucion = -Math.abs(n);\r\n	}\r\n	return solucion;\r\n}\r\n', '2015-05-28 16:02:23', 4, 'Java'),
(5, 'public int minimo(int a, int b) {\r\n	int minimo;\r\n	if (a < b) {\r\n		minimo = a;\r\n	}\r\n	else {\r\n		minimo = b;\r\n	}\r\n	return minimo;\r\n}\r\n', '2015-05-28 16:02:23', 5, 'Java'),
(6, 'public boolean hora_correcta(int h, int m, int s) {\r\n	boolean horas_correctas= false; \r\n	boolean minutos_correctos = false;\r\n	boolean segundos_correctos = false;\r\n	if (h >= 0 && h < 24) {\r\n		horas_correctas = true;\r\n	}\r\n	if (m >= 0  && m < 60) {\r\n		minutos_correctos = true;\r\n	}\r\n	if (s >=0 && s < 60) {\r\n		segundos_correctos = true;\r\n	}\r\n	boolean correcto = horas_correctas && minutos_correctos && segundos_correctos;\r\n	return correcto;\r\n}\r\n', '2015-05-28 16:02:23', 6, 'Java'),
(7, 'public int cuadrante(int x, int y) {\r\n	int cuadrante; \r\n	// Ejes\r\n	if (x == 0 || y == 0 ) {\r\n		cuadrante = 0;\r\n	}\r\n	else {\r\n		if (x > 0) {\r\n			if (y > 0) {\r\n				cuadrante = 1;\r\n			}\r\n			else {\r\n				cuadrante = 4;\r\n			}\r\n		}\r\n		else { //x < 0\r\n			if (y > 0 ) {\r\n				cuadrante = 2;\r\n			}\r\n			else {\r\n				cuadrante = 3;\r\n			}\r\n		}\r\n			\r\n	}\r\n	return cuadrante;\r\n}\r\n', '2015-05-28 16:02:23', 7, 'Java'),
(8, 'public char rotacion_alfabeto(char l) { \r\n	int posicion = (int)l;\r\n	int nueva_posicion;\r\n	if ((posicion >= 97 && posicion < 122) || (posicion >= 65 && posicion < 90)) {\r\n		nueva_posicion = posicion + 1;\r\n	}\r\n	else {\r\n		if (posicion == 122) { // z\r\n			nueva_posicion = 97;\r\n		}\r\n		else { // Z\r\n			nueva_posicion = 65;\r\n		}\r\n	}\r\n	char letra_correspondiente = (char) nueva_posicion;\r\n	return letra_correspondiente;\r\n}\r\n', '2015-05-28 16:02:23', 8, 'Java'),
(9, 'public boolean bisiesto(int n) {\r\n	boolean valor = false;\r\n	if (((n % 4 == 0) && (n % 100 != 0)) || (n%400 == 0)) {\r\n		valor = true;\r\n	}\r\n	return valor;\r\n}\r\n', '2015-05-28 16:02:23', 9, 'Java'),
(10, 'private boolean cuadrado_perfecto(double m) {\r\n	double cuadrado = Math.sqrt(m);\r\n	boolean resultado = (cuadrado*cuadrado == m);\r\n	return resultado;\r\n}\r\n', '2015-05-28 16:02:23', 10, 'Java'),
(11, 'public double cuadrado_perfecto_previo(double n) { \r\n	double raiz = Math.sqrt(n);\r\n	if (!cuadrado_perfecto(n)) {\r\n		raiz = (int) raiz; // Función parte entera\r\n	}\r\n	else {\r\n		raiz = ((int) raiz) - 1;\r\n	}\r\n	double cuadrado = raiz*raiz;\r\n	return cuadrado;\r\n}\r\n', '2015-05-28 16:02:23', 11, 'Java'),
(12, 'public Pareja<Integer, Integer> ec_segundo_grado(int a, int b, int c) {\r\n	Pareja<Integer, Integer> solucion = new Pareja<Integer, Integer>(0, 0);\r\n	int raiz1 = b*b - 4*a*c;\r\n	int exp1 = (int) Math.sqrt(raiz1);\r\n	if (Math.sqrt(raiz1) == exp1){\r\n		int sol1 = (-b - exp1)/(2*a);\r\n		int sol2 = (-b + exp1)/(2*a);\r\n		solucion = new Pareja<Integer, Integer>(sol1, sol2);\r\n	}\r\n	return solucion; \r\n}\r\n', '2015-05-28 16:02:23', 12, 'Java');

CREATE TABLE IF NOT EXISTS `envio` (
`ID` int(20) NOT NULL,
  `codigo` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `puntuacion` int(20) unsigned NOT NULL DEFAULT '0',
  `idUsuario` int(20) unsigned NOT NULL,
  `idEjercicio` int(20) unsigned NOT NULL,
  `lenguajeOrigen` varchar(20) NOT NULL,
  `lenguajeDestino` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `favorito` (
  `idUsuario` int(20) unsigned NOT NULL,
  `idEjercicio` int(20) unsigned NOT NULL,
  `lenguajeOrigen` varchar(20) NOT NULL,
  `lenguajeDestino` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `leccion` (
`ID` int(20) unsigned NOT NULL,
  `orden` int(20) unsigned NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `descripcion` text,
  `explicacion` text NOT NULL,
  `idTema` int(20) unsigned NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=86 ;

INSERT INTO `leccion` (`ID`, `orden`, `titulo`, `descripcion`, `explicacion`, `idTema`) VALUES
(33, 1, 'Expresiones numéricas', 'Haciendo especial énfasis en las cambios de tipo al mezclar diferentes números (e.g. enteros y float), incluyendo operadores de redondeo y la división entera.', '', 9),
(34, 2, 'Booleanos', 'Incluyendo tanto operaciones lógicas como operaciones de comparación entre otros tipos.\r\n', '', 9),
(35, 3, 'Cadenas', 'Incluyendo funciones de busqueda de subcadenas e impresión por pantalla', '', 9),
(36, 4, 'Listas/arrays', 'Incluyendo acceso, inserción y borrado.', '', 9),
(45, 1, 'Usos básicos', 'Cálculo de expresiones simples.', '', 10),
(46, 2, 'Invocación de funciones', 'Invocación de funciones desde otras funciones.', '', 10),
(47, 3, 'Parámetros', 'Semánticas en el paso de parámetros.', '', 10),
(48, 4, 'Procedimientos vs. funciones', 'Procedimientos vs. funciones.', '', 10),
(49, 1, 'Condicionales simples', 'Es una instrucción o grupo de instrucciones que se pueden ejecutar o no en función del valor de una condición.', 'El bloque de instrucciones que se encuentra dentro del condicional se evaluará si la condición que está dentro de los paréntesis se cumple, es decir si su valor se evalúa a True.\r\n\r\nEl comportamiento de los condicionales se extiende con la clausula else que siempre que se usa se sitúa a continuación del if. Su comportamiento es similar al del if, sólo que las instrucciones que se encuentran entre sus llaves se evalúan si la (o las) condiciones anteriores no se ha evaluado a True. \r\n\r\nSe pueden hacer más comprobaciones añadiendo else if.', 11),
(50, 2, 'Condicionales anidados', 'Una estructura condicional es anidada cuando por la rama del verdadero o el falso de una estructura condicional hay otra estructura condicional.', 'Decimos que una estructura condicional es anidada cuando por la rama del verdadero o el falso de una estructura condicional hay otra estructura condicional.\r\n\r\nPor ejemplo si existe un if-else dentro de otro if.', 11),
(55, 1, 'Bucles simples', 'Es una sentencia que se realiza repetidas veces a un trozo aislado de código, hasta que la condición asignada a dicho bucle deje de cumplirse.', '', 12),
(56, 2, 'Optimización en bucles', 'Mejorando las condiciones.', '', 12),
(57, 3, 'Combinación de bucles y condicionales', '', '', 12),
(58, 4, 'Bucles anidados', 'Constan de un bucle externo con uno o más bucles internos.', '', 12),
(61, 1, 'Recursión básica', 'Una acción recursiva tiene recursión simple (o lineal) si cada caso recursivo realiza exactamente una llamada recursiva.', '', 13),
(62, 2, 'Recursión múltiple', 'Este tipo de recursión se caracteriza por que, al menos en un caso recursivo, se realizan varias llamadas recursivas.', '', 13),
(66, 3, 'Recursión mutua', 'Es una forma de recursividad donde dos funciones o tipos de datos se definen cada una en términos de la otra.', '', 13),
(67, 4, 'Técnicas de inmersión', 'Tecnicas de inmersión para funciones recursivas.', '', 13),
(68, 5, 'No final a final', 'Transformacion de recusión no final en recursión final. ', '', 13),
(69, 1, 'Sintaxis clases', NULL, '', 14),
(70, 2, 'Encapsulamiento', NULL, '', 14),
(71, 3, 'Herencia', NULL, '', 14),
(72, 4, 'Polimorfismo', NULL, '', 14),
(73, 1, 'Estructuras lineales', 'Pilas, colas y listas.\r\n', '', 15),
(74, 2, 'Árboles', 'árboles binarios, árboles generales, árboles de búsqueda y árboles equilibrados.', '', 15),
(75, 3, 'Grafos', '', '', 15),
(81, 1, 'Divide y vencerás', 'Incluyendo algoritmos de ordenación. ', '', 16),
(82, 2, 'Metodo voraz', 'Incluyendo algoritmos sobre grafos.', '', 16),
(83, 3, 'Programacion dinámica', 'Distinguiendo su dificultad según el número de parámetros necesarios en la fórmula que soluciona el problema y la complejidad de la reducción de espacio. ', '', 16),
(84, 4, 'Vuelta atrás', 'Prestando atención a posibles técnicas de optimización.', '', 16),
(85, 5, 'Ramificación y poda', 'Prestando atención al esquema optimista-pesimista. ', '', 16);

CREATE TABLE IF NOT EXISTS `leccionconstaejercicio` (
  `idLeccion` int(20) unsigned NOT NULL,
  `idEjercicio` int(20) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `leccionconstaejercicio` (`idLeccion`, `idEjercicio`) VALUES
(49, 1),
(49, 2),
(49, 3),
(49, 4),
(49, 5),
(49, 6),
(50, 7),
(50, 8),
(49, 9),
(49, 10),
(49, 11),
(49, 12);

CREATE TABLE IF NOT EXISTS `lenguaje` (
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `lenguaje` (`nombre`) VALUES
('C++'),
('Java'),
('PHP'),
('Python');

CREATE TABLE IF NOT EXISTS `tema` (
`ID` int(20) unsigned NOT NULL,
  `orden` int(20) unsigned NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `descripcion` text
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

INSERT INTO `tema` (`ID`, `orden`, `titulo`, `descripcion`) VALUES
(9, 1, 'Expresiones', 'Permite a los usuarios conocer los distintos tipos basicos disponibles en el lenguaje, las operaciones disponibles sobre ellos y las instrucciones basicas como ´\r\nla asignacion y la composición de instrucciones básicas.'),
(10, 2, 'Funciones', 'Introduce la sintaxis de las funciones, la forma de invocarlas, sus posibles\r\nmodificadores de privacidad, las diferentes semánticas para los parámetros y las diferencias entre funciones y procedimientos.'),
(11, 3, 'Condicionales', 'Presenta las distintas instrucciones de control que permitan establecer distintos caminos de ejecución, como son las instrucciones if, case o switch.'),
(12, 4, 'Bucles', 'Un bucle es una sentencia que se realiza repetidas veces a un trozo aislado de código, hasta que la condición asignada a dicho bucle deje de cumplirse.'),
(13, 5, 'Recursión', 'Presenta los distintos esquemas para programar y optimizar funciones recursivas.'),
(14, 6, 'Clases', 'Presenta como programar una clase, haciendo énfasis en las ideas básicas de encapsulamiento, herencia y polimorfismo, y describiendo las principales características de los\r\natributos, constantes y funciones.'),
(15, 7, 'Estructuras de datos', 'Permite a los usuarios definir estructuras de datos básicas y operaciones sobre ellas.'),
(16, 8, 'Métodos algorítmicos', 'Presenta los principales esquemas algorítmicos para resolución de problemas.');

CREATE TABLE IF NOT EXISTS `usuario` (
`ID` int(20) unsigned NOT NULL,
  `IDGoogle` text NOT NULL,
  `rol` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `usuariocompletaleccion` (
  `idUsuario` int(20) unsigned NOT NULL DEFAULT '0',
  `idLeccion` int(20) unsigned NOT NULL DEFAULT '0',
  `lenguaje` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `usuariovotacandidato` (
  `idUsuario` int(20) unsigned NOT NULL,
  `idCandidato` int(20) unsigned NOT NULL,
  `voto` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `candidato`
 ADD PRIMARY KEY (`ID`), ADD KEY `gestionadoPor` (`gestionadoPor`), ADD KEY `idUsuario` (`idUsuario`), ADD KEY `idEjercicio` (`idEjercicio`), ADD KEY `lenguajeOrigen` (`lenguajeOrigen`), ADD KEY `lenguajeDestino` (`lenguajeDestino`);

ALTER TABLE `desbloqueadapor`
 ADD PRIMARY KEY (`idLeccion`,`desbloqueadaPor`), ADD KEY `desbloqueadaPor` (`desbloqueadaPor`);

ALTER TABLE `ejercicio`
 ADD PRIMARY KEY (`ID`);

ALTER TABLE `enunciado`
 ADD PRIMARY KEY (`ID`), ADD KEY `idEjercicio` (`idEjercicio`), ADD KEY `lenguaje` (`lenguaje`);

ALTER TABLE `envio`
 ADD PRIMARY KEY (`ID`), ADD KEY `lenguajeOrigen` (`lenguajeOrigen`), ADD KEY `lenguajeDestino` (`lenguajeDestino`), ADD KEY `lenguajeOrigen_2` (`lenguajeOrigen`,`lenguajeDestino`), ADD KEY `idUsuario` (`idUsuario`), ADD KEY `idEjercicio` (`idEjercicio`);

ALTER TABLE `favorito`
 ADD PRIMARY KEY (`idUsuario`,`idEjercicio`,`lenguajeOrigen`,`lenguajeDestino`), ADD KEY `idEjercicio` (`idEjercicio`), ADD KEY `lenguajeOrigen` (`lenguajeOrigen`), ADD KEY `lenguajeDestino` (`lenguajeDestino`);

ALTER TABLE `leccion`
 ADD PRIMARY KEY (`ID`), ADD KEY `idTema` (`idTema`);

ALTER TABLE `leccionconstaejercicio`
 ADD PRIMARY KEY (`idLeccion`,`idEjercicio`), ADD KEY `idEjercicio` (`idEjercicio`);

ALTER TABLE `lenguaje`
 ADD PRIMARY KEY (`nombre`);

ALTER TABLE `tema`
 ADD PRIMARY KEY (`ID`);

ALTER TABLE `usuario`
 ADD PRIMARY KEY (`ID`);

ALTER TABLE `usuariocompletaleccion`
 ADD PRIMARY KEY (`idUsuario`,`idLeccion`), ADD KEY `idLeccion` (`idLeccion`);

ALTER TABLE `usuariovotacandidato`
 ADD PRIMARY KEY (`idUsuario`,`idCandidato`), ADD KEY `idCandidato` (`idCandidato`);


ALTER TABLE `candidato`
MODIFY `ID` int(20) unsigned NOT NULL AUTO_INCREMENT;
ALTER TABLE `ejercicio`
MODIFY `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
ALTER TABLE `enunciado`
MODIFY `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
ALTER TABLE `envio`
MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `leccion`
MODIFY `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=86;
ALTER TABLE `tema`
MODIFY `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
ALTER TABLE `usuario`
MODIFY `ID` int(20) unsigned NOT NULL AUTO_INCREMENT;

ALTER TABLE `candidato`
ADD CONSTRAINT `candidato_ibfk_1` FOREIGN KEY (`gestionadoPor`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `candidato_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `candidato_ibfk_3` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `candidato_ibfk_4` FOREIGN KEY (`lenguajeOrigen`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `candidato_ibfk_5` FOREIGN KEY (`lenguajeDestino`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `desbloqueadapor`
ADD CONSTRAINT `desbloqueadapor_ibfk_1` FOREIGN KEY (`idLeccion`) REFERENCES `leccion` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `desbloqueadapor_ibfk_2` FOREIGN KEY (`desbloqueadaPor`) REFERENCES `leccion` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `enunciado`
ADD CONSTRAINT `enunciado_ibfk_1` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `enunciado_ibfk_2` FOREIGN KEY (`lenguaje`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `envio`
ADD CONSTRAINT `envio_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `envio_ibfk_2` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `envio_ibfk_3` FOREIGN KEY (`lenguajeOrigen`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `envio_ibfk_4` FOREIGN KEY (`lenguajeDestino`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `favorito`
ADD CONSTRAINT `favorito_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `favorito_ibfk_2` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `favorito_ibfk_3` FOREIGN KEY (`lenguajeOrigen`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `favorito_ibfk_4` FOREIGN KEY (`lenguajeDestino`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `leccion`
ADD CONSTRAINT `leccion_ibfk_1` FOREIGN KEY (`idTema`) REFERENCES `tema` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `leccionconstaejercicio`
ADD CONSTRAINT `leccionconstaejercicio_ibfk_1` FOREIGN KEY (`idLeccion`) REFERENCES `leccion` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `leccionconstaejercicio_ibfk_2` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `usuariocompletaleccion`
ADD CONSTRAINT `usuariocompletaleccion_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `usuariocompletaleccion_ibfk_2` FOREIGN KEY (`idLeccion`) REFERENCES `leccion` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `usuariovotacandidato`
ADD CONSTRAINT `usuariovotacandidato_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `usuariovotacandidato_ibfk_2` FOREIGN KEY (`idCandidato`) REFERENCES `candidato` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
