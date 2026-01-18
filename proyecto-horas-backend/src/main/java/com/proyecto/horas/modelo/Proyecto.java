package com.proyecto.horas.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entidad que representa un proyecto dentro del sistema.
 */
@Entity
@Table(name = "proyectos")
public class Proyecto {
    @Id
    private String idProyecto;
    private String nombreProyecto;
    private String descripcionProyecto;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UsuarioProyecto> usuarioProyectos = new HashSet<>();

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<HorasTrabajadas> horasTrabajadas = new HashSet<>();

    /**
     * Constructor vacío necesario para JPA.
     */
    public Proyecto() {
    }

    /**
     * Constructor para crear un proyecto.
     *
     * @param idProyecto
     * @param nombreProyecto
     * @param descripcionProyecto
     */
    public Proyecto(String idProyecto, String nombreProyecto, String descripcionProyecto) {
        this.idProyecto=idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.descripcionProyecto = descripcionProyecto;
    }

    // Getters y setters
    public Set<HorasTrabajadas> getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Set<HorasTrabajadas> horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Set<UsuarioProyecto> getUsuarioProyectos() {
        return usuarioProyectos;
    }

    public void setUsuarioProyectos(Set<UsuarioProyecto> usuarioProyectos) {
        this.usuarioProyectos = usuarioProyectos;
    }
}
