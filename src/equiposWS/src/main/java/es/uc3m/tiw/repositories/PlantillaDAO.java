package es.uc3m.tiw.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.uc3m.tiw.entities.Plantilla;
import es.uc3m.tiw.entities.PlantillaKey;



public interface PlantillaDAO extends CrudRepository<Plantilla, PlantillaKey> {

    public List<Plantilla> findAll();

    public Plantilla findByPlantillaId(PlantillaKey plantillaId);
    public List<Plantilla> findByPlantillaIdEquipoNombre(String equipo);
}

