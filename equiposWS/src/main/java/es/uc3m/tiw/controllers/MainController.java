package es.uc3m.tiw.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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


    // TODO: gestión de errores con códigos
    private Map<String, String> generateError(String code, String message) {
		Map<String, String> error = new HashMap<>();
		error.put("code", code);
		error.put("message", message);

		return error;
	}

    /*
     * Removes a Jugador from his Equipo's Plantilla.
     * Must validate if the Plantilla and Equipo exists beforehand.
     */
    private void removeJugadorFromPlantilla(Jugador jugador) throws IllegalStateException {
        Plantilla plantilla = daoPlan.findByPlantillaId(new PlantillaKey(jugador.getEquipoNombre(), jugador.getPosicionNombre()));

        int num_jugadores = plantilla.getNumJugadores();
        if (num_jugadores <= 0) {
            throw new IllegalStateException();
        }

        plantilla.setNumJugadores(num_jugadores - 1);
    }

    /*
     * Adds a Jugador from his Equipo's Plantilla.
     * Must validate if the Plantilla and Equipo exists beforehand.
     */
    private void addJugadorToPlantilla(Jugador jugador) throws IndexOutOfBoundsException {

        String posicion_nombre = jugador.getPosicionNombre();
        String equipo_nombre = jugador.getEquipoNombre();

        Posicion posicion = daoPos.findByNombre(posicion_nombre);
        Plantilla plantilla = daoPlan.findByPlantillaId(new PlantillaKey(equipo_nombre, posicion_nombre));

        int num_jugadores = plantilla.getNumJugadores();
        if (plantilla.getNumJugadores() >= posicion.getMaxJugadores())
            throw new IndexOutOfBoundsException();

        plantilla.setNumJugadores(num_jugadores + 1);
    }



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
        if (!daoEq.existsById(equipo_nombre))
            return new ResponseEntity<>("Equipo '" + equipo_nombre + "' not found", HttpStatus.BAD_REQUEST);

        // get jugadores
        List<Jugador> jugadores = daoJug.findByEquipoNombre(equipo_nombre);
        if (jugadores.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(jugadores, HttpStatus.OK);
    }

    @GetMapping("/jugador/{dni}")
    public ResponseEntity<Jugador> getJugador(@PathVariable String dni) {

        Optional<Jugador> jugador = daoJug.findById(dni);

        if (jugador.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(jugador.get(), HttpStatus.OK);
    }


    @PostMapping("/jugador")
    public ResponseEntity<?> addJugador(@RequestBody @Validated Jugador nu_jugador) {

        // validate equipo
        String equipo_nombre = nu_jugador.getEquipoNombre();
        if (!daoEq.existsById(equipo_nombre))
            return new ResponseEntity<>("Equipo '" + equipo_nombre + "' not found", HttpStatus.BAD_REQUEST);

        // validate posicion
        String posicion_nombre = nu_jugador.getPosicionNombre();
        if (!daoPos.existsById(posicion_nombre))
            return new ResponseEntity<>("Posicion '" + posicion_nombre + "' not found", HttpStatus.BAD_REQUEST);

        // check if jugador exists
        if (daoJug.existsById(nu_jugador.getDni()))
            return new ResponseEntity<>("Jugador '" + nu_jugador.getDni() + "' already exists", HttpStatus.BAD_REQUEST);

        // update plantilla
        try {
            addJugadorToPlantilla(nu_jugador);
        }
        catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>("Posicion '" + posicion_nombre + "' for equipo '" + equipo_nombre + "' is already full", HttpStatus.BAD_REQUEST);
        }

        // save jugador
        try {
            Jugador saved_jugador = daoJug.save(nu_jugador);

            return new ResponseEntity<>(saved_jugador, HttpStatus.CREATED);
        }
        catch (DataAccessException ex) {
            return new ResponseEntity<>(ex.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/jugador/{dni}")
    public ResponseEntity<?> deleteJugador(@PathVariable String dni) {

        Optional<Jugador> jugador = daoJug.findById(dni);

        if (jugador.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // delete jugador
        try {
            daoJug.deleteById(dni);
        }
        catch (DataAccessException ex) {
            return new ResponseEntity<>(ex.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST);
        }

        // update plantilla
        try {
            removeJugadorFromPlantilla(jugador.get());
        }
        catch (IllegalStateException e) {
            return new ResponseEntity<>("Se ha llegado al límite inferior de jugadores", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(jugador.get(), HttpStatus.OK);
    }


    @PutMapping("/jugador")
    public ResponseEntity<?> updateJugador(@RequestBody Jugador nuJugador) {

        // check if jugador exists
		Optional<Jugador> jugador = daoJug.findById(nuJugador.getDni());
		if (jugador.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        Jugador oldJugador = jugador.get();

        // validate equipo
        String equipo_nombre = nuJugador.getEquipoNombre();
        if (!daoEq.existsById(equipo_nombre))
            return new ResponseEntity<>("Equipo '" + equipo_nombre + "' not found", HttpStatus.BAD_REQUEST);

        // validate posicion
        String posicion_nombre = nuJugador.getPosicionNombre();
        if (!daoPos.existsById(posicion_nombre))
            return new ResponseEntity<>("Posicion '" + posicion_nombre + "' not found", HttpStatus.BAD_REQUEST);

        // update posicion, if changed
        if (
            (oldJugador.getPosicionNombre() != nuJugador.getPosicionNombre()) ||
            (oldJugador.getEquipoNombre() != nuJugador.getEquipoNombre())
        ) {
            try {
                removeJugadorFromPlantilla(oldJugador);
            }
            catch (IllegalStateException e) {
                return new ResponseEntity<>("Se ha llegado al límite inferior de jugadores", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            try {
                addJugadorToPlantilla(nuJugador);
            }
            catch (IndexOutOfBoundsException e) {
                return new ResponseEntity<>("Posicion '" + posicion_nombre + "' for equipo '" + equipo_nombre + "' is already full", HttpStatus.BAD_REQUEST);
            }
        }

		// save it
		try {
			Jugador savedJugador = daoJug.save(nuJugador);

			return new ResponseEntity<>(savedJugador, HttpStatus.OK);

		} catch (DataAccessException ex) {
            return new ResponseEntity<>(ex.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


    /* EQUIPOS */

    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> getAllEquipos() {
        List<Equipo> equipos = daoEq.findAll();

        if (equipos.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }


    // TODO: getEquipoImage


    @PostMapping("/equipo")
    public ResponseEntity<?> addEquipo(@RequestBody @Validated Equipo nu_equipo) {

        // search equipo
        String equipo_nombre = nu_equipo.getNombre();
        if (daoEq.existsById(equipo_nombre))
            return new ResponseEntity<>("Equipo '" + equipo_nombre + "' already exists", HttpStatus.BAD_REQUEST);

        try {
            // add equipo
            Equipo saved_equipo = daoEq.save(nu_equipo);

            // generate new posiciones in plantilla
            List<Posicion> posiciones = daoPos.findAll();

            for (Posicion posicion : posiciones) {
                daoPlan.save(new Plantilla(equipo_nombre, posicion.getNombre()));
            }

            return new ResponseEntity<>(saved_equipo, HttpStatus.CREATED);
        }
        catch (DataAccessException ex) {
            return new ResponseEntity<>(ex.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST);
        }

    }



    /* POSICIONES */

    @GetMapping("/posiciones")
    public ResponseEntity<List<Posicion>> getAllPosiciones() {
        List<Posicion> posiciones = daoPos.findAll();

        if (posiciones.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(posiciones, HttpStatus.OK);
    }


    // TODO: getEquipoPosiciones


    /* PLANTILLAS */

    @GetMapping("/plantilla/{equipo_nombre}")
    public ResponseEntity<?> getPlantillaEquipo(@PathVariable String equipo_nombre) {

        // search equipo
        if (!daoEq.existsById(equipo_nombre))
            return new ResponseEntity<>("Equipo '" + equipo_nombre + "' not found", HttpStatus.BAD_REQUEST);


        // get plantilla
        List<Plantilla> plantilla = daoPlan.findByPlantillaIdEquipoNombre(equipo_nombre);

        if (plantilla.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(plantilla, HttpStatus.OK);
    }


    @GetMapping("/coffee")
    public ResponseEntity<HttpStatus> coffe() { return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT); }

}