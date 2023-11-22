package es.uc3m.tiw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import es.uc3m.tiw.entities.Jugador;
import es.uc3m.tiw.repositories.JugadorDAO;


@RestController
@CrossOrigin
public class MainController {
    @Autowired
    JugadorDAO daoJug;

    /* GET */

    @GetMapping("/jugadores")
    public List<Jugador> getAllJugadores() {
        return daoJug.findAll();
    }


}
