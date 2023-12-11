package es.uc3m.tiw.entities;


import java.io.Serializable;



public class PlantillaKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private String equipoNombre;
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

}
