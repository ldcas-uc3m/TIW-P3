package es.uc3m.tiw.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.entities.Equipo;
import es.uc3m.tiw.entities.Jugador;
import es.uc3m.tiw.entities.Mensaje;
import es.uc3m.tiw.entities.Plantilla;
import es.uc3m.tiw.entities.PlantillaKey;
import es.uc3m.tiw.entities.Posicion;
import es.uc3m.tiw.entities.Usuario;
import es.uc3m.tiw.entities.Administrador;



@Controller  // can't be RestController
public class MainController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/")
    public String index(){
        return "index";
    }


	/* JUGADORES */

    @GetMapping("/pagina-form-NewJugador")
    public String formNewJugador(Model modelo) {

		// get posiciones
		Posicion[] posiciones = restTemplate.getForObject("http://localhost:8022/posiciones", Posicion[].class);
		modelo.addAttribute("posiciones", posiciones);

		// get equipos
		Equipo[] equipos = restTemplate.getForObject("http://localhost:8022/equipos", Equipo[].class);
		modelo.addAttribute("equipos", equipos);

		// create bean for jugador
		modelo.addAttribute("jugador", new Jugador());

        return "formNewJugador";  // Devuelve el nombre de la plantilla Thymeleaf (sin extensión .html)
    }

    @GetMapping("/formUpdateJugador/{dni}")
    public String formUpdateJugador(Model modelo, @PathVariable String dni) {
		// get jugador
		Jugador jugador = restTemplate.getForObject("http://localhost:8022/jugador/{dni}", Jugador.class, dni);
		modelo.addAttribute("jugador", jugador);

		// get posiciones
		Posicion[] posiciones = restTemplate.getForObject("http://localhost:8022/posiciones", Posicion[].class);
		modelo.addAttribute("posiciones", posiciones);

		// get equipos
		Equipo[] equipos = restTemplate.getForObject("http://localhost:8022/equipos", Equipo[].class);
		modelo.addAttribute("equipos", equipos);

		// modelo.addAttribute("jugador", new Jugador());

        return "formUpdateJugador";
    }


	@PostMapping ("post-jugador")
	public String saveJugador(Model model, @ModelAttribute Jugador ju) {

		try {
			Jugador newJugador = restTemplate.postForObject("http://localhost:8022/jugador", ju, Jugador.class);
			model.addAttribute("jugador", newJugador);
		}
		catch(HttpClientErrorException ex) {  // different from 200

			switch (ex.getStatusCode()) {
				case BAD_REQUEST:
					String msg = "Invalid data";
					System.out.println(msg);
					model.addAttribute("error", msg);
					break;
				default:
					break;
			}

			return formNewJugador(model);
		}

		return returnTodosJugadoresUs(model);
	}

	@PostMapping ("delete-jugador/{dni}")
	public String deleteJugador(Model model, @PathVariable String dni) {

		try {
			restTemplate.delete("http://localhost:8022/jugador/{dni}", dni);
		}
		catch(HttpClientErrorException ex) {  // different from 200

			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				String msg = "Jugador doesn't exist";
				System.out.println(msg);
				model.addAttribute("error", msg);
			}
		}

		return returnTodosJugadoresAd(model);
	}
	
	/*@PostMapping ("update-jugador/{dni}")
	public String updateJugador(Model model, @ModelAttribute Jugador ju, @PathVariable dni) {

		try {
			restTemplate.put("http://localhost:8022/jugador/{dni}", ju, dni);
		}
		catch(HttpClientErrorException ex) {  // different from 200

			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				String msg = "Jugador doesn't exist";
				System.out.println(msg);
				model.addAttribute("error", msg);
			}
		}

		return returnTodosJugadoresAd(model);
	}


	/* LOGIN */

	@GetMapping("/pagina-login-administrador")
    public String gotLoginAdministrador(Model modelo) {

		// create bean for usuario
		modelo.addAttribute("administrador", new Administrador());

        return "loginAdmin";
    }

    @PostMapping("/loginAdmin")
    public String loginAdmin(Model modelo, HttpSession session, @ModelAttribute Administrador ad) {

		try {
			restTemplate.postForObject("http://localhost:8021/Adminlogin", ad, String.class);
		}
		catch(HttpClientErrorException ex) {  // different from 200

			if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
				String msg = "Invalid credentials";
				System.out.println(msg);
				modelo.addAttribute("error", msg);

				return gotLoginAdministrador(modelo);
			}
		}

		session.setAttribute("user", ad.getAdminCorreo());

        return returnTodosJugadoresAd(modelo);
    }


	@GetMapping("/pagina-login-usuario")
    public String gotLoginUsuario(Model modelo ) {
		// get equipos
		Equipo[] equipos = restTemplate.getForObject("http://localhost:8022/equipos", Equipo[].class);
		modelo.addAttribute("equipos", equipos);

		// create bean for usuario
		modelo.addAttribute("usuario", new Usuario());
        return "loginUsuario";
    }

    @PostMapping ("/loginUsuario")
	public String loginUsuario(Model model, HttpSession session, @ModelAttribute Usuario us) {
		try {
			restTemplate.postForObject("http://localhost:8021/Userlogin", us, String.class);
		}
		catch(HttpClientErrorException ex) {  // different from 200

			if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
				String msg = "Invalid credentials";
				System.out.println(msg);
				model.addAttribute("error", msg);

				return gotLoginUsuario(model);

			}
		}

		session.setAttribute("user", us.getUserCorreo());

        return returnTodosJugadoresUs(model);
	}



	/* CRUD CONTROLADOR - JUGADORES */

	@GetMapping ("pagina-todos-jugadores-user")
	public String returnTodosJugadoresUs(Model model) {
		try {
			Jugador[] listaJu = restTemplate.getForObject("http://localhost:8022/jugadores", Jugador[].class);
			model.addAttribute("jugadorList", listaJu);
		}
		catch(HttpClientErrorException ex) {  // different from 200

			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				String msg = "No jugadores found";
				System.out.println(msg);
				model.addAttribute("error", msg);
			}
		}

		return "viewTodosJugadoresUs";
	}
	
	@GetMapping ("pagina-todos-jugadores-admin")
	public String returnTodosJugadoresAd(Model model) {
		try {
			Jugador[] listaJu = restTemplate.getForObject("http://localhost:8022/jugadores", Jugador[].class);
			model.addAttribute("jugadorList", listaJu);
		}
		catch(HttpClientErrorException ex) {  // different from 200

			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				String msg = "No jugadores found";
				System.out.println(msg);
				model.addAttribute("error", msg);
			}
		}
		
		return "viewTodosJugadoresAd";
	}

	/* CRUD CONTROLADOR - CHATS */

	@GetMapping("/formMessage")
    public String formMessage(Model modelo) {

		// create bean for jugador
		modelo.addAttribute("mensaje", new Mensaje());

        return "formMessage";  // Devuelve el nombre de la plantilla Thymeleaf (sin extensión .html)
    }

	@PostMapping ("post-mensaje")
	public String saveMensaje(Model model, HttpSession session, @ModelAttribute Mensaje me) {

		me.setEmailori(session.getAttribute("user").toString());

		Mensaje newMensaje = restTemplate.postForObject("http://localhost:8080/mensaje", me, Mensaje.class);
		model.addAttribute("mensaje", newMensaje);

		return returnTodosMensajes(model, session);
	}


	@GetMapping ("pagina-todos-mensajes")
	public String returnTodosMensajes(Model model, HttpSession session) {
		try {
			Mensaje[] listaMe = restTemplate.getForObject("http://localhost:8080/mensajes/{user}", Mensaje[].class, session.getAttribute("user").toString());
			model.addAttribute("mensajeList", listaMe);
		}
		catch(HttpClientErrorException ex) {  // different from 200

			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				String msg = "No mensajes found";
				System.out.println(msg);
				model.addAttribute("error", msg);
			}
		}
		
		return "viewMensajes";
	}

}
