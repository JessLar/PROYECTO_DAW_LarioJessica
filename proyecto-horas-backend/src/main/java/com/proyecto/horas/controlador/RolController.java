package com.proyecto.horas.controlador;

import com.proyecto.horas.modelo.Rol;
import com.proyecto.horas.servicio.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar roles.
 */
@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class RolController {

    @Autowired
    private RolService rolService;

    /**
     * Devuelve todos los roles del sistema.
     */
    @GetMapping("/")
    public List<Rol> obtenerTodosLosRoles() {
        return rolService.obtenerTodosLosRoles();
    }
}
