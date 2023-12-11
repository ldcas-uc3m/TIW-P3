package es.uc3m.tiw.repositories;
import org.springframework.data.repository.CrudRepository;

import es.uc3m.tiw.entities.UserPassword;

import java.util.List;
import java.util.Optional;

public interface UserPasswordDAO extends CrudRepository<UserPassword, String>{
    public List<UserPassword> findAll();
    Optional<UserPassword> findByCorreo(String correo);
}