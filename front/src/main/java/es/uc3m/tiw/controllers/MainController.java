package es.uc3m.tiw.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.entities.Equipo;
import es.uc3m.tiw.entities.Jugador;
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

    @GetMapping("/formNewJugador")
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
		Posicion[] posiciones = restTemplate.getForObject("http://localhost:8022/posiciones/", Posicion[].class);
		modelo.addAttribute("posiciones", posiciones);

		// get equipos
		Equipo[] equipos = restTemplate.getForObject("http://localhost:8022/equipos", Equipo[].class);
		modelo.addAttribute("equipos", equipos);

		// modelo.addAttribute("jugador", new Jugador());

        return "formUpdateJugador";
    }


	@PostMapping ("post-jugador")
	public String saveJugador(Model model, @ModelAttribute Jugador ju) {
		Jugador newJugador = restTemplate.postForObject("http://localhost:8022/jugador", ju, Jugador.class);
		model.addAttribute("jugador", newJugador);

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
	
	@PostMapping ("update-jugador/{dni}")
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
    public String loginAdmin(Model modelo, @ModelAttribute Administrador ad) {

		try {
			restTemplate.postForObject("http://localhost:8021/Adminlogin", ad, String.class);
		}
		catch(HttpClientErrorException ex) {  // different from 200

			if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
				String msg = "Invalid credentials";
				System.out.println(msg);
				modelo.addAttribute("error", msg);
			}
		}

        return returnTodosJugadoresAd(modelo);
    }


	@GetMapping("/pagina-login-usuario")
    public String gotLoginUsuario(Model modelo) {
		// get equipos
		Equipo[] equipos = restTemplate.getForObject("http://localhost:8022/equipos", Equipo[].class);
		modelo.addAttribute("equipos", equipos);

		// create bean for usuario
		modelo.addAttribute("usuario", new Usuario());
        return "loginUsuario";
    }

    @PostMapping ("/loginUsuario")
	public String loginUsuario(Model model, @ModelAttribute Usuario us) {
		Usuario newUser = restTemplate.postForObject("http://localhost:8021/Userlogin", us, Usuario.class);

		model.addAttribute("usuario", newUser);

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



}
