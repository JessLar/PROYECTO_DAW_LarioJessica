package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.modelo.Usuario;
import com.proyecto.horas.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Clase de implementación del servicio de carga de usuarios de Spring Security.
 * Se utiliza durante la autenticación para obtener los datos de la usuaria.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carga una usuaria por su username.
     *
     * @return usuaria encontrada.
     * @throws UsernameNotFoundException si no existe.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }
}
