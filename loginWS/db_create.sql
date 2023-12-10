CREATE DATABASE IF NOT EXISTS `tiwp3` DEFAULT CHARACTER SET utf8;
USE `tiwp3`;

DROP TABLE IF EXISTS `administradores`;
DROP TABLE IF EXISTS `usuarios`;
DROP TABLE IF EXISTS `adminpasswords`;
DROP TABLE IF EXISTS `userpasswords`;



CREATE TABLE IF NOT EXISTS `administradores` (
  `correo` VARCHAR(30) NOT NULL,
  `nombre` VARCHAR(30) NOT NULL,
  `apellidos` VARCHAR(30) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  
  PRIMARY KEY (`correo`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `usuarios` (
  `correo` VARCHAR(30) NOT NULL,
  `nombre` VARCHAR(30) NOT NULL,
  `apellidos` VARCHAR(30) NOT NULL,
  `equipo` VARCHAR(30),
  `password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`correo`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `adminpasswords` (
  `correo` VARCHAR(30) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  
  PRIMARY KEY (`correo`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `userpasswords` (
  `correo` VARCHAR(30) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  
  PRIMARY KEY (`correo`)
)
ENGINE = InnoDB;





START TRANSACTION;
  USE `tiwp3`;

  INSERT INTO `administradores` (`nombre`, `apellidos`, `correo`, `password`)
    VALUES ('Javier', 'Moreno Yebenes', 'javi@uc3m.es', '123');
  INSERT INTO `administradores` (`nombre`, `apellidos`, `correo`, `password`)
    VALUES ('Luis', 'Casais Mezquida', 'luis@uc3m.es', '123');
  INSERT INTO `administradores` (`nombre`, `apellidos`, `correo`, `password`)
    VALUES ('Alberto', 'Urbano Ballesteros', 'alberto@uc3m.es', '123');
  INSERT INTO `administradores` (`nombre`, `apellidos`, `correo`, `password`)
    VALUES ('Pablo', 'Montero Poyatos', 'pablo@uc3m.es', '123');

INSERT INTO `adminpasswords` ( `correo`, `password`)
    VALUES ('javi@uc3m.es', '123');
INSERT INTO `adminpasswords` ( `correo`, `password`)
    VALUES ('luis@uc3m.es', '123');
INSERT INTO `adminpasswords` ( `correo`, `password`)
    VALUES ('alberto@uc3m.es', '123');
INSERT INTO `adminpasswords` ( `correo`, `password`)
    VALUES ('pablo@uc3m.es', '123');
  

COMMIT;