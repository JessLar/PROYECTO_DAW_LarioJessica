package com.proyecto.horas.servicio.impl;

import com.proyecto.horas.modelo.Rol;
import com.proyecto.horas.repositorio.RolRepository;
import com.proyecto.horas.servicio.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Clase de implementación del servicio de roles.
 */
@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol obtenerRolPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }

    @Override
    public List<Rol> obtenerTodosLosRoles() {
        return rolRepository.findAll();
    }
}
