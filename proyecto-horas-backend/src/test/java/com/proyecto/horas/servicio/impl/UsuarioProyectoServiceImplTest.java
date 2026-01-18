package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.modelo.UsuarioProyecto;
import com.proyecto.horas.repositorio.UsuarioProyectoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioProyectoServiceImplTest {

    @Mock
    private UsuarioProyectoRepository usuarioProyectoRepository;

    @InjectMocks
    private UsuarioProyectoServiceImpl usuarioProyectoService;

    @Test
    void guardarUsuarioProyecto() {
        UsuarioProyecto usuarioProyecto = new UsuarioProyecto();
        when(usuarioProyectoRepository.save(usuarioProyecto)).thenReturn(usuarioProyecto);

        UsuarioProyecto resultado = usuarioProyectoService.guardarUsuarioProyecto(usuarioProyecto);

        assertEquals(usuarioProyecto, resultado);
        verify(usuarioProyectoRepository).save(usuarioProyecto);
    }

    @Test
    void obtenerProyectosPorUsuario() {
        List<UsuarioProyecto> proyectos = Arrays.asList(new UsuarioProyecto());
        when(usuarioProyectoRepository.findByUsuarioId(10L)).thenReturn(proyectos);

        List<UsuarioProyecto> resultado = usuarioProyectoService.obtenerProyectosPorUsuario(10L);

        assertEquals(1, resultado.size());
        verify(usuarioProyectoRepository).findByUsuarioId(10L);
    }
}
