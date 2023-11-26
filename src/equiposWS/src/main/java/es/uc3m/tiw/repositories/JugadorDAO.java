package es.uc3m.tiw.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.uc3m.tiw.entities.Jugador;



public interface JugadorDAO extends CrudRepository<Jugador, String> {

    public List<Jugador> findAll();

    public Jugador findByDni(String dni);
}
