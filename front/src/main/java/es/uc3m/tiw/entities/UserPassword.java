package es.uc3m.tiw.entities;


import java.io.Serializable;


public class UserPassword implements Serializable{
    private static final long serialVersionUID = 1L;

    String correo;
    private String password;


    public UserPassword() {}

    public String getUserCorreo() {
        return this.correo;
    }
    public void setUserCorreo(String correo) {
        this.correo = correo;
    }

    public String getUserPassword() {
        return this.password;
    }
    public void setUserPassword(String password) {
        this.password = password;
    }
}