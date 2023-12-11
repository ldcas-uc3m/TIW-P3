package es.uc3m.tiw.entities;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administradores")
public class Administrador implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    String correo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
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