package com.proyecto.horas.servicio;

import com.proyecto.horas.modelo.Rol;
import java.util.List;

/**
 * Interfaz/Servicio para gestionar roles.
 */
public interface RolService {

    /**
     * Guarda un rol.
     *
     * @param rol objeto a guardar.
     * @return rol guardado.
     */
    Rol guardarRol(Rol rol);

    /**
     * Busca un rol por su nombre.
     *
     * @param nombre nombre del rol.
     * @return rol encontrado o null si no existe.
     */
    Rol obtenerRolPorNombre(String nombre);

    /**
     * Devuelve todos los roles registrados.
     *
     * @return lista completa de roles.
     */
    List<Rol> obtenerTodosLosRoles();
}
