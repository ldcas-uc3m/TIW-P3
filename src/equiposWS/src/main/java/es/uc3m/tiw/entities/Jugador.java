package es.uc3m.tiw.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "jugadores")
public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String dni;

    private String nombre;
    private String apellidos;
    private String alias;

    @Lob
    private byte[] imagen;

    @ManyToOne
    private Posicion posicion;

    @ManyToOne
    private Equipo equipo;


    public Jugador() {}


    public String getDni() { return this.dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return this.apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getAlias() { return this.alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public byte[] getImagen() { return this.imagen; }
    public void setImagen(byte[] imagen) { this.imagen = imagen; }

    public Posicion getPosicion() { return this.posicion; }
    public void setPosicion(Posicion posicion) { this.posicion = posicion; }

    public Equipo getEquipo() { return this.equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }

}
