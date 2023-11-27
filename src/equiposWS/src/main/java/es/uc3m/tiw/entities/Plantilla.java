package es.uc3m.tiw.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name = "plantillas")
public class Plantilla implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PlantillaKey plantillaId;

    @Column(name = "num_jugadores")
    private int numJugadores = 0;


    public Plantilla() {}


    public PlantillaKey getPlantillaID() { return this.plantillaId; }
    public void setPlantillaID(PlantillaKey plantillaId) { this.plantillaId = plantillaId; }

    public int getNumJugadores() { return this.numJugadores; }
    public void setNumJugadores(int numJugadores) { this.numJugadores = numJugadores; }

}
