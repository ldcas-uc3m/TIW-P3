package es.uc3m.tiw.entities;


import java.io.Serializable;


public class UserPassword implements Serializable{
    private static final long serialVersionUID = 1L;

    String userCorreo;
    private String userPassword;


    public UserPassword() {}

    public String getUserCorreo() {
        return this.userCorreo;
    }
    public void setUserCorreo(String correo) {
        this.userCorreo = correo;
    }

    public String getUserPassword() {
        return this.userPassword;
    }
    public void setUserPassword(String password) {
        this.userPassword = password;
    }
}