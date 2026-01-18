package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.modelo.UsuarioProyecto;
import com.proyecto.horas.repositorio.UsuarioProyectoRepository;
import com.proyecto.horas.servicio.UsuarioProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Clase de implementación del servicio de relación usuaria–proyecto.
 */
@Service
public class UsuarioProyectoServiceImpl implements UsuarioProyectoService {

    @Autowired
    private UsuarioProyectoRepository usuarioProyectoRepository;

    @Override
    public UsuarioProyecto guardarUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
        return usuarioProyectoRepository.save(usuarioProyecto);
    }

    @Override
    public List<UsuarioProyecto> obtenerProyectosPorUsuario(Long usuarioId) {
        return usuarioProyectoRepository.findByUsuarioId(usuarioId);
    }
}
