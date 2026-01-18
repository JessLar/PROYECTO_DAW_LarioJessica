package com.proyecto.horas.repositorio;

import com.proyecto.horas.modelo.HorasTrabajadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


import com.proyecto.horas.modelo.HorasTrabajadas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Clase/Repositorio para gestionar consultas sobre horas trabajadas.
 */
@Repository
public interface HorasTrabajadasRepository extends JpaRepository<HorasTrabajadas, Long> {
    List<HorasTrabajadas> findByUsuario_Id(Long usuarioId);
    List<HorasTrabajadas> findByProyecto_IdProyecto(String proyectoId);
    List<HorasTrabajadas> findByUsuario_IdAndProyecto_IdProyecto(Long usuarioId, String proyectoId);

    /**
     * Filtro dinámico por usuaria, proyecto y mes.
     * Si un parámetro es null, no se aplica ese filtro.
     */
    @Query("SELECT h FROM HorasTrabajadas h WHERE (:usuarioId IS NULL OR h.usuario.id = :usuarioId) AND (:proyectoId IS NULL OR h.proyecto.idProyecto = :proyectoId) AND (:mes IS NULL OR FUNCTION('MONTH', h.fechaInicio) = :mes)")
    List<HorasTrabajadas> findHorasTrabajadasPorFiltro(@Param("usuarioId") Long usuarioId, @Param("proyectoId") String proyectoId, @Param("mes") Integer mes);

}
