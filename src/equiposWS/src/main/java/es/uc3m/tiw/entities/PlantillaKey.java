package es.uc3m.tiw.entities;


import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PlantillaKey implements Serializable {
    private static final long serialVersionUID = 1L;

    Equipo equipo;
    Posicion posicion;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((equipo == null) ? 0 : equipo.hashCode());
        result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        PlantillaKey other = (PlantillaKey) obj;

        if (equipo == null) {
            if (other.equipo != null)
                return false;
        } else if (!equipo.equals(other.equipo))
            return false;

        if (posicion == null) {
            if (other.posicion != null)
                return false;
        } else if (!posicion.equals(other.posicion))
            return false;

            return true;
    }
}
