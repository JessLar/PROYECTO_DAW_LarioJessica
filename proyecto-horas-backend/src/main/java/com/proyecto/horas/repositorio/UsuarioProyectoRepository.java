package com.proyecto.horas.repositorio;

import com.proyecto.horas.modelo.UsuarioProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase/Repositorio para gestionar la relación usuario-proyecto.
 */
@Repository
public interface UsuarioProyectoRepository extends JpaRepository<UsuarioProyecto, Long> {
    public List<UsuarioProyecto> findByUsuarioId(Long usuarioId);
}
