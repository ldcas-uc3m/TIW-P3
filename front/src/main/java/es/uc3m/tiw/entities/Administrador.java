package es.uc3m.tiw.entities;


import java.io.Serializable;



public class Administrador implements Serializable{
    private static final long serialVersionUID = 1L;

    String correo;
    private String nombre;
    private String apellidos;
    private String password;


    public Administrador() {}

    public String getAdminCorreo() {
        return this.correo;
    }
    public void setAdminCorreo(String correo) {
        this.correo = correo;
    }

    public String getAdminNombre() {
        return this.nombre;
    }
    public void setAdminNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAdminApellido() {
        return this.apellidos;
    }
    public void setAdminApellido(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getAdminPassword() {
        return this.password;
    }
    public void setAdminPassword(String password) {
        this.password = password;
    }
}