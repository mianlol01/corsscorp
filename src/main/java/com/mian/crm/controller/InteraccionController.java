package com.mian.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mian.crm.dto.InteraccionDTO;
import com.mian.crm.dto.InteraccionMergeDTO;
import com.mian.crm.service.InteraccionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/interacciones")
public class InteraccionController {

    @Autowired
    private InteraccionService interaccionService;

    @GetMapping("/listar")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> listarInteracciones() {
        return ResponseEntity.ok(interaccionService.findAllInteracciones());
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> buscarInteraccionPorId(@PathVariable Long id) {
        InteraccionDTO interaccion = interaccionService.findInteraccionById(id);
        if (interaccion == null) {
            return ResponseEntity.badRequest().body("La interaccion no existe");
        }
        return ResponseEntity.ok(interaccion);
    }

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> crearInteraccion(@Valid @RequestBody InteraccionMergeDTO interaccion,
            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList());
        }
        return ResponseEntity.ok(interaccionService.saveInteraccion(interaccion));

    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> actualizarInteraccion(@Valid @RequestBody InteraccionMergeDTO interaccion,
            BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList());
        }
        InteraccionDTO interaccionUpdate = interaccionService.updateInteraccion(interaccion, id);
        if (interaccionUpdate == null) {
            return ResponseEntity.badRequest().body("La interaccion no existe");
        }
        return ResponseEntity.ok(interaccionUpdate);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> eliminarInteraccion(@PathVariable Long id) {
        int resultado = interaccionService.deleteInteraccion(id);
        if (resultado == 0) {
            return ResponseEntity.badRequest().body("La interaccion no existe");
        }
        return ResponseEntity.ok("Interaccion eliminada correctamente");
    }

    @GetMapping("/cliente/{idCliente}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> buscarInteraccionesPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(interaccionService.findInteraccionesByClienteId(idCliente));
    }

    @GetMapping("/vendedor/{idVendedor}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> buscarInteraccionesPorVendedor(@PathVariable Long idVendedor) {
        return ResponseEntity.ok(interaccionService.findInteraccionesByVendedorId(idVendedor));
    }
}