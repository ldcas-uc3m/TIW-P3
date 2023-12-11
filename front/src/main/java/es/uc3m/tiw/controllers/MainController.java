package es.uc3m.tiw.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;


import es.uc3m.tiw.entities.Equipo;
import es.uc3m.tiw.entities.Jugador;
import es.uc3m.tiw.entities.Plantilla;
import es.uc3m.tiw.entities.PlantillaKey;
import es.uc3m.tiw.entities.Posicion;


@RestController
@CrossOrigin
public class MainController {


    /* REDIRECCIONES */

    @GetMapping("/formNewJugador")
    public String formNewJugador() {
        return "formNewJugador"; // Devuelve el nombre de la plantilla Thymeleaf (sin extensión .html)
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
	@RequestMapping("/")
	public String index(){
		return "index";
	}

	@RequestMapping (value = "pagina-crear-usuario", method = RequestMethod.GET)
	public String mostrarElFormularioDelUsuario(Model modelo){
		modelo.addAttribute("usuario", new User());
		return "ViewCrearUsuario.html";
	}

	@RequestMapping (value = "pagina-borrar-usuario", method = RequestMethod.GET)
	public String mostrarElFormularioBorrarUsuario(){
		return "ViewDeleteUsuario.html";
	}

	@RequestMapping (value = "pagina-update-usuario", method = RequestMethod.GET)
	public String mostrarElFormularioUpdateUsuario(Model modelo){
		modelo.addAttribute("usuario", new User());
		return "ViewUpdateUsuario.html";
	}


	/* LLAMADAS AL CONTROLADOR */
	@RequestMapping (value = "pagina-usuario/{name}", method = RequestMethod.GET)
	public String returnUsuarios(Model model, @PathVariable String name) {

		User us = restTemplate.getForObject("http://localhost:8082/user/{name}", User.class, name);
		model.addAttribute("usuario", us);
		return "viewUsuarios";

	}

	@RequestMapping (value = "pagina-todos-usuarios", method = RequestMethod.GET)
	public String returnTodosUsuarios(Model model) {
		User[] listaUs = restTemplate.getForObject("http://localhost:8082/users", User[].class);
		model.addAttribute("userList", listaUs);
		return "viewTodosUsuarios";
	}


	@RequestMapping (value = "pagina-post-usuario", method = RequestMethod.POST)
	public String saveUser(Model model, @ModelAttribute User us) {
		User newUser = restTemplate.postForObject("http://localhost:8082/users", us, User.class);
		model.addAttribute("usuario", newUser);
		return "viewUsuarios";
	}

	@RequestMapping (value = "pagina-delete-usuario", method = RequestMethod.POST)
	public String deleteUser(Model model, @RequestParam String userName){
		User delUser = restTemplate.getForObject("http://localhost:8082/user/{name}", User.class, userName);
		if (delUser != null) {
			restTemplate.delete("http://localhost:8082/users/{id}", delUser.getIduser());
		}
		return "index";
	}

	@RequestMapping (value = "pagina-search-usuario", method = RequestMethod.POST)
	public String searchUsuarios(Model model, @RequestParam String name) {
		User us = restTemplate.getForObject("http://localhost:8082/user/{name}", User.class, name);
		model.addAttribute("usuario", us);
		return "viewUpdateUsuario";

	}

	@RequestMapping (value = "pagina-update-usuario", method = RequestMethod.POST)
	public String deleteUser(Model model, @ModelAttribute User us){
		restTemplate.put("http://localhost:8082/users", us, User.class);
		return "index";
	}

}
