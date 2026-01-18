package com.proyecto.horas.modelo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entidad que representa un rol dentro del sistema (por ejemplo: ADMIN, USER).
 */
@Entity
@Table(name = "roles")
public class Rol {

    @Id
    private Long rolId;
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    private Set<UsuarioRol> usuarioRoles= new HashSet<>();

    /**
     * Constructor vacío necesario para JPA.
     */
    public Rol(){}

    /**
     * Constructor para crear un rol.
     *
     * @param rolId
     * @param nombre nombre del rol.
     */
    public Rol(Long rolId, String nombre) {
        this.rolId = rolId;
        this.nombre = nombre;
    }

    // Getters y setters
    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }
}
