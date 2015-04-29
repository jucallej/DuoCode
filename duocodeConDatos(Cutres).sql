-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-04-2015 a las 18:48:17
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `duocode`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `candidato`
--

CREATE TABLE IF NOT EXISTS `candidato` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `codigo` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  `gestionadoPor` int(20) unsigned DEFAULT NULL,
  `idUsuario` int(20) unsigned NOT NULL,
  `idEjercicio` int(20) unsigned NOT NULL,
  `lenguajeOrigen` varchar(20) NOT NULL,
  `lenguajeDestino` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `gestionadoPor` (`gestionadoPor`),
  KEY `idUsuario` (`idUsuario`),
  KEY `idEjercicio` (`idEjercicio`),
  KEY `lenguajeOrigen` (`lenguajeOrigen`),
  KEY `lenguajeDestino` (`lenguajeDestino`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `desbloqueadapor`
--

CREATE TABLE IF NOT EXISTS `desbloqueadapor` (
  `idLeccion` int(20) unsigned NOT NULL,
  `desbloqueadaPor` int(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`idLeccion`,`desbloqueadaPor`),
  KEY `desbloqueadaPor` (`desbloqueadaPor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `desbloqueadapor`
--

INSERT INTO `desbloqueadapor` (`idLeccion`, `desbloqueadaPor`) VALUES
(2, 1),
(6, 1),
(14, 1),
(15, 1),
(23, 1),
(14, 3),
(15, 3),
(6, 6),
(23, 6),
(25, 6),
(24, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejercicio`
--

CREATE TABLE IF NOT EXISTS `ejercicio` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `ejercicio`
--

INSERT INTO `ejercicio` (`ID`, `nombre`) VALUES
(3, 'Ej1'),
(4, 'ej2'),
(5, 'ej3'),
(6, 'ej4'),
(7, 'ej5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enunciado`
--

CREATE TABLE IF NOT EXISTS `enunciado` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `texto` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idEjercicio` int(20) unsigned NOT NULL,
  `lenguaje` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `idEjercicio` (`idEjercicio`),
  KEY `lenguaje` (`lenguaje`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `enunciado`
--

INSERT INTO `enunciado` (`ID`, `texto`, `fecha`, `idEjercicio`, `lenguaje`) VALUES
(2, 'codigo del enunciado Modificado', '2014-12-13 23:00:00', 5, 'Java'),
(5, 'codigo del enunciado', '2014-12-14 17:26:32', 5, 'C++'),
(6, 'enunciado Java', '2015-03-18 09:57:35', 3, 'Java'),
(7, 'enunciado Java', '2015-03-18 09:57:40', 4, 'Java'),
(8, 'enunciado C++', '2015-03-18 09:57:52', 4, 'C++'),
(9, 'enunciado C++', '2015-03-18 09:57:54', 3, 'C++');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envio`
--

CREATE TABLE IF NOT EXISTS `envio` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `codigo` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `puntuacion` int(20) unsigned NOT NULL DEFAULT '0',
  `idUsuario` int(20) unsigned NOT NULL,
  `idEjercicio` int(20) unsigned NOT NULL,
  `lenguajeOrigen` varchar(20) NOT NULL,
  `lenguajeDestino` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `lenguajeOrigen` (`lenguajeOrigen`),
  KEY `lenguajeDestino` (`lenguajeDestino`),
  KEY `lenguajeOrigen_2` (`lenguajeOrigen`,`lenguajeDestino`),
  KEY `idUsuario` (`idUsuario`),
  KEY `idEjercicio` (`idEjercicio`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favorito`
--

CREATE TABLE IF NOT EXISTS `favorito` (
  `idUsuario` int(20) unsigned NOT NULL,
  `idEjercicio` int(20) unsigned NOT NULL,
  `lenguajeOrigen` varchar(20) NOT NULL,
  `lenguajeDestino` varchar(20) NOT NULL,
  PRIMARY KEY (`idUsuario`,`idEjercicio`,`lenguajeOrigen`,`lenguajeDestino`),
  KEY `idEjercicio` (`idEjercicio`),
  KEY `lenguajeOrigen` (`lenguajeOrigen`),
  KEY `lenguajeDestino` (`lenguajeDestino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `leccion`
--

CREATE TABLE IF NOT EXISTS `leccion` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `orden` int(20) unsigned NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `descripcion` text,
  `explicacion` text NOT NULL,
  `idTema` int(20) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `idTema` (`idTema`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=26 ;

--
-- Volcado de datos para la tabla `leccion`
--

INSERT INTO `leccion` (`ID`, `orden`, `titulo`, `descripcion`, `explicacion`, `idTema`) VALUES
(1, 1, 'Leccion inicialEditado', 'desc inicial', '', 3),
(2, 4, 'fae', 'faefa', '', 1),
(3, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(4, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(5, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(6, 10, 'Leccion PruebaPst', 'desc inicial', '', 1),
(7, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(8, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(9, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(10, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(11, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(12, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(13, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(14, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(15, 8, 'Leccion PruebaPost', 'desc inicial', '', 1),
(16, 8, 'Leccion PruebaPost2', 'desc inicial', '', 1),
(17, 8, 'Leccion PruebaPost2', 'desc inicial', '', 1),
(19, 8, 'Leccion PruebaPost2', 'desc inicial', '', 1),
(20, 9, 'Probando', 'desc inicial', 'Explicacion detallada', 1),
(21, 8, 'Leccion PruebaPost', 'desc inicial', 'Explicacion detallada', 1),
(22, 9, 'Leccion PruebaPut', 'desc inicial', '', 1),
(23, 9, 'Leccion PruebaPost', 'desc inicial', '', 1),
(24, 9, 'Leccion PruebaPut', 'desc inicial', '', 1),
(25, 10, 'Leccion PruebaPst', 'desc inicial', '', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `leccionconstaejercicio`
--

CREATE TABLE IF NOT EXISTS `leccionconstaejercicio` (
  `idLeccion` int(20) unsigned NOT NULL,
  `idEjercicio` int(20) unsigned NOT NULL,
  PRIMARY KEY (`idLeccion`,`idEjercicio`),
  KEY `idEjercicio` (`idEjercicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `leccionconstaejercicio`
--

INSERT INTO `leccionconstaejercicio` (`idLeccion`, `idEjercicio`) VALUES
(1, 3),
(6, 3),
(14, 3),
(15, 3),
(16, 3),
(17, 3),
(19, 3),
(20, 3),
(23, 3),
(25, 3),
(20, 4),
(6, 5),
(14, 5),
(15, 5),
(16, 5),
(17, 5),
(19, 5),
(20, 5),
(23, 5),
(24, 5),
(20, 6),
(20, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lenguaje`
--

CREATE TABLE IF NOT EXISTS `lenguaje` (
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `lenguaje`
--

INSERT INTO `lenguaje` (`nombre`) VALUES
('"python"'),
('C++'),
('Java'),
('lenguaje Nuevo'),
('lenguajeNuevo'),
('python');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tema`
--

CREATE TABLE IF NOT EXISTS `tema` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `orden` int(20) unsigned NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `tema`
--

INSERT INTO `tema` (`ID`, `orden`, `titulo`, `descripcion`) VALUES
(1, 1, 'IF', 'tema de if'),
(3, 9, 'titulo del tema (ej. bucles)Editado', 'descripción del tema (ej. los bucles hacen bla bla)'),
(4, 1, 'temaNuevo', 'tema de if'),
(5, 0, 'IF', 'tema de if'),
(6, 73, 'IF', 'tema de if');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `IDGoogle` varchar(200) DEFAULT NULL,
  `IDFacebook` varchar(200) DEFAULT NULL,
  `rol` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IDFacebook` (`IDFacebook`),
  UNIQUE KEY `IDGoogle` (`IDGoogle`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuariocompletaleccion`
--

CREATE TABLE IF NOT EXISTS `usuariocompletaleccion` (
  `idUsuario` int(20) unsigned NOT NULL DEFAULT '0',
  `idLeccion` int(20) unsigned NOT NULL DEFAULT '0',
  `lenguaje` varchar(20) NOT NULL,
  PRIMARY KEY (`idUsuario`,`idLeccion`),
  KEY `idLeccion` (`idLeccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuariovotacandidato`
--

CREATE TABLE IF NOT EXISTS `usuariovotacandidato` (
  `idUsuario` int(20) unsigned NOT NULL,
  `idCandidato` int(20) unsigned NOT NULL,
  `voto` tinyint(1) NOT NULL,
  PRIMARY KEY (`idUsuario`,`idCandidato`),
  KEY `idCandidato` (`idCandidato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `candidato`
--
ALTER TABLE `candidato`
  ADD CONSTRAINT `candidato_ibfk_1` FOREIGN KEY (`gestionadoPor`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `candidato_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `candidato_ibfk_3` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `candidato_ibfk_4` FOREIGN KEY (`lenguajeOrigen`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `candidato_ibfk_5` FOREIGN KEY (`lenguajeDestino`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `desbloqueadapor`
--
ALTER TABLE `desbloqueadapor`
  ADD CONSTRAINT `desbloqueadapor_ibfk_1` FOREIGN KEY (`idLeccion`) REFERENCES `leccion` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `desbloqueadapor_ibfk_2` FOREIGN KEY (`desbloqueadaPor`) REFERENCES `leccion` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `enunciado`
--
ALTER TABLE `enunciado`
  ADD CONSTRAINT `enunciado_ibfk_1` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `enunciado_ibfk_2` FOREIGN KEY (`lenguaje`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `envio`
--
ALTER TABLE `envio`
  ADD CONSTRAINT `envio_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `envio_ibfk_2` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `envio_ibfk_3` FOREIGN KEY (`lenguajeOrigen`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `envio_ibfk_4` FOREIGN KEY (`lenguajeDestino`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `favorito`
--
ALTER TABLE `favorito`
  ADD CONSTRAINT `favorito_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favorito_ibfk_2` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favorito_ibfk_3` FOREIGN KEY (`lenguajeOrigen`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favorito_ibfk_4` FOREIGN KEY (`lenguajeDestino`) REFERENCES `lenguaje` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `leccion`
--
ALTER TABLE `leccion`
  ADD CONSTRAINT `leccion_ibfk_1` FOREIGN KEY (`idTema`) REFERENCES `tema` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `leccionconstaejercicio`
--
ALTER TABLE `leccionconstaejercicio`
  ADD CONSTRAINT `leccionconstaejercicio_ibfk_1` FOREIGN KEY (`idLeccion`) REFERENCES `leccion` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `leccionconstaejercicio_ibfk_2` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuariocompletaleccion`
--
ALTER TABLE `usuariocompletaleccion`
  ADD CONSTRAINT `usuariocompletaleccion_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuariocompletaleccion_ibfk_2` FOREIGN KEY (`idLeccion`) REFERENCES `leccion` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuariovotacandidato`
--
ALTER TABLE `usuariovotacandidato`
  ADD CONSTRAINT `usuariovotacandidato_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuariovotacandidato_ibfk_2` FOREIGN KEY (`idCandidato`) REFERENCES `candidato` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
