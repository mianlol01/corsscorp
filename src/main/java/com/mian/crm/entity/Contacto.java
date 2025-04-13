package com.mian.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String cargo;
    private String email;
    private String telefono;

    @ManyToOne
    private Cliente cliente;

    // constructor sin id
    public Contacto(String nombre, String apellido, String cargo, String email, String telefono , Cliente cliente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.email = email;
        this.telefono = telefono;
        this.cliente = cliente;
    }
}
