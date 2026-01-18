package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.modelo.HorasTrabajadas;
import com.proyecto.horas.repositorio.HorasTrabajadasRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HorasTrabajadasServiceImplTest {

    @Mock
    private HorasTrabajadasRepository horasTrabajadasRepository;

    @InjectMocks
    private HorasTrabajadasServiceImpl horasTrabajadasService;

    @Test
    void guardarHorasTrabajadas() {
        HorasTrabajadas horasTrabajadas = mock(HorasTrabajadas.class);

        when(horasTrabajadas.calcularTotalHoras()).thenReturn(8.5);
        when(horasTrabajadasRepository.save(horasTrabajadas)).thenReturn(horasTrabajadas);

        HorasTrabajadas resultado = horasTrabajadasService.guardarHorasTrabajadas(horasTrabajadas);

        verify(horasTrabajadas).setTotalHoras(8.5);
        verify(horasTrabajadasRepository).save(horasTrabajadas);
        assertEquals(horasTrabajadas, resultado);
    }

    @Test
    void obtenerHorasTrabajadas() {
        HorasTrabajadas horasTrabajadas = new HorasTrabajadas();
        when(horasTrabajadasRepository.findById(1L)).thenReturn(Optional.of(horasTrabajadas));

        HorasTrabajadas resultado = horasTrabajadasService.obtenerHorasTrabajadas(1L);

        assertNotNull(resultado);
        verify(horasTrabajadasRepository).findById(1L);
    }

    @Test
    void obtenerTodasLasHorasTrabajadas() {
        List<HorasTrabajadas> lista = Arrays.asList(new HorasTrabajadas(), new HorasTrabajadas());
        when(horasTrabajadasRepository.findAll()).thenReturn(lista);

        List<HorasTrabajadas> resultado = horasTrabajadasService.obtenerTodasLasHorasTrabajadas();

        assertEquals(2, resultado.size());
        verify(horasTrabajadasRepository).findAll();
    }

    @Test
    void obtenerHorasTrabajadasPorUsuario() {
        List<HorasTrabajadas> lista = Arrays.asList(new HorasTrabajadas());
        when(horasTrabajadasRepository.findByUsuario_Id(10L)).thenReturn(lista);

        List<HorasTrabajadas> resultado = horasTrabajadasService.obtenerHorasTrabajadasPorUsuario(10L);

        assertEquals(1, resultado.size());
        verify(horasTrabajadasRepository).findByUsuario_Id(10L);
    }

    @Test
    void obtenerHorasTrabajadasPorProyecto() {
        List<HorasTrabajadas> lista = Arrays.asList(new HorasTrabajadas());
        when(horasTrabajadasRepository.findByProyecto_IdProyecto("P1")).thenReturn(lista);

        List<HorasTrabajadas> resultado = horasTrabajadasService.obtenerHorasTrabajadasPorProyecto("P1");

        assertEquals(1, resultado.size());
        verify(horasTrabajadasRepository).findByProyecto_IdProyecto("P1");
    }

    @Test
    void obtenerHorasTrabajadasPorUsuarioYProyecto() {
        List<HorasTrabajadas> lista = Arrays.asList(new HorasTrabajadas());
        when(horasTrabajadasRepository.findByUsuario_IdAndProyecto_IdProyecto(5L, "P1"))
                .thenReturn(lista);

        List<HorasTrabajadas> resultado =
                horasTrabajadasService.obtenerHorasTrabajadasPorUsuarioYProyecto(5L, "P1");

        assertEquals(1, resultado.size());
        verify(horasTrabajadasRepository).findByUsuario_IdAndProyecto_IdProyecto(5L, "P1");
    }

    @Test
    void eliminarHorasTrabajadas() {
        horasTrabajadasService.eliminarHorasTrabajadas(5L);
        verify(horasTrabajadasRepository).deleteById(5L);
    }

    @Test
    void actualizarHorasTrabajadas() {
        HorasTrabajadas horasTrabajadas = mock(HorasTrabajadas.class);

        when(horasTrabajadas.calcularTotalHoras()).thenReturn(7.25);
        when(horasTrabajadasRepository.save(horasTrabajadas)).thenReturn(horasTrabajadas);

        HorasTrabajadas resultado = horasTrabajadasService.actualizarHorasTrabajadas(horasTrabajadas);

        verify(horasTrabajadas).setTotalHoras(7.25);
        verify(horasTrabajadasRepository).save(horasTrabajadas);
        assertEquals(horasTrabajadas, resultado);
    }

    @Test
    void obtenerHorasPorFiltro() {
        List<HorasTrabajadas> lista = Arrays.asList(new HorasTrabajadas());
        when(horasTrabajadasRepository.findHorasTrabajadasPorFiltro(1L, "P1", 3))
                .thenReturn(lista);

        List<HorasTrabajadas> resultado =
                horasTrabajadasService.obtenerHorasPorFiltro(1L, "P1", 3);

        assertEquals(1, resultado.size());
        verify(horasTrabajadasRepository).findHorasTrabajadasPorFiltro(1L, "P1", 3);
    }
}
