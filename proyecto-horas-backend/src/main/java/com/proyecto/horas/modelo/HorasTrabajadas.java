package com.proyecto.horas.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad que representa un registro de horas trabajadas por una usuaria en un proyecto.
 */
@Entity
@Table(name = "horas_trabajadas")
public class HorasTrabajadas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Proyecto proyecto;

    private LocalDate fechaInicio;
    private double numeroHoras;

    @Enumerated(EnumType.STRING)
    private TipoHoras tipoHoras;
    private double totalHoras;

    /**
     * Constructor vacío necesario para JPA.
     */
    public HorasTrabajadas() {}

    /**
     * Calcula el total de horas aplicando el factor según el tipo.
     *
     * @return total de horas ya multiplicadas por el factor correspondiente.
     */
    public double calcularTotalHoras() {
        double factor;
        switch (tipoHoras) {
            case EXTRAS:
                factor = 1.5;
                break;
            case FESTIVAS:
                factor = 2.0;
                break;
            case ORDINARIAS:
            default:
                factor = 1.0;
        }
        return numeroHoras * factor;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public double getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(double numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public TipoHoras getTipoHoras() {
        return tipoHoras;
    }

    public void setTipoHoras(TipoHoras tipoHoras) {
        this.tipoHoras = tipoHoras;
    }

    public double getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(double totalHoras) {
        this.totalHoras = totalHoras;
    }
}

/**
 * Tipos de horas que puede registrar una usuaria.
 */
enum TipoHoras {
    ORDINARIAS,
    EXTRAS,
    FESTIVAS
}

