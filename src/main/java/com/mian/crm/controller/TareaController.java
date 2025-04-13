package com.mian.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mian.crm.dto.TareaDTO;
import com.mian.crm.dto.TareaMergeDTO;
import com.mian.crm.service.TareaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/listar")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> listarTareas() {
        return ResponseEntity.ok(tareaService.findAllTareas());
    }

    @GetMapping("/cliente/{idCliente}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> listarTareasPorClienteId(@PathVariable Long idCliente) {
        List<TareaDTO> tareas = tareaService.findTareasByClienteId(idCliente);
        if (tareas == null) {
            return ResponseEntity.badRequest().body("El cliente no existe");
        }
        if (tareas.isEmpty()) {
            return ResponseEntity.badRequest().body("No hay tareas para este cliente");
        }
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/empleado/{idEmpleado}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> listarTareasPorEmpleadoId(@PathVariable Long idEmpleado) {
        List<TareaDTO> tareas = tareaService.findTareasByEmpleadoId(idEmpleado);
        if (tareas == null) {
            return ResponseEntity.badRequest().body("El empleado no existe");
        }
        if (tareas.isEmpty()) {
            return ResponseEntity.badRequest().body("No hay tareas para este empleado");
        }
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> buscarTareaPorId(@PathVariable Long id) {
        TareaDTO tarea = tareaService.findTareaById(id);
        if (tarea == null) {
            return ResponseEntity.badRequest().body("La tarea no existe");
        }
        return ResponseEntity.ok(tarea);
    }

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> crearTarea(@Valid @RequestBody TareaMergeDTO tarea, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }
        return ResponseEntity.ok(tareaService.saveTarea(tarea));
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> actualizarTarea(@Valid @RequestBody TareaMergeDTO tarea, @PathVariable Long id,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }
        if (tareaService.findTareaById(id) == null) {
            return ResponseEntity.badRequest().body("La tarea no existe");
        }
        return ResponseEntity.ok(tareaService.updateTarea(tarea, id));

    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> eliminarTarea(@PathVariable Long id) {
        int resultado = tareaService.deleteTarea(id);
        if (resultado == 0) {
            return ResponseEntity.badRequest().body("La tarea no existe");
        }
        return ResponseEntity.ok("Tarea eliminada con éxito");
    }

}