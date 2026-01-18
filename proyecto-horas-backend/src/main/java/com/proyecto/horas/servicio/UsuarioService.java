package com.proyecto.horas.servicio;

import com.proyecto.horas.modelo.Usuario;
import com.proyecto.horas.modelo.UsuarioRol;
import java.util.List;
import java.util.Set;

/**
 * Interfaz/Servicio para gestionar usuarias.
 */
public interface UsuarioService {

    /**
     * Guarda una nueva usuaria con sus roles asociados.
     *
     * @param usuario datos de la usuaria.
     * @param usuarioRoles roles asignados.
     * @return usuaria guardada.
     * @throws Exception si la usuaria ya existe.
     */
    Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    /**
     * Busca una usuaria por su username.
     *
     * @return usuaria encontrada o null si no existe.
     */
    Usuario obtenerUsuario(String username);

    /**
     * Elimina una usuaria por su id.
     */
    void eliminarUsuario(Long usuarioId);

    /**
     * Devuelve todas las usuarias registradas.
     *
     * @return listado completo de usuarias.
     */
    List<Usuario> obtenerTodosLosUsuarios();

    /**
     * Edita una usuaria existente.
     *
     * @param id id de la usuaria.
     * @param usuario datos actualizados.
     * @return usuaria actualizada o null si no existe.
     */
    Usuario editarUsuario(Long id, Usuario usuario);
}
