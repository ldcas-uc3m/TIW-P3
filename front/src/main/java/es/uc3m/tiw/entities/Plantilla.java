package es.uc3m.tiw.entities;


import java.io.Serializable;



public class Plantilla implements Serializable {
    private static final long serialVersionUID = 1L;

    private PlantillaKey plantillaId;
    private int numJugadores = 0;


    public Plantilla() {}

    public Plantilla(String equipo, String posicion) {
        this.plantillaId = new PlantillaKey(equipo, posicion);
    }


    public PlantillaKey getPlantillaID() { return this.plantillaId; }
    public void setPlantillaID(PlantillaKey plantillaId) { this.plantillaId = plantillaId; }

    public int getNumJugadores() { return this.numJugadores; }
    public void setNumJugadores(int numJugadores) { this.numJugadores = numJugadores; }

}
