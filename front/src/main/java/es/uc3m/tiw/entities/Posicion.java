package es.uc3m.tiw.entities;


import java.io.Serializable;



public class Posicion implements Serializable {
    private static final long serialVersionUID = 1L;

    String nombre;
    int maxJugadores;


    public Posicion() {}


    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getMaxJugadores() { return this.maxJugadores; }
    public void setMaxJugadores(int maxJugadores) { this.maxJugadores = maxJugadores; }
}
