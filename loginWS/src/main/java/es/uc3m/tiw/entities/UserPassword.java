package es.uc3m.tiw.entities;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userpasswords")
public class UserPassword implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    String correo;

    @Column(nullable = false)
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