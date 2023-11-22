package es.uc3m.tiw.entities;


import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Entity
@Table(name = "plantillas")
public class Plantilla implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    PlantillaKey id;

    @ManyToOne
    @MapsId("equipo")
    Equipo equipo;

    @ManyToOne
    @MapsId("posicion")
    Posicion posicion;

    int num_jugadores = 0;


    public PlantillaKey getId() { return this.id; }
    public void setId(PlantillaKey id) { this.id = id; }
}
