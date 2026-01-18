package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.excepciones.UsuarioFoundException;
import com.proyecto.horas.modelo.Rol;
import com.proyecto.horas.modelo.Usuario;
import com.proyecto.horas.modelo.UsuarioRol;
import com.proyecto.horas.repositorio.RolRepository;
import com.proyecto.horas.repositorio.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void guardarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("jess");

        Rol rol = new Rol();
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setRol(rol);

        Set<UsuarioRol> roles = Set.of(usuarioRol);

        when(usuarioRepository.findByUsername("jess")).thenReturn(null);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.guardarUsuario(usuario, roles);

        verify(rolRepository).save(rol);
        verify(usuarioRepository).save(usuario);
        assertEquals(usuario, resultado);
    }

    @Test
    void guardarUsuario_usuarioYaExiste() {
        Usuario usuario = new Usuario();
        usuario.setUsername("jess");

        when(usuarioRepository.findByUsername("jess")).thenReturn(usuario);

        assertThrows(UsuarioFoundException.class,
                () -> usuarioService.guardarUsuario(usuario, new HashSet<>()));

        verify(usuarioRepository).findByUsername("jess");
    }

    @Test
    void obtenerUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findByUsername("jess")).thenReturn(usuario);

        Usuario resultado = usuarioService.obtenerUsuario("jess");

        assertEquals(usuario, resultado);
        verify(usuarioRepository).findByUsername("jess");
    }

    @Test
    void eliminarUsuario() {
        usuarioService.eliminarUsuario(5L);
        verify(usuarioRepository).deleteById(5L);
    }

    @Test
    void obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioService.obtenerTodosLosUsuarios();

        assertEquals(2, resultado.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void editarUsuario() {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(5L);

        Usuario usuarioNuevo = new Usuario();

        when(usuarioRepository.findById(5L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.save(usuarioNuevo)).thenReturn(usuarioNuevo);

        Usuario resultado = usuarioService.editarUsuario(5L, usuarioNuevo);

        assertEquals(usuarioNuevo, resultado);
        verify(usuarioRepository).save(usuarioNuevo);
    }

    @Test
    void editarUsuario_noExiste() {
        Usuario usuarioNuevo = new Usuario();

        when(usuarioRepository.findById(5L)).thenReturn(Optional.empty());

        Usuario resultado = usuarioService.editarUsuario(5L, usuarioNuevo);

        assertNull(resultado);
        verify(usuarioRepository).findById(5L);
    }
}
