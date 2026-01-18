package com.proyecto.horas.repositorio;

import com.proyecto.horas.modelo.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase/Repositorio para gestionar proyectos.
 */
@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, String> {
    List<Proyecto> findByUsuarioProyectos_Usuario_Id(Long usuarioId);

    void deleteByIdProyecto(String idProyecto);
}