package es.uc3m.tiw.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.entities.Equipo;
import es.uc3m.tiw.entities.Jugador;
import es.uc3m.tiw.entities.Plantilla;
import es.uc3m.tiw.entities.PlantillaKey;
import es.uc3m.tiw.entities.Posicion;
import es.uc3m.tiw.entities.Usuario;
import es.uc3m.tiw.entities.Administrador;



@RestController
public class MainController {

	@Autowired
	RestTemplate restTemplate;

    /* REDIRECCIONES */

    @GetMapping("/formNewJugador")
    public String formNewJugador() {
        return "formNewJugador";  // Devuelve el nombre de la plantilla Thymeleaf (sin extensión .html)
    }

    @GetMapping("/formUpgradeJugador")
    public String formUpgadeJugador() {
        return "formUpgradeJugador";
    }

    @GetMapping("/loginAdmin")
    public String loginAdmin() {
        return "loginAdmin";
    }

    @GetMapping("/loginUsuario")
    public String loginUsuario() {
        return "loginUsuario";
    }

    @GetMapping("/viewTodosJugadoresAd")
    public String viewTodosJugadoresAd() {
        return "viewTodosJugadoresAd";
    }



	/* NAVEGACION */
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping ("pagina-crear-usuario")
	public String mostrarElFormularioDelUsuario(Model modelo){
		modelo.addAttribute("usuario", new Usuario());
		return "ViewCrearUsuario.html";
	}

	@GetMapping ("pagina-borrar-usuario")
	public String mostrarElFormularioBorrarUsuario() {
		return "ViewDeleteUsuario.html";
	}

	@GetMapping ("pagina-update-usuario")
	public String mostrarElFormularioUpdateUsuario(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "ViewUpdateUsuario.html";
	}


	/* CRUD CONTROLADOR - USUARIOS */
	@GetMapping ("pagina-usuario/{nombre}")
	public String returnUsuarios(Model model, @PathVariable String nombre) {

		Usuario us = restTemplate.getForObject("http://localhost:8021/user/{nombre}", Usuario.class, nombre);
		model.addAttribute("usuario", us);
		return "viewUsuarios";

	}

	@GetMapping ("pagina-todos-usuarios")
	public String returnTodosUsuarios(Model model) {
		Usuario[] listaUs = restTemplate.getForObject("http://localhost:8021/users", Usuario[].class);
		model.addAttribute("userList", listaUs);
		return "viewTodosUsuarios";
	}


	@PostMapping ("pagina-post-usuario")
	public String saveUser(Model model, @ModelAttribute Usuario us) {
		Usuario newUser = restTemplate.postForObject("http://localhost:8021/users", us, Usuario.class);
		model.addAttribute("usuario", newUser);
		return "viewUsuarios";
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
		return "viewJugadores";

	}

	@GetMapping ("pagina-todos-jugadores")
	public String returnTodosJugadores(Model model) {
		Jugador[] listaJu = restTemplate.getForObject("http://localhost:8022/jugadores", Jugador[].class);
		model.addAttribute("jugadorList", listaJu);
		return "viewTodosJugadores";
	}


	@PostMapping ("pagina-post-jugador")
	public String saveJugador(Model model, @ModelAttribute Jugador ju) {
		Jugador newJugador = restTemplate.postForObject("http://localhost:8022/jugadores", ju, Jugador.class);
		model.addAttribute("jugador", newJugador);
		return "viewJugadores";
	}

	@PostMapping ("pagina-delete-jugador")
	public String deleteJugador(Model model, @RequestParam String jugadorDNI) {
		Jugador delJugador = restTemplate.getForObject("http://localhost:8022/jugador/{dni}", Jugador.class, jugadorDNI);
		if (delJugador != null) {
			restTemplate.delete("http://localhost:8082/jugadores/{dni}", delJugador.getDni());
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
