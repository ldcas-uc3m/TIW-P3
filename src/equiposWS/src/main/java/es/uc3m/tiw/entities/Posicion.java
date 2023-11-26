package es.uc3m.tiw.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "posiciones")
public class Posicion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    String nombre;

    @Column(name = "max_jugadores")
    int maxJugadores;


    public Posicion() {}


    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getMaxJugadores() { return this.maxJugadores; }
    public void setMaxJugadores(int maxJugadores) { this.maxJugadores = maxJugadores; }
}
