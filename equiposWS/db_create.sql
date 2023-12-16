CREATE DATABASE IF NOT EXISTS `tiwp3` DEFAULT CHARACTER SET utf8;
USE `tiwp3`;


DROP TABLE IF EXISTS `imagenes`;
DROP TABLE IF EXISTS `jugadores`;
DROP TABLE IF EXISTS `plantillas`;
DROP TABLE IF EXISTS `equipos`;
DROP TABLE IF EXISTS `posiciones`;



CREATE TABLE IF NOT EXISTS `posiciones` (
  `nombre` VARCHAR(15) NOT NULL,
  `max_jugadores` INT NOT NULL,
  PRIMARY KEY (`nombre`)
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `equipos` (
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`)
)
ENGINE = InnoDB;


-- Cada equipo tiene diferente número de jugadores por posición
CREATE TABLE IF NOT EXISTS `plantillas` (
  `equipo_nombre` VARCHAR(45) NOT NULL,
  `posicion_nombre` VARCHAR(15) NOT NULL,
  `num_jugadores` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`equipo_nombre`, `posicion_nombre`),
  CONSTRAINT `fk_plantillas_posicion`
    FOREIGN KEY (`posicion_nombre`)
    REFERENCES `posiciones` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plantillas_equipo`
    FOREIGN KEY (`equipo_nombre`)
    REFERENCES `equipos` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `jugadores` (
  `dni` VARCHAR(9) NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `alias` VARCHAR(20) NULL,
  `posicion_nombre` VARCHAR(15) NOT NULL,
  `equipo_nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`dni`),
  CONSTRAINT `fk_jugadores_posicion`
    FOREIGN KEY (`posicion_nombre`)
    REFERENCES `posiciones` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_jugadores_equipo`
    FOREIGN KEY (`equipo_nombre`)
    REFERENCES `equipos` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `imagenes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `jugador_dni` VARCHAR(9) NOT NULL UNIQUE,
  `name` VARCHAR(40) NULL,
  `type` VARCHAR(15) NULL,
  `imagedata` LONGBLOB NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_imagenes_jugador`
    FOREIGN KEY (`jugador_dni`)
    REFERENCES `jugadores` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;



START TRANSACTION;
  USE `tiwp3`;

  INSERT INTO `posiciones` (`nombre`, `max_jugadores`)
    VALUES ('Portero', 3);
  INSERT INTO `posiciones` (`nombre`, `max_jugadores`)
    VALUES ('Defensa', 8);
  INSERT INTO `posiciones` (`nombre`, `max_jugadores`)
    VALUES ('Medio', 8);
  INSERT INTO `posiciones` (`nombre`, `max_jugadores`)
    VALUES ('Delantero', 6);

  INSERT INTO `equipos` (`nombre`)
    VALUES ('Atletico de Madrid');

  INSERT INTO `plantillas` (`equipo_nombre`, `posicion_nombre`)
    VALUES
      ('Atletico de Madrid', 'Portero'),
      ('Atletico de Madrid', 'Defensa'),
      ('Atletico de Madrid', 'Medio'),
      ('Atletico de Madrid', 'Delantero');

COMMIT;