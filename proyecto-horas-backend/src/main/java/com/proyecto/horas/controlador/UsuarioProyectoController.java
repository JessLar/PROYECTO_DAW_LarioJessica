package com.proyecto.horas.controlador;

import com.proyecto.horas.modelo.UsuarioProyecto;
import com.proyecto.horas.servicio.UsuarioProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar la relación entre usuarias y proyectos.
 */
@RestController
@RequestMapping("/usuario-proyectos")
@CrossOrigin("*")
public class UsuarioProyectoController {

    @Autowired
    private UsuarioProyectoService usuarioProyectoService;

    /**
     * Asigna una usuaria a un proyecto.
     *
     * @param usuarioProyecto relación usuario-proyecto.
     * @return relación creada.
     */
    @PostMapping("/")
    public UsuarioProyecto asignarProyecto(@RequestBody UsuarioProyecto usuarioProyecto) {
        return usuarioProyectoService.guardarUsuarioProyecto(usuarioProyecto);
    }

    /**
     * Obtiene todos los proyectos asignados a una usuaria.
     *
     * @param usuarioId
     * @return lista de proyectos por usuaria.
     */
    @GetMapping("/usuario/{usuarioId}")
    public List<UsuarioProyecto> obtenerProyectosPorUsuario(@PathVariable Long usuarioId) {
        return usuarioProyectoService.obtenerProyectosPorUsuario(usuarioId);
    }
}
