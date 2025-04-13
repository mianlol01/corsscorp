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

import com.mian.crm.dto.ClienteDTO;
import com.mian.crm.dto.ClienteMergeDTO;
import com.mian.crm.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> listarClientes() {
        return ResponseEntity.ok(clienteService.findAllClientes());
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.findClienteById(id);
        if (cliente == null) {
            return ResponseEntity.badRequest().body("El cliente no existe");
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteMergeDTO cliente, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }
        return ResponseEntity.ok(clienteService.saveCliente(cliente));
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> actualizarCliente(@Valid @RequestBody ClienteMergeDTO cliente, BindingResult result,
            @PathVariable Long id) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }
        if (clienteService.findClienteById(id) == null) {
            return ResponseEntity.badRequest().body("El cliente no existe");
        }
        return ResponseEntity.ok(clienteService.updateCliente(cliente, id));
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        int resultado = clienteService.deleteCliente(id);
        if (resultado == 0) {
            return ResponseEntity.badRequest().body("El cliente no existe");
        }
        return ResponseEntity.ok("Cliente eliminado con éxito");
    }

}
