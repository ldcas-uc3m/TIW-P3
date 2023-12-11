package es.uc3m.tiw.chatWS.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.tiw.chatWS.domains.Mensaje;
import es.uc3m.tiw.chatWS.repositories.MensajeRepository;


@RestController
@CrossOrigin
@EnableAutoConfiguration
public class ChatController {

	@Autowired
	MensajeRepository mensajeRepository;

	@GetMapping(value = "/mensajes", produces = "application/json")
	public ResponseEntity<?> getConversaciones() {

		try {
			List<Mensaje> entityList = mensajeRepository.findAll();

			return new ResponseEntity<>(entityList, HttpStatus.OK);

		} catch (Exception ex) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "/mensajes/{emailori}", produces = "application/json")
	public ResponseEntity<?> getMensajesUsuario(@PathVariable(value = "emailori", required = true) String emailori) {

		try {

			List<Mensaje> entityList = mensajeRepository.findByEmailori(emailori);

			return new ResponseEntity<>(entityList, HttpStatus.OK);

		} catch (Exception ex) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}


	@PostMapping(value = "/mensaje", produces = "application/json")
	public ResponseEntity<?> sendMensaje(@RequestBody Mensaje mensaje) {

		try {
			Mensaje saved_mensaje = mensajeRepository.save(mensaje);
			return new ResponseEntity<>(saved_mensaje, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}


	@GetMapping(value = "/conversaciones/{emailori}/{emaildest}", produces = "application/json")
	public ResponseEntity<?> getConversaciones(@PathVariable(value = "emailori", required = true) String emailori,
			@PathVariable(value = "emaildest", required = true) String emaildest) {

		try {

			List<Mensaje> entityList = mensajeRepository.findByEmailoriAndEmaildest(emailori, emaildest);

			if (entityList.isEmpty())
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(entityList, HttpStatus.OK);


		} catch (Exception e) {

			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
	}


	@GetMapping(value = "/mensajeslike/{expression}", produces = "application/json")
	public ResponseEntity<?> getMensajesLike(@PathVariable(value = "expression", required = true) String expresion) {

		try {
			List<Mensaje> entityList = mensajeRepository.findByMensajeLikeOrderByMensajeAsc(expresion);

			return new ResponseEntity<>(entityList, HttpStatus.OK);

		} catch (Exception ex) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}


}