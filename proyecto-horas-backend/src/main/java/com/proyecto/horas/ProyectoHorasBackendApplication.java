package com.proyecto.horas;

import com.proyecto.horas.excepciones.UsuarioFoundException;
import com.proyecto.horas.modelo.Rol;
import com.proyecto.horas.modelo.Usuario;
import com.proyecto.horas.modelo.UsuarioRol;
import com.proyecto.horas.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProyectoHorasBackendApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoHorasBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*try{
			Usuario usuario = new Usuario("Ismael","Bernad","ibernad",bCryptPasswordEncoder.encode("987654"),"ibernad@gmail.com","foto_user4.png");

			Rol rol = new Rol(2L, "USER");

			Set<UsuarioRol> usuarioRoles = new HashSet<>();
			UsuarioRol usuarioRol = new UsuarioRol(usuario, rol);
			usuarioRoles.add(usuarioRol);

			Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);
			System.out.println(usuarioGuardado.getUsername());


		}catch (UsuarioFoundException e){

			e.printStackTrace();
		}*/
	}
}
