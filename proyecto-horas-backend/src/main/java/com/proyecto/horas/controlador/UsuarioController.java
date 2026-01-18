package com.proyecto.horas.controlador;

import com.proyecto.horas.modelo.Rol;
import com.proyecto.horas.modelo.Usuario;
import com.proyecto.horas.modelo.UsuarioRol;
import com.proyecto.horas.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Controlador para gestionar usuarias del sistema.
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Crea una nueva usuaria con rol e imagen por defecto si no los expecificamos.
     *
     * @param usuario datos de la usuaria.
     * @return usuaria creada.
     */
    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    public Usuario guardarUsuario(
            @RequestPart("usuario") Usuario usuario,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws Exception {

        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path imagePath = Paths.get("src/main/resources/static/images", fileName);
            Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            usuario.setImagenPerfil(fileName);
        } else {
            usuario.setImagenPerfil("default.png");
        }

        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);

        return usuarioService.guardarUsuario(usuario, usuarioRoles);
    }


    /**
     * Obtiene una usuaria por su username.
     */
    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable String username) {
        return usuarioService.obtenerUsuario(username);
    }

    /**
     * Elimina una usuaria por id.
     */
    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable Long usuarioId) {
        usuarioService.eliminarUsuario(usuarioId);
    }

    /**
     * Devuelve todas las usuarias del sistema.
     */
    @GetMapping("/")
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    /**
     * Edita una usuaria existente.
     *
     * @param id      id de la usuaria.
     * @param usuario datos actualizados.
     * @return usuaria actualizada o 404 si no existe.
     */
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Usuario> editarUsuario(
            @PathVariable Long id,
            @RequestPart("usuario") Usuario usuario,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {

        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path imagePath = Paths.get("src/main/resources/static/images", fileName);
            Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            usuario.setImagenPerfil(fileName);
        }

        Usuario usuarioActualizado = usuarioService.editarUsuario(id, usuario);

        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        }

        return ResponseEntity.notFound().build();
    }
}
