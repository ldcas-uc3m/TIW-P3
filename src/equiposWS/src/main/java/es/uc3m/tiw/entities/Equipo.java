package es.uc3m.tiw.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;



@Entity
@Table(name = "equipos")
public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    String nombre;

    @Lob
    private byte[] escudo;


    public Equipo() {}

    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public byte[] getEscudo() { return this.escudo; }
    public void setEscudo(byte[] escudo) { this.escudo = escudo; }
}


// TODO: Fix tildes 'n stuff (Atlético, etc)