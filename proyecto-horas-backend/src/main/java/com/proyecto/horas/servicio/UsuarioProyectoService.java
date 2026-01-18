package com.proyecto.horas.servicio;

import com.proyecto.horas.modelo.UsuarioProyecto;
import java.util.List;

/**
 * Interfaz/Servicio para gestionar la relación entre usuarias y proyectos.
 */
public interface UsuarioProyectoService {

    /**
     * Guarda una asignación usuaria–proyecto.
     *
     * @param usuarioProyecto relación a guardar.
     * @return relación guardada.
     */
    UsuarioProyecto guardarUsuarioProyecto(UsuarioProyecto usuarioProyecto);

    /**
     * Devuelve los proyectos asignados a una usuaria.
     *
     * @param usuarioId
     * @return lista de relaciones usuario–proyecto.
     */
    List<UsuarioProyecto> obtenerProyectosPorUsuario(Long usuarioId);
}
