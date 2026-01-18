package com.proyecto.horas.repositorio;

import com.proyecto.horas.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Clase/Repositorio para gestionar usuarias.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByUsername(String username);
}
