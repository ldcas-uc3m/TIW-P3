package es.uc3m.tiw.entities;


import java.io.Serializable;


public class AdminPassword implements Serializable{
    private static final long serialVersionUID = 1L;

    String correo;
    private String password;


    public AdminPassword() {}


    public String getAdminCorreo() {
        return this.correo;
    }
    public void setAdminCorreo(String correo) {
        this.correo = correo;
    }

    public String getAdminPassword() {
        return this.password;
    }
    public void setAdminPassword(String password) {
        this.password = password;
    }
}