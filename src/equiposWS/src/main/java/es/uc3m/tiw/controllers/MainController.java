package es.uc3m.tiw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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


    /* JUGADORES */

    @GetMapping("/jugadores")
    public ResponseEntity<List<Jugador>> getAllJugadores() {

        List<Jugador> jugadores = daoJug.findAll();

        if (jugadores.isEmpty())
            return new ResponseEntity<>(jugadores, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(jugadores, HttpStatus.OK);
    }

    @GetMapping("/jugadores/{equipo_nombre}")
    public ResponseEntity<?> getJugadoresEquipo(@PathVariable String equipo_nombre) {

        // search equipo
        Equipo equipo = daoEq.findByNombre(equipo_nombre);

        if (equipo == null)
            return new ResponseEntity<>("Equipo '" + equipo_nombre + "' not found", HttpStatus.BAD_REQUEST);

        List<Jugador> jugadores = daoJug.findByEquipoNombre(equipo_nombre);

        if (jugadores.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(jugadores, HttpStatus.OK);
    }


    @PostMapping("/jugador")
    public ResponseEntity<?> saveJugador(@RequestBody @Validated Jugador nu_jugador) {
        // search equipo
        String equipo_nombre = nu_jugador.getEquipoNombre();
        Equipo equipo = daoEq.findByNombre(equipo_nombre);

        if (equipo == null)
            return new ResponseEntity<>("Equipo '" + equipo_nombre + "' not found", HttpStatus.BAD_REQUEST);

        // search posicion
        String posicion_nombre = nu_jugador.getPosicionNombre();
        Posicion posicion = daoPos.findByNombre(posicion_nombre);

        if (posicion == null)
            return new ResponseEntity<>("Posicion '" + posicion_nombre + "' not found", HttpStatus.BAD_REQUEST);

        // check if jugador exists
        if (daoJug.findByDni(nu_jugador.getDni()) != null)
            return new ResponseEntity<>("Jugador '" + nu_jugador.getDni() + "' already exists", HttpStatus.NOT_FOUND);

        // update plantilla
        Plantilla plantilla = daoPlan.findByPlantillaId(new PlantillaKey(equipo_nombre, posicion_nombre));

        if (plantilla.getNumJugadores() >= posicion.getMaxJugadores())
            return new ResponseEntity<>("Posicion '" + posicion_nombre + "' for equipo '" + equipo_nombre + "' is already full", HttpStatus.BAD_REQUEST);

        plantilla.setNumJugadores(plantilla.getNumJugadores() + 1);

        try {
            daoJug.save(nu_jugador);
        }
        catch (DataAccessException ex) {
            return new ResponseEntity<>(ex.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(nu_jugador, HttpStatus.CREATED);

    }


    /* EQUIPOS */

    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> getAllEquipos() {
        List<Equipo> equipos = daoEq.findAll();

        if (equipos.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }



    /* POSICIONES */

    @GetMapping("/posiciones")
    public ResponseEntity<List<Posicion>> getAllPosiciones() {
        List<Posicion> posiciones = daoPos.findAll();

        if (posiciones.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(posiciones, HttpStatus.OK);
    }


    /* PLANTILLAS */

    @GetMapping("/plantilla/{equipo_nombre}")
    public ResponseEntity<?> getPlantillaEquipo(@PathVariable String equipo_nombre) {

        // TODO: include jugadores in plantilla

        // search equipo
        Equipo equipo = daoEq.findByNombre(equipo_nombre);

        if (equipo == null)
            return new ResponseEntity<>("Equipo '" + equipo_nombre + "' not found", HttpStatus.BAD_REQUEST);


        // get plantilla
        List<Plantilla> plantilla = daoPlan.findByPlantillaIdEquipoNombre(equipo_nombre);

        if (plantilla.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(plantilla, HttpStatus.OK);
    }

}
