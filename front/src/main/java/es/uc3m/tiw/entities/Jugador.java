package es.uc3m.tiw.entities;


import java.io.Serializable;



public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;

    private String dni;
    private String nombre;
    private String apellidos;
    private String alias;
    private byte[] imagen;
    private String posicionNombre;
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

    public byte[] getImagen() { return this.imagen; }
    public void setImagen(byte[] imagen) { this.imagen = imagen; }

    public String getPosicionNombre() { return this.posicionNombre; }
    public void setPosicionNombre(String posicionNombre) { this.posicionNombre = posicionNombre; }

    public String getEquipoNombre() { return this.equipoNombre; }
    public void setEquipoNombre(String equipoNombre) { this.equipoNombre = equipoNombre; }

}
