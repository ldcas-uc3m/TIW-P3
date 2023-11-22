CREATE DATABASE IF NOT EXISTS `tiwp3` DEFAULT CHARACTER SET utf8;
USE `tiwp3`;


DROP TABLE IF EXISTS `posiciones`;
CREATE TABLE IF NOT EXISTS `posiciones` (
  `nombre` VARCHAR(15) NOT NULL,
  `max_jugadores` INT NOT NULL,
  PRIMARY KEY (`nombre`)
)
ENGINE = InnoDB;


DROP TABLE IF EXISTS `equipos`;
CREATE TABLE IF NOT EXISTS `equipos` (
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`)
)
ENGINE = InnoDB;


-- Cada equipo tiene diferente número de jugadores por posición
DROP TABLE IF EXISTS `plantillas`;
CREATE TABLE IF NOT EXISTS `plantillas` (
  `equipo` VARCHAR(45) NOT NULL,
  `posicion` VARCHAR(15) NOT NULL,
  `num_jugadores` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`equipo`, `posicion`),
  CONSTRAINT `fk_plantillas_posicion`
    FOREIGN KEY (`posicion`)
    REFERENCES `posiciones` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  CONSTRAINT `fk_plantillas_equipo`
    FOREIGN KEY (`equipo`)
    REFERENCES `equipos` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


DROP TABLE IF EXISTS `jugadores` ;
CREATE TABLE IF NOT EXISTS `jugadores` (
  `dni` VARCHAR(9) NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `alias` VARCHAR(20) NULL,
  `imagen` LONGBLOB NULL,
  `posicion` VARCHAR(15) NOT NULL,
  `equipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`dni`),
  CONSTRAINT `fk_jugadores_posicion`
    FOREIGN KEY (`posicion`)
    REFERENCES `posiciones` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  CONSTRAINT `fk_jugadores_equipo`
    FOREIGN KEY (`equipo`)
    REFERENCES `equipos` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;