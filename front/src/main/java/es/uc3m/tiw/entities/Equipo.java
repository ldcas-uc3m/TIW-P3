package es.uc3m.tiw.entities;


import java.io.Serializable;




public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;

    String nombre;
    private byte[] escudo;


    public Equipo() {}

    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public byte[] getEscudo() { return this.escudo; }
    public void setEscudo(byte[] escudo) { this.escudo = escudo; }
}