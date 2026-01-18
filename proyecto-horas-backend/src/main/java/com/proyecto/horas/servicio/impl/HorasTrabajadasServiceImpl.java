package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.modelo.HorasTrabajadas;
import com.proyecto.horas.repositorio.HorasTrabajadasRepository;
import com.proyecto.horas.servicio.HorasTrabajadasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Clase de implementación del servicio de horas trabajadas.
 */
@Service
public class HorasTrabajadasServiceImpl implements HorasTrabajadasService {

    @Autowired
    private HorasTrabajadasRepository horasTrabajadasRepository;

    /**
     * Guarda el registro y calcula el total de horas antes de persistir.
     */
    @Override
    public HorasTrabajadas guardarHorasTrabajadas(HorasTrabajadas horasTrabajadas) {
        horasTrabajadas.setTotalHoras(horasTrabajadas.calcularTotalHoras());
        return horasTrabajadasRepository.save(horasTrabajadas);
    }

    @Override
    public HorasTrabajadas obtenerHorasTrabajadas(Long id) {
        return horasTrabajadasRepository.findById(id).orElse(null);
    }

    @Override
    public List<HorasTrabajadas> obtenerTodasLasHorasTrabajadas() {
        return horasTrabajadasRepository.findAll();
    }

    @Override
    public List<HorasTrabajadas> obtenerHorasTrabajadasPorUsuario(Long usuarioId) {
        return horasTrabajadasRepository.findByUsuario_Id(usuarioId);
    }

    @Override
    public List<HorasTrabajadas> obtenerHorasTrabajadasPorProyecto(String proyectoId) {
        return horasTrabajadasRepository.findByProyecto_IdProyecto(proyectoId);
    }

    @Override
    public List<HorasTrabajadas> obtenerHorasTrabajadasPorUsuarioYProyecto(Long usuarioId, String proyectoId) {
        return horasTrabajadasRepository.findByUsuario_IdAndProyecto_IdProyecto(usuarioId, proyectoId);
    }

    @Override
    public void eliminarHorasTrabajadas(Long id) {
        horasTrabajadasRepository.deleteById(id);
    }

    /**
     * Reutiliza el método guardar para actualizar el registro.
     */
    @Override
    public HorasTrabajadas actualizarHorasTrabajadas(HorasTrabajadas horasTrabajadas) {
        return guardarHorasTrabajadas(horasTrabajadas);
    }

    @Override
    public List<HorasTrabajadas> obtenerHorasPorFiltro(Long usuarioId, String proyectoId, Integer mes) {
        return horasTrabajadasRepository.findHorasTrabajadasPorFiltro(usuarioId, proyectoId, mes);
    }
}
