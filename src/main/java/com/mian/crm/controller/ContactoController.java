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

import com.mian.crm.dto.ContactoMergeDTO;
import com.mian.crm.service.ContactoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contactos")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @GetMapping("/listar")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> listarContactos() {
        return ResponseEntity.ok(contactoService.findAllContactos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> buscarContactoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contactoService.findContactoById(id));
    }

    @GetMapping("/cliente/{idCliente}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> buscarContactosPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(contactoService.findContactosByClienteId(idCliente));
    }

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> crearContacto(@Valid @RequestBody ContactoMergeDTO contacto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }
        return ResponseEntity.ok(contactoService.saveContacto(contacto));
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> editarContacto(@Valid @RequestBody ContactoMergeDTO contacto, BindingResult result,
            @PathVariable Long id) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }
        if (contactoService.findContactoById(id) == null) {
            return ResponseEntity.badRequest().body("El contacto no existe");
        }
        return ResponseEntity.ok(contactoService.updateContacto(contacto, id));
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<?> eliminarContacto(@PathVariable Long id) {
        int deleted = contactoService.deleteContacto(id);
        if (deleted == 0) {
            return ResponseEntity.badRequest().body("El contacto no existe");
        }
        return ResponseEntity.ok("Contacto eliminado correctamente");
    }
}