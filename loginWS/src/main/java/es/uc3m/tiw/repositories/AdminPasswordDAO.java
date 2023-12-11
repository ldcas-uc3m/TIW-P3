package es.uc3m.tiw.repositories;
import org.springframework.data.repository.CrudRepository;
import es.uc3m.tiw.entities.AdminPassword;

import java.util.List;
import java.util.Optional;

public interface AdminPasswordDAO extends CrudRepository<AdminPassword, String>{
    public List<AdminPassword> findAll();
    Optional<AdminPassword> findByCorreo(String correo);
}