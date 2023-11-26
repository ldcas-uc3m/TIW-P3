package es.uc3m.tiw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import es.uc3m.tiw.entities.Equipo;
import es.uc3m.tiw.entities.Jugador;
import es.uc3m.tiw.entities.Plantilla;
import es.uc3m.tiw.entities.PlantillaKey;
import es.uc3m.tiw.entities.Posicion;
import es.uc3m.tiw.repositories.EquipoDAO;
import es.uc3m.tiw.repositories.JugadorDAO;
import es.uc3m.tiw.repositories.PlantillaDAO;
import es.uc3m.tiw.repositories.PosicionDAO;


@RestController
@CrossOrigin
public class MainController {
    @Autowired
    JugadorDAO daoJug;

    @Autowired
    EquipoDAO daoEq;

    @Autowired
    PlantillaDAO daoPlan;

    @Autowired
    PosicionDAO daoPos;


    /* GET */

    @GetMapping("/jugadores")
    public ResponseEntity<List<Jugador>> getAllJugadores() {

        List<Jugador> jugadores = daoJug.findAll();

        if (jugadores.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(jugadores, HttpStatus.OK);
    }

    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> getAllEquipos() {
        List<Equipo> equipos = daoEq.findAll();

        if (equipos.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @GetMapping("/posiciones")
    public ResponseEntity<List<Posicion>> getAllPosiciones() {
        List<Posicion> posiciones = daoPos.findAll();

        if (posiciones.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(posiciones, HttpStatus.OK);
    }

    @GetMapping("/plantilla/{equipo_nombre}")
    public ResponseEntity<?> getPlantillaEquipo(@PathVariable @Validated String equipo_nombre) {

        // TODO: include jugadores in plantilla

        // search equipo
        Equipo equipo = daoEq.findByNombre(equipo_nombre);

        if (equipo == null)
            return new ResponseEntity<>("Equipo '" + equipo_nombre + "' not found", HttpStatus.NOT_FOUND);


        // get plantilla
        List<Plantilla> plantilla = daoPlan.findByEquipo(equipo);

        if (plantilla.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(plantilla, HttpStatus.OK);
    }

}
