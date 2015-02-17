SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

CREATE DATABASE IF NOT EXISTS `duocode` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `duocode`;

DROP TABLE IF EXISTS `candidato`;
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

DROP TABLE IF EXISTS `desbloqueadapor`;
CREATE TABLE IF NOT EXISTS `desbloqueadapor` (
  `idLeccion` int(20) unsigned NOT NULL,
  `desbloqueadaPor` int(20) unsigned NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ejercicio`;
CREATE TABLE IF NOT EXISTS `ejercicio` (
`ID` int(20) unsigned NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

DROP TABLE IF EXISTS `enunciado`;
CREATE TABLE IF NOT EXISTS `enunciado` (
`ID` int(20) unsigned NOT NULL,
  `texto` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idEjercicio` int(20) unsigned NOT NULL,
  `lenguaje` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

DROP TABLE IF EXISTS `envio`;
CREATE TABLE IF NOT EXISTS `envio` (
`ID` int(20) NOT NULL,
  `codigo` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `puntuacion` int(20) unsigned NOT NULL DEFAULT '0',
  `idUsuario` int(20) unsigned NOT NULL,
  `idEjercicio` int(20) unsigned NOT NULL,
  `lenguajeOrigen` varchar(20) NOT NULL,
  `lenguajeDestino` varchar(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

DROP TABLE IF EXISTS `favorito`;
CREATE TABLE IF NOT EXISTS `favorito` (
  `idUsuario` int(20) unsigned NOT NULL,
  `idEjercicio` int(20) unsigned NOT NULL,
  `lenguajeOrigen` varchar(20) NOT NULL,
  `lenguajeDestino` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `leccion`;
CREATE TABLE IF NOT EXISTS `leccion` (
`ID` int(20) unsigned NOT NULL,
  `orden` int(20) unsigned NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `descripcion` text,
  `idTema` int(20) unsigned NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

DROP TABLE IF EXISTS `leccionconstaejercicio`;
CREATE TABLE IF NOT EXISTS `leccionconstaejercicio` (
  `idLeccion` int(20) unsigned NOT NULL,
  `idEjercicio` int(20) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lenguaje`;
CREATE TABLE IF NOT EXISTS `lenguaje` (
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tema`;
CREATE TABLE IF NOT EXISTS `tema` (
`ID` int(20) unsigned NOT NULL,
  `orden` int(20) unsigned NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `descripcion` text
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
`ID` int(20) unsigned NOT NULL,
  `nick` varchar(20) NOT NULL,
  `correo` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `rol` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

DROP TABLE IF EXISTS `usuariocompletaleccion`;
CREATE TABLE IF NOT EXISTS `usuariocompletaleccion` (
  `idUsuario` int(20) unsigned NOT NULL DEFAULT '0',
  `idLeccion` int(20) unsigned NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `usuariovotacandidato`;
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
 ADD PRIMARY KEY (`ID`), ADD UNIQUE KEY `nick` (`nick`,`correo`);

ALTER TABLE `usuariocompletaleccion`
 ADD PRIMARY KEY (`idUsuario`,`idLeccion`), ADD KEY `idLeccion` (`idLeccion`);

ALTER TABLE `usuariovotacandidato`
 ADD PRIMARY KEY (`idUsuario`,`idCandidato`), ADD KEY `idCandidato` (`idCandidato`);


ALTER TABLE `candidato`
MODIFY `ID` int(20) unsigned NOT NULL AUTO_INCREMENT;
ALTER TABLE `ejercicio`
MODIFY `ID` int(20) unsigned NOT NULL AUTO_INCREMENT;
ALTER TABLE `enunciado`
MODIFY `ID` int(20) unsigned NOT NULL AUTO_INCREMENT;
ALTER TABLE `envio`
MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `leccion`
MODIFY `ID` int(20) unsigned NOT NULL AUTO_INCREMENT;
ALTER TABLE `tema`
MODIFY `ID` int(20) unsigned NOT NULL AUTO_INCREMENT;
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
