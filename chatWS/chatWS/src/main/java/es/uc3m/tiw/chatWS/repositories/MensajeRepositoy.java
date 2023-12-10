package tidw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tidw.domains.Mensaje;


public interface MensajeRepository extends CrudRepository<Mensaje, String>{

	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

	List<Mensaje> findAll();

	@Query("SELECT u FROM Mensajes u WHERE u.emailori=:email1 AND emaildest=:email2")
	List<Mensaje> findByEmailoriAndEmaildest(@Param("email1")String emailori, @Param("email2") String emaildest);

	List<Mensaje> findByEmailori(String emailori);

	List<Mensaje> findByMensajeLikeOrderByMensajeAsc(String expresion);

}