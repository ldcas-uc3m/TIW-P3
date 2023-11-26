package es.uc3m.tiw.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.uc3m.tiw.entities.Equipo;
import es.uc3m.tiw.entities.Plantilla;
import es.uc3m.tiw.entities.PlantillaKey;



public interface PlantillaDAO extends CrudRepository<Plantilla, String> {

    public List<Plantilla> findAll();

    public Plantilla findById(PlantillaKey id);
    public List<Plantilla> findByEquipo(Equipo equipo);
}

