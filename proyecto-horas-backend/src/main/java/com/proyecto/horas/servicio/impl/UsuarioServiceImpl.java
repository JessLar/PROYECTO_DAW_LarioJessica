package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.excepciones.UsuarioFoundException;
import com.proyecto.horas.modelo.Usuario;
import com.proyecto.horas.modelo.UsuarioRol;
import com.proyecto.horas.repositorio.RolRepository;
import com.proyecto.horas.repositorio.UsuarioRepository;
import com.proyecto.horas.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Clase de implementación del servicio de usuarias.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    /**
     * Guarda una usuaria nueva si no existe.
     * También persiste los roles asociados.
     */
    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {

        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());

        if (usuarioLocal != null) {
            throw new UsuarioFoundException("El usuario ya existe en la base de datos");
        }

        for (UsuarioRol usuarioRol : usuarioRoles) {
            rolRepository.save(usuarioRol.getRol());
        }

        usuario.getUsuarioRoles().addAll(usuarioRoles);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Actualiza una usuaria si existe.
     */
    @Override
    public Usuario editarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

        if (usuarioExistente != null) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }

        return null;
    }
}
