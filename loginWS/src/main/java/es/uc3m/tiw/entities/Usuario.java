package es.uc3m.tiw.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private String correo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String equipo;

    @Column(nullable = false)
    private String password;

    public Usuario() {}

    public String getUserCorreo() {
        return this.correo;
    }
    public void setUserCorreo(String correo) {
        this.correo = correo;
    }

    public String getUserNombre() {
        return this.nombre;
    }
    public void setUserNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserApellido() {
        return this.apellidos;
    }
    public void setUserApellido(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUserEquipo() {
        return this.equipo;
    }
    public void setUserEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getUserPassword() {
        return this.password;
    }
    public void setUserPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "correo='" + correo + '\'' +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellidos + '\'' +
               ", equipo='" + equipo + '\'' +
               ", password='" + password + '\''+
               '}';
    }
}