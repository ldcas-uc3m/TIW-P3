package es.uc3m.tiw.entities;


import java.io.Serializable;


public class AdminPassword implements Serializable{
    private static final long serialVersionUID = 1L;

    String adminCorreo;
    private String AdminPassword;


    public AdminPassword() {}


    public String getAdminCorreo() {
        return this.adminCorreo;
    }
    public void setAdminCorreo(String correo) {
        this.adminCorreo = correo;
    }

    public String getAdminPassword() {
        return this.AdminPassword;
    }
    public void setAdminPassword(String password) {
        this.AdminPassword = password;
    }
}