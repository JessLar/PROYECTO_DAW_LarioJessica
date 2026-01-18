package com.proyecto.horas.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String username;
    private String password;
    private boolean activado =true;
    private String imagenPerfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UsuarioProyecto> usuarioProyectos = new HashSet<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<HorasTrabajadas> horasTrabajadas = new HashSet<>();

    /**
     * Constructor vacío necesario para JPA.
     */
    public Usuario (){}

    /**
     * Constructor para crear una usuaria.
     *
     * @param nombre
     * @param apellido
     * @param username
     * @param password
     * @param email
     * @param imagenPerfil ruta o nombre del perfil.
     */
    public Usuario(String nombre, String apellido, String username, String password,  String email, String imagenPerfil) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.email = email;
        this.imagenPerfil = imagenPerfil;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {return password;}
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }


    public Set<UsuarioProyecto> getUsuarioProyectos() {
        return usuarioProyectos;
    }

    public void setUsuarioProyectos(Set<UsuarioProyecto> usuarioProyectos) {
        this.usuarioProyectos = usuarioProyectos;
    }

    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }

    public Set<HorasTrabajadas> getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Set<HorasTrabajadas> horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    /**
     * Devuelve las autoridades (roles) de la usuaria.
     *
     * @return colección de roles convertidos a GrantedAuthority.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autoridades=new HashSet<>();
        this.usuarioRoles.forEach(usuarioRol -> {
            autoridades.add(new Authority(usuarioRol.getRol().getNombre()));
        });
        return autoridades;
    }

    // Otros métodos de UserDetails
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
