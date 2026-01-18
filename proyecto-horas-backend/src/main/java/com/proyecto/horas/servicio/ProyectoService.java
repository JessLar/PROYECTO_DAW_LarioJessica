package com.proyecto.horas.servicio;

import com.proyecto.horas.modelo.Proyecto;
import java.util.List;

/**
 * Interfaz/Servicio para gestionar proyectos.
 */
public interface ProyectoService {

    /**
     * Devuelve todos los proyectos registrados.
     *
     * @return lista completa de proyectos.
     */
    List<Proyecto> obtenerTodosLosProyectos();

    /**
     * Guarda un nuevo proyecto.
     *
     * @param proyecto datos del proyecto a guardar.
     * @return proyecto guardado.
     */
    Proyecto agregarProyecto(Proyecto proyecto);

    /**
     * Actualiza un proyecto existente.
     *
     * @param proyecto datos actualizados.
     * @return proyecto actualizado.
     */
    Proyecto editarProyecto(Proyecto proyecto);

    /**
     * Elimina un proyecto por su id.
     *
     * @param idProyecto
     */
    void eliminarProyecto(String idProyecto);
}
