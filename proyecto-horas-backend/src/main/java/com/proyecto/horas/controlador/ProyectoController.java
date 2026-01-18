package com.proyecto.horas.controlador;

import com.proyecto.horas.modelo.Proyecto;
import com.proyecto.horas.servicio.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar proyectos.
 */
@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    /**
     * Devuelve todos los proyectos.
     */
    @GetMapping("/")
    public List<Proyecto> obtenerTodosLosProyectos() {
        return proyectoService.obtenerTodosLosProyectos();
    }

    /**
     * Crea un nuevo proyecto.
     */
    @PostMapping("/")
    public ResponseEntity<Proyecto> agregarProyecto(@RequestBody Proyecto proyecto) {
        return ResponseEntity.ok(proyectoService.agregarProyecto(proyecto));
    }

    /**
     * Edita un proyecto existente.
     */
    @PutMapping("/")
    public ResponseEntity<Proyecto> editarProyecto(@RequestBody Proyecto proyecto) {
        return ResponseEntity.ok(proyectoService.editarProyecto(proyecto));
    }

    /**
     * Elimina un proyecto por id.
     */
    @DeleteMapping("/{idProyecto}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable String idProyecto) {
        proyectoService.eliminarProyecto(idProyecto);
        return ResponseEntity.noContent().build();
    }
}
