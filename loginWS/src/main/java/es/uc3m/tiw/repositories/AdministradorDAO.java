package es.uc3m.tiw.repositories;
import org.springframework.data.repository.CrudRepository;
import es.uc3m.tiw.entities.Administrador;

import java.util.List;

public interface AdministradorDAO extends CrudRepository<Administrador, String>{
    public List<Administrador> findAll();
    /*public Administrador findByCorreo(String correo);*/
}