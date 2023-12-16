package es.uc3m.tiw.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uc3m.tiw.entities.Imagen;


public interface ImageDataRepository extends JpaRepository<Imagen, String> {
    public Optional<Imagen> findByjugadorDni(String dni);
}
