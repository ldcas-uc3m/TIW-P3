package es.uc3m.tiw.entities;


import java.io.Serializable;



public class Administrador implements Serializable{
    private static final long serialVersionUID = 1L;

    String adminCorreo;
    private String adminNombre;
    private String adminApellidos;
    private String adminPassword;


    public Administrador() {}

    public String getAdminCorreo() {
        return this.adminCorreo;
    }
    public void setAdminCorreo(String correo) {
        this.adminCorreo = correo;
    }

    public String getAdminNombre() {
        return this.adminNombre;
    }
    public void setAdminNombre(String nombre) {
        this.adminNombre = nombre;
    }

    public String getAdminApellido() {
        return this.adminApellidos;
    }
    public void setAdminApellido(String apellidos) {
        this.adminApellidos = apellidos;
    }

    public String getAdminPassword() {
        return this.adminPassword;
    }
    public void setAdminPassword(String password) {
        this.adminPassword = password;
    }
}