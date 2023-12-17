package es.uc3m.tiw.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import es.uc3m.tiw.entities.Usuario;
import es.uc3m.tiw.entities.AdminPassword;
import es.uc3m.tiw.entities.Administrador;
import es.uc3m.tiw.entities.UserPassword;

import es.uc3m.tiw.repositories.UsuarioDAO;
import es.uc3m.tiw.repositories.AdminPasswordDAO;
import es.uc3m.tiw.repositories.AdministradorDAO;
import es.uc3m.tiw.repositories.UserPasswordDAO;



@RestController
@CrossOrigin
public class MainController {

    /* REPOSITORIOS */

    @Autowired
    AdministradorDAO daoAdmin;

    @Autowired
    UsuarioDAO daoUser;

    @Autowired
    AdminPasswordDAO daoAdminPas;

    @Autowired
    UserPasswordDAO daoUserPas;


    private Map<String, String> generateError(String code, String message){
        Map<String, String> error = new HashMap<>();
        error.put("code", code);
        error.put("message", message);

        return error;
    }

    @GetMapping("/coffee")
    public ResponseEntity<HttpStatus> coffe() { return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT); }

    /* LOGIN ADMINISTRADORES */


    @GetMapping("/administradores")
    public ResponseEntity<List<Administrador>> getAllAdministradores() {
        List<Administrador> administrador = daoAdmin.findAll();
        if (administrador.isEmpty())
            return new ResponseEntity<>(administrador, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(administrador, HttpStatus.OK);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuario = daoUser.findAll();
        if (usuario.isEmpty())
            return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/usuarios/{user}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String user) {
        Usuario usuario = daoUser.findByCorreo(user);
        if (usuario == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/adminpassword")
    public ResponseEntity<List<AdminPassword>> getAllAdminPasswords() {
        List<AdminPassword> adminpas = daoAdminPas.findAll();
        if (adminpas.isEmpty())
            return new ResponseEntity<>(adminpas, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(adminpas, HttpStatus.OK);
    }

     @GetMapping("/userpassword")
    public ResponseEntity<List<UserPassword>> getAllUserPasswords() {
        List<UserPassword> userpas = daoUserPas.findAll();
        if (userpas.isEmpty())
            return new ResponseEntity<>(userpas, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userpas, HttpStatus.OK);
    }

    @PostMapping(path = "/registrarUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario nu_usuario) {

        String usuario_correo = nu_usuario.getUserCorreo();
        String usuario_equipo = nu_usuario.getUserEquipo();
        String usuario_password = nu_usuario.getUserPassword();
        String usuario_apellido = nu_usuario.getUserApellido();
        String usuario_nombre = nu_usuario.getUserNombre();

        //comprobar que el correo, el nombre, el apellido y la contraseña no es null
        if (usuario_correo == null || usuario_password == null || usuario_nombre == null || usuario_apellido == null){
            String msg = "Uno de los campos obligatorios no ha sido rellenado";
            System.err.println(msg);
            return new ResponseEntity<>(generateError("CAMPOS FALTANTES", msg), HttpStatus.BAD_REQUEST);

        }

        // ver si el correo existe
        if (daoUser.existsById(usuario_correo)) {
            String msg = "User '" + usuario_correo + " ' already registered";
            System.err.println(msg);
            return new ResponseEntity<>(generateError("USER_ALREADY_REGISTERED", msg), HttpStatus.BAD_REQUEST);
        }
        

        try {
            
            //si no tiene equipo le ponemos el atleti por defecto porque es el mejor equipo
            if (usuario_equipo == null) {
                nu_usuario.setUserEquipo("Atletico de Marid");
               
            }

            //Guardamos el nuevo correo y la nueva contraseña tmb en su respectiva base de datos
            UserPassword nu_pass = new UserPassword();
            nu_pass.setUserCorreo(usuario_correo);
            nu_pass.setUserPassword(usuario_password);
            nu_pass = daoUserPas.save(nu_pass);

            Usuario saved_usuario = daoUser.save(nu_usuario);
    
            return new ResponseEntity<>(saved_usuario, HttpStatus.CREATED);
        } 
        catch (DataAccessException ex) {
            
            System.err.println(ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //metodo para verificar contraseñas
    private boolean verificarContraseña(String contraseñaIngresada, String contraseñaAlmacenada) {
        // Aquí, asumiremos que las contraseñas son iguales para simplificar
        return contraseñaIngresada.equals(contraseñaAlmacenada);
    }

    @PostMapping(path = "/Adminlogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> AdminLogin(@RequestBody AdminPassword admin) {
        
        String correo_admin = admin.getAdminCorreo();
        String password_admin = admin.getAdminPassword();

        // Buscar el admin por correo
        Optional<AdminPassword> adminEncontrado = daoAdminPas.findByCorreo(correo_admin);

       // Verificar si el admin existe
        if (adminEncontrado.isEmpty()) {
            String msg = "Admin '" + correo_admin + "' does not exist";
            System.err.println(msg);
            return new ResponseEntity<>(generateError("ADMIN_DOES_NOT_EXIST", msg), HttpStatus.BAD_REQUEST);
        }
        // Obtener la contraseña almacenada
        String storedPassword = adminEncontrado.get().getAdminPassword();

        // Verificar la contraseña utilizando un método seguro, como bcrypt
        if (!verificarContraseña(password_admin, storedPassword)) {
            String msg = "Incorrect password";
            System.err.println(msg);
            return new ResponseEntity<>(generateError("WRONG_PASSWORD", msg), HttpStatus.BAD_REQUEST);
        }
        try {

            //generar token 
            String authToken = "Authentication successful";
            return new ResponseEntity<>(authToken, HttpStatus.ACCEPTED);
        } 
        catch (DataAccessException ex) {
            
            System.err.println(ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(path = "/Userlogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> UserLogin(@RequestBody UserPassword user) {
        
        String correo_user = user.getUserCorreo();
        String password_user = user.getUserPassword();

        // Buscar el admin por correo
        Optional<UserPassword> userEncontrado = daoUserPas.findByCorreo(correo_user);

       // Verificar si el admin existe
        if (userEncontrado.isEmpty()) {
            String msg = "Admin '" + correo_user + "' does not exist";
            System.err.println(msg);
            return new ResponseEntity<>(generateError("ADMIN_DOES_NOT_EXIST", msg), HttpStatus.BAD_REQUEST);
        }
        // Obtener la contraseña almacenada
        String storedPassword = userEncontrado.get().getUserPassword();

        // Verificar la contraseña utilizando un método seguro, como bcrypt
        if (!verificarContraseña(password_user, storedPassword)) {
            String msg = "Incorrect password";
            System.err.println(msg);
            return new ResponseEntity<>(generateError("WRONG_PASSWORD", msg), HttpStatus.BAD_REQUEST);
        }
        try {

            //generar token 
            String authToken = "Authentication successful";
            return new ResponseEntity<>(authToken, HttpStatus.ACCEPTED);
        } 
        catch (DataAccessException ex) {
            
            System.err.println(ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    
 

}
