package es.uc3m.tiw.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.uc3m.tiw.entities.Equipo;



public interface EquipoDAO extends CrudRepository<Equipo, String> {

    public List<Equipo> findAll();

    public Equipo findByNombre(String nombre);
}
