package es.uc3m.tiw.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /* REPOSITORIOS */

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

    @GetMapping("/jugador/{dni}")
    public ResponseEntity<Jugador> getJugador(@PathVariable String dni) {

        Jugador jugador = daoJug.findByDni(dni);

        if (jugador == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(jugador, HttpStatus.OK);
    }


    @PostMapping("/jugador")
    public ResponseEntity<?> addJugador(@RequestBody @Validated Jugador nu_jugador) {
        // search equipo
        String equipo_nombre = nu_jugador.getEquipoNombre();
        if (daoEq.findByNombre(equipo_nombre) == null)
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


    @DeleteMapping("/jugador/{dni}")
    public ResponseEntity<?> deleteJugador(@PathVariable String dni) {

        Jugador jugador = daoJug.findByDni(dni);

        if (jugador == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            daoJug.deleteById(dni);
        }
        catch (DataAccessException ex) {
            return new ResponseEntity<>(ex.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(jugador, HttpStatus.OK);
    }



    /* EQUIPOS */

    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> getAllEquipos() {
        List<Equipo> equipos = daoEq.findAll();

        if (equipos.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }


    @PostMapping("/equipo")
    public ResponseEntity<?> addEquipo(@RequestBody @Validated Equipo nu_equipo) {

        // search equipo
        String equipo_nombre = nu_equipo.getNombre();
        if (daoEq.findByNombre(equipo_nombre) != null)
            return new ResponseEntity<>("Equipo '" + equipo_nombre + "' already exists", HttpStatus.BAD_REQUEST);

        try {
            // add equipo
            daoEq.save(nu_equipo);

            // generate new posiciones in plantilla
            List<Posicion> posiciones = daoPos.findAll();

            for (Posicion posicion : posiciones) {
                daoPlan.save(new Plantilla(equipo_nombre, posicion.getNombre()));
            }
        }
        catch (DataAccessException ex) {
            return new ResponseEntity<>(ex.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(nu_equipo, HttpStatus.CREATED);
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



    // TODO: gestión de errores con códigos
    private Map<String, String> generarError(String code, String message) {
		Map<String, String> error = new HashMap<>();
		error.put("code", code);
		error.put("message", message);
		return error;
	}
}
