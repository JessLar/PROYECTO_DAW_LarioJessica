package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.modelo.Proyecto;
import com.proyecto.horas.repositorio.ProyectoRepository;
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
class ProyectoServiceImplTest {

    @Mock
    private ProyectoRepository proyectoRepository;

    @InjectMocks
    private ProyectoServiceImpl proyectoService;

    @Test
    void obtenerTodosLosProyectos() {
        List<Proyecto> proyectos = Arrays.asList(new Proyecto(), new Proyecto());
        when(proyectoRepository.findAll()).thenReturn(proyectos);

        List<Proyecto> resultado = proyectoService.obtenerTodosLosProyectos();

        assertEquals(2, resultado.size());
        verify(proyectoRepository).findAll();
    }

    @Test
    void agregarProyecto() {
        Proyecto proyecto = new Proyecto();
        when(proyectoRepository.save(proyecto)).thenReturn(proyecto);

        Proyecto resultado = proyectoService.agregarProyecto(proyecto);

        assertEquals(proyecto, resultado);
        verify(proyectoRepository).save(proyecto);
    }

    @Test
    void editarProyecto() {
        Proyecto proyecto = new Proyecto();
        when(proyectoRepository.save(proyecto)).thenReturn(proyecto);

        Proyecto resultado = proyectoService.editarProyecto(proyecto);

        assertEquals(proyecto, resultado);
        verify(proyectoRepository).save(proyecto);
    }

    @Test
    void eliminarProyecto() {
        proyectoService.eliminarProyecto("P1");
        verify(proyectoRepository).deleteById("P1");
    }
}
