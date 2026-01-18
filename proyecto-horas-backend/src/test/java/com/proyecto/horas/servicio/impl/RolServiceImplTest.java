package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.modelo.Rol;
import com.proyecto.horas.repositorio.RolRepository;
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
class RolServiceImplTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolServiceImpl rolService;

    @Test
    void guardarRol() {
        Rol rol = new Rol();
        when(rolRepository.save(rol)).thenReturn(rol);

        Rol resultado = rolService.guardarRol(rol);

        assertEquals(rol, resultado);
        verify(rolRepository).save(rol);
    }

    @Test
    void obtenerRolPorNombre() {
        Rol rol = new Rol();
        when(rolRepository.findByNombre("ADMIN")).thenReturn(rol);

        Rol resultado = rolService.obtenerRolPorNombre("ADMIN");

        assertEquals(rol, resultado);
        verify(rolRepository).findByNombre("ADMIN");
    }

    @Test
    void obtenerTodosLosRoles() {
        List<Rol> roles = Arrays.asList(new Rol(), new Rol());
        when(rolRepository.findAll()).thenReturn(roles);

        List<Rol> resultado = rolService.obtenerTodosLosRoles();

        assertEquals(2, resultado.size());
        verify(rolRepository).findAll();
    }
}
