package com.proyecto.horas.modelo;

import org.springframework.security.core.GrantedAuthority;

/**
 * Representa una autoridad/rol dentro de Spring Security.
 * Se usa para indicar qué permisos tiene una usuaria.
 */
public class Authority implements GrantedAuthority {

    private String authority;

    /**
     * Crea una autoridad con el nombre indicado.
     *
     * @param authority nombre del permiso o rol.
     */
    public Authority (String authority){

        this.authority=authority;
    }

    /** Crea una autoridad con el nombre indicado.
     *
     * @return nombre del permiso o rol.
     */
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
