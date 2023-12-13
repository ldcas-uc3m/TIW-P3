package es.uc3m.tiw.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
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

    /* REDIRECCIONES */

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

    @GetMapping("/formUpdateJugador")
    public String formUpdateJugador() {
        return "formUpdateJugador";
    }

    @GetMapping("/loginAdmin")
    public String loginAdmin(Model modelo) {
        return "loginAdmin";
    }

    @GetMapping("/loginUsuario")
    public String loginUsuario(Model modelo) {
        // create model attribute to bind form data
		modelo.addAttribute("usuario", new Usuario());
        return "loginUsuario";
    }

    @GetMapping("/viewTodosJugadoresAd")
    public String viewTodosJugadoresAd() {
        return "viewTodosJugadoresAd";
    }



	/* NAVEGACION */
	@GetMapping("/")
    public String index(){
        return "index";
    }


	/* CRUD CONTROLADOR - USUARIOS */
	@GetMapping ("pagina-usuario/{nombre}")
	public String returnUsuarios(Model model, @PathVariable String nombre) {

		Usuario us = restTemplate.getForObject("http://localhost:8021/user/{nombre}", Usuario.class, nombre);
		model.addAttribute("usuario", us);
		return "viewTodosJugadoresUs";

	}

	@GetMapping ("pagina-todos-usuarios")
	public String returnTodosUsuarios(Model model) {
		Usuario[] listaUs = restTemplate.getForObject("http://localhost:8021/users", Usuario[].class);
		model.addAttribute("userList", listaUs);
		return "viewTodosJugadoresUs";
	}
 

	@PostMapping ("pagina-post-usuario")
	public String saveUser(Model model, @ModelAttribute Usuario us) {
		Usuario newUser = restTemplate.postForObject("http://localhost:8021/users", us, Usuario.class);
		model.addAttribute("usuario", newUser);
		return "viewTodosJugadoresUs";
	}

	@PostMapping ("pagina-delete-usuario")
	public String deleteUser(Model model, @RequestParam String userName) {
		Usuario delUser = restTemplate.getForObject("http://localhost:8021/user/{nombre}", Usuario.class, userName);
		if (delUser != null) {
			restTemplate.delete("http://localhost:8082/users/{nombre}", delUser.getUserCorreo());
		}
		return "index";
	}

	@PostMapping ("pagina-search-usuario")
	public String searchUsuarios(Model model, @RequestParam String nombre) {
		Usuario us = restTemplate.getForObject("http://localhost:8021/user/{nombre}", Usuario.class, nombre);
		model.addAttribute("usuario", us);
		return "viewUpdateUsuario";

	}

	@PostMapping ("pagina-update-usuario")
	public String deleteUser(Model model, @ModelAttribute Usuario us){
		restTemplate.put("http://localhost:8082/users", us, Usuario.class);
		return "index";
	}


	/* CRUD CONTROLADOR - EQUIPOS */
	@GetMapping ("pagina-equipo/{nombre}")
	public String returnEquipos(Model model, @PathVariable String nombre) {

		Equipo eq = restTemplate.getForObject("http://localhost:8022/equipo/{nombre}", Equipo.class, nombre);
		model.addAttribute("equipo", eq);
		return "viewEquipos";

	}

	@GetMapping ("pagina-todos-equipos")
	public String returnTodosEquipos(Model model) {
		Equipo[] listaEq = restTemplate.getForObject("http://localhost:8022/equipos", Equipo[].class);
		model.addAttribute("equipoList", listaEq);
		return "viewTodosEquipos";
	}

	@PostMapping ("pagina-post-equipo")
	public String saveEquipo(Model model, @ModelAttribute Equipo eq) {
		Equipo newEquipo = restTemplate.postForObject("http://localhost:8022/equipos", eq, Equipo.class);
		model.addAttribute("equipo", newEquipo);
		return "viewEquipos";
	}

	@PostMapping ("pagina-delete-equipo")
	public String deleteEquipo(Model model, @RequestParam String equipoName) {
		Equipo delEquipo = restTemplate.getForObject("http://localhost:8022/equipos/{nombre}", Equipo.class, equipoName);
		if (delEquipo != null) {
			restTemplate.delete("http://localhost:8082/equipos/{nombre}", delEquipo.getNombre());
		}
		return "index";
	}

	@PostMapping ("pagina-search-equipo")
	public String searchEquipos(Model model, @RequestParam String nombre) {
		Equipo eq = restTemplate.getForObject("http://localhost:8022/equipos/{nombre}", Equipo.class, nombre);
		model.addAttribute("equipo", eq);
		return "viewUpdateEquipo";

	}

	@PostMapping ("pagina-update-equipo")
	public String deleteEquipo(Model model, @ModelAttribute Equipo eq){
		restTemplate.put("http://localhost:8022/equipos", eq, Equipo.class);
		return "index";
	}


	/* CRUD CONTROLADOR - JUGADORES */
	@GetMapping ("pagina-jugador/{dni}")
	public String returnJugadores(Model model, @PathVariable String dni) {

		Jugador ju = restTemplate.getForObject("http://localhost:8022/jugador/{dni}", Jugador.class, dni);
		model.addAttribute("jugador", ju);
		return "viewTodosJugadoresUs";

	}

	@GetMapping ("pagina-todos-jugadores")
	public String returnTodosJugadores(Model model) {
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


	@PostMapping ("post-jugador")
	public String saveJugador(Model model, @ModelAttribute Jugador ju) {
		Jugador newJugador = restTemplate.postForObject("http://localhost:8022/jugador", ju, Jugador.class);
		model.addAttribute("jugador", newJugador);

		return returnTodosJugadores(model);
	}

	@PostMapping ("pagina-delete-jugador")
	public String deleteJugador(Model model, @RequestParam String jugadorDNI) {
		Jugador delJugador = restTemplate.getForObject("http://localhost:8022/jugador/{dni}", Jugador.class, jugadorDNI);
		if (delJugador != null) {
			restTemplate.delete("http://localhost:8082/jugador/{dni}", delJugador.getDni());
		}
		return "index";
	}

	@PostMapping ("pagina-search-jugador")
	public String searchJugadores(Model model, @RequestParam String dni) {
		Jugador ju = restTemplate.getForObject("http://localhost:8022/jugador/{dni}", Jugador.class, dni);
		model.addAttribute("jugador", ju);
		return "viewUpdateJugador";

	}

	@PostMapping ("pagina-update-jugador")
	public String deleteJugador(Model model, @ModelAttribute Jugador ju){
		restTemplate.put("http://localhost:8022/jugadores", ju, Jugador.class);
		return "index";
	}

	/* CRUD CONTROLADOR - ADMINISTRADOR */
	@GetMapping ("pagina-administrador/{nombre}")
	public String returnAdministrador(Model model, @PathVariable String nombre) {

		Administrador ad = restTemplate.getForObject("http://localhost:8021/admin/{nombre}", Administrador.class, nombre);
		model.addAttribute("administrador", ad);
		return "viewAdministrador";

	}

	@GetMapping ("pagina-todos-administradores")
	public String returnTodosAdministradores(Model model) {
		Administrador[] listaAd = restTemplate.getForObject("http://localhost:8021/administradores", Administrador[].class);
		model.addAttribute("adminList", listaAd);
		return "viewTodosAdministradores";
	}


	@PostMapping ("pagina-post-administrador")
	public String saveAdmin(Model model, @ModelAttribute Administrador ad) {
		Administrador newAdministrador = restTemplate.postForObject("http://localhost:8021/administradores", ad, Administrador.class);
		model.addAttribute("administrador", newAdministrador);
		return "viewAdministrador";
	}

	@PostMapping ("pagina-delete-administrador")
	public String deleteAdmin(Model model, @RequestParam String adminName) {
		Administrador delAdmin = restTemplate.getForObject("http://localhost:8021/admin/{nombre}", Administrador.class, adminName);
		if (delAdmin != null) {
			restTemplate.delete("http://localhost:8082/admin/{nombre}", delAdmin.getAdminCorreo());
		}
		return "index";
	}

	@PostMapping ("pagina-search-administrador")
	public String searchAdmin(Model model, @RequestParam String nombre) {
		Administrador ad = restTemplate.getForObject("http://localhost:8021/admin/{nombre}", Administrador.class, nombre);
		model.addAttribute("administrador", ad);
		return "viewUpdateAdministrador";

	}

	@PostMapping ("pagina-update-administrador")
	public String deleteAdmin(Model model, @ModelAttribute Administrador ad){
		restTemplate.put("http://localhost:8021/administradores", ad, Administrador.class);
		return "index";
	}




}
