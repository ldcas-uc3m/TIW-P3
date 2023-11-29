package es.uc3m.tiw.entities;


import java.io.Serializable;
import java.util.Objects;

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
        return Objects.hash(equipoNombre, posicionNombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PlantillaKey other = (PlantillaKey) obj;
        return posicionNombre.equals(other.posicionNombre) &&
                equipoNombre.equals(other.equipoNombre);
    }
}
