package es.uc3m.tiw.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.uc3m.tiw.entities.Posicion;



public interface PosicionDAO extends CrudRepository<Posicion, String> {

    public List<Posicion> findAll();

    public Posicion findByNombre(String nombre);
}

