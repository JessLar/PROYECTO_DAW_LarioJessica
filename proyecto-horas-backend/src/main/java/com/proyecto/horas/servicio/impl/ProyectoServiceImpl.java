package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.modelo.Proyecto;
import com.proyecto.horas.repositorio.ProyectoRepository;
import com.proyecto.horas.servicio.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Clase de implementación del servicio de proyectos.
 */
@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Override
    public List<Proyecto> obtenerTodosLosProyectos() {
        return proyectoRepository.findAll();
    }

    @Override
    public Proyecto agregarProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public Proyecto editarProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void eliminarProyecto(String idProyecto) {
        proyectoRepository.deleteById(idProyecto);
    }
}
