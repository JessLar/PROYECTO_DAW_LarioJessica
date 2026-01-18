package com.proyecto.horas.repositorio;

import com.proyecto.horas.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Clase/Repositorio para gestionar roles.
 */
public interface RolRepository extends JpaRepository<Rol, Long> {
    public Rol findByNombre(String nombre);
}
