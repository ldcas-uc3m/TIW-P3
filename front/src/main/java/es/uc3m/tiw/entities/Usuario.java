package es.uc3m.tiw.entities;


import java.io.Serializable;


public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userCorreo;
    private String userNombre;
    private String userApellidos;
    private String userEquipo;
    private String userPassword;


    public Usuario() {}


    public String getUserCorreo() {
        return this.userCorreo;
    }
    public void setUserCorreo(String correo) {
        this.userCorreo = correo;
    }

    public String getUserNombre() {
        return this.userNombre;
    }
    public void setUserNombre(String nombre) {
        this.userNombre = nombre;
    }

    public String getUserApellido() {
        return this.userApellidos;
    }
    public void setUserApellido(String apellidos) {
        this.userApellidos = apellidos;
    }

    public String getUserEquipo() {
        return this.userEquipo;
    }
    public void setUserEquipo(String equipo) {
        this.userEquipo = equipo;
    }

    public String getUserPassword() {
        return this.userPassword;
    }
    public void setUserPassword(String password) {
        this.userPassword = password;
    }

}