package com.mian.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mian.crm.dto.LoginDTO;
import com.mian.crm.security.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/public")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginDTO request, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }
        try {
            System.out.println("Authenticating user: " + request.getUsername());
            System.out.println("Password: " + request.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            if (authentication.isAuthenticated()) {
                System.out.println("User authenticated successfully: " + request.getUsername());
                UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
                String jwtToken = jwtService.generateToken(userDetails);
                return ResponseEntity.ok(Map.of("token", jwtToken));
            } else {
                System.out.println("Authentication failed for user: " + request.getUsername());
                return ResponseEntity.status(401).build(); // Unauthorized
            }
        } catch (Exception e) {
            System.out.println("Authentication error: " + e.getMessage());
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
        }
    }
}
