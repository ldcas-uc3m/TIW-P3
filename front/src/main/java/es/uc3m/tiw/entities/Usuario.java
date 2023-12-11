package es.uc3m.tiw.entities;


import java.io.Serializable;


public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String correo;
    private String nombre;
    private String apellidos;
    private String equipo;
    private String password;


    public Usuario() {}


    public String getUserCorreo() {
        return this.correo;
    }
    public void setUserCorreo(String correo) {
        this.correo = correo;
    }

    public String getUserNombre() {
        return this.nombre;
    }
    public void setUserNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserApellido() {
        return this.apellidos;
    }
    public void setUserApellido(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUserEquipo() {
        return this.equipo;
    }
    public void setUserEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getUserPassword() {
        return this.password;
    }
    public void setUserPassword(String password) {
        this.password = password;
    }

}