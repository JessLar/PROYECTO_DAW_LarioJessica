package com.proyecto.horas.controlador;

import com.proyecto.horas.modelo.HorasTrabajadas;
import com.proyecto.horas.servicio.HorasTrabajadasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar las horas trabajadas.
 */
@RestController
@RequestMapping("/horas-trabajadas")
@CrossOrigin(origins = "http://localhost:4200")
public class HorasTrabajadasController {

    @Autowired
    private HorasTrabajadasService horasTrabajadasService;

    /**
     * Guarda un nuevo registro de horas trabajadas.
     */
    @PostMapping("/")
    public ResponseEntity<HorasTrabajadas> guardarHorasTrabajadas(@RequestBody HorasTrabajadas horasTrabajadas) {
        return ResponseEntity.ok(horasTrabajadasService.guardarHorasTrabajadas(horasTrabajadas));
    }

    /**
     * Obtiene un registro por su id.
     */
    @GetMapping("/{id}")
    public HorasTrabajadas obtenerHorasTrabajadas(@PathVariable Long id) {
        return horasTrabajadasService.obtenerHorasTrabajadas(id);
    }

    /**
     * Obtiene todas las horas de una usuaria.
     */
    @GetMapping("/usuario/{usuarioId}")
    public List<HorasTrabajadas> obtenerHorasTrabajadasPorUsuario(@PathVariable Long usuarioId) {
        return horasTrabajadasService.obtenerHorasTrabajadasPorUsuario(usuarioId);
    }

    /**
     * Obtiene todas las horas de un proyecto.
     */
    @GetMapping("/proyecto/{proyectoId}")
    public List<HorasTrabajadas> obtenerHorasTrabajadasPorProyecto(@PathVariable String proyectoId) {
        return horasTrabajadasService.obtenerHorasTrabajadasPorProyecto(proyectoId);
    }

    /**
     * Obtiene horas filtradas por usuaria y proyecto.
     */
    @GetMapping("/usuario/{usuarioId}/proyecto/{proyectoId}")
    public List<HorasTrabajadas> obtenerHorasTrabajadasPorUsuarioYProyecto(
            @PathVariable Long usuarioId,
            @PathVariable String proyectoId) {
        return horasTrabajadasService.obtenerHorasTrabajadasPorUsuarioYProyecto(usuarioId, proyectoId);
    }

    /**
     * Obtiene todas las horas registradas.
     */
    @GetMapping("/")
    public List<HorasTrabajadas> obtenerTodasLasHorasTrabajadas() {
        return horasTrabajadasService.obtenerTodasLasHorasTrabajadas();
    }

    /**
     * Actualiza un registro de horas.
     */
    @PutMapping("/")
    public HorasTrabajadas actualizarHorasTrabajadas(@RequestBody HorasTrabajadas horasTrabajadas) {
        return horasTrabajadasService.actualizarHorasTrabajadas(horasTrabajadas);
    }

    /**
     * Elimina un registro por id.
     */
    @DeleteMapping("/{id}")
    public void eliminarHorasTrabajadas(@PathVariable Long id) {
        horasTrabajadasService.eliminarHorasTrabajadas(id);
    }

    /**
     * Filtra horas por usuaria, proyecto y mes.
     */
    @GetMapping("/usuario/{usuarioId}/proyecto/{proyectoId}/mes/{mes}")
    public List<HorasTrabajadas> obtenerHorasPorFiltro(
            @PathVariable Long usuarioId,
            @PathVariable String proyectoId,
            @PathVariable int mes) {
        return horasTrabajadasService.obtenerHorasPorFiltro(usuarioId, proyectoId, mes);
    }

    /**
     * Filtro general por parámetros opcionales.
     */
    @GetMapping("/filtro")
    public List<HorasTrabajadas> obtenerHorasPorFiltro(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) String proyectoId,
            @RequestParam(required = false) Integer mes) {
        return horasTrabajadasService.obtenerHorasPorFiltro(usuarioId, proyectoId, mes);
    }
}
