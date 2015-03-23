-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-03-2015 a las 16:26:57
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

--
-- Volcado de datos para la tabla `candidato`
--

INSERT INTO `candidato` (`ID`, `codigo`, `fecha`, `estado`, `gestionadoPor`, `idUsuario`, `idEjercicio`, `lenguajeOrigen`, `lenguajeDestino`) VALUES
(6, 'hjjk', '2015-02-17 17:47:18', 1, 1, 1, 4, 'C++', 'Java'),
(9, 'hjjk', '2015-02-17 17:49:02', 1, 1, 1, 4, 'C++', 'Java'),
(10, 'hjjk', '2015-02-17 17:49:04', 1, 1, 1, 4, 'C++', 'Java'),
(11, 'hjjk', '2015-02-17 17:49:05', 1, 1, 1, 4, 'C++', 'Java'),
(14, 'hjjk', '2015-02-16 23:00:00', 2, 1, 1, 4, 'C++', 'Java'),
(15, 'hjjk', '2015-02-17 18:00:53', 1, 1, 1, 4, 'C++', 'Java');

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
(20, 1),
(23, 1),
(14, 3),
(15, 3),
(6, 6),
(20, 6),
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `ejercicio`
--

INSERT INTO `ejercicio` (`ID`, `nombre`) VALUES
(3, 'Ej1'),
(4, 'ej2'),
(5, 'ej3'),
(6, 'ej4');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=110 ;

--
-- Volcado de datos para la tabla `envio`
--

INSERT INTO `envio` (`ID`, `codigo`, `fecha`, `puntuacion`, `idUsuario`, `idEjercicio`, `lenguajeOrigen`, `lenguajeDestino`) VALUES
(10, 'codigo en java', '2015-02-17 18:09:27', 4, 1, 3, 'Java', 'C++'),
(11, 'codigo enjavaNuevo', '2015-02-19 11:03:09', 9, 1, 3, 'Java', 'C++'),
(12, 'codigo enjavaNuevo', '2015-02-19 11:03:13', 6, 1, 3, 'Java', 'C++'),
(16, 'fdfd', '2015-03-18 19:30:58', 9, 1, 3, 'Java', 'C++'),
(17, 'fefe', '2015-03-18 19:32:16', 1, 1, 5, 'Java', 'C++'),
(18, 'fefe', '2015-03-18 19:38:06', 9, 1, 5, 'Java', 'C++'),
(19, 'fefe', '2015-03-18 19:41:26', 8, 1, 3, 'Java', 'C++'),
(20, 'fefe', '2015-03-18 19:41:30', 8, 1, 3, 'Java', 'C++'),
(21, 'fefe', '2015-03-18 19:41:31', 9, 1, 3, 'Java', 'C++'),
(22, 'fefe', '2015-03-18 19:41:35', 9, 1, 3, 'Java', 'C++'),
(23, 'fefe', '2015-03-18 19:41:38', 4, 1, 3, 'Java', 'C++'),
(24, 'ffere', '2015-03-18 19:45:34', 1, 1, 3, 'Java', 'C++'),
(25, 'fefere', '2015-03-18 19:47:05', 6, 1, 5, 'Java', 'C++'),
(26, 'dere', '2015-03-18 19:47:40', 1, 1, 3, 'Java', 'C++'),
(27, 'dere', '2015-03-18 19:47:53', 4, 1, 3, 'Java', 'C++'),
(28, 'dere', '2015-03-18 19:47:54', 7, 1, 3, 'Java', 'C++'),
(29, 'dere', '2015-03-18 19:47:54', 2, 1, 3, 'Java', 'C++'),
(30, 'trtr', '2015-03-18 19:50:53', 0, 1, 3, 'Java', 'C++'),
(31, 'trtr', '2015-03-18 19:50:54', 4, 1, 3, 'Java', 'C++'),
(32, 'trtr', '2015-03-18 19:50:54', 2, 1, 3, 'Java', 'C++'),
(33, 'trtr', '2015-03-18 19:50:57', 5, 1, 3, 'Java', 'C++'),
(34, 'trtr', '2015-03-18 19:51:00', 8, 1, 3, 'Java', 'C++'),
(35, 'trtr', '2015-03-18 19:51:07', 3, 1, 5, 'Java', 'C++'),
(36, 'trtrtrt', '2015-03-18 19:51:10', 1, 1, 5, 'Java', 'C++'),
(37, 'trtrtrt', '2015-03-18 19:51:11', 5, 1, 5, 'Java', 'C++'),
(38, 'trtrtrt', '2015-03-18 19:51:11', 9, 1, 5, 'Java', 'C++'),
(39, 'fderere', '2015-03-18 19:51:26', 6, 1, 3, 'Java', 'C++'),
(40, 'fderere', '2015-03-18 19:51:27', 4, 1, 3, 'Java', 'C++'),
(41, 'fderere', '2015-03-18 19:51:28', 8, 1, 3, 'Java', 'C++'),
(42, 'gugu', '2015-03-20 00:14:07', 3, 1, 3, 'Java', 'C++'),
(43, 'defefe', '2015-03-20 14:50:02', 1, 1, 5, 'Java', 'C++'),
(44, 'defefe', '2015-03-20 14:50:05', 8, 1, 5, 'Java', 'C++'),
(45, 'defefe', '2015-03-20 14:50:06', 9, 1, 3, 'Java', 'C++'),
(46, 'fefef', '2015-03-21 20:28:10', 7, 1, 5, 'Java', 'C++'),
(47, 'fefef', '2015-03-21 20:28:20', 2, 1, 3, 'Java', 'C++'),
(48, 'fefe', '2015-03-23 12:14:39', 7, 1, 5, 'Java', 'C++'),
(49, 'fefe', '2015-03-23 12:14:39', 3, 1, 5, 'Java', 'C++'),
(50, 'fefe', '2015-03-23 12:14:39', 5, 1, 5, 'Java', 'C++'),
(51, 'fefe', '2015-03-23 12:14:42', 0, 1, 3, 'Java', 'C++'),
(52, 'fefe', '2015-03-23 12:14:42', 4, 1, 3, 'Java', 'C++'),
(53, 'mi prueba', '2015-03-23 12:24:32', 3, 1, 3, 'Java', 'C++'),
(54, 'mi prueba', '2015-03-23 12:24:36', 3, 1, 3, 'Java', 'C++'),
(55, 'mi prueba', '2015-03-23 12:24:37', 8, 1, 3, 'Java', 'C++'),
(56, 'mi codigo', '2015-03-23 12:26:29', 2, 1, 5, 'Java', 'C++'),
(57, 'mi codigo', '2015-03-23 12:26:30', 9, 1, 5, 'Java', 'C++'),
(58, 'fjiejfie', '2015-03-23 12:27:43', 6, 1, 5, 'Java', 'C++'),
(59, 'fjiejfie', '2015-03-23 12:27:44', 7, 1, 5, 'Java', 'C++'),
(60, 'fjiejfie', '2015-03-23 12:28:05', 8, 1, 3, 'Java', 'C++'),
(61, 'fefe', '2015-03-23 12:32:11', 1, 1, 3, 'Java', 'C++'),
(62, 'fefe', '2015-03-23 12:32:52', 1, 1, 3, 'Java', 'C++'),
(63, 'fefefe', '2015-03-23 12:33:41', 2, 1, 3, 'Java', 'C++'),
(64, 'fefefe', '2015-03-23 12:34:50', 0, 1, 5, 'Java', 'C++'),
(65, 'huhu', '2015-03-23 12:36:06', 8, 1, 5, 'Java', 'C++'),
(66, 'gygy', '2015-03-23 12:36:17', 9, 1, 3, 'Java', 'C++'),
(67, 'fefe', '2015-03-23 12:37:03', 7, 1, 5, 'Java', 'C++'),
(68, 'fefe', '2015-03-23 12:39:09', 1, 1, 5, 'Java', 'C++'),
(69, 'gygy', '2015-03-23 12:39:45', 8, 1, 5, 'Java', 'C++'),
(70, 'fefe', '2015-03-23 12:40:20', 7, 1, 3, 'Java', 'C++'),
(71, 'gygyg', '2015-03-23 12:41:23', 7, 1, 5, 'Java', 'C++'),
(72, 'fefe', '2015-03-23 12:41:58', 9, 1, 3, 'Java', 'C++'),
(73, 'fefefe', '2015-03-23 12:42:31', 3, 1, 3, 'Java', 'C++'),
(74, 'fefefe', '2015-03-23 12:42:37', 5, 1, 5, 'Java', 'C++'),
(75, 'fefefe', '2015-03-23 12:42:47', 9, 1, 3, 'Java', 'C++'),
(76, 'fefefe', '2015-03-23 12:43:26', 0, 1, 5, 'Java', 'C++'),
(77, 'fefe', '2015-03-23 12:43:41', 0, 1, 3, 'Java', 'C++'),
(78, 'fefe', '2015-03-23 12:43:52', 5, 1, 3, 'Java', 'C++'),
(79, 'fefefe', '2015-03-23 12:43:58', 2, 1, 3, 'Java', 'C++'),
(80, 'fdfe', '2015-03-23 12:44:02', 0, 1, 3, 'Java', 'C++'),
(81, 'fefe', '2015-03-23 12:44:22', 4, 1, 3, 'Java', 'C++'),
(82, 'fefe', '2015-03-23 12:44:48', 1, 1, 5, 'Java', 'C++'),
(83, 'rer', '2015-03-23 12:45:10', 2, 1, 3, 'Java', 'C++'),
(84, 'fefefe', '2015-03-23 12:45:44', 3, 1, 3, 'Java', 'C++'),
(85, 'fefe', '2015-03-23 12:46:10', 4, 1, 5, 'Java', 'C++'),
(86, 're', '2015-03-23 12:46:43', 5, 1, 3, 'Java', 'C++'),
(87, 'fefefe', '2015-03-23 12:47:09', 7, 1, 3, 'Java', 'C++'),
(88, 'fere', '2015-03-23 12:47:51', 1, 1, 3, 'Java', 'C++'),
(89, 'uhuh', '2015-03-23 12:51:59', 1, 1, 5, 'Java', 'C++'),
(90, 'fefe', '2015-03-23 12:53:45', 0, 1, 3, 'Java', 'C++'),
(91, 'fefe', '2015-03-23 12:53:50', 6, 1, 5, 'Java', 'C++'),
(92, 'bygy', '2015-03-23 12:54:31', 0, 1, 3, 'Java', 'C++'),
(93, 'fefe', '2015-03-23 12:54:56', 7, 1, 3, 'Java', 'C++'),
(94, 'fefe', '2015-03-23 12:56:08', 1, 1, 5, 'Java', 'C++'),
(95, 'fefe', '2015-03-23 13:10:58', 6, 1, 3, 'Java', 'C++'),
(96, 'fefe', '2015-03-23 13:11:05', 2, 1, 5, 'Java', 'C++'),
(97, 'fefefe', '2015-03-23 13:13:10', 7, 1, 3, 'Java', 'C++'),
(98, 'f', '2015-03-23 13:13:14', 7, 1, 5, 'Java', 'C++'),
(99, 'fef', '2015-03-23 13:13:45', 8, 1, 5, 'Java', 'C++'),
(100, 'fefe', '2015-03-23 13:13:49', 2, 1, 3, 'Java', 'C++'),
(101, 'fefe', '2015-03-23 13:18:28', 2, 1, 5, 'Java', 'C++'),
(102, 'cscs', '2015-03-23 13:18:45', 1, 1, 3, 'Java', 'C++'),
(103, 'ccee', '2015-03-23 13:19:02', 3, 1, 3, 'Java', 'C++'),
(104, 'fefe', '2015-03-23 13:20:31', 4, 1, 3, 'Java', 'C++'),
(105, 'fefe', '2015-03-23 13:20:43', 2, 1, 5, 'Java', 'C++'),
(106, 'fefe', '2015-03-23 13:20:48', 8, 1, 3, 'Java', 'C++'),
(107, 'fefefe', '2015-03-23 15:20:21', 9, 1, 5, 'Java', 'C++'),
(108, 'huhu', '2015-03-23 15:21:01', 5, 1, 3, 'Java', 'C++'),
(109, 'huhu', '2015-03-23 15:23:20', 7, 1, 5, 'Java', 'C++');

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

--
-- Volcado de datos para la tabla `favorito`
--

INSERT INTO `favorito` (`idUsuario`, `idEjercicio`, `lenguajeOrigen`, `lenguajeDestino`) VALUES
(1, 5, 'Java', 'C++');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `leccion`
--

CREATE TABLE IF NOT EXISTS `leccion` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `orden` int(20) unsigned NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `descripcion` text,
  `idTema` int(20) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `idTema` (`idTema`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=26 ;

--
-- Volcado de datos para la tabla `leccion`
--

INSERT INTO `leccion` (`ID`, `orden`, `titulo`, `descripcion`, `idTema`) VALUES
(1, 1, 'Leccion inicialEditado', 'desc inicial', 3),
(2, 4, 'fae', 'faefa', 1),
(3, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(4, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(5, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(6, 10, 'Leccion PruebaPst', 'desc inicial', 1),
(7, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(8, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(9, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(10, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(11, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(12, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(13, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(14, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(15, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(16, 8, 'Leccion PruebaPost2', 'desc inicial', 1),
(17, 8, 'Leccion PruebaPost2', 'desc inicial', 1),
(19, 8, 'Leccion PruebaPost2', 'desc inicial', 1),
(20, 9, 'Leccion PruebaPut', 'desc inicial', 1),
(21, 8, 'Leccion PruebaPost', 'desc inicial', 1),
(22, 9, 'Leccion PruebaPut', 'desc inicial', 1),
(23, 9, 'Leccion PruebaPost', 'desc inicial', 1),
(24, 9, 'Leccion PruebaPut', 'desc inicial', 1),
(25, 10, 'Leccion PruebaPst', 'desc inicial', 1);

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
(24, 4),
(6, 5),
(14, 5),
(15, 5),
(16, 5),
(17, 5),
(19, 5),
(20, 5),
(23, 5),
(24, 5),
(25, 6);

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
(3, 9, 'titulo del tema (ej. bucles)Editado', 'descripciÃƒÂ³n del tema (ej. los bucles hacen bla bla)'),
(4, 1, 'temaNuevo', 'tema de if'),
(5, 0, 'IF', 'tema de if'),
(6, 73, 'IF', 'tema de if');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `nick` varchar(20) NOT NULL,
  `correo` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `rol` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `nick` (`nick`,`correo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`ID`, `nick`, `correo`, `pass`, `rol`) VALUES
(1, 'faef', 'afea', 'afda', 0),
(2, 'feaf', 'afea', 'afes', 0);

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

--
-- Volcado de datos para la tabla `usuariocompletaleccion`
--

INSERT INTO `usuariocompletaleccion` (`idUsuario`, `idLeccion`, `lenguaje`) VALUES
(1, 1, 'C++'),
(1, 2, 'C++'),
(1, 6, 'C++');

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
