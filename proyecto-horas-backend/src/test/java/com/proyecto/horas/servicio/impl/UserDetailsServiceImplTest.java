package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.modelo.Usuario;
import com.proyecto.horas.repositorio.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsername() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findByUsername("jess")).thenReturn(usuario);

        var resultado = userDetailsService.loadUserByUsername("jess");

        assertEquals(usuario, resultado);
        verify(usuarioRepository).findByUsername("jess");
    }

    @Test
    void loadUserByUsername_usuarioNoExiste() {
        when(usuarioRepository.findByUsername("jess")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("jess"));

        verify(usuarioRepository).findByUsername("jess");
    }
}
