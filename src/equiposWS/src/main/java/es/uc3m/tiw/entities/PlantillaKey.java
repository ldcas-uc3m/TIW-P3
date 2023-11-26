package es.uc3m.tiw.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class PlantillaKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "equipo_nombre")
    private String equipoNombre;

    @Column(name = "posicion_nombre")
    private String posicionNombre;


    public PlantillaKey() {}
    public PlantillaKey(String equipo, String posicion) {
        this.equipoNombre = equipo;
        this.posicionNombre = posicion;
    }


    public String getEquipoNombre() { return this.equipoNombre; }
    public void setEquipo(String equipoNombre) { this.equipoNombre = equipoNombre; }

    public String getPosicion() { return this.posicionNombre; }
    public void setPosicionNombre(String posicionNombre) { this.posicionNombre = posicionNombre; }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((equipoNombre == null) ? 0 : equipoNombre.hashCode());
        result = prime * result + ((posicionNombre == null) ? 0 : posicionNombre.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        PlantillaKey other = (PlantillaKey) obj;

        if (equipoNombre == null) {
            if (other.equipoNombre != null)
                return false;
        } else if (!equipoNombre.equals(other.equipoNombre))
            return false;

        if (posicionNombre == null) {
            if (other.posicionNombre != null)
                return false;
        } else if (!posicionNombre.equals(other.posicionNombre))
            return false;

            return true;
    }
}
