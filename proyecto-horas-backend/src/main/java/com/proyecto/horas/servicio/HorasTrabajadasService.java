package com.proyecto.horas.servicio;

import com.proyecto.horas.modelo.HorasTrabajadas;
import java.util.List;

/**
 * Interfaz/Servicio para gestionar horas trabajadas.
 */
public interface HorasTrabajadasService {

    /**
     * Guarda un nuevo registro de horas trabajadas.
     *
     * @param horasTrabajadas objeto con los datos a guardar.
     * @return registro guardado con totalHoras calculado.
     */
    HorasTrabajadas guardarHorasTrabajadas(HorasTrabajadas horasTrabajadas);

    /**
     * Busca un registro por su id.
     *
     * @param id identificador del registro.
     * @return registro encontrado o null si no existe.
     */
    HorasTrabajadas obtenerHorasTrabajadas(Long id);

    /**
     * Devuelve todos los registros de horas trabajadas.
     *
     * @return lista completa.
     */
    List<HorasTrabajadas> obtenerTodasLasHorasTrabajadas();

    /**
     * Devuelve las horas trabajadas por una usuaria.
     *
     * @param usuarioId id de la usuaria.
     * @return lista de registros.
     */
    List<HorasTrabajadas> obtenerHorasTrabajadasPorUsuario(Long usuarioId);

    /**
     * Devuelve las horas trabajadas en un proyecto.
     *
     * @param proyectoId id del proyecto.
     * @return lista de registros.
     */
    List<HorasTrabajadas> obtenerHorasTrabajadasPorProyecto(String proyectoId);

    /**
     * Devuelve las horas trabajadas por una usuaria en un proyecto concreto.
     *
     * @param usuarioId id de la usuaria.
     * @param proyectoId id del proyecto.
     * @return lista de registros.
     */
    List<HorasTrabajadas> obtenerHorasTrabajadasPorUsuarioYProyecto(Long usuarioId, String proyectoId);

    /**
     * Elimina un registro por su id.
     *
     * @param id identificador del registro.
     */
    void eliminarHorasTrabajadas(Long id);

    /**
     * Actualiza un registro existente.
     *
     * @param horasTrabajadas objeto con los datos actualizados.
     * @return registro actualizado.
     */
    HorasTrabajadas actualizarHorasTrabajadas(HorasTrabajadas horasTrabajadas);

    /**
     * Devuelve las horas filtradas por usuaria, proyecto y mes.
     * Si algún parámetro es null, no se aplica ese filtro.
     *
     * @param usuarioId id de la usuaria (opcional).
     * @param proyectoId id del proyecto (opcional).
     * @param mes número de mes (opcional).
     * @return lista filtrada.
     */
    List<HorasTrabajadas> obtenerHorasPorFiltro(Long usuarioId, String proyectoId, Integer mes);
}
