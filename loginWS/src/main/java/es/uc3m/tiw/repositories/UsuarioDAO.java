package es.uc3m.tiw.repositories;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

import es.uc3m.tiw.entities.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, String>{
    public List<Usuario> findAll();
    /*public List<Usuario> findByUserCorreo(String correo);*/
}