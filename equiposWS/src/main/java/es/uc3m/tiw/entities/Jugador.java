package es.uc3m.tiw.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "jugadores")
public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    private String alias;

    @Column(name = "posicion_nombre", nullable = false)
    private String posicionNombre;

    @Column(name = "equipo_nombre", nullable = false)
    private String equipoNombre;


    public Jugador() {}


    public String getDni() { return this.dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return this.apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getAlias() { return this.alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getPosicionNombre() { return this.posicionNombre; }
    public void setPosicionNombre(String posicionNombre) { this.posicionNombre = posicionNombre; }

    public String getEquipoNombre() { return this.equipoNombre; }
    public void setEquipoNombre(String equipoNombre) { this.equipoNombre = equipoNombre; }

}
