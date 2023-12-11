package es.uc3m.tiw.entities;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adminpasswords")
public class AdminPassword implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    String correo;

    @Column(nullable = false)
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