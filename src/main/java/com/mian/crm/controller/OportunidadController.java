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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mian.crm.dto.OportunidadDTO;
import com.mian.crm.dto.OportunidadMergeDTO;
import com.mian.crm.service.OportunidadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadController {

    @Autowired
    private OportunidadService oportunidadService;

    @GetMapping("/listar")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> listarOportunidades() {
        return ResponseEntity.ok(oportunidadService.findAllOportunidades());
    }

    @GetMapping("/vendedor/{vendedorId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> listarOportunidadesPorVendedor(@PathVariable Long vendedorId) {
        return ResponseEntity.ok(oportunidadService.findOportunidadesByVendedorId(vendedorId));
    }

    @GetMapping("/cliente/{clienteId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> listarOportunidadesPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(oportunidadService.findOportunidadesByClienteId(clienteId));
    }

    @GetMapping("/etapa/{etapa}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> listarOportunidadesPorEtapa(@PathVariable String etapa) {
        return ResponseEntity.ok(oportunidadService.findOportunidadesByEtapa(etapa));
    }

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> crearOportunidad(@Valid OportunidadMergeDTO oportunidad, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }
        return ResponseEntity.ok(oportunidadService.saveOportunidad(oportunidad));
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> actualizarOportunidad(@Valid OportunidadMergeDTO oportunidad, @PathVariable Long id,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }
        if (oportunidadService.findOportunidadById(id) == null) {
            return ResponseEntity.badRequest().body("La oportunidad no existe");
        }
        return ResponseEntity.ok(oportunidadService.updateOportunidad(oportunidad, id));

    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> eliminarOportunidad(@PathVariable Long id) {
        int resultado = oportunidadService.deleteOportunidad(id);
        if (resultado == 0) {
            return ResponseEntity.badRequest().body("La oportunidad no existe");
        }
        return ResponseEntity.ok("Oportunidad eliminada con éxito");
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> buscarOportunidadPorId(@PathVariable Long id) {
        OportunidadDTO oportunidad = oportunidadService.findOportunidadById(id);
        if (oportunidad == null) {
            return ResponseEntity.badRequest().body("La oportunidad no existe");
        }
        return ResponseEntity.ok(oportunidad);
    }
}
