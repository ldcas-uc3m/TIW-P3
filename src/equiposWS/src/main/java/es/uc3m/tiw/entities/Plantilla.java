package es.uc3m.tiw.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;



@Entity
@Table(name = "plantillas")
public class Plantilla implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PlantillaKey id;

    @ManyToOne
    @MapsId("equipoNombre")
    @JoinColumn(name = "equipo_nombre")
    private Equipo equipo;

    @ManyToOne
    @MapsId("posicionNombre")
    @JoinColumn(name = "posicion_nombre")
    private Posicion posicion;

    @Column(name = "num_jugadores")
    private int numJugadores = 0;


    public Plantilla() {}


    public PlantillaKey getId() { return this.id; }
    public void setId(PlantillaKey id) { this.id = id; }

    public Equipo getEquipo() { return this.equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }

    public Posicion getPosicion() { return this.posicion; }
    public void setPosicion(Posicion posicion) { this.posicion = posicion; }

    public int getNumJugadores() { return this.numJugadores; }
    public void setNumJugadores(int numJugadores) { this.numJugadores = numJugadores; }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Plantilla other = (Plantilla) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
