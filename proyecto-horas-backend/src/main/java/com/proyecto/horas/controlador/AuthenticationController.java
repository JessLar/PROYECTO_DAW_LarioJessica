package com.proyecto.horas.controlador;

import com.proyecto.horas.configuraciones.JwtUtils;
import com.proyecto.horas.excepciones.UsuarioNotFoundException;
import com.proyecto.horas.modelo.JwtRequest;
import com.proyecto.horas.modelo.JwtResponse;
import com.proyecto.horas.modelo.Usuario;
import com.proyecto.horas.servicio.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Controlador encargado de la autenticación y generación de tokens JWT.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Genera un token JWT a partir de las credenciales recibidas.
     *
     * @param jwtRequest objeto con username y password.
     * @return JwtResponsetoken token JWT si las credenciales son válidas.
     */
    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UsuarioNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Usuario no encontrado.");
        }
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
            String token = this.jwtUtils.generateToken(userDetails);
            System.out.println("Token" + token);
            return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * Valida las credenciales de la usuaria.
     *
     * @param username
     * @param password
     */
    private void autenticar(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException disabledException){
            throw new Exception("Usuario deshabilitado" + disabledException.getMessage());
        }catch (BadCredentialsException badCredentialsException){
            throw new Exception("Credenciales invalidas" + badCredentialsException.getMessage());
        }
    }
    /**
     * Devuelve la usuaria actualmente autenticada.
     *
     * @param principal usuario autenticado.
     * @return datos de la usuaria.
     */
    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual (Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }

}
