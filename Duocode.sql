-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-04-2015 a las 18:48:50
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejercicio`
--

CREATE TABLE IF NOT EXISTS `ejercicio` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lenguaje`
--

CREATE TABLE IF NOT EXISTS `lenguaje` (
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
