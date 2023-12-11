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


	/* LLAMADAS AL CONTROLADOR */
	@GetMapping ("pagina-usuario/{name}")
	public String returnUsuarios(Model model, @PathVariable String name) {

		Usuario us = restTemplate.getForObject("http://localhost:8082/user/{name}", Usuario.class, name);
		model.addAttribute("usuario", us);
		return "viewUsuarios";

	}

	@GetMapping ("pagina-todos-usuarios")
	public String returnTodosUsuarios(Model model) {
		Usuario[] listaUs = restTemplate.getForObject("http://localhost:8082/users", Usuario[].class);
		model.addAttribute("userList", listaUs);
		return "viewTodosUsuarios";
	}


	@PostMapping ("pagina-post-usuario")
	public String saveUser(Model model, @ModelAttribute Usuario us) {
		Usuario newUser = restTemplate.postForObject("http://localhost:8082/users", us, Usuario.class);
		model.addAttribute("usuario", newUser);
		return "viewUsuarios";
	}

	@PostMapping ("pagina-delete-usuario")
	public String deleteUser(Model model, @RequestParam String userName) {
		Usuario delUser = restTemplate.getForObject("http://localhost:8082/user/{name}", Usuario.class, userName);
		if (delUser != null) {
			restTemplate.delete("http://localhost:8082/users/{id}", delUser.getUserCorreo());
		}
		return "index";
	}

	@PostMapping ("pagina-search-usuario")
	public String searchUsuarios(Model model, @RequestParam String name) {
		Usuario us = restTemplate.getForObject("http://localhost:8082/user/{name}", Usuario.class, name);
		model.addAttribute("usuario", us);
		return "viewUpdateUsuario";

	}

	@PostMapping ("pagina-update-usuario")
	public String deleteUser(Model model, @ModelAttribute Usuario us){
		restTemplate.put("http://localhost:8082/users", us, Usuario.class);
		return "index";
	}

}
